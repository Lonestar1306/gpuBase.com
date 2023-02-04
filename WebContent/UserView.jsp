<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
<script src="script/FormScript.js" type="text/javascript"></script>
<title>Area utente</title>
</head>
<body>

	<%@ page
	import="java.util.*,
				com.gpuBase.model.OrdinazioneBean, 
				com.gpuBase.model.ClienteBean"%>

	<%
	if ("user".equals(request.getSession().getAttribute("loginType"))) 
	{
	%>
	
	
	
	<%
	
		//System.out.println("UserView");
		
		String username=(String)request.getSession().getAttribute("username");
		String address=(String)request.getSession().getAttribute("address");
		Collection<?> orders = (Collection<?>) request.getSession().getAttribute("orders");
	
		
		
			
			if(address==null)
			{
				request.getSession().setAttribute("action", "getUserAddress");
				response.sendRedirect("./user");
				return;
				
			}
			//System.out.println("user done");
		
			if (orders == null || "true".equals(request.getSession().getAttribute("toReloadOrders"))) {
				
				request.getSession().setAttribute("toReloadOrders",null);
				request.getSession().setAttribute("action", "getOrders");
				response.sendRedirect("./user");
				return;
			}
			//System.out.println("orders done");
			
			
	%>
		
		<%@ include file="Header.jsp"%>
		<br>
		<div>
		<h3>
			Indirizzo di Spedizione:<%=address%><br>
			
		</h3>
		</div>
		<br>
		
		
		
		
		
		<%
		if (orders != null && !orders.isEmpty())
		{
		%>
		
		
		<div>	ORDINI EFFETTUATI	<hr>
		<br>
			<div class="showTableDiv" onmouseover=checkData()> 	FILTRO DATA	<br>
			<form class="numberFilter" id="dataFilterForm" action="user" method="get" >
				<input type="hidden" id="formAction" name="formAction" value="getOrderByDates">
				<input class="dataFilterInput" type="number" id="day1" value="" name="day1" min="1" max="31"  placeholder="giorno inizio" required placeholder="day1">
				<input class="dataFilterInput" type="number" id="month1"  value="" name="month1"  min="1" max="12" placeholder="mese inizio" required placeholder="month1">
				<input class="dataFilterInput" type="number" id="year1"  value="" name="year1" placeholder="anno inizio" required placeholder="yead1">
				<br><br>
				<input class="dataFilterInput" type="number" id="day2"  value="" name="day2" min="1" max="31" placeholder="giorno fine" required placeholder="day2">
				<input class="dataFilterInput" type="number" id="month2" value="" name="month2"  min="1" max="12" placeholder="mese fine" required placeholder="month2">
				<input class="dataFilterInput" type="number" id="year2"  value="" name="year2" placeholder="anno fine" required placeholder="year2" >
				<br>
				<br>
				<input class="showTableButton" type="submit" id="dataFilterSubmitButton" name="dataFiter" value="RICERCA">
				<span class="formSpan" id="dataFilterSpanError"></span>
			</form>
			</div>
			
		<br><br>
		
		<hr>	
		PDFs will be saved in Download folder automatically
			
		<div class="showTableDiv">
		<table>
			<tr>
				<th>n.ordine</th>
				<th>data</th>
				<th>totale</th>
				<th>dettagli</th>
				<th>scarica pdf</th>
			</tr>
			
				<%
				OrdinazioneBean buf;
				Iterator<?> it = orders.iterator();
				while (it.hasNext()) 
				{
					buf=(OrdinazioneBean)it.next();
				%>
					<tr>
						<td>
							<%=buf.getIdOrdine() %>
						</td>
						<td>
							<%=buf.getData() %>
						</td>
						<td>
							<%=buf.getTotale()%>
						</td>
						<td>
							<form action="user" method="get">
								<input type="hidden" name="formAction" value="getOrderDetails">
								<input type="hidden" name="idOrdine" value="<%=buf.getIdOrdine() %>">
								<input class="showTableButton" type="submit" name="details" value="DETTAGLI"> 
							</form>
						</td>
						<td>
							<form action="pdf" method="post">
								<input type="hidden" name="formAction" value="getPdfOrder">
								<input type="hidden" name="idOrdine" value="<%=buf.getIdOrdine() %>">
								<input class="userInput" type="text" name="path" placeholder="nomeFile1(no ext.)" required placeholder="nomeFile1(no ext.)"> 
								<input class="showTableButton" type="submit" name="pdf" value="PDF"> 
							</form>
						</td>
					</tr>
				<%
				}
				%>
				
		
			
		</table>
		
			
		<br><br>
		
			
			<form action="pdf" method="post">
				<input type="hidden" name="formAction" value="getPdfAllOrder">
				To get all the orders showed here in a pdf, please insert a file name without file extension
				<br>
				<input class="userInput" type="text" name="path" placeholder="nomeFile2(senza estensione)" required placeholder="nomeFile2(senza estensione)"> 
				<input class="showTableButton" type="submit" name="pdf" value="PDF"> 
			</form>
			
			</div>
		</div>
		<%
		}
		
		
		
		
		if(request.getSession().getAttribute("detailedOrder")!=null)
		{
			OrdinazioneBean detailedOrder=(OrdinazioneBean)request.getSession().getAttribute("detailedOrder");
		%>
			<br>
			<div class="showTableDiv">
				<table>
					<tr>
						<th>n.ordine</th>
						<th>mailCliente</th>
						<th>idProdotto</th>
						<th>data</th>
						<th>prezzo</th>
						<th>iva</th>
						<th>pezzi</th>
						<th>totale</th>
					</tr>
					
					<tr>
						<td>
							<%=detailedOrder.getIdOrdine() %>
						</td>
						<td>
							<%=detailedOrder.getMailCliente()%>
						</td>
						<td>
							<%=detailedOrder.getIdCustom()%>
						</td>
						<td>
							<%=detailedOrder.getData() %>
						</td>
						<td>
							<%=detailedOrder.getPrezzo()%>
						</td>
						<td>
							<%=detailedOrder.getIva()%>
						</td>
						<td>
							<%=detailedOrder.getPezzi()%>
						</td>
						<td>
							<%=detailedOrder.getTotale() %>
						</td>
						
					</tr>
				</table>
			</div>
			
		<%	
		request.getSession().setAttribute("detailedOrder", null);
		}
		%>
		
		<br>
		
		
		
		
		
		<hr>
		
		<%@ include file="Footer.jsp"%>
		
	<%	
	}		
	else
	{
		//System.out.println("errore userview\n");
		response.sendRedirect("Login.jsp");
	}
	%>



</body>
</html>