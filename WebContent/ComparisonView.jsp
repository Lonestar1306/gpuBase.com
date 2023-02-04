<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*,
				com.gpuBase.model.VenditaBean, 
				com.gpuBase.model.CustomBean, 
				com.gpuBase.model.SchedaBean,
				com.gpuBase.model.FotoBean,
				com.gpuBase.model.MyCollection,
				com.gpuBase.model.VenditaCustomSchedaBean"
				
		%>
				
	
		
<!DOCTYPE html>
<html>
	<head>
		<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
		<script src="script/ComparisonScript.js" type="text/javascript"></script>
		<title>
			Comparison
		</title>
	
		<%
		MyCollection toCompare = (MyCollection)request.getSession().getAttribute("toCompare");
		if (toCompare == null || toCompare.getSize()<2) {
			response.sendRedirect("ProductView.jsp");
			return;
		}

		%>
		<span style="display:none;" id="compSize" ><%=toCompare.getSize()%></span>
	</head>

	<body onload="compareDetails()">
	
		<%@ include file="Header.jsp" %>
	
		<div  class="showComparisonDiv" id="comparisonDiv">
	
	
		
		<%
		
		if (toCompare!=null && toCompare.getSize()>0) {
			
			int maxSize=toCompare.getMaxSize();
			VenditaCustomSchedaBean bean;
			for(int i=0;i<toCompare.getSize();i++) {
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
							<td class="comparisonTipoScheda" id=<%=i+"idScheda"%>>
								<%=bean.getIdScheda()%>
							</td>
						</tr>
						<tr>
							<td >
								modello
							</td>
							<td class="comparisonModello" id=<%=i+"nomeCustom"%>>
								<%=bean.getNomeCustom() %>
							</td>
						</tr>
						<tr>
							<td >
								data uscita
							</td>
							<td class="comparisonDataUscita" id=<%=i+"dataUscita"%>>
								 <%=bean.getDataUscita() %>
							</td>
						</tr>
						<tr>
							<td >
								prezzo
							</td>
							<td class="comparisonPrezzo" id=<%=i+"prezzo"%>>
								<%=bean.getPrezzo() %>
							</td>
						</tr>
						<tr>
							<td >
							risoluzione max
							</td>
							<td class="comparisonRisoluzionMax"  id=<%=i+"risoluzionMax"%>>
								<%=bean.getRisoluzioneMax() %>
							</td>
						</tr>
						<tr>
							<td >
								TDP
							</td>
							<td class="comparisonTdp" id=<%=i+"tdp"%>>
								<%=bean.getTDP() %>
							</td>
						</tr>
						<tr>
							<td >
								num. Core
							</td>
							<td class="comparisonNumCore" id=<%=i+"numeroCore"%>>
								<%=bean.getNumeroCore() %>
							</td>
						</tr>
						<tr>
							<td >
								freq. Core
							</td>
							<td class="comparisonFreqCore" id=<%=i+"frequenzaCore"%>>
								<%=bean.getFrequenzaCore() %>
							</td>
						</tr>
						<tr>
							<td >
								freq. memoria
							</td>
							<td class="comparisonFreqMemoria" id=<%=i+"frequenzaMemoia"%>>
							<%=bean.getFrequenzaMemoria() %>
							</td>
						</tr>
						<tr>
							<td>
								dim. memoria
							</td>
							<td  class="comparisonDimMemoria" id=<%=i+"dimensioneMemoria"%>>
							<%=bean.getDimensioneMemoria() %>
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
				</div>
			
		
		
		
	<%
			}
		}
	%>
		
	<%

	}else System.out.println("conparisonError");%>
		</div>
		
		<%@ include file="Footer.jsp" %>
	</body>
</html>