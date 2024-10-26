package api.endpoints;

//In this class i will maintain only url's-- uniform resources locators.

/* 
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes {
	
	
	public static String base_url= "https://petstore.swagger.io/v2";
	
	//User Model request URL
	
	public static String post_url  =  base_url+"/user";
	public static String get_url  =  base_url+"/user/{username}";
	public static String update_url  =  base_url+"/user/{username}";
	public static String delete_url  =  base_url+"/user/{username}";
	
	
	
	//Suppose tomorrow i have to add more model then i can continue from here. 
	
	//Store Model request URL
	        //Here we can add store url's
	
	
	//Pet Model request URL
	        //Here we can add pet url's

}
