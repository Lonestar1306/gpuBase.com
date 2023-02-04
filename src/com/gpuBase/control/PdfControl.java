package com.gpuBase.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import com.gpuBase.model.OrdinazioneBean;
import com.gpuBase.model.OrdinazioneModelDS;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/pdf")

public class PdfControl extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;

	public PdfControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		throw new ServletException("to perform an order you must use post");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		
		//System.out.println("PdfControlStart");
			
		String formAction=(String)request.getParameter("formAction");
		Collection<?> orders = (Collection<?>) request.getSession().getAttribute("orders");
		
		String path=(String)System.getProperty("user.home");
		path+="\\Downloads\\";
		path+=request.getParameter("path");
		//System.out.println(request.getParameter("path"));
		path+="_"+System.currentTimeMillis();
		//scaricherà di default nella cartella Download dell'utente 
		
		//System.out.println(path);
		
		if("user".equals(request.getSession().getAttribute("loginType")) && orders!=null && path!=null)
		{
			
			if("getPdfAllOrder".equals(formAction)||"getPdfOrder".equals(formAction))
			{
			
				//System.out.println("starting creating pdf");
				
				try {
					
					/*********creazione file*****/
					
					String fileName=path+".pdf";
					Document document = new Document();
					PdfWriter.getInstance(document, new FileOutputStream(fileName));
					document.open();
					
					//System.out.println("doc ok");
					/*********tabella cliente*********/
					
					String username=(String)request.getSession().getAttribute("username");
					String address=(String)request.getSession().getAttribute("address");
					
					PdfPTable clientTable=new PdfPTable(2);
					clientTable.setWidthPercentage(75);
					
					PdfPCell mail=new PdfPCell(new Phrase("mailCliente"));
					PdfPCell indirizzo=new PdfPCell(new Phrase("indirizzo"));
					clientTable.addCell(mail);
					clientTable.addCell(indirizzo);
					clientTable.setHeaderRows(1);
					
					//riga 1 cliente
					clientTable.addCell(username);
					clientTable.addCell(address);
					document.add(clientTable);
					
					//System.out.println("cliente ok");
					/*********paragrafi***********/
					
					Paragraph p=new Paragraph();
					p.add(" ");//per andare a capo
					document.add(p);
					
					/********tabella ordini********/
					
					PdfPTable orderTable =new PdfPTable(7);
					orderTable.setWidthPercentage(100);
					
					//header(nome colonne)
					PdfPCell idOrdine=new PdfPCell(new Phrase("idOrdine"));
					PdfPCell idCustom=new PdfPCell(new Phrase("idCustom"));
					PdfPCell pezzi=new PdfPCell(new Phrase("pezzi"));
					PdfPCell iva=new PdfPCell(new Phrase("iva"));
					PdfPCell prezzo=new PdfPCell(new Phrase("prezzo"));
					PdfPCell data=new PdfPCell(new Phrase("data"));
					PdfPCell totale=new PdfPCell(new Phrase("totale"));
					orderTable.addCell(idOrdine);
					orderTable.addCell(idCustom);
					orderTable.addCell(pezzi);
					orderTable.addCell(iva);
					orderTable.addCell(prezzo);
					orderTable.addCell(data);
					orderTable.addCell(totale);
					orderTable.setHeaderRows(1);
					
	
					//riempimento interno
					//in AUTOMATICO, inserisce riempendo una riga per volta
					OrdinazioneBean buf;
					
					if("getPdfAllOrder".equals(formAction))//ordini multipli
					{
						
						Iterator<?> it = orders.iterator();
						while (it.hasNext()) 
						{
							buf=(OrdinazioneBean)it.next();
							
							//riga n
							orderTable.addCell(""+buf.getIdOrdine());
							orderTable.addCell(""+buf.getIdCustom());
							orderTable.addCell(""+buf.getPezzi());
							orderTable.addCell(""+buf.getIva());
							orderTable.addCell(""+buf.getPrezzo());
							orderTable.addCell(""+buf.getData());
							orderTable.addCell(""+buf.getTotale());
						}
					}
					else if("getPdfOrder".equals(formAction))//singolo ordine
					{
						OrdinazioneModelDS ordinazioneDS=new OrdinazioneModelDS();
						int idOrdinazione=Integer.parseInt(request.getParameter("idOrdine"));
						buf=ordinazioneDS.doRetrieveByKey(idOrdinazione);
						
						//riga n
						orderTable.addCell(""+buf.getIdOrdine());
						orderTable.addCell(""+buf.getIdCustom());
						orderTable.addCell(""+buf.getPezzi());
						orderTable.addCell(""+buf.getIva());
						orderTable.addCell(""+buf.getPrezzo());
						orderTable.addCell(""+buf.getData());
						orderTable.addCell(""+buf.getTotale());
					}
					
					
					document.add(orderTable);
				
					
					/**********immagini*********/
					
					//document.addImage.getIstance("path");
					
					/**************************/
					
					//System.out.println("all done");
					document.close();
					
					//System.out.println("pdf closed");
					
				} catch (FileNotFoundException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
					
				} catch (DocumentException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
				
				} catch (SQLException e) {
					response.setStatus(409);
					request.getSession().setAttribute("errorMsg", e.getMessage());
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
					dispatcher.forward(request, response);
				}
			
			}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserView.jsp");
		dispatcher.forward(request,response);
		
	}


}
