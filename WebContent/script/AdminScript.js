

function adminShowDiv(id){

	var last="";
	
	if(id==1){last="visualizzaVenditaDiv";}
	else if(id==2){last="inserisciVenditaDiv";}
	else if(id==3){last="inserisciSchedaDiv";}
	else if(id==4){last="inserisciCustomDiv";}
	else if(id==5){last="aggiornaVenditaDiv";}
	else if(id==6){last="inserisciFotoDiv";}
	else if(id==7){last="visualizzaOrdiniDiv";}
	
	var d0=document.getElementById(last);
	var d1=document.getElementById("visualizzaVenditaDiv");
	var d2=document.getElementById("inserisciVenditaDiv");
	var d3=document.getElementById("inserisciSchedaDiv");
	var d4=document.getElementById("inserisciCustomDiv");
	var d5=document.getElementById("aggiornaVenditaDiv");
	var d6=document.getElementById("inserisciFotoDiv");
	var d7=document.getElementById("visualizzaOrdiniDiv");
	
	d1.style.display="none";
	d2.style.display="none";
	d3.style.display="none";
	d4.style.display="none";
	d5.style.display="none";
	d6.style.display="none";
	d7.style.display="none";
	d0.style.display="block";
}


