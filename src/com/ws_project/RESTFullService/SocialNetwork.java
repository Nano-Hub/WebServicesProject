package com.ws_project.RESTFullService;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.rest.DB.DBClass;
import com.rest.util.ToJSON;

@Path("socialNetwork")
public class SocialNetwork {

	String version ="1.0";

	@GET
	@Path("/version")
	public String version()
	{
		return "The current version is " + version;
	}

	@GET
	@Path("/{token}/users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(@PathParam("token") String token) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT * FROM users;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}

	}


	@GET
	@Path("/{token}/groups/{idGroup}")//getGroupInfos
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupInfos(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT * FROM groups WHERE id_group = " + idGroup + ";";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/name")//getGroupInfos
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupInfosName(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT name FROM groups WHERE id_group = " + idGroup + ";";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}

	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/description")//getGroupInfos
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupInfosDescription(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT description FROM groups WHERE id_group = " + idGroup + ";";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}

	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/admin")//getGroupInfos
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGroupInfosAdmin(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT admin FROM groups WHERE id_group = " + idGroup + ";";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}

	}

	@GET
	@Path("/{token}/groups/{idGroup}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComments(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT discussion.id_comment, discussion.timePosted, discussion.comment, users.firstname, users.lastname FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();		
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsInfos(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT discussion.comment, users.firstname, users.lastname FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();			
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}/idUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsUser(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.id_user FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}/firstname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsUserFirstname(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.firstname FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}/lastname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsUserLastname(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.lastname FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}/text")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsText(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT discussion.comment FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/groups/{idGroup}/comments/{idComment}/time")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCommentsTime(@PathParam("token") String token, @PathParam("idGroup") int idGroup, @PathParam("idComment") int idComment) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT discussion.timePosted FROM discussion LEFT JOIN users on discussion.id_user = users.id_user WHERE id_group = " + idGroup + " AND id_comment = "+idComment+" ORDER BY discussion.timePosted DESC;";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@GET
	@Path("{token}/groups/{idGroup}/members")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMembersFrom(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT * FROM members WHERE id_user= " + idUser + " AND id_group=  "+ idGroup + ";";
	
			Connection con = DBClass.returnConnection();
	
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
	
			//return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
			if(!rs.next())
			{
				con.close();
				return Response.status(400).entity("You do not belong to this group.").build();
			}
			else
			{
				request = "SELECT users.id_user, users.firstname, users.lastname, users.biography FROM members LEFT JOIN users ON users.id_user = members.id_user WHERE id_group=  "+ idGroup + ";";
				ps = con.prepareStatement(request);
				rs = ps.executeQuery(request);
				con.close();
				return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@GET
	@Path("{token}/groups")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllGroups(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT groups.id_group, groups.name, groups.description, count(id_user) as membership_count FROM groups LEFT JOIN members on members.id_group = groups.id_group GROUP BY members.id_group;";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("{token}/mygroups")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMyGroups(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT groups.id_group, groups.name, groups.description, count(id_user) as membership_count FROM groups LEFT JOIN members on members.id_group = groups.id_group  WHERE members.id_user="+idUser+" GROUP BY members.id_group;";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@GET
	@Path("/{token}/users/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkProfile(@PathParam("token") String token, @PathParam("idUser") int idUser) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.firstname, users.lastname, users.biography, count(id_group) as group_count  FROM users LEFT JOIN members on members.id_user = users.id_user WHERE users.id_user="+idUser+";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/users/{idUser}/firstname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkProfileFirstname(@PathParam("token") String token, @PathParam("idUser") int idUser) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.firstname FROM users WHERE users.id_user="+idUser+";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/users/{idUser}/lastname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkProfileLastname(@PathParam("token") String token, @PathParam("idUser") int idUser) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.lastname FROM users WHERE users.id_user="+idUser+";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/users/{idUser}/biography")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkProfileBiography(@PathParam("token") String token, @PathParam("idUser") int idUser) throws Exception
	{
		if(isTokenInDatabase(token) != -1)
		{
			String request = "SELECT users.biography FROM users WHERE users.id_user="+idUser+";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	
	@GET
	@Path("/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT * FROM users WHERE id_user = " + idUser + ";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/firstname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserFirstname(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT users.firstname FROM users WHERE id_user = " + idUser + ";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/lastname")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserLastname(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT users.lastname FROM users WHERE id_user = " + idUser + ";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	@GET
	@Path("/{token}/biography")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserBiography(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT users.biography FROM users WHERE id_user = " + idUser + ";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/email")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserEmail(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			String request = "SELECT users.email_address FROM users WHERE id_user = " + idUser + ";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);	
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}
	
	@GET
	@Path("/{token}/idUser")
	public Response getUserId(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			return Response.status(400).entity(idUser).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@POST
	@Path("/{token}/groups/{idGroup}/joinGroup")
	public Response joinGroup(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{		
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(groupExist(idGroup))
			{
				if(userExist(idUser))
				{		
					if(!isUserInGroup(idUser, idGroup))
					{
						String request = "INSERT INTO members(id_group,id_user)  VALUES (" + idGroup + "," + idUser + ");";
						Connection con = DBClass.returnConnection();
						PreparedStatement ps = con.prepareStatement(request);
						ps.executeUpdate(request);	
						con.close();
						return Response.status(201).entity("{OK}").build();
					}
					else
					{
						return Response.status(400).entity("You already belong to this group.").build();
					}
				}
				else
				{
					return Response.status(400).entity("User doesn't exist.").build();
				}
			}
			else
			{
				return Response.status(400).entity("Group doesn't exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@DELETE
	@Path("/{token}/groups/{idGroup}/leaveGroup")
	public Response leaveGroup(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(groupExist(idGroup))
			{
				if(userExist(idUser))
				{
					if(isUserInGroup(idUser, idGroup))
					{
						if(!isAdminFromGroup(idUser, idGroup))
						{
							String request = "DELETE FROM members WHERE id_user = " + idUser + " AND id_group=" + idGroup + ";";
							Connection con = DBClass.returnConnection();
							PreparedStatement ps = con.prepareStatement(request);
							ps.executeUpdate(request);	
							con.close();
							return Response.status(200).entity("OK").build();
						}
						else
						{
							return Response.status(400).entity("User is admin of the group but delete available!").build();
						}
					}
					else
					{
						return Response.status(400).entity("User not in this group;").build();
					}
				}
				else
				{
					return Response.status(400).entity("User doesn't exist.").build();
				}	
			}
			else
			{
				return Response.status(400).entity("Group doesn't exist.").build();
			}	
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}


	@DELETE
	@Path("/{token}/deleteUser")
	public Response deleteUser(@PathParam("token") String token) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				int usersBefore = 0;
				int usersAfter = 0;
				String request = "SELECT count(id_user) as counted from users;";
				Connection con = DBClass.returnConnection();
				PreparedStatement ps = con.prepareStatement(request);
				ResultSet rs = ps.executeQuery(request);
				rs.next();
				usersBefore = rs.getInt("counted");
	
				request = "DELETE FROM users WHERE id_user = " + idUser + ";";
				ps = con.prepareStatement(request);
				/*rs =*/ ps.executeUpdate(request);	
	
	
				request = "SELECT count(id_user) as counted from users;";
				ps = con.prepareStatement(request);
				rs = ps.executeQuery(request);
				rs.next();
				usersAfter = rs.getInt("counted");
				con.close();
	
				if (usersAfter == usersBefore)
					return Response.status(400).entity("Deletion error").build();
				else
					return Response.status(200).entity("Deletion complete").build();
			}
			else
			{
				return Response.status(400).entity("User doesn't exist").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}

	}

	
	@GET
	@Path("/login")
	public Response login(@QueryParam("email") String email, @QueryParam("password") String password) throws Exception
	{
		String request = "SELECT token FROM users WHERE email_address=\""+email+"\" AND password =\""+password+"\";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();
		if(rs.next())
		{
			String token = rs.getString("token");
			System.out.println(token);
			return Response.status(200).entity(token).build();
		}
		else
		{
			return Response.status(400).entity("Connection failed.").build();
		}
	}
	
	@POST
	@Path("/createUser")
	public Response createUser(@QueryParam("email") String email, @QueryParam("password") String password, @QueryParam("firstname") String firstname,
			@QueryParam("lastname") String lastname,@QueryParam("biography") String biography) throws Exception
	{
		String request = "SELECT email_address FROM users WHERE email_address=\""+email+"\";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		if(!rs.next())
		{
			String tokenGenerate = "";
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for(int i = 0; i< 4; i++)
			{
				int nbAlea = (int)(Math.random()*(25));
				tokenGenerate += characters.charAt(nbAlea);
			}
				    		    
			request = "INSERT INTO users(email_address,firstname,lastname,biography, password, token)  VALUES (\"" + email + "\",\"" + firstname + "\",\"" + lastname + "\",\"" + biography + "\",\"" + password + "\",\"" + tokenGenerate + "\");";
			ps = con.prepareStatement(request);
			ps.executeUpdate(request);	
			con.close();
			return Response.status(201).entity(tokenGenerate).build();
		}
		else
		{
			con.close();
			return Response.status(400).entity("Email already in use.").build();
		}
	}

	@POST
	@Path("/{token}/groups/createGroup")
	public Response createGroup(@PathParam("token") String token, @QueryParam("name") String name,@QueryParam("description") String description) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				String request = "SELECT id_group FROM groups WHERE name=\""+name+"\";";
				Connection con = DBClass.returnConnection();
				PreparedStatement ps = con.prepareStatement(request);
				ResultSet rs = ps.executeQuery(request);
				if(!rs.next())
				{
					request = "INSERT INTO groups(name,description,admin)  VALUES (\"" + name + "\",\"" + description + "\"," + idUser +  ");";
					ps = con.prepareStatement(request);
					ps.executeUpdate(request);	
	
					request = "SELECT id_group FROM groups WHERE name=\""+name+"\";";
					ps = con.prepareStatement(request);
					rs = ps.executeQuery(request);
					rs.next();
					int id = rs.getInt("id_group");
	
					request = "INSERT INTO members(id_group,id_user)  VALUES (" + id + "," + idUser + ");";
					ps = con.prepareStatement(request);
					ps.executeUpdate(request);	
	
					con.close();
					return Response.status(201).entity(id).build();
				}
				else
				{
					con.close();
					return Response.status(400).entity("Name already in use.").build();
				}
			}
			else
			{
				return Response.status(400).entity("Admin does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@DELETE
	@Path("/{token}/groups/{idGroup}/deleteGroup")
	public Response deleteGroup(@PathParam("token") String token, @PathParam("idGroup") int idGroup) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				if(groupExist(idGroup))
				{
					if(isAdminFromGroup(idUser, idGroup))
					{
						String request = "DELETE FROM groups WHERE id_group = " + idGroup + ";";
						Connection con = DBClass.returnConnection();
						PreparedStatement ps = con.prepareStatement(request);
						ps.executeUpdate(request);	
						con.close();
						return Response.status(200).entity("Group deleted.").build();
					}
					else
					{
						return Response.status(400).entity("User is not the admin of the group.").build();
					}
				}
				else
				{
					return Response.status(400).entity("Group does not exist.").build();
				}
			}
			else
			{
				return Response.status(400).entity("Admin does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@PUT
	@Path("/{token}/groups/{idGroup}/changeDescription")
	public Response changeDescription(@PathParam("token") String token, @PathParam("idGroup") int idGroup,@QueryParam("description") String description) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				if(groupExist(idGroup))
				{
					if(isAdminFromGroup(idUser, idGroup))
					{
						String request = "UPDATE groups SET description = \"" + description + "\" WHERE id_group = " + idGroup +";";
						Connection con = DBClass.returnConnection();
						PreparedStatement ps = con.prepareStatement(request);
						ps.executeUpdate(request);	
						con.close();
						return Response.status(200).entity("Description updated.").build();
					}
					else
					{
						return Response.status(400).entity("User is not the admin of the group.").build();
					}
				}
				else
				{
					return Response.status(400).entity("Group does not exist.").build();
				}
			}
			else
			{
				return Response.status(400).entity("Admin does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@POST
	@Path("/{token}/groups/{idGroup}/postComment")
	public Response comment(@PathParam("token") String token,@PathParam("idGroup") int idGroup,@QueryParam("comment") String comment) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(comment == null)
				return Response.status(400).entity("Comment empty.").build();
	
			if(userExist(idUser))
			{
				if(groupExist(idGroup))
				{
					if(isUserInGroup(idUser, idGroup))
					{
						String request = "INSERT INTO discussion(id_user, id_group, comment)  VALUES (" + idUser + "," + idGroup + ",\"" + comment +  "\");";
						Connection con = DBClass.returnConnection();
						PreparedStatement ps = con.prepareStatement(request);
						ps.executeUpdate(request);	
						con.close();
						return Response.status(201).entity("Comment posted.").build();
					}
					else
					{
						return Response.status(400).entity("User is not in the group.").build();
					}
				}
				else
				{
					return Response.status(400).entity("Group does not exist.").build();
				}
			}
			else
			{
				return Response.status(400).entity("User does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@PUT
	@Path("{token}/changeFirstname")
	public Response changeFirstname(@PathParam("token") String token, @QueryParam("firstname") String firstname) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				String request = "UPDATE users SET firstname = \"" + firstname + "\" WHERE id_user = " + idUser +";";
				Connection con = DBClass.returnConnection();
				PreparedStatement ps = con.prepareStatement(request);
				ps.executeUpdate(request);	
				con.close();
				return Response.status(200).entity("Firstname updated.").build();
			}
			else
			{
				return Response.status(400).entity("User does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@PUT
	@Path("{token}/changeLastname")
	public Response changeLastname(@PathParam("token") String token, @QueryParam("lastname") String lastname) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				String request = "UPDATE users SET lastname = \"" + lastname + "\"  WHERE id_user = " + idUser +";";
				Connection con = DBClass.returnConnection();
				PreparedStatement ps = con.prepareStatement(request);
				ps.executeUpdate(request);	
				con.close();
				return Response.status(200).entity("Lastname updated.").build();
			}
			else
			{
				return Response.status(400).entity("User does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@PUT
	@Path("{token}/changeBiography")
	public Response changeBiography(@PathParam("token") String token, @QueryParam("biography") String biography) throws Exception
	{
		int idUser = isTokenInDatabase(token);
		if(idUser != -1)
		{
			if(userExist(idUser))
			{
				String request = "UPDATE users SET biography = \"" + biography + "\" WHERE id_user = " + idUser +";";
				Connection con = DBClass.returnConnection();
				PreparedStatement ps = con.prepareStatement(request);
				ps.executeUpdate(request);	
				con.close();
				return Response.status(200).entity("Biography updated.").build();
			}
			else
			{
				return Response.status(400).entity("User does not exist.").build();
			}
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	@GET
	@Path("{token}/users/{idUser}/groups")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserGroups(@PathParam("token") String token, @PathParam("idUser") int idUser) throws Exception
	{
		if( isTokenInDatabase(token) != -1)
		{
			String request = "SELECT groups.name, members.id_group FROM members LEFT JOIN groups ON groups.id_group= members.id_group WHERE members.id_user="+idUser+";";
			Connection con = DBClass.returnConnection();
			PreparedStatement ps = con.prepareStatement(request);
			ResultSet rs = ps.executeQuery(request);
			con.close();
			return Response.status(200).entity(new ToJSON().toJSONArray(rs).toString()).build();
		}
		else
		{
			return Response.status(400).entity("Wrong token").build();
		}
	}

	public boolean groupExist(int idGroup) throws SQLException
	{
		String request = "SELECT * FROM groups WHERE id_group=  "+ idGroup + ";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();

		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean userExist(int idUser) throws SQLException
	{
		String request = "SELECT * FROM users WHERE id_user=  "+ idUser + ";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();

		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isUserInGroup(int idUser, int idGroup) throws SQLException
	{
		String request = "SELECT id_user FROM members WHERE id_user= " + idUser + " AND id_group=  "+ idGroup + ";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean isAdminFromGroup(int idUser, int idGroup) throws SQLException
	{
		String request = "SELECT id_group FROM groups WHERE admin= " + idUser + " AND id_group=  "+ idGroup + ";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int isTokenInDatabase(String token) throws SQLException
	{
		String request = "SELECT id_user FROM users WHERE token=\"" + token +"\";";
		Connection con = DBClass.returnConnection();
		PreparedStatement ps = con.prepareStatement(request);
		ResultSet rs = ps.executeQuery(request);
		con.close();
		
		if(rs.next())
		{
			return rs.getInt("id_user");
		}
		else
		{
			return -1;
		}
	}


}
