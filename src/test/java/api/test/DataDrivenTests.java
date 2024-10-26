package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilites.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	User Userpayload;
	
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String LName, String email, String pwd, String phone)
	{
		Userpayload =new User();
		
		Userpayload.setId(Integer.parseInt(userID));
		Userpayload.setUsername(userName);
		Userpayload.setFirstName(fName);
		Userpayload.setLastName(LName);
		Userpayload.setEmail(email);
		Userpayload.setPassword(pwd);
		Userpayload.setPhone(phone);
		
		Response response= UserEndPoints.createUser(Userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
