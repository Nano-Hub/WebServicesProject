function instantiateUser(){
	var id = getCookie("id");
	var user = getCookie("UserId");

	if(id == "")
	{
		document.location.href="login.html";
	}
	else
	{
		checkProfile(id, user);
	}
}

function checkProfile(id, user){
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/users/"+user, function(data){ 

		$.each(data, function(index, d){         
			$("#tableUserBody").append("<tr><td>"+d.firstname +"</td><td>"+d.lastname+"</td><td>"+d.biography+"</td><td>"+d.group_count+"</td></tr>");
		});


	});
}
