function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+ d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires;
}

function loginFunction()
{
	var email = document.getElementById("identifiant").value;
	var psw = document.getElementById("password").value;
	$.get("/WS_Project/v1/socialNetwork/login?email="+email+"&password="+psw, function(data){
		
		//console.log("yo");
		var token ="";
		
		for(var i = 0; i < data.length; i++)
			{
			token += data[i];
			}
		setCookie("id", token, 1);	
		document.location.href="index.html";
	
	})
	.error(function() { 
		alert("Wrong combination."); 
	});

}

