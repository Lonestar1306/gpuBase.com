package com.gpuBase.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpuBase.model.CustomBean;
import com.gpuBase.model.CustomModel;
import com.gpuBase.model.CustomModelDS;

@WebServlet("/custom")

/*
 * per funzionare ha bisogno di "Vendita" : possono essere gestiti dal carrello
 * solo i prodotto che sono in vendita.
 */

public class CustomControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static CustomModel model = new CustomModelDS();

	public CustomControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		//System.out.println("CustomControl");
		
		String action = request.getParameter("formAction");
		String idCustomDetails=request.getParameter("idCustomDetails");
		
		//System.out.println(action);
		//System.out.println(idCustomDetails);

		try {
			if (action != null && idCustomDetails !=null) {
				int idCustom = Integer.parseInt(idCustomDetails);
				
				if(action.contentEquals("showCustomDetalis")) {
					
					request.getSession().setAttribute("customDetails",model.doRetrieveByKey(idCustom));
					
				}
				else if(action.equals("addCustom")) {

					
					String idScheda=request.getParameter("idScheda");
					double profondita=Double.parseDouble(request.getParameter("profondita"));
					double larghezza=Double.parseDouble(request.getParameter("larghezza"));
					double lunghezza=Double.parseDouble(request.getParameter("lunghezza"));
					double peso=Double.parseDouble(request.getParameter("peso"));
					String nomeCustom=request.getParameter("nomeCustom");
					String azienda=request.getParameter("azienda");
					String risoluzioneMax=request.getParameter("risoluzioneMax");
					int porteDP=Integer.parseInt(request.getParameter("porteDP"));
					int porteVGA=Integer.parseInt(request.getParameter("porteVGA"));
					int porteDVI=Integer.parseInt(request.getParameter("porteDVI"));
					int porteHDMI=Integer.parseInt(request.getParameter("porteHDMI"));
					
					
					
					CustomBean bean= new CustomBean(idCustom, idScheda, profondita, larghezza, lunghezza, peso, nomeCustom, azienda, risoluzioneMax, porteDP, porteVGA, porteDVI, porteHDMI);
					//System.out.println(bean);
					if(bean!=null)model.doSave(bean);
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String fromSource=request.getParameter("fromSource");
		if(fromSource==null) {fromSource="/CustomView.jsp";}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fromSource);
		dispatcher.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
