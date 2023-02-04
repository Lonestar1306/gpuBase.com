
$(document).ready(function (){
	
		$(".closebtn").click(function(){
			$("#expandedImgDiv").fadeOut(function(){
				$("#expandedImg").attr("src","");
				$(".onePhoto").fadeIn();
				$("#headerMainDiv").show();
			});
			
			
		});
	
		$("#onePhoto1").click(function(){
			$("#expandedImgDiv").hide();
			$("#expandedImg").attr("src",$("#onePhoto1").attr("src"));
			$(".onePhoto").fadeOut(1,function(){
				$("#headerMainDiv").hide();
				$("#expandedImgDiv").delay(150).fadeIn(1000);
			});
			
		  });
		
		
		
		$("#onePhoto2").click(function(){
			$("#expandedImgDiv").hide();
			$("#expandedImg").attr("src",$("#onePhoto2").attr("src"));
			$("#expandedImgDiv").fadeIn(1000);
			$(".onePhoto").fadeOut(1,function(){
				$("#headerMainDiv").hide();
				$("#expandedImgDiv").delay(100).fadeIn(1000);
			});
			
		  });
		
		
		$("#onePhoto3").click(function(){
			$("#expandedImgDiv").hide();
			$("#expandedImg").attr("src",$("#onePhoto3").attr("src"));
			$("#expandedImgDiv").fadeIn(1000);
			$(".onePhoto").fadeOut(1,function(){
				$("#headerMainDiv").hide();
				$("#expandedImgDiv").delay(50).fadeIn(1000);
			});
			
		  });
		
		
		$("#onePhoto4").click(function(){
			$("#expandedImgDiv").hide();
			$("#expandedImg").attr("src",$("#onePhoto4").attr("src"));
			$("#expandedImgDiv").fadeIn(1000);
			$(".onePhoto").fadeOut(1,function(){
				$("#headerMainDiv").hide();
				$("#expandedImgDiv").fadeIn(1000);
			});
			
		  });
		
});


function hidePhoto(img){
	img.style.display="none";
}
