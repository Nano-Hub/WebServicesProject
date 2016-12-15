function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires;
}

function registerFunction()
{
	var email = document.getElementById("email").value;
	var firstname = document.getElementById("firstname").value;
	var lastname = document.getElementById("lastname").value;
	var password = document.getElementById("password").value;

	$.post("/WS_Project/v1/socialNetwork/createUser?email="+email+"&password="+password+"&firstname="+firstname+"&lastname="+lastname, function(data){
		var token ="";
		
		for(var i = 0; i < data.length; i++)
			{
			token += data[i];
			}
		setCookie("id", token, 1);	
		document.location.href="index.html";
	});
	//setCookie("psw", psw, 1);
	//document.location.href="index.html";
}

