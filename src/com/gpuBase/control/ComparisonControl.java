package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.gpuBase.model.VenditaCustomSchedaBean;
import com.gpuBase.model.VenditaCustomSchedaModelDS;
import com.gpuBase.model.MyCollection;



@WebServlet("/comparison")

public class ComparisonControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public  ComparisonControl() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String from=request.getParameter("from");
		String formAction=request.getParameter("formAction");
		String id=request.getParameter("idVendita");
		int idVendita=Integer.parseInt(id);
		
		
		//System.out.println("ComparisonControl");
		
		
		MyCollection toCompare = (MyCollection)request.getSession().getAttribute("toCompare");
		if (toCompare == null) {toCompare=new MyCollection();}
		
		VenditaCustomSchedaBean el=new VenditaCustomSchedaBean();
		VenditaCustomSchedaModelDS elDS=new VenditaCustomSchedaModelDS();
		
		
	
		if("addToComparison".equals(formAction) && toCompare.getSize()<toCompare.getMaxSize()) { 
			
			
			//System.out.println("addToComparison");
			from = "/ProductView.jsp";

			try {
				el=elDS.doRetrieveByKey(idVendita);
				toCompare.addProduct(el);
			} 
			catch (SQLException e) {
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			request.getSession().removeAttribute("toCompare");
			request.getSession().setAttribute("toCompare",toCompare);
			
			
		}
		
		else if("removeComparison".equals(formAction) && toCompare.getSize()>0) {
			//System.out.println("removeComparison");
			from = "/ProductView.jsp";
				try {
					el=elDS.doRetrieveByKey(idVendita);
					toCompare.deleteProduct(el);
					
				} catch (SQLException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
				}
				
			request.getSession().removeAttribute("toCompare");
			request.getSession().setAttribute("toCompare",toCompare);
			
		}
		
		
		if(from==null) {from = "/ComparisonView.jsp";}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(from);
		dispatcher.forward(request, response);
	}
}
