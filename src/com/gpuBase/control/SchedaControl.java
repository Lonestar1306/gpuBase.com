package com.gpuBase.control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.gpuBase.model.SchedaBean;
import com.gpuBase.model.SchedaModel;
import com.gpuBase.model.SchedaModelDS;

@WebServlet("/scheda")

/*
 * per funzionare ha bisogno di "Vendita" : possono essere gestiti dal carrello
 * solo i prodotto che sono in vendita.
 */

public class SchedaControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static SchedaModel model = new SchedaModelDS();

	public SchedaControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//System.out.println("SchedaControl"); 
		

		String action = request.getParameter("formAction");
		String idScheda=request.getParameter("idSchedaDetails");
		
		
		
		//System.out.println(action);
		//System.out.println(idScheda);

		try {
			if (action != null && idScheda !=null) {
				
				
				//mostra dettagli scheda in base ad una custom
				if(action.contentEquals("showSchedaDetalis")) {
					
					if (request.getSession().getAttribute("customDetails") == null) {
						//System.out.println("scheda control: dettagli custom non presenti");
					}
					else
						request.getSession().setAttribute("schedaDetails",model.doRetrieveByKey(idScheda));
					
				}
				
				//salva una nuova scheda nel db
				else if (action.contentEquals("addScheda")){
					String produttore=request.getParameter("produttore");
					Date dataUscita=Date.valueOf(request.getParameter("dataUscita"));
					String nomeCore=request.getParameter("nomeCore");
					int numeroCore=Integer.parseInt(request.getParameter("numeroCore"));
					int TDP=Integer.parseInt(request.getParameter("TDP"));
					int frequenzaCore=Integer.parseInt(request.getParameter("frequenzaCore"));
					int frequenzaMemoria=Integer.parseInt(request.getParameter("frequenzaMemoria"));
					String generazioneMemoria=request.getParameter("generazioneMemoria");
					double dimensioneMemoria=Double.parseDouble(request.getParameter("dimensioneMemoria"));
				
					SchedaBean bean=new SchedaBean(idScheda, produttore, dataUscita, nomeCore, numeroCore, TDP, frequenzaCore, frequenzaMemoria, generazioneMemoria, dimensioneMemoria);
					if(bean!=null)model.doSave(bean);
					//else System.out.println("scheda non valida");
				}
				
			}
		} catch (SQLException e) {
			response.setStatus(409);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}

		String fromSource=request.getParameter("fromSource");
		if(fromSource==null) {fromSource="/SchedaView.jsp";}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(fromSource);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
