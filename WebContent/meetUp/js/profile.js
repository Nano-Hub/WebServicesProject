
function getUserData(id)
{
	 $.getJSON("/WS_Project/v1/socialNetwork/"+id, function(data){
			  document.getElementById("textEmail").value = data[0].email_address;
			  document.getElementById("textFirstname").value = data[0].firstname;
			  document.getElementById("textLastname").value = data[0].lastname;
			  document.getElementById("textBiography").value = data[0].biography;
});
}


function instantiateProfile()
{
	var id = getCookie("id");
	getUserData(id);
}

function changeFirstname()
{
	var firstname = document.getElementById("textFirstname").value;
	var id = getCookie("id");
	
	$.ajax({
	    type: "PUT",
	    url: "/WS_Project/v1/socialNetwork/"+id+"/changeFirstname?firstname="+firstname,
	});
	instantiate();
	

}
function changeLastname()
{

	var lastname = document.getElementById("textLastname").value;
	var id = getCookie("id");
		
	$.ajax({
	    type: "PUT",
	    url: "/WS_Project/v1/socialNetwork/"+id+"/changeLastname?lastname="+lastname,
	});
	instantiate();
}

function changeBiography()
{

	var biography = document.getElementById("textBiography").value;
	var id = getCookie("id");
	
	$.ajax({
	    type: "PUT",
	    url: "/WS_Project/v1/socialNetwork/"+id+"/changeBiography?biography="+biography,
	});
	instantiate();

}


function deleteUser()
{
	var id = getCookie("id");
	
	$.ajax({
	    type: "DELETE",
	    url: "/WS_Project/v1/socialNetwork/"+id+"/deleteUser",
	});	
	
	setCookie("groupId", "", 1);
	setCookie("id", "", 1);
	document.location.href="register.html";
}


