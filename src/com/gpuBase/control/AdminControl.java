package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpuBase.model.FotoModel;
import com.gpuBase.model.FotoModelDS;
import com.gpuBase.model.OrdinazioneBean;
import com.gpuBase.model.OrdinazioneModelDS;
import com.gpuBase.model.VenditaBean;
import com.gpuBase.model.VenditaModel;
import com.gpuBase.model.VenditaModelDS;

@WebServlet("/admin")

public class AdminControl extends HttpServlet {

	
	
	private static final long serialVersionUID = 1L;

	static VenditaModel model = new VenditaModelDS();
	static FotoModel foto= new FotoModelDS();

	public AdminControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		if("admin".equals(request.getSession().getAttribute("loginType")))
		{

			String action = request.getParameter("action");
	
			try {
				if (action != null) {
					
					if(request.getParameter("idVendita")!=null)
					{
						int idVendita = Integer.parseInt(request.getParameter("idVendita"));
						
						if (action.equalsIgnoreCase("delete")) {
							model.doDelete(idVendita);
							foto.doDeleteByCustom(idVendita);
		
						} else if (action.equalsIgnoreCase("insert")) {
							String nomeAmministratore = request.getParameter("nomeAmministratore");
							double sconto = Double.parseDouble(request.getParameter("sconto"));
							double prezzo = Double.parseDouble(request.getParameter("prezzo"));
							double iva = Double.parseDouble(request.getParameter("iva"));
							int pezzi = Integer.parseInt(request.getParameter("pezzi"));
		
							VenditaBean bean = new VenditaBean();
		
							bean.setIdVendita(idVendita);
							bean.setNomeAmministratore(nomeAmministratore);
							bean.setSconto(sconto);
							bean.setPrezzo(prezzo);
							bean.setIva(iva);
							bean.setPezzi(pezzi);
		
							model.doSave(bean);
							request.setAttribute("products",null);
							
							
							}else if (action.equalsIgnoreCase("update")) {
							String nomeAmministratore = request.getParameter("nomeAmministratore");
							double sconto = Double.parseDouble(request.getParameter("sconto"));
							double prezzo = Double.parseDouble(request.getParameter("prezzo"));
							double iva = Double.parseDouble(request.getParameter("iva"));
							int pezzi = Integer.parseInt(request.getParameter("pezzi"));
			
							VenditaBean bean = new VenditaBean();
			
							bean.setIdVendita(idVendita);
							bean.setNomeAmministratore(nomeAmministratore);
							bean.setSconto(sconto);
							bean.setPrezzo(prezzo);
							bean.setIva(iva);
							bean.setPezzi(pezzi);
			
							model.doDelete(idVendita);
							model.doSave(bean);
							request.setAttribute("products",null);
						}
					}
					
					
					
					if ("true".equals(request.getSession().getAttribute("toReloadOrders"))
							|| "getOrderByDatesOnUser".contentEquals(action))
					{
						//System.out.println("getOrderByDatesOnUser");
						
						request.getSession().setAttribute("toReloadOrders",null);
						OrdinazioneModelDS ordinazione=new OrdinazioneModelDS();
						String mailCliente=request.getParameter("mailCliente");
						String day1=request.getParameter("day1");
						String day2=request.getParameter("day2");
						String month1=request.getParameter("month1");
						String month2=request.getParameter("month2");
						String year1=request.getParameter("year1");
						String year2=request.getParameter("year2");
						
						String dataInizio = year1+"-"+month1+"-"+day1;
						String dataFine= year2+"-"+month2+"-"+day2;
						
						//System.out.println(mailCliente);
						//System.out.println(dataInizio);
						//System.out.println(dataFine);
						
						Collection<OrdinazioneBean> orders=ordinazione.doRetrieveAllByUserBetweenDates(mailCliente, dataInizio, dataFine);
						request.getSession().removeAttribute("orders");
						request.getSession().setAttribute("orders",orders);
						
						//if(request.getSession().getAttribute("orders")!=null) {System.out.println("getOrderByDatesOnUser done");}
					}
					
					else if("resetOrders".contentEquals(action))request.getSession().setAttribute("orders", null);
					
					
				}
			} catch (SQLException e) {
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			
		
			String sort = request.getParameter("sort");
	
			try {
				request.removeAttribute("products");
				request.setAttribute("products", model.doRetrieveAll(sort));
			} catch (SQLException e) {
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}

		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
