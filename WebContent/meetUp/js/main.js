function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+ d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires;
}

function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length,c.length);
		}
	}
	return "";
}
function goGroupPage(id)
{
	setCookie("groupId", id, 1);
	document.location.href="group.html";
}
function goUserPage(id)
{
	setCookie("UserId", id, 1);
	document.location.href="user.html";
}

function instantiate(){
	var id = getCookie("id");
	if(id == "")
	{
		document.location.href="login.html";
	}
	else
	{
		getUser(id);
		getUserGroups(id);
		//$("#indexUser").html(id);
	}
}

function getUser(id)
{
	$.getJSON("/WS_Project/v1/socialNetwork/"+id, function(data){ 

		$("#indexUser").replaceWith("<p>id: "+data[0].id_user +"<br>"+data[0].firstname +" "+data[0].lastname+"<br> biography: "+data[0].biography +"<br></p>");
		setCookie("myId", data[0].id_user, 1);
	});
}

function getUserGroups(id)
{
	//method get groups
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/mygroups", function(data){ 

		/* loop through array */
		$.each(data, function(index, d)
				{ 
			$("#main-menu").append("<li> <a href=\"group.html\" onclick=\"goGroupPage("+d.id_group+")\"><i class=\"fa fa-sitemap\"></i>"+d.name +"</a>");		  	  
				});

	});
}

function getAllUsers()
{
	var id = getCookie("id");
	
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/users", function(data){ 

		document.getElementById("tableGroups").innerHTML = "";
		document.getElementById("tableUsers").innerHTML = "";
		document.getElementById("headUsers").innerHTML = "";
		document.getElementById("headGroups").innerHTML = "";
		
		$("#headUsers").append("<tr><td>Firstname</td><td>Lastname</td><td>Biography</td><td>Profile</td></tr>");
		$.each(data, function(index, d){         
			$("#tableUsers").append("<tr><td>"+d.firstname +"</td><td>"+d.lastname+"</td><td>"+d.biography+"</td><td><a onclick=\"goUserPage("+d.id_user+")\">Check</a></td></tr>");
		});		 
	});
}

function getAllGroups()
{
	var id = getCookie("id");
	
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/groups", function(data){ 

		document.getElementById("tableUsers").innerHTML = "";
		document.getElementById("tableGroups").innerHTML = "";
		document.getElementById("headGroups").innerHTML = "";
		document.getElementById("headUsers").innerHTML = "";
		
		$("#headGroups").append("<tr><td>Name</td><td>Description</td><td>User count</td><td>Join group</td></tr>");
		$.each(data, function(index, d){         
			$("#tableGroups").append("<tr><td>"+d.name +"</td><td>"+d.description+"</td><td>"+d.membership_count+"</td><td><a onclick=\"joinGroup("+d.id_group+")\">Join</a> </td></tr>");
		});		 
	});
}

function joinGroup(idGroup)
{
	var id = getCookie("id");


	$.post("/WS_Project/v1/socialNetwork/"+id+"/groups/"+idGroup+"/joinGroup", function(data){ 

	});

	goGroupPage(idGroup);
}