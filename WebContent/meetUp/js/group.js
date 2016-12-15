

function deleteGroup(){
var id = getCookie("id");
var group = getCookie("groupId");

 $.ajax({
	    type: "DELETE",
	    url: "/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/deleteGroup",
	 });
	document.location.href="index.html";
}

function changeDescription(){
var id = getCookie("id");
var group = getCookie("groupId");
var description = document.getElementById('textGroupDescription').value;

$.ajax({
    type: "PUT",
    url: "/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/changeDescription?description="+description,
 });
}

function leaveGroup(){
	var id = getCookie("id");
	var group = getCookie("groupId");
	
	 $.ajax({
		    type: "DELETE",
		    url: "/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/leaveGroup",
		 });

		document.location.href="index.html";
	}




function instantiateGroup(){
	var id = getCookie("id");
	var group = getCookie("groupId");

	if(id == "")
	{
		document.location.href="login.html";
	}
	else
	{
		getMembersFrom(id, group);
		getGroupInfos(id, group);
		getGroupComments();


	}
}

function getMembersFrom(id, group){
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/members", function(data){ 
		// var html = [];

		/* loop through array */
		$.each(data, function(index, d){         
			$("#tableBody").append("<tr><td>"+d.firstname +"</td><td>"+d.lastname+"</td><td>"+d.biography+"</td></tr>");
		});


	});
}

function getGroupInfos(id,group){
	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/groups/"+group, function(data){ 
		// var html = [];

		/* loop through array */

		document.getElementById('textGroupName').innerHTML = data[0].name;
		document.getElementById('textGroupDescription').placeholder = data[0].description;

		if(data[0].admin == getCookie("myId"))
		{
			document.getElementById("buttonGroupDelete").disabled = false;
			document.getElementById("buttonGroupDescription").disabled = false;
			document.getElementById("buttonLeave").disabled = true;
		}

	});
}


function getGroupComments(){
	var id = getCookie("id");

	var group = getCookie("groupId");

	$.getJSON("/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/comments", function(data){ 
		// var html = [];

		/* loop through array */

		document.getElementById("chatMain").innerHTML = "";

		$.each(data, function(index, d){
			if(typeof d.firstname == "undefined" && typeof d.lastname == "undefined")
				{
				d.firstname = "User";
				d.lastname = "deleted";
				}
			if(index %2 ==0)
			{
				$("#chatMain").append("<div class=\"chat-widget-left\">"+d.comment+"</div><div class=\"chat-widget-name-left\"><h4>"+d.firstname +" " + d.lastname+"</h4></div>");
			}
			else
			{
				$("#chatMain").append("<div class=\"chat-widget-right\">"+d.comment+"</div><div class=\"chat-widget-name-right\"><h4>"+d.firstname +" " + d.lastname+"</h4></div>");

			}
		});


	});

	setTimeout(getGroupComments, 1000);
}


function addComment(){
	var id = getCookie("id");
	var group = getCookie("groupId");
	var comment = document.getElementById("inputComment").value;

	$.post("/WS_Project/v1/socialNetwork/"+id+"/groups/"+group+"/postComment?comment="+comment, function(data){ });

	//update
	//getGroupComments(group);

}



