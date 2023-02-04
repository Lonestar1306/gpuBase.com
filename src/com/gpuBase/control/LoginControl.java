package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpuBase.model.AmministratoreBean;
import com.gpuBase.model.AmministratoreModelDS;
import com.gpuBase.model.ClienteBean;
import com.gpuBase.model.ClienteModelDS;

@WebServlet("/login")

public class LoginControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String from = "/ProductView.jsp";
		request.getSession().setAttribute("loginResult", "loginNotDone");
		
		String action=request.getParameter("action");
		//System.out.println(action);
		
			
		if("logout".equals(action))
			{
					request.getSession().setAttribute("username", null);
					request.getSession().setAttribute("password", null);
					request.getSession().setAttribute("loginType", null);
					request.getSession().invalidate();
					request.getSession().setAttribute("loginResult", "logoutDone");
			}
		else if("login".equals(action))
		{

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String loginType = request.getParameter("loginType");
			
			if(username!=null && password !=null && loginType!=null)
			{
				
				try {
		
					if (loginType.equals("user")) {
		
						from = "/ProductView.jsp";
		
						ClienteModelDS clienteDS = new ClienteModelDS();
		
						ClienteBean cliente = clienteDS.doRetrieveByKey(username);
						if (username.equals(cliente.getMailCliente())&& password.equals(cliente.getPassword())) {
							request.getSession().setAttribute("username", username);
							request.getSession().setAttribute("loginType", loginType);
							
							request.getSession().setAttribute("loginResult", "loginDone");
							
						}
		
						else {
							//System.out.println("errore login 1");
							from = "/Login.jsp";
						}
		
						
					}
		
					else if (loginType.equals("admin")) {
						from = "/AdminView.jsp";
		
						AmministratoreModelDS ammDS = new AmministratoreModelDS();
		
						AmministratoreBean amm = ammDS.doRetrieveByKey(username);
						if (username.equals(amm.getNomeAmministratore()) && password.equals(amm.getPassword())) {
							request.getSession().setAttribute("username", username);
							request.getSession().setAttribute("loginType", loginType);
		
							request.getSession().setAttribute("loginResult", "loginDone");
						}
		
						else {
							//System.out.println("errore login 2");
							from = "/Login.jsp";
						}
		
						
		
					}
		
				} catch (SQLException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
		
				}
			}
		
		}
		
		else if("register".equals(action)) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			
			ClienteBean cliente=new ClienteBean(username,password,address,tel);
			ClienteModelDS clienteDS = new ClienteModelDS();
			try {
				clienteDS.doSave(cliente);
			} catch (SQLException e) {
				
				response.setStatus(409);
				request.getSession().setAttribute("errorMsg", e.getMessage());
				from="/ErrorPage.jsp";
				
			}
			
			
			
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(from);
		dispatcher.forward(request, response);

	}
}
