package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
Here we are implementing the endpoints methods which will perform CRUD Operations.
---->It contains CURD method implementations.

UserEndPoints.java class is created to perform Create, Read, Update, Delete request to the user APIs.
Similarly we can create another end points java class to perform CRUD operation. (e.g pet, store)

*/

public class UserEndPoints2 {
	
	//This will load properties file-->In java we have a special class called as resources bundle 
	//by using this class we will be able to load the properties file and able to read the data from the properties file.
	
	
	
	//Method Created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle all_url = ResourceBundle.getBundle("routes"); //Load the properties file.
		return all_url;
	}
	
	
	public static Response createUser(User payload)
	{
		String post_url= getURL().getString("post_url");     //This statement will get the actual URL from the properties file.
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		 .when()
		    .post(post_url);          
		 
		return response;
	}
	
	
	public static Response readUser(String user_name)
	{
		String get_url= getURL().getString("get_url");
		
		Response response= given()
		  .accept(ContentType.JSON)
		  .pathParam("username", user_name)
		
		.when()
		   .get(get_url);
		
		return response;
	}
	
	
	public static Response updateUser(String user_name, User payload)
	{
		String update_url= getURL().getString("update_url");
		
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("username", user_name)
		    .body(payload)
		 .when()
		    .put(update_url);          
		 
		return response;
	}
	
	public static Response deleteUser(String user_name)
	{
		String delete_url= getURL().getString("delete_url");
		
		Response response= given()
		  .accept(ContentType.JSON)
		  .pathParam("username", user_name)
		
		.when()
		   .delete(delete_url);
		
		return response;
	}
	
	
	

}
