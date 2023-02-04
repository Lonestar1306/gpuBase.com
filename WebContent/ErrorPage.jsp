<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<link href="css/ErrorStyle.css" rel="stylesheet" type="text/css">
<title>Error</title>
<%
	int status=response.getStatus();
	String errorMsg=(String)request.getSession().getAttribute("errorMsg");
	
%>
</head>
<body>

<table><tr>

<td>
<form method="post" action="ProductView.jsp" >
			<button class="errorButton" id="homeButton" type="submit">HOME</button>
</form>
</td>
<td>
<form method="post" action="AdminView.jsp" >
			<button class="errorButton" id="homeButton" type="submit">ADMIN</button>
</form>
</td>		
</tr></table>
<div class="space"></div><hr>

	<h1>STATUS CODE <br> <%=status%> </h1> 
	
<hr><div class="space"></div><hr>
	
 	<h1>ERROR MESSAGE <br> <%=errorMsg%></h1>
 	
 <hr><div class="space"></div>
 
 

</body>
</html>