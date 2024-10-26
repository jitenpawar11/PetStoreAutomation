package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userpayload;
	
	public Logger logger;         //Here we created the variable
	
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userpayload = new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Logs--->here we initiate that Log variable by using log manager.
		
		logger = LogManager.getLogger(this.getClass());
	
	}
	
	//All authentication should be part of the endpoints package 
	//For Schema/Body validation we can create a separate test class under the test package 
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************ Creating User ***************");
		Response response =UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is Created ***************");
		
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("************ Reading user info ***************");
		
		Response response =UserEndPoints.readUser(this.userpayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		String set_email= response.jsonPath().get("email").toString();
		Assert.assertEquals(set_email,this.userpayload.getEmail());
		
		logger.info("************ User info is Displayed ***************");
		
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("************ Updating User ***************");
		
		//Updating user data using payload. This will again regenerate the first name, last name & email. But other details will not be change.
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response =UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is Updated ***************");
		
		//Checking data after update 
		Response response_afterupdate =UserEndPoints.readUser(this.userpayload.getUsername());
		
		response_afterupdate.then().log().body();
		Assert.assertEquals(response_afterupdate.getStatusCode(), 200);
		
		String update_lastname=response_afterupdate.jsonPath().get("lastName").toString();
		Assert.assertEquals(update_lastname, this.userpayload.getLastName());
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("************ Deleting the user ***************");
		
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is Deleted ***************");
	}
	
	
	

}
