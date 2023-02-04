package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.gpuBase.model.ClienteBean;
import com.gpuBase.model.ClienteModelDS;

@WebServlet("/ServletResponseJSON")
public class ServletResponseJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletResponseJSON() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		
		
		
		System.out.println("ServletResponseJSON");
		
		String formAction=request.getParameter("formAction");
		//System.out.println(formAction);
		
		
		if("checkUniqueMail".equals(formAction) || "checkAdminMail".equals(formAction))
		{
			String mailCliente=request.getParameter("mailCliente");
			//System.out.println(mailCliente);
			
			ClienteBean cliente=new ClienteBean();
			ClienteModelDS clienteDS=new ClienteModelDS();
			JSONObject json = new JSONObject();
			try {
				cliente=clienteDS.doRetrieveByKey(mailCliente);
				json.append("mailCliente",cliente.getMailCliente());
			} catch (SQLException e) {
				e.printStackTrace();response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			} catch (JSONException e) {
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
				dispatcher.forward(request, response);
			}
			
			
			//System.out.println("fromDB:"+cliente.getMailCliente());
			
			if("checkUniqueMail".equals(formAction)) {
					if(mailCliente.equals(cliente.getMailCliente()))
						{
							//System.out.println("mailError");
							
							try {json.append("mailStatus","mailError");} 
							catch (JSONException e) {
								response.setStatus(409);
								request.getSession().setAttribute("errorMsg", e.getMessage());
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
								dispatcher.forward(request, response);
								
							}
							
						}
					else
						{
							//System.out.println("mailOK");
							
							try {json.append("mailStatus","mailOk");} 
							catch (JSONException e) {
								response.setStatus(409);
								request.getSession().setAttribute("errorMsg", e.getMessage());
								RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
								dispatcher.forward(request, response);
								
							}
							
							
						}
			}
			else if("checkAdminMail".equals(formAction)) {
				if(mailCliente.equals(cliente.getMailCliente()))
					{
						//System.out.println("mailOk");
						
						try {json.append("mailStatus","mailOK");} 
						catch (JSONException e) {
							response.setStatus(409);
							request.getSession().setAttribute("errorMsg", e.getMessage());
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
							dispatcher.forward(request, response);
							}
						
					}
				else
					{
						//System.out.println("mailError");
						
						try {json.append("mailStatus","mailError");} 
						catch (JSONException e) {
							response.setStatus(409);
							request.getSession().setAttribute("errorMsg", e.getMessage());
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
							dispatcher.forward(request, response);
							}
						
						
					}
		}
			System.out.println(json.toString());
			response.getWriter().print(json.toString());
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
