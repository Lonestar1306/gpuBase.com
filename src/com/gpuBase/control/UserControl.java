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

import  com.gpuBase.model.ClienteModelDS;
import  com.gpuBase.model.OrdinazioneBean;
import  com.gpuBase.model.OrdinazioneModelDS;


@WebServlet("/user")

public class UserControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public UserControl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//System.out.println("UserControl");
		
		String from=request.getParameter("from");
		String formAction=(String)request.getParameter("formAction");
		if("getOrderDetails".equals(formAction))
		{
			int idOrdine=Integer.parseInt((String)request.getParameter("idOrdine"));
			OrdinazioneModelDS ordinazioni=new OrdinazioneModelDS();
			
			try 
			{
				request.getSession().setAttribute("detailedOrder",ordinazioni.doRetrieveByKey(idOrdine));
				//System.out.println(request.getSession().getAttribute("detailedOrder"));
				request.getSession().setAttribute("toReloadOrders","true");
			} 
			catch (SQLException e) 
			{
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			
			
		}
		
		else if("getOrderByDates".equals(formAction))
		{
			
		
			OrdinazioneModelDS ordinazioni=new OrdinazioneModelDS();
			String day1=request.getParameter("day1");
			String day2=request.getParameter("day2");
			String month1=request.getParameter("month1");
			String month2=request.getParameter("month2");
			String year1=request.getParameter("year1");
			String year2=request.getParameter("year2");
			
			
			String dataInizio = year1+"-"+month1+"-"+day1;
			String dataFine= year2+"-"+month2+"-"+day2;
			
		
			
			String mailCliente;
			if(from!=null && from.contentEquals("/AdminView.jsp")) {
				mailCliente=request.getParameter("mailCliente");
			}
			
			else{
				mailCliente=(String)request.getSession().getAttribute("username");
				}
			
			try 
			{
				request.getSession().setAttribute("orders",ordinazioni.doRetrieveAllByUserBetweenDates(mailCliente, dataInizio, dataFine));
			} 
			catch (SQLException e) 
			{
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		
		
		String action = (String) request.getSession().getAttribute("action");

		try {
			if (action != null) {
				
				
				if(action.contentEquals("getUserAddress")) {
				
					ClienteModelDS clienteDS=new ClienteModelDS();
					String address=clienteDS.doRetrieveByKey((String)request.getSession().getAttribute("username")).getIndirizzo();
					request.getSession().removeAttribute("address");
					request.getSession().setAttribute("address",address);
					
				}
				
				else if(action.contentEquals("getOrders") && !("getOrderByDates".equals(formAction))) {
					
					String username=(String)request.getSession().getAttribute("username");
					OrdinazioneModelDS ordinazioni=new OrdinazioneModelDS();
					Collection<OrdinazioneBean> orders=ordinazioni.doRetrieveAllByUser(username);
					request.getSession().removeAttribute("orders");
					request.getSession().setAttribute("orders",orders);
					
					
				}
				

				
				
			}
		} catch (SQLException e) {
			response.setStatus(409);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}
		
		//System.out.println("control dispacher\n");
		if(from==null) {from="/UserView.jsp";}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(from);
		dispatcher.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


