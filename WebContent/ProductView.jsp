<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*,
				com.gpuBase.model.VenditaBean, 
				com.gpuBase.model.Carrello,
				com.gpuBase.model.MyCollection,
				com.gpuBase.model.VenditaCustomSchedaBean,
				com.gpuBase.model.FotoBean"%>



<%

	
	//products contiene tutti i prodotti in vendita
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if (products == null) {
		response.sendRedirect("./product");
		return;
	}

	//collezione di prodotti da ordinare
	Carrello cart = (Carrello) request.getAttribute("cart");
	
	
	//collezione di prodotti da comparare
	MyCollection toCompare = (MyCollection)request.getSession().getAttribute("toCompare");
	if(toCompare==null){toCompare=new MyCollection();}
	int toCompareSize=toCompare.getSize();
	int toCompareMaxSize=toCompare.getMaxSize();

%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>

<head>

<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
<link href="css/SliderStyle.css" rel="stylesheet" type="text/css">

<script src="script/SliderScript.js" type="text/javascript"></script>
		

<title> Home </title>
</head>

<body onload="showSlides()">

	
	
	<%@ include file="Header.jsp" %>
	
	
	
	<br><br>
	
	
	<%
	//controllo autorizzazione 
	if ("admin".equals(request.getSession().getAttribute("loginType"))) 
	{
	%>
	<br>
		<div>
			<h1>
				GLI AMMINISTRATORI NON POSSONO
				ACCEDERE A QUESTA PAGINA<br>
			</h1>
		</div>
		<br>
	
	<%
	} 
	else 
	{
	%>

<!-- SLIDER -->
		<div class="slideshow-container" >
			<div class="mySlides fade">
				<img src="./foto/sliderImages/img1.png">			 
			</div>	
			<div class="mySlides fade">
				<img src="./foto/sliderImages/img2.png">
			</div>
			<div class="mySlides fade">
				<img src="./foto/sliderImages/img3.png">
			</div>
		</div>
	<br>						


<!-- VENDITA -->
		
		<%
		if (products != null && products.size() != 0) 
		{
		%>
		<hr><hr>
		<h2>Prodotti in vendita</h2>
		<hr><hr>	
			<form method="get" action="product">
				<input type="hidden" name="sort" value="prezzo">
				<button  class="sortButton" id="SortPrezzo" type="submit">Ordina per prezzo</button>
			</form>
			
			<form method="get" action="product">
				<input type="hidden" name="sort" value="sconto">
				<button  class="sortButton" id="SortPrezzo" type="submit">Ordina per sconto</button>
			</form>
			
			<br><br>
				<%
				
					Iterator<?> it = products.iterator();
					while (it.hasNext()) 
					{
						VenditaBean bean = (VenditaBean) it.next();
				%>
				<div class="showTableDiv">
					<table class="showProductTable">
					<tr>
							<%String idFoto=Integer.toString(bean.getIdVendita()); %>
							<td colspan=2><img src="./foto?from=ProductView&action=getMainProductFoto&idFoto=<%=idFoto%>"
								onerror="this.src='./foto/notFound.png'"></td>
						</tr>
						
						<tr>
							<td>tipo scheda:</td><td><%=bean.getIdScheda()%></td>
						</tr>
						
						<tr>
							<td>modello custom:</td><td><%=bean.getNomeCustom()%></td>
						</tr>
						
						<tr>
							<td>prezzo (iva esclusa):</td><td><%=bean.getPrezzo()%></td>
						</tr>
						<tr>
							<td>sconto applicato:</td><td><%=bean.getSconto()%></td>
						</tr>
						<tr>
							<td>pezzi rimasti:</td><td><%=bean.getPezzi()%></td>
						</tr>
						
						<tr>
							<td>prezzo finale:</td><td class="cellFinalPrice"><%=bean.getTotale()/bean.getPezzi()%></td>
						</tr>
						
						<tr><td colspan=2>
							<form method="get" action="product">
								<input type="hidden" name="action" value="addC"></input>
								<input type="hidden" name="idVendita" value=<%=bean.getIdVendita() %>></input>
								<button class="showTableButton" id="addCartButton" type="submit">Add to cart</button>
							</form>
						</td></tr>
						<tr><td colspan=2>
							<form method="get" action="custom">
								<input type="hidden" name="formAction" value="showCustomDetalis"></input>
								<input type="hidden" name="idCustomDetails" value=<%=bean.getIdVendita() %>></input>
								<button class="showTableButton" id="showCustomDetailsButton" type="submit">Details</button>
							</form>	
						</td></tr>
						 <%if(toCompareSize<toCompareMaxSize){ %>
							<tr><td colspan=2>
								<form method="get" action="comparison">
									<input type="hidden" name="formAction" value="addToComparison"></input>
									<input type="hidden" name="idVendita" value=<%=bean.getIdVendita()%>></input>
									<input type="hidden" name="from" value="/ProductView.jsp"></input>
									<button class="showTableButton" id="addComparisonButton" type="submit">Add to Comparison</button>
								</form>	
							</td></tr>
						 <%} %>
					</table>
				</div>
				<%
					}
				} 
				else 
				{
				%>
				Nessun prodotto in vendita
				<%
				}
				%>
	
		
	
	<div class="clearDiv"></div>
		
	
		
		
		
	<!-- CARRELLO -->
	
	
		<%
		if (cart != null && !cart.getProducts().isEmpty())
		{
		%>
		<br>
		<br>
		
		<div class="showTableDiv">
			<hr><hr><h2>Carrello</h2><hr><hr>
			
				<%
				List<VenditaBean> prodcart = cart.getProducts();
					for (VenditaBean beancart : prodcart) 
					{
					%>
					
				<table class="showProductTable">
					<tr >
						<td>
							<img src="./foto?from=ProductView&action=getMainProductFoto&idFoto=<%=beancart.getIdVendita()%>"
							onerror="this.src='./foto/notFound.png'">

						</td>
						<td>
						<br>
						<form action="product" method="post" name="formUpdatePzC">
							<input type="hidden" name="action" value="updatePzC">
							 <input type="hidden" name="idVendita" value="<%=beancart.getIdVendita()%>"> 
							 <input class="userInput" name="pezzi" type="number" value="<%=beancart.getPezzi()%>" 
							 		width="3" required placeholder="pezzi" min="1"  max="100" style="width: 30%"> 

							 <button class="showTableButton" id="updatePzButton" type="submit">aggiorna pezzi</button>
						</form>
					
					<br>Totale: <%=beancart.getTotale()%><br>
					
					<br>
						<form action="product" method="post" name="formDeleteC">
							<input type="hidden" name="action" value="deleteC">
							 <input type="hidden" name="idVendita" value="<%=beancart.getIdVendita()%>">  
							 <button class="showTableButton" id="deleteCButton" type="submit">Elimina</button>
						</form>
						<br>
				
					
					<%if(toCompareSize<toCompareMaxSize){ %>
							
								<form method="get" action="comparison">
									<input type="hidden" name="formAction" value="addToComparison"></input>
									<input type="hidden" name="idVendita" value=<%=beancart.getIdVendita()%>></input>
									<input type="hidden" name="from" value="/ProductView.jsp"></input>
									<button class="showTableButton" id="addComparisonButton" type="submit">Add to Comparison</button>
								</form>	
							
						 <%} %>
				</td></tr>
			</table>
					<%
					}
					%>
					
					<div class="clearDiv"></div>
			<h2> Totale Carrello:<%=cart.getTotale()%></h2>
		</div>
		<%
		}
		%>
		
	
		<!-- ORDINAZIONE  -->
		<%
		if((cart != null && !cart.getProducts().isEmpty()) || "done".equals(request.getSession().getAttribute("orderResult")))
		{
		%>
		<div class="showProductOrderDiv">
			<hr><hr><h2>Ordinazione</h2><hr><hr>

			
			<% 
			if("done".equals(request.getSession().getAttribute("orderResult")))
				{
				%>
				<br><h4>Ordine Effettuato</h4><br>
				<%
				request.getSession().setAttribute("orderResult","notDone");
				}
				%>
			<br>
			<%
			if(cart != null && !cart.getProducts().isEmpty())
			{
				if("user".equals(session.getAttribute("loginType")))
				{
				
					%>
					<form action="order" method="post">
						<input type="hidden" name="action" value="performOrder">
						<input class="headerButton" type="submit" value="ordina">
					</form><br>
					<%
				} 
				
				else
				{
				%>
				<h3>Per ordinare devi prima effettuare il login as user</h3>
				<%
				} 
			} 
			%>
		</div>
		
	
	<%
	}
	%>
		
		
				
		
			<!-- COMPARISON -->
		
		
		<%
		if (toCompare!=null && toCompare.getSize()>0) {
			
			%>
			<hr><hr><h3> Comparison</h3><hr><hr>
			<%
			int maxSize=toCompare.getMaxSize();
			VenditaCustomSchedaBean bean;
			for(int i=0;i<maxSize;i++) {
				bean=toCompare.getProductIndex(i);
				if(bean!=null){ 
					//System.out.println(bean);
	
					%>
	
					<div class="showTableDiv">
						<table class="showProductTable">
							<tr>
								<td colspan="2">
									<img src="./foto?from=ProductView&action=getMainProductFoto&idFoto=<%=bean.getIdCustom()%>"
									onerror="this.src='./foto/notFound.png'">
								</td>
							</tr>
							<tr>
								<td>
									tipo Scheda
								</td>
								<td>
									<%=bean.getIdScheda()%>
								</td>
							</tr>
							<tr>
								<td>
									modello
								</td>
								<td>
									<%=bean.getNomeCustom() %>
								</td>
							</tr>
							<tr>
								<td>
									<form method="get" action="comparison">
										<input type="hidden" name="formAction" value="removeComparison"></input>
										<input type="hidden" name="idVendita" value=<%=bean.getIdCustom()%>></input>
										<input type="hidden" name="from" value="ComparisonView.jsp"></input>
										<button class="headerButton" id="showComparisonButton" type="submit">Remove</button>
									</form>
								</td>
								<td>
									<form method="get" action="custom">
										<input type="hidden" name="formAction" value="showCustomDetalis"></input>
										<input type="hidden" name="idCustomDetails" value=<%=bean.getIdCustom()%>></input>
										<button class="headerButton" id="showCustomDetailsButton" type="submit">Details</button>
									</form>	
								</td>
							</tr>
						</table>
					
				
					<%
					}
				}
				%>
				
				<div class="clearDiv"></div><br>
				
		
				
			<%
			if(toCompareSize>1){ %>
				<form method="get" action="ComparisonView.jsp">
					<button class="headerButton" id="showComparisonButton" type="submit">Comparison</button>
				</form>
			<%
			}
			else {%>
				<h5>devi inserire almeno due prodotti da confrontare</h5>
			<%
			}
		}
		%>
		</div>
		
		

		
			
	<%
	}
	%>
	<br>
	
	<%@ include file="Footer.jsp" %>


</body>
</html>
