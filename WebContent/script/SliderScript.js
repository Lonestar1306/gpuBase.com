var slideIndex = 0;

function showSlides() {

  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  
  //1)NASCONDE TUTTE LE SLIDE MOSTRATE PRECEDENTEMENTE
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  
  //2)PASSA ALLA PROSSIMA SLIDE DA MOSTRARE
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  
  //3)MOSTRA LA SLIDE ATTUALE
  slides[slideIndex-1].style.display = "block";  
  
  //4)RIAVVIA QUESTA FUNZIONE (showSlides) DOPO AVER ASPETTATO PER TOT SECONDI (5000),
  setTimeout(showSlides, 5000); 
}
