package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
Here we are implementing the endpoints methods which will perform CRUD Operations.
---->It contains CURD method implementations.

UserEndPoints.java class is created to perform Create, Read, Update, Delete request to the user APIs.
Similarly we can create another end points java class to perform CRUD operation. (e.g pet, store)

*/

public class UserEndPoints {
	
	
	public static Response createUser(User payload)
	{
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		 .when()
		    .post(Routes.post_url);          //Referring the URL from routes class
		 
		return response;
	}
	
	
	public static Response readUser(String user_name)
	{
		Response response= given()
		  .accept(ContentType.JSON)
		  .pathParam("username", user_name)
		
		.when()
		   .get(Routes.get_url);
		
		return response;
	}
	
	
	public static Response updateUser(String user_name, User payload)
	{
		Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("username", user_name)
		    .body(payload)
		 .when()
		    .put(Routes.update_url);          //Referring the URL from routes class
		 
		return response;
	}
	
	public static Response deleteUser(String user_name)
	{
		Response response= given()
		  .accept(ContentType.JSON)
		  .pathParam("username", user_name)
		
		.when()
		   .delete(Routes.delete_url);
		
		return response;
	}
	
	
	

}
