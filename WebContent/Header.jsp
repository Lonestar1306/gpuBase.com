<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
	<head>
		
	<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
	<link rel="icon" type="image/png" href="./foto/titleLogo1.png"/>	
	</head>
<body>

	<div class="headerDiv" id="headerMainDiv">
	
		<table >
			<tr>
				<td class="headerImageCell" id="headerLogoCell">
				
				<img src="./foto/gpuBaseIco3.png" onerror="this.src='./foto/wip.jpg'"  
						class="headerImage" id="headerLogo"	>
				</td>
				
				<td class="headerBigBlankCell"></td>
				
				<td class="headerButtonCell" id="headerHomeButtonCell">
						<form method="post" action="ProductView.jsp" >
								<button class="headerButton" id="headerHomeButton" type="submit">HOME</button>
							</form>
				</td>
				
			
					
					<%
					if(!"loginDone".equals(request.getSession().getAttribute("loginResult")))
					{
					%>
						<td class="headerButtonCell"  id="headerLoginButtonCell">
						
						<form method="post" action="Login.jsp" >
							<button class="headerButton" id="headerLoginButton" type="submit">LOGIN</button>
								
							</form>
						</td>	
						
					<%
					} 
					else
					{
					%>
						<td class="headerButtonCell"  id="headerLogoutButtonCell">
							<form method="post" action="login"  >
								<input type="hidden" name="action" value="logout"> 
								<button class="headerButton" id="headerLogoutButton" type="submit">LOGOUT</button>
							</form>
						</td>
						
						
						
						<%
						if("user".equals(request.getSession().getAttribute("loginType")))
						{
						%>
						
						
						<td class="headerButtonCell"  id="headerUserButtonCell">
							<form method="post" action="UserView.jsp" >
								<button class="headerButton" id="headerUserButton" type="submit">UTENTE</button>
							</form>
						</td>
						<%
						}
					
					
					
						
						%>
							
						<td class="headerBlockCell" id="headerUserInfo" >
						<%=request.getSession().getAttribute("username")%>
						</td>
						
					
					
					<%
					}
					%>			
					
				</tr>
				
		</table>
	
	</div>

</body>
</html>



