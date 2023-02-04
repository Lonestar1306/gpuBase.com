<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.*,
				com.gpuBase.model.CustomBean, 
				com.gpuBase.model.SchedaBean, 
				com.gpuBase.model.MyCollection,
				com.gpuBase.model.FotoBean,
				com.gpuBase.model.FotoModelDS"%>

<!DOCTYPE html>
<html>
<head>
<title>Dettagli Custom</title>
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">

<link href="css/GalleryStyle.css" rel="stylesheet" type="text/css">

<script src="script/JQuery.js" type="text/javascript"></script>
<script src="script/GalleryScriptJQuery.js" type="text/javascript"></script>



</head>
<body>

	<%
		//System.out.println("CustomView");
	%>

	<%@ include file="Header.jsp"%>
	<%
		CustomBean customDetails = (CustomBean) request.getSession().getAttribute("customDetails");
		if (customDetails == null) {
			System.out.println("dettagli custom non presenti");
		}

		if (customDetails != null) {
			//System.out.println("Dettagli Custom");
	%>
	
	
	<%
		FotoModelDS fotoDS=new FotoModelDS();
		int idFoto=customDetails.getIdCustom();
		int countFoto=fotoDS.getCountFoto(idFoto);
		int i;
	%>
	
	
	
	
		<div class="row">
			
		  		<div class="column">
		   			 <img  id="onePhoto1" class="onePhoto" src="./foto?from=CustomView&action=getOnePhoto&idFoto=<%=idFoto%>&flagFoto=1" alt="" 
		    		style="width:100%" onerror="hidePhoto(this)">		
		  		</div>
		  		
		  		<div class="column">
		   			 <img  id="onePhoto2" class="onePhoto" src="./foto?from=CustomView&action=getOnePhoto&idFoto=<%=idFoto%>&flagFoto=2" alt="" 
		    		style="width:100%" onerror="hidePhoto(this)">		
		  		</div>
		  		
		  		<div class="column">
		   			 <img  id="onePhoto3" class="onePhoto" src="./foto?from=CustomView&action=getOnePhoto&idFoto=<%=idFoto%>&flagFoto=3" alt="" 
		    		style="width:100%" onerror="hidePhoto(this)">		
		  		</div>
		  		
		  		<div class="column">
		   			 <img  id="onePhoto4" class="onePhoto" src="./foto?from=CustomView&action=getOnePhoto&idFoto=<%=idFoto%>&flagFoto=4" alt="" 
		    		style="width:100%" onerror="hidePhoto(this)">		
		  		</div>
		  
		 
		</div>
		
		<div class="container" id="expandedImgDiv">
		  <button class="closebtn">x</button>
		  <img id="expandedImg" style="width:100%;">
		 
		</div>
	
	
		 
		 
<div class="showTableDetailsAllDiv">
	<div class="showTableDetailsDiv">
		<table>
		<tr>
			<th colspan="2"><font size="5">Custom</font></th>
		</tr>
			<tr>
				<th>idCustom</th>
				<td><%=customDetails.getIdCustom()%></td>
			</tr>
			<tr>
				<th>idScheda</th>
				<td><%=customDetails.getIdScheda()%></td>
			</tr>
			<tr>
				<th>profondita</th>
				<td><%=customDetails.getProfondita()%></td>
			</tr>
			<tr>
				<th>larghezza</th>
				<td><%=customDetails.getLarghezza()%></td>
			<tr>
				<th>lunghezza</th>
				<td><%=customDetails.getLunghezza()%></td>
			</tr>
			<tr>
				<th>peso</th>
				<td><%=customDetails.getPeso()%></td>
			</tr>
			<tr>
				<th>nomeCustom</th>
				<td><%=customDetails.getNomeCustom()%></td>
			</tr>
			<tr>
				<th>azienda</th>
				<td><%=customDetails.getAzienda()%></td>
			</tr>
			<tr>
				<th>risoluzioneMax</th>
				<td><%=customDetails.getRisoluzioneMax()%></td>
			</tr>
			<tr>
				<th>porteDP</th>
				<td><%=customDetails.getPorteDP()%></td>
			</tr>
			<tr>
				<th>porteVGA</th>
				<td><%=customDetails.getPorteVGA()%></td>
			</tr>
			<tr>
				<th>porteDVI</th>
				<td><%=customDetails.getPorteDVI()%></td>
			</tr>
			<tr>
				<th>porteHDMI</th>
				<td><%=customDetails.getPorteHDMI()%></td>
			<tr>
			<tr>
				<th>Show more</th>
					
				

				<td>
					<form method="get" action="scheda">
						<input type="hidden" name="formAction" value="showSchedaDetalis"></input>
						<input type="hidden" name="idSchedaDetails" value=<%=customDetails.getIdScheda()%>></input>
						<input type="hidden" name="fromSource" value="/CustomView.jsp"></input>
						<button class="showTableButton" id="showSchedaDetailsButton" type="submit">Details</button>
					</form>
				</td>

			</tr>
		</table>
		
		
		
		
	</div>
		
	


	<%if("showSchedaDetalis".equals(request.getParameter("formAction"))){%>
		<%@ include file="SchedaView.jsp"%>
		
	<%
	} %>
	

		<div class="clearDiv"></div>
		
		<%
		if ("user".equals(request.getSession().getAttribute("loginType"))) 
		{
		%>
			
			<br>
				<form method="get" action="product">
						<input type="hidden" name="action" value="addC"></input>
						<input type="hidden" name="idVendita" value=<%=customDetails.getIdCustom()%>></input>
						<button class="showTableButton" id="addCartButton" type="submit">Add to cart</button>
				</form>
			<br>
		
		
		
		
		
		 <%
		 
		 MyCollection toCompare = (MyCollection)request.getSession().getAttribute("toCompare");
		 	if(toCompare==null){toCompare=new MyCollection();}
		 	int toCompareSize=toCompare.getSize();
		 	int toCompareMaxSize=toCompare.getMaxSize();
		 
		 if(toCompareSize<toCompareMaxSize){ %>
						<br>
		
								<form method="get" action="comparison">
									<input type="hidden" name="formAction" value="addToComparison"></input>
									<input type="hidden" name="idVendita" value=<%=customDetails.getIdCustom()%>></input>
									<input type="hidden" name="from" value="/ProductView.jsp"></input>
									<button class="showTableButton" id="addComparisonButton" type="submit">Add to Comparison</button>
								</form>	
						<br>
		
						 <%} %>
		<%
		}
		%>

</div>

	<%
		}
	%>

<br><br>
	<%@ include file="Footer.jsp"%>
	

</body>
</html>