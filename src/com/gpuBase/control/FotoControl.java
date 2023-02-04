package com.gpuBase.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpuBase.model.FotoBean;
import com.gpuBase.model.FotoModel;
import com.gpuBase.model.FotoModelDS;

@WebServlet("/foto")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, 
		maxFileSize = 1024 * 1024 * 50, 
		maxRequestSize = 1024 * 1024 * 100)

public class FotoControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static FotoModel model = new FotoModelDS();

	public FotoControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String from = request.getParameter("from");
		//System.out.println("photo control called from "+from);
		String action = request.getParameter("action");
		//System.out.println("the action requested is "+action);
		
		int idCustom = Integer.parseInt(request.getParameter("idFoto"));

		//System.out.println("the idCustom is "+idCustom+"\n\n");
		
		try {
			if (action != null) {
				
				
				
				// Collection<FotoBean> buf = model.doRetrieveByCustom(id);//tutte le foto di
				// una custom
				// servirà dopo quando faremo le altre pagine
				
				

				if (action.equals("getMainProductFoto")) { // legge una sola foto di un acustom
					
					//System.out.println("getMainProductFoto");

					byte[] bt = model.doRetrieveOneByCustom(idCustom).getFile();
					

 					ServletOutputStream out = response.getOutputStream();
					if (bt != null) {
						out.write(bt);
						response.setContentType("image/jpeg");
					}
					out.close();
					
					
					

				} else if (action.equalsIgnoreCase("setProductFoto")) { 
					
					//System.out.println("setProductFoto");
					
					// inserisce un nuova foto nel DB

					//String nomeFoto = request.getParameter("nomeFoto");
					// nome (univoco) con cui si vuole salvare la  foto nel DB
					String cartellaFoto = request.getParameter("cartellaFoto");
					// path intera sul disco locale da cui leggere la foto
					//System.out.println(cartellaFoto);
					
					
					FileInputStream fis=null;
					try {
						fis = new FileInputStream(new File(cartellaFoto));
					}catch(FileNotFoundException e) {
						//System.out.println(("file non presente nella path specificata"));
						response.setStatus(409);
						request.getSession().setAttribute("errorMsg", "file non presente nella path specificata");
						from="ErrorPage";
						} 
					
					if(fis!=null)
					{
					
					byte[] file = fis.readAllBytes();

					FotoBean bean = new FotoBean();

					bean.setIdCustom(idCustom);
					
					//bean.setPath(nomeFoto);// univoco nel DB
					bean.setPath("_"+System.currentTimeMillis());
					
					bean.setFile(file);

					model.doSave(bean);

					fis.close();
					}
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+from+".jsp");
					dispatcher.forward(request, response);
				
				}
				
				else if(action.equalsIgnoreCase("getCountFoto")){
					
					//System.out.println("getCountFoto");
					
					request.setAttribute("contFoto", model.getCountFoto(idCustom));
				}
				
				else if(action.equalsIgnoreCase("getOnePhoto")) {
					
					//System.out.println("getOnePhoto");
					
					String flag=request.getParameter("flagFoto");
					if(flag!=null)
					{
						int flagFoto=Integer.parseInt(flag);
						FotoBean buf= model.doRetrieveOneByCustomFlag(idCustom,flagFoto);
						
						if(buf!=null) {
						byte[] bt =buf.getFile();
	 					ServletOutputStream out = response.getOutputStream();
						if (bt != null) {
							out.write(bt);
							response.setContentType("image/jpeg");
						}
						out.close();
						}
						else {
							RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+from+".jsp");
							dispatcher.forward(request, response);
						}
					}
					
				}
				
				
			}
		} catch (SQLException e) {
			response.setStatus(409);
			request.getSession().setAttribute("errorMsg", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
			dispatcher.forward(request, response);
		}
		

		
	}
		
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
		
	}
}
