<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.*,
				com.gpuBase.model.VenditaBean,
				com.gpuBase.model.FotoBean,
				com.gpuBase.model.OrdinazioneBean"%>
				
<!DOCTYPE html>
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">

<script src="script/AdminScript.js" type="text/javascript"></script>
<script src="script/FormScript.js" type="text/javascript"></script>

<title>gpuBase admin</title>
</head>

<body onload="adminShowDiv(0)">

<%@ include file="Header.jsp"%>



<%
	if ("admin".equals(request.getSession().getAttribute("loginType"))) {
		
			

		Collection<?> products = (Collection<?>) request.getAttribute("products");
		if (products == null) {
			response.sendRedirect("./admin");
			return;
		}

		VenditaBean product = (VenditaBean) request.getAttribute("product");
%>

<br>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(1)">Visualizza Vendita</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(2)">Inserisci Vendita</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(3)">Inserisci Scheda</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(4)">inserisci Custom</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(5)">Aggiorna Vendita</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(6)">Inserisci Foto</button>
		<button class="showTableButton" type="submit" onclick="adminShowDiv(7)">Visualizza Ordini</button>

<br>
<br>

		<div class="showTableDiv" id="visualizzaVenditaDiv">
				<%
				if (products != null && products.size() != 0) 
				{
					Iterator<?> it = products.iterator();
					while (it.hasNext()) 
					{
						VenditaBean bean = (VenditaBean) it.next();
				%>
				
					<table class="showProductTable">
					<tr>
							<%String idFoto=Integer.toString(bean.getIdVendita()); %>
							<td><img src="./foto?from=ProductView&action=getMainProductFoto&idFoto=<%=idFoto%>"
								onerror="this.src='./foto/notFound.png'"></td>
						</tr>
						<tr>
							<td>idVendita: <%=bean.getIdVendita()%></td>
						</tr>
						<tr>
							<td>nomeAmministratore: <%=bean.getNomeAmministratore()%></td>
						</tr>
						<tr>
							<td>pezzi: <%=bean.getPezzi()%></td>
						</tr>
						<tr>
							<td>iva: <%=bean.getIva()%></td>
						</tr>
						<tr>
							<td>prezzo: <%=bean.getPrezzo()%></td>
						</tr>
						<tr>
							<td>sconto: <%=bean.getSconto()%></td>
						</tr>
						<tr><td>
						<form method="get" action="admin">
							<input type="hidden" name="action" value="delete"></input>
							<input type="hidden" name="idVendita" value=<%=bean.getIdVendita() %>></input>
							<button class="showTableButton" id="deleteVendita" type="submit">Delete</button>
						</form>	
						</td></tr>
						<tr><td>
							<form method="get" action="custom">
								<input type="hidden" name="formAction" value="showCustomDetalis"></input>
								<input type="hidden" name="idCustomDetails" value=<%=bean.getIdVendita() %>></input>
								<button class="showTableButton" id="showCustomDetailsButton" type="submit">Details</button>
							</form>	
						</td></tr>
					</table>
				
				<%
					}
				%>
				</div><br><br>
				<%
				} 
				else 
				{
				%>
				Nessun prodotto in vendita
				<%
				}
				%>
	
		



<div class="showTableDiv" id="inserisciSchedaDiv">
		<h2>Inserimento Scheda</h2>	
		<form action="scheda" method="post">
			<input type="hidden" name="formAction" value="addScheda"> 
			<input type="hidden" name="fromSource" value="/AdminView.jsp"> 
			
			<label	for="idSchedaDetails">modello scheda:</label><br> 
			<input class="userInput" name="idSchedaDetails"	type="text" required placeholder="idSchedaDetails"><br> 
			
			<label for="produttore">produttore :</label><br>
			<input class="userInput" name="produttore" type="text"
			required placeholder="enter produttore"></input>
			
			<br> <label for="dataUscita">dataUscita:</label><br> 
			<input class="userInput" name="dataUscita" type="date" required><br>
			
			<label for="TDP">TDP:</label><br> 
			<input class="userInput" name="TDP" type="number" min="1"  required><br> 
			
			<label for="nomeCore">nomeCore:</label><br> 
			<input class="userInput" name="nomeCore" type="text" required><br> 

			<label for="numeroCore">numeroCore:</label><br> 
			<input class="userInput" name="numeroCore" type="number" required><br> 
			
			<label for="frequenzaCore">frequenzaCore:</label><br>
			<input class="userInput" name="frequenzaCore" type="number" min="1"  required><br>
			
			<label for="frequenzaMemoria">frequenzaMemoria:</label><br>
			<input class="userInput" name="frequenzaMemoria" type="number" min="1"  required><br>
			
			<label for="generazioneMemoria">generazioneMemoria:</label><br>
			<input class="userInput" name="generazioneMemoria" type="text" required><br>
			
			<label for="dimensioneMemoria">dimensioneMemoria:</label><br>
			<input class="userInput" name="dimensioneMemoria" type="number" min="1"  required><br>
			
			<br>
			<input class="showTableButton" type="submit" value="Add">
			<input class="showTableButton" type="reset" value="Reset">
		</form>

	</div>

					
<div class="showTableDiv" id="inserisciCustomDiv">
		<h2>Inserimento Custom</h2>	
		<form action="custom" method="post">
			<input type="hidden" name="formAction" value="addCustom"> 
			<input type="hidden" name="fromSource" value="/AdminView.jsp"> 
			
			<label	for="idCustomDetails">id custom:</label><br> 
			<input class="userInput" name="idCustomDetails"	type="number" min=1 required placeholder="idCustomDetails"><br> 
			
			<label for="idScheda">modello Scheda :</label><br>
			<input class="userInput" name="idScheda" type="text"
			required placeholder="enter produttore"></input>
			
			<br> <label for="profondita">profondita:</label><br> 
			<input class="userInput" name="profondita" type="number" min="1" required><br>
			
			<label for="larghezza">larghezza:</label><br> 
			<input class="userInput" name="larghezza" type="number" min="1" required><br> 
			
			<label for="lunghezza">lunghezza:</label><br> 
			<input class="userInput" name="lunghezza" type="number" min="1" required><br> 
			
			<label for="peso">peso:</label><br> 
			<input class="userInput" name="peso" type="number" min="1" required><br> 

			<label for="nomeCustom">nomeCustom:</label><br> 
			<input class="userInput" name="nomeCustom" type="text" required><br> 
			
			<label for="azienda">azienda:</label><br>
			<input class="userInput" name="azienda" type="text"  required><br>
			
			<label for="risoluzioneMax">risoluzioneMax:</label><br>
			<input class="userInput" name="risoluzioneMax" type="text"  required><br>
			
			<label for="porteDP">porteDP:</label><br>
			<input class="userInput" name="porteDP" type="number" min="0" required><br>
			
			<label for="porteVGA">porteVGA:</label><br>
			<input class="userInput" name="porteVGA" type="number" min="0"  required><br>
			
			<label for="porteDVI">porteDVI:</label><br>
			<input class="userInput" name="porteDVI" type="number" min="0"  required><br>
			
			<label for="porteHDMI">porteHDMI:</label><br>
			<input class="userInput" name="porteHDMI" type="number" min="0"  required><br>
			
			<br>
			<input class="showTableButton" type="submit" value="Add">
			<input class="showTableButton" type="reset" value="Reset">
		</form>

	</div>








	<div class="showTableDiv" id="inserisciVenditaDiv">
		<h2>Inserimento prodotti</h2>
		<form action="admin" method="post">
			<input type="hidden" name="action" value="insert"> 
			
			<label	for="idVendita">id:</label><br> 
			<input class="userInput" name="idVendita"	type="number" required placeholder="idVendita"><br> 
			
			<label for="nomeAmministratore">nomeAmministratore:</label><br>
			<input class="userInput" name="nomeAmministratore" maxlength="32"
			required placeholder="enter nomeAmministratore"></input>
			
			<br> <label for="prezzo">prezzo:</label><br> 
			<input class="userInput" name="prezzo" type="number" min="0" value="0" required><br>

			<label for="sconto">sconto:</label><br> 
			<input class="userInput" name="sconto" type="number" min="0" value="0" required><br> 
			
			<label for="iva">iva:</label><br> 
			<input class="userInput" name="iva" type="number" min="0" value="0" required><br> 
			
			<label for="pezzi">pezzi:</label><br>
			<input class="userInput" name="pezzi" type="number" min="0" value="0" required><br>
			<br>
			<input class="showTableButton" type="submit" value="Add">
			<input class="showTableButton" type="reset" value="Reset">
		</form>

	</div>
	
	
	
	
	<div  id="aggiornaVenditaDiv">
		<h2>aggiornamento prodotti</h2>
		<form action="admin" method="post">
			<input type="hidden" name="action" value="update"> 
			
			<label	for="idVendita">id:</label><br> 
			<input class="userInput" name="idVendita"	type="number" required placeholder="idVendita"><br> 
			
			<label for="nomeAmministratore">nomeAmministratore:</label><br>
			<input class="userInput" name="nomeAmministratore" maxlength="32"
			required placeholder="enter nomeAmministratore"></input>
			
			<br> <label for="prezzo">prezzo:</label><br> 
			<input class="userInput" name="prezzo" type="number" min="0" value="0" required><br>

			<label for="sconto">sconto:</label><br> 
			<input class="userInput" name="sconto" type="number" min="0" value="0" required><br> 
			
			<label for="iva">iva:</label><br> 
			<input class="userInput" name="iva" type="number" min="0" value="0" required><br> 
			
			<label for="pezzi">pezzi:</label><br>
			<input class="userInput" name="pezzi" type="number" min="0" value="0" required><br>
			<br>
			<input class="showTableButton" type="submit" value="Add">
			<input class="showTableButton" type="reset" value="Reset">
		</form>
	</div>




	<div id="inserisciFotoDiv">
		<h2>Inserimento di nuove foto</h2>
		<form action="foto" method="post">

			<input type="hidden" name="action" value="setProductFoto"> 
			<input type="hidden" name="from" value="AdminView"> 
			
			<label for="idFoto">id della Custom a cui vuoi associare la foto:</label><br> 
			<input class="userInput" name="idFoto" type="number" required placeholder="idFoto"><br> 
			
			
			<!--  
			<label for="nomeFoto">con che nome vuoi che sia salvata la foto:</label><br>
			<input class="userInput" name="nomeFoto" type="text" required placeholder="nomeFoto"></input><br>
			-->
			
			
			
			<label for="cartellaFoto">dove si trova la foto (path completa + nome foto + estensione)</label><br> 
			<input class="userInput" name="cartellaFoto" type="text" required placeholder="cartellaFoto"></input><br>
			<br>
			<input class="showTableButton" type="submit" value="Add">
			<input class="showTableButton" type="reset" value="Reset">
		</form>
	</div>
	
	
	
	

	<div id="visualizzaOrdiniDiv">
		<h2>Visualizza Ordini utente per Data</h2>
		
		
		<div class="showTableDiv" onmouseover=checkData()> 	FILTRO DATA	<br>
			<form class="numberFilter" id="dataFilterForm" action="user" method="get" >
				<input type="hidden" name="formAction" value="getOrderByDates">
				<input type="hidden" name="from" value="/AdminView.jsp">
				<input class="dataFilterInput" type="number" id="day1" value="" name="day1" min="1" max="31"  placeholder="giorno inizio" required placeholder="day1">
				<input class="dataFilterInput" type="number" id="month1"  value="" name="month1"  min="1" max="12" placeholder="mese inizio" required placeholder="month1">
				<input class="dataFilterInput" type="number" id="year1"  value="" name="year1" placeholder="anno inizio" required placeholder="yead1">
				<br><br>
				<input class="dataFilterInput" type="number" id="day2"  value="" name="day2" min="1" max="31" placeholder="giorno fine" required placeholder="day2">
				<input class="dataFilterInput" type="number" id="month2" value="" name="month2"  min="1" max="12" placeholder="mese fine" required placeholder="month2">
				<input class="dataFilterInput" type="number" id="year2"  value="" name="year2" placeholder="anno fine" required placeholder="year2" >
				<br>
				<br>
				<input id="username" class="userInput" type="email" name="mailCliente" placeholder="mailCliente" required placeholder="mailCliente" oninput=checkAdminMail(this)> 
				<br>
				<br>
				<input class="showTableButton" type="submit" id="dataFilterSubmitButton" name="dataFiter" value="RICERCA">
				<span class="formSpan" id="dataFilterSpanError"></span>
				<span class="formSpan" id="mailSpanError"></span>
			</form>
			</div>
			
		<br>
		
		<%
		Collection<?> orders = (Collection<?>) request.getSession().getAttribute("orders");
		//if(orders==null){System.out.println("orders null");}
		
		if(orders!=null)
		{
		/**/if(orders.isEmpty())System.out.println("orders empty");
		%>	
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
					
					<%
					Iterator<?> it = orders.iterator();
					while (it.hasNext()) 
					{
						OrdinazioneBean bean = (OrdinazioneBean) it.next();
					%>
						<tr>
							<td>
								<%=bean.getIdOrdine() %>
							</td>
							<td>
								<%=bean.getMailCliente()%>
							</td>
							<td>
								<%=bean.getIdCustom()%>
							</td>
							<td>
								<%=bean.getData() %>
							</td>
							<td>
								<%=bean.getPrezzo()%>
							</td>
							<td>
								<%=bean.getIva()%>
							</td>
							<td>
								<%=bean.getPezzi()%>
							</td>
							<td>
								<%=bean.getTotale() %>
							</td>
							
						</tr>
					<%
					}
					%>
				</table>
				
			<br>
			<form action="admin" method="get">
				<input class="showTableButton" type="hidden" name="action" value="resetOrders">
				<input class="showTableButton" type="submit" name="reset" value="RESET ORDER TABLE">
			</form>
		<%
		}
		%>
	
	</div>

	<%
	} 
	else 
	{
	%>
	<br>
	<div>
		<h1>
			NON HAI I PERMESSI PER ACCEDERE<br>
			ALLE FUNZIONALITA DI AMMINISTRATORE<br> 
			DI QUESTA PAGINA<br>
		</h1>
	</div>
	
	<%
		}
	%>

	<br>
	<br>
	<br>
	<br>
	<%@ include file="Footer.jsp"%>

</body>
</html>