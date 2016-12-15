

function createGroup()
{
	var name = document.getElementById("textName").value;
	var desc = document.getElementById("textDescription").value;
	var id = getCookie("id");

	$.post("/WS_Project/v1/socialNetwork/"+id+"/groups/createGroup?name="+name+"&description="+desc, function(data){ });
}