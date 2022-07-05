package project2;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class file {
	@Test(enabled = true,dependsOnMethods={"putManikanta"})
	public void getManikanta()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
		given()
		.get("user/Manikanta")
        .then()
		.statusCode(200).log().all();	
	}
	@SuppressWarnings("unchecked")
	@Test(enabled=true,dataProvider="postData")
	public void postMani(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.contentType(ContentType.JSON)
        .body(data)
		.when()
		.post("user")
		.then()
		.statusCode(200).log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="postData")
    public Object[][] providerPOST(){
		JSONObject j1 = new JSONObject();
        j1.put("username", "Mani");
        j1.put("firstName", "kanta");
        j1.put("lastName","mani");
        j1.put("email", "Mani@gmail.com");
        j1.put("password", "1234mani");
        j1.put("phone","0123456789");
        j1.put("userStatus","1");
        
        Object[][] postData = {
            {j1.toString()}
        };        
        return postData;
    }
	@Test(enabled=true,dataProvider="putData",dependsOnMethods={"postManikanta"})
	public void putManikanta(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.contentType(ContentType.JSON)
        .body(data)
		.when()
		.put("user/Manim")
		.then()
		.statusCode(200).log().all();
		
	}
	
	@SuppressWarnings("unchecked")
	@DataProvider(name="putData")
    public Object[][] providerPUT(){
		JSONObject j1 = new JSONObject();
        j1.put("username", "Mani");
        j1.put("firstName", "Mani");
        j1.put("lastName","kanta");
        j1.put("email", "Mani@gmail.com");
        j1.put("password", "1234mani");
        j1.put("phone","9876543210");
        j1.put("userStatus","1");
        
        Object[][] putData = {
            {j1.toString()}
        };        
        return putData;
	}  
	@Test(enabled=true,dataProvider="deleteData",dependsOnMethods={"putMani"})
	public void deleteMani(String data)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.delete("user/"+data)
		.then()
		.statusCode(200).log().all();
		
	}
	@DataProvider(name="deleteData")
    public Object[][] providerDELETE(){
        Object[][] deleteData = {{"Manim"}};
        return deleteData;
    }
	@Test(enabled=true,dataProvider="loginData",dependsOnMethods={"postMani"})
	public void loginMani(String username, String password)
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2/";
		
	
		given()
		.queryParam("username", username)
        .queryParam("password", password)
		.get("user/login")
		.then()
		.statusCode(200).log().all();
	}
    @DataProvider(name="loginData")
    public Object[][] providerLogin(){
        Object[][] loginData = {{"Manim", "1234mani"}};
        return loginData;
    }

}
