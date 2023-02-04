package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpuBase.model.Carrello;
import com.gpuBase.model.VenditaBean;
import com.gpuBase.model.VenditaModel;
import com.gpuBase.model.VenditaModelDS;
import com.gpuBase.model.OrdinazioneBean;
import com.gpuBase.model.OrdinazioneModel;
import com.gpuBase.model.OrdinazioneModelDS;
import com.gpuBase.model.ClienteModel;
import com.gpuBase.model.ClienteModelDS;

@WebServlet("/order")

public class OrderControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static VenditaModel vendita = new VenditaModelDS();
	static ClienteModel cliente = new ClienteModelDS();
	static OrdinazioneModel ordinazione = new OrdinazioneModelDS();

	public OrderControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		throw new ServletException("to perform an order you must use post");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		/*Controllo autenticazione utente con Sessioni*/
		
		if("user".equals(request.getSession().getAttribute("loginType")))
		{
	
			Carrello cart = (Carrello) request.getSession().getAttribute("cart");
			if (cart == null) {
				cart = new Carrello();
				request.getSession().setAttribute("cart", cart);
			}
	
			
			String action =request.getParameter("action");
			String mailCliente=(String) request.getSession().getAttribute("username");
	
				try {
					if (action != null && action.equals("performOrder")) {
						
						request.getSession().setAttribute("orderResult", "done");
						
							/* prima di procedere, controllare che ci siano abbastanza pezzi disponibili */
										
							boolean checked=true;
								for (VenditaBean beancart : cart.getProducts()) {
									if(beancart.getPezzi()>vendita.doRetrieveByKey(beancart.getIdVendita()).getPezzi()) {
										//System.out.println("non abbiamo abbastanza pezzi di "+beancart.getIdVendita());
										checked=false;
										request.getSession().setAttribute("orderResult", "notDone");
									}
								}
							
									
							/*WORK IN PROGRESS*/
								/*AD OGNI ORDINAZIONE DOVREMO AGGIORNARE IL NUMERO DI
								 *  PEZZI DI CIASCUN PRODOTTO NEL DATABASE  (DA FARE)*/
								
							if(checked)
							{
								
							OrdinazioneBean ord;
							String idCliente = mailCliente;
							int idCustom;
							String data;
							double prezzo;
							double iva;
							int pezzi;
	
							// per ogni prodotto presente nel carrello
							List<VenditaBean> prodcart = cart.getProducts();
							for (VenditaBean beancart : prodcart) {
	
								idCustom = beancart.getIdVendita();
								iva = beancart.getIva();
								pezzi = vendita.doRetrieveByKey(idCustom).getPezzi()-beancart.getPezzi();
								//sottrae i pezzi dai prodotti in vendita
								prezzo = beancart.getPrezzo();
								prezzo = prezzo - (prezzo / 100 * beancart.getSconto());
								data=java.time.LocalDate.now().toString();
								ord = new OrdinazioneBean(0, idCliente, idCustom, data ,
										prezzo, iva, beancart.getPezzi());
								
								if(vendita.doUpdatePz(idCustom, pezzi))
								{
									ordinazione.doSave(ord);
									request.getSession().setAttribute("toReloadOrders", "true");
								}else
								{
									request.getSession().setAttribute("orderResult", "notDone");
									throw new SQLException("not enough pieces");
								}
								
	
								//System.out.println("saved one");
	
							}
	
							cart = new Carrello();
							request.getSession().setAttribute("cart", cart);// svuota carrello
	
							//System.out.println("reset cart");
							//System.out.println("order Done");
							
						
						
							
							}
							//else
							//{System.out.println("order NOT done");}
						}
					
				} catch (SQLException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
				}
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
			dispatcher.forward(request,response);
	}
}
