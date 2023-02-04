<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*,
				com.gpuBase.model.SchedaBean"%>

<!DOCTYPE html>
<html>
<head>
<title>Dettagli Scheda</title>
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
</head>

<body>

	<%System.out.println("SchedaView"); %>
	<%
		SchedaBean schedaDetails = (SchedaBean) request.getSession().getAttribute("schedaDetails");
		if (schedaDetails == null) {
			response.sendRedirect("./scheda");
			return;
		}

		
		if (schedaDetails != null){
		/**/System.out.println("Dettagli Scheda");
	%>

	<div class="showTableDetailsDiv">
		
		<table>
		<tr>
			<th colspan="2"><font size="5">Scheda</font></th>
		</tr>
			<tr>
				<th>idScheda</th>
				<td><%=schedaDetails.getIdScheda()%></td>
			</tr>
			<tr>
				<th>produttore</th>
				<td><%=schedaDetails.getProduttore()%></td>
			</tr>
			<tr>
				<th>data uscita</th>
				<td><%=schedaDetails.getDataUscita()%></td>
			</tr>
			<tr>
				<th>TDP</th>
				<td><%=schedaDetails.getTDP()%></td>
			</tr>
			<tr>
				<th>nome core</th>
				<td><%=schedaDetails.getNomeCore()%></td>
			</tr>
			<tr>
				<th>numero core</th>
				<td><%=schedaDetails.getNumeroCore()%></td>
			</tr>
			<tr>
				<th>freequenza core</th>
				<td><%=schedaDetails.getFrequenzaCore()%></td>
			</tr>
			<tr>
				<th>generazione memoria</th>
				<td><%=schedaDetails.getGenerazioneMemoria()%></td>
			</tr>
			<tr>
				<th>dimensione memoria</th>
				<td><%=schedaDetails.getDimensioneMemoria()%></td>
			</tr>
			<tr>
				<th>frequenza memoria</th>
				<td><%=schedaDetails.getFrequenzaMemoria()%></td>
			</tr>
			
	
		</table>
	</div>

	<%
		}
	%>

</body>
</html>