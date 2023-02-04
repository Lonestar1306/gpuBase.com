<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/MainStyle.css" rel="stylesheet" type="text/css">
<script src="script/FormScript.js" type="text/javascript"></script>
<title>Registration form</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>

<div>
<form action="login" method="post"> 
<fieldset >
     <legend>Registrazione</legend>
     <input type="hidden" name="action" value="register"> 
     <input type="hidden" name="loginType" value="user">   
     
     
     
  
		   <input class="userInput" id="username" type="email" name="username" placeholder="userMail" required placeholder="username" value=""
		      oninput=checkUniqueMail(this)> 
		     <label for="username">Mail/Username</label>
			<span class="formSpan" id="mailSpanError"></span>
			
		     <br>
		     <br>
		    
		     <input class="userInput" id="password" type="password" name="password" placeholder="password" required placeholder="password"
		     	value="" oninput=checkPasswordQuality(this)> 
		     <label for="password">Password</label>
		     <span class="formSpan" id="passwordSpanError"></span>
     
  
     
     <br>
     <br>
         
     
      <input class="userInput" id="address" type="text" name="address" placeholder="Indirizzo" required placeholder="address" value="" maxlength="256"> 
     <label for="address">Indirizzo</label>
 
     
     
     <br>
     <br>
     
     <input class="userInput" id="tel" type="tel" name="tel" placeholder="Telefono" required placeholder="tel"  value="" maxlength="10"> 
     <label for="tel">Telefono</label>
     
     <br>
     <br>
     
    
	     <input class="showTableButton" id="registerSubmitButton" type="submit" value="Register" onmouseover=checkRegistrationButton() />
	     <input class="showTableButton" type="reset" value="Reset"/>
	     
</fieldset>
</form> 



<br><br>
 <a href="Login.jsp" >Sei già registrato? Esegui il login qui.</a>
</div>
<br>
<%@ include file="Footer.jsp" %>
</body>
</html>