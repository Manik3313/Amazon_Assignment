package sample;

import org.testng.annotations.Test;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest2
{
private String place_id=null;
	@Test
	public void  Test1PostApi()
	{
		 	
		Headers response=given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("key","qaclick123")
		.when()
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.post(" /maps/api/place/add/json")
		.then()
		.extract()
		.headers();
		System.out.println(response.get("Content-Length")+"Manik");
		System.out.println(response.size());
		//.response()
		//.asString();
		System.out.println(response);
	
		//.log()
		//.all()
		//.extract()

		//.response()
		
		/*
		 * JsonPath jsonPath=new JsonPath(response);
		 * System.out.println(jsonPath.getString("Access-Control-Max-Age"));
		 * place_id=jsonPath.getString("Access-Control-Max-Age");
		 */ 
		 }

@Test
public void  Test2GetApi()
{
	given()
	.baseUri("https://rahulshettyacademy.com")
	.queryParam("key","qaclick123")
	.queryParam("place_id", place_id)
	.when()
	.get("/maps/api/place/get/json")
	.then()
	//.log()
	//.all()
	/*
	 * .assertThat() .body("name",equalTo("Frontline house"))
	 */;
}
}
