var xhr = new XMLHttpRequest();
var mailStatus = false;
var passwordStatus = false;

function checkUniqueMailCallback() {
	//alert("callback: state= "+ xhr.readyState);

	if (xhr.readyState == 4 && xhr.status == 200) {
		var jsonObj=JSON.parse(xhr.responseText);
		var st=jsonObj.mailStatus;
		//alert(st);
		if (st == "mailError") {
			mailStatus = false;
			document.getElementById("mailSpanError").innerHTML = "Mail già registrata";
		} else if (st == "mailOK") {
			mailStatus = true;
			document.getElementById("mailSpanError").innerHTML = "";
		}
	}
}

function checkUniqueMail(mail) {
	xhr.onreadystatechange = checkUniqueMailCallback;
	xhr.open("POST", "/gpuBase_V_1-0/ServletResponseJSON", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.responseType = 'text';
	xhr.send("formAction=" + "checkUniqueMail" + "&mailCliente=" + mail.value);
	xhr.setRequestHeader("connection", "close");

}





function checkAdminMailCallback() {
	//alert("callback: state= "+ xhr.readyState);
	
	if (xhr.readyState == 4 && xhr.status == 200) {
		var jsonObj=JSON.parse(xhr.responseText);
		var st=jsonObj.mailStatus;
		//alert(st);
		if (st == "mailError") {
			mailStatus = false;
			document.getElementById("dataFilterSubmitButton").style.display="none";
			document.getElementById("mailSpanError").innerHTML = "Mail non presente";
		} else if (st == "mailOK") {
			mailStatus = true;
			document.getElementById("dataFilterSubmitButton").style.display="block";
			document.getElementById("mailSpanError").innerHTML = "";
		}
	}
}

function checkAdminMail(mail) {
	xhr.onreadystatechange = checkAdminMailCallback;
	xhr.open("POST", "/gpuBase_V_1-0/ServletResponseJSON", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.responseType = 'text';
	xhr.send("formAction=" + "checkAdminMail" + "&mailCliente=" + mail.value);
	xhr.setRequestHeader("connection", "close");

}








function checkPasswordQuality(psw) {
	var errorMsg = "";
	var space = " ";
	var fieldname = psw;
	var fieldvalue = fieldname.value;
	var fieldlength = fieldvalue.length;

	// It must not contain a space
	if (fieldvalue.indexOf(space) > -1) {
		errorMsg += "<br>Passwords cannot include a space";
	}

	// It must contain at least one number character
	if (!(fieldvalue.match(/\d/))) {
		errorMsg += "<br>Insert at least one number";
	}

	// It must contain at least one upper case character
	if (!(fieldvalue.match(/[A-Z]/))) {
		errorMsg += "<br>Insert at least one uppercase letter";
	}
	// It must contain at least one lower case character
	if (!(fieldvalue.match(/[a-z]/))) {
		errorMsg += "<br>Insert one or more lowercase letters";
	}
	// It must contain at least one special character
	if (!(fieldvalue.match(/\W+/))) {
		errorMsg += "<br>Insert at least one special character - #,@,%,!";
	}
	// It must be at least 8 characters long.
	if (!(fieldlength >= 8)) {
		errorMsg += "<br>Insert at least 8 characters long";
	}
	// If there is a problem with the form then display an error
	if (errorMsg != "") {
		document.getElementById("passwordSpanError").innerHTML = errorMsg;
		document.getElementById("dataFilterSubmitButton").style.display="none";
		passwordStatus = false;
	} else {
		document.getElementById("passwordSpanError").innerHTML = "";
		document.getElementById("dataFilterSubmitButton").style.display="block";
		passwordStatus = true;
	}
}

function checkRegistrationButton() {
	var mail = document.getElementById("username");
	var psw = document.getElementById("password");
	var addr = document.getElementById("address");
	var tel = document.getElementById("tel");
	if (passwordStatus && mailStatus) {
		document.getElementById("registerSubmitButton").style.display = "block";
	} else {
		document.getElementById("registerSubmitButton").style.display = "";

	}

}

function checkData() {
	
	var errorFlag1 = true;
	var errorFlag2 = true;
	var errorMsg = "";
	var errorSpan = document.getElementById("dataFilterSpanError");

	var day1 = document.getElementById("day1").value;
	var month1 = document.getElementById("month1").value;
	var year1 = document.getElementById("year1").value;
	

	var day2 = document.getElementById("day2").value;
	var month2 = document.getElementById("month2").value;
	var year2 = document.getElementById("year2").value;
	
	/**/
	if  ((month1==4 || month1==6 || month1==9 || month1==11) && day1==31) {
		  errorMsg += "<br>Month "+month1+" doesn't have 31 days!";
		  errorFlag1=false;
	}
	
	if (month1 == 2) { // check for february 29th
		 var isleap1 = (year1 % 4 == 0 && (year1 % 100 != 0 || year1 % 400 == 0));
		  if (day1>29 || (day1==29 && !isleap1)) {
		   errorMsg += "<br>February " + year1 + " doesn't have " + day1 + " days!";
		   errorFlag1=false;
		  }
	}
	
	/**/
	if ((month2==4 || month2==6 || month2==9 || month2==11) && day2==31) {
		  errorMsg += "<br>Month "+month2+" doesn't have 31 days!";
		  errorFlag2=false;
	}
	if (month2 == 2) { // check for february 29th
		 var isleap2 = (year2 % 4 == 0 && (year2 % 100 != 0 || year2 % 400 == 0));
		  if (day2>29 || (day2==29 && !isleap2)){
		   errorMsg += "<br>February " + year2 + " doesn't have " + day2 + " days!";
		   errorFlag2=false;
		  }
	}
		  
		  
		  
	/**/
	if (errorFlag1 && errorFlag2) {
		errorMsg = "";
		document.getElementById("dataFilterSubmitButton").style.display = "block";
	} 
	else
		{
		document.getElementById("dataFilterSubmitButton").style.display = "none";
		}
	
	
	if (!errorFlag1) {
		errorMsg += "<br>Data 1 non valida!";
	}
	if (!errorFlag2) {
		errorMsg += "<br>Data 2 non valida!";
	}

	errorSpan.innerHTML = errorMsg;
	return errorFlag1 && errorFlag2;
}
