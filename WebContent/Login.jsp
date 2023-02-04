<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
<title>Login form</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>

<div>
<form action="login" method="post"> 
<fieldset>
     <legend>Login</legend>
     <input type="hidden" name="action" value="login"> 
     
     <input class="userInput" id="username" type="text" name="username" placeholder="userMail" required placeholder="username"> 
     <label for="username">Mail/Username</label>
     <br>
     <br>
    
     <input class="userInput" id="password" type="password" name="password" placeholder="password" required placeholder="password"> 
     <label for="password">Password</label>
     
     <br>
     <br>
         
     <label for="user">as User</label>
     <input id="user" type="radio" name="loginType" value="user" checked>    
     
     <label for="admin">as Admin</label>
     <input id="admin" type="radio" name="loginType" value="admin"> 
     
   
     
     <br>
     <br>
     
     
     <input class="showTableButton" type="submit" value="Login"/>
     <input class="showTableButton" type="reset" value="Reset"/>
</fieldset>
</form> 
 <a href="Registration.jsp" >Non sei registrato? Registrati Ora!</a>
</div>
<br>
<%@ include file="Footer.jsp" %>
</body>
</html>