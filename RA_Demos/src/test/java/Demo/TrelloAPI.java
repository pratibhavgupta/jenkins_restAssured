package Demo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class TrelloAPI {

	//	We are going to test  all end-points of trello
	//	We create the base url of trello
	public static String baseUrl="https://trello.com/" ;
	public static String key="4d35a80badaad6362c69d75f010f6bd3";
	public static String token="ATTA411af6dd1b2ca6751014f018e1124a41d5c7d286ddec3faa1fc08af8828bbf9125115BFC";
	public static String id="";
	
	@Test(priority = 1)
	public void CreateABoard() {
		RestAssured.baseURI=baseUrl;
		
		Response responce= given().queryParam("name", "masai")
		.queryParam("key", key)
		.queryParam("token", token)
		.header("Content-Type","application/json")
		.when()
		.post("/1/boards")
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
//		convert responce into string
		String jsresponce=responce.asString();
		System.out.println(jsresponce);
		
//		convert into entire responce into json format
		JsonPath js=new JsonPath(jsresponce);
		
//		this is to fetch the specific object
		id=js.get("id");
		
		System.out.println(id);
		
	}
	
	
	@Test(priority=2)
	public void UpdateABoard() {
		RestAssured.baseURI=baseUrl;
		
		Response res= given().queryParam("key", key)
		.queryParam("token", token)
		.queryParams("desc", "new Description added")
		.header("Content-Type", "application/json")
		.when()
		.put("/1/boards/"+id)
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		System.out.println(res);
		
		String jsresponse=res.toString();
//		System.out.println(jsresponse);
		
		JsonPath js=new JsonPath(jsresponse);
		System.out.println(js.get("desc"));
		
	}
	
	@Test(priority=3)
	public void DeleteABoard() {
		RestAssured.baseURI=baseUrl;
		
		Response res= given().queryParam("key", key)
		.queryParam("token", token)
		.header("Content-Type","application/json")
		.when()
		.delete("/1/boards/"+id)
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		
		System.out.println(res.toString());
		
	}
	
}
