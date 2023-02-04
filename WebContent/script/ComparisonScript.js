function compareDetails(){
	
	var max=document.getElementById("compSize").innerHTML;


	var dataUscitaBest=document.getElementById(0+"dataUscita").innerHTML;
	var prezzoBest=document.getElementById(0+"prezzo").innerHTML;
	var risoluzionMaxBest=document.getElementById(0+"risoluzionMax").innerHTML;
	var tdpBest=document.getElementById(0+"tdp").innerHTML;
	var numeroCoreBest=document.getElementById(0+"numeroCore").innerHTML;
	var frequenzaCoreBest=document.getElementById(0+"frequenzaCore").innerHTML;
	var frequenzaMemoiaBest=document.getElementById(0+"frequenzaMemoia").innerHTML;
	var dimensioneMemoriaBest=document.getElementById(0+"dimensioneMemoria").innerHTML;
	
	
	for(var i=1;i<max;i++){
		
		
		if(document.getElementById(i+"dataUscita").innerHTML>dataUscitaBest){
			dataUscitaBest=document.getElementById(i+"dataUscita").innerHTML;
		}
		
		if(document.getElementById(i+"prezzo").innerHTML<prezzoBest){
			prezzoBest=document.getElementById(i+"prezzo").innerHTML;
		}
		
		if(document.getElementById(i+"risoluzionMax").innerHTML>risoluzionMaxBest){
			risoluzionMaxBest=document.getElementById(i+"risoluzionMax").innerHTML;
		}
		
		if(document.getElementById(i+"tdp").innerHTML<tdpBest){
			tdpBest=document.getElementById(i+"tdp").innerHTML;
		}
		
		if(document.getElementById(i+"numeroCore").innerHTML>numeroCoreBest){
			numeroCoreBest=document.getElementById(i+"numeroCore").innerHTML;
		}
		
		if(document.getElementById(i+"frequenzaCore").innerHTML>frequenzaCoreBest){
			frequenzaCoreBest=document.getElementById(i+"frequenzaCore").innerHTML;
		}
		
		if(document.getElementById(i+"frequenzaMemoia").innerHTML>frequenzaMemoiaBest){
			frequenzaMemoiaBest=document.getElementById(i+"frequenzaMemoia").innerHTML;
		}
		
		if(document.getElementById(i+"dimensioneMemoria").innerHTML<dimensioneMemoriaBest){
			dimensioneMemoriaBest=document.getElementById(i+"dimensioneMemoria").innerHTML;
		}	
		
	}
	

	
	
	for(var i=0;i<max;i++){
		
		
		if(document.getElementById(i+"dataUscita").innerHTML=dataUscitaBest){
			document.getElementById(i+"dataUscita").style.color="#1BD1A0";
		
	
		}
		
		if(document.getElementById(i+"prezzo").innerHTML==prezzoBest){
			document.getElementById(i+"prezzo").style.color="#1BD1A0";
		

		}
		
		if(document.getElementById(i+"risoluzionMax").innerHTML==risoluzionMaxBest){
			document.getElementById(i+"risoluzionMax").style.color="#1BD1A0";
		

		}
		
		if(document.getElementById(i+"tdp").innerHTML==tdpBest){
			document.getElementById(i+"tdp").style.color="#1BD1A0";
		

		}
		
		if(document.getElementById(i+"numeroCore").innerHTML==numeroCoreBest){
			document.getElementById(i+"numeroCore").style.color="#1BD1A0";
			

		}
		
		if(document.getElementById(i+"frequenzaCore").innerHTML==frequenzaCoreBest){
			document.getElementById(i+"frequenzaCore").style.color="#1BD1A0";
		

		}
		
		if(document.getElementById(i+"frequenzaMemoia").innerHTML==frequenzaMemoiaBest){
			document.getElementById(i+"frequenzaMemoia").style.color="#1BD1A0";
			

		}
		
		if(document.getElementById(i+"dimensioneMemoria").innerHTML==dimensioneMemoriaBest){
			document.getElementById(i+"dimensioneMemoria").style.color="#1BD1A0";
		
		}	
	}
	
	
}