package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/product")

/*
 * per funzionare ha bisogno di "Vendita" : possono essere gestiti dal carrello
 * solo i prodotto che sono in vendita.
 */

public class ProductControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static VenditaModel model = new VenditaModelDS();

	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Carrello cart = (Carrello) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("cart", cart);
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				int idVendita = Integer.parseInt(request.getParameter("idVendita"));
				VenditaBean buf = model.doRetrieveByKeyDetailed(idVendita);

				if (action.equalsIgnoreCase("addC")) {

					if (cart.getProduct(idVendita) == null) {// nuovo prodotto
						cart.addProduct(buf);
						cart.updatePz(buf, 1);
					} else { // prodotto già presente nel carrello
						cart.updatePz(buf, cart.getProduct(idVendita).getPezzi() + 1);
					}

				} else if (action.equalsIgnoreCase("updatePzC")) {
					/* aggiorna pezzi di un item nel carrello */
					int pezzi = Integer.parseInt(request.getParameter("pezzi"));
					cart.updatePz(model.doRetrieveByKeyDetailed(idVendita), pezzi);

				} else if (action.equalsIgnoreCase("deleteC")) {
					cart.deleteProduct(model.doRetrieveByKeyDetailed(idVendita));

				

				} else if (action.equalsIgnoreCase("delete")) {
					/* se il prodotto non è più in vendita, lo elimino anche dal carrello */
					cart.deleteProduct(model.doRetrieveByKeyDetailed(idVendita));
					model.doDelete(idVendita);
					request.setAttribute("products",null);
				}
				
				
			}
		} catch (SQLException e) {
			response.setStatus(409);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		String sort = request.getParameter("sort");

		try {
			
				request.removeAttribute("products");
				request.setAttribute("products", model.doRetrieveAllDetailed(sort));
			
		} catch (SQLException e) {
			response.setStatus(409);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
