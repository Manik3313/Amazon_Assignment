package sample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import POJO.DeletePlaceId;
import POJO.PutPlaceId;
import io.restassured.path.json.JsonPath;
public class Test3 
{

public static void main(String[] args)
	{
	
	////////////////////////////////////////////////////get///////////////////////////////////////////////////
		
		System.out.println("In get");
		String response=given()
		.baseUri("https://reqres.in/")
		.header("Content-Type","application/json")  
		.when()
		.get("api/users?page=2")
		.then()
		.assertThat()
		.body("data[1].email", equalTo("lindsay.ferguson@reqres.in"))
		.body("data[1].id", equalTo(8))
		.statusCode(200)
		.extract()
		.asString()
		;
		JsonPath jsonPath=new JsonPath(response);
		System.out.println(jsonPath.getString("data[1].id"));
		
		
		///////////////////////////////////////////////////////////////////post//////////////////////////////////////////////
		System.out.println("In post");
		String response1=given()
		.baseUri("https://rahulshettyacademy.com")
		.header("Content-Type","application/json")	  
		.queryParam("key", "qaclick123")
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
		.post("/maps/api/place/add/json")
		.then()
		.extract()
		.asString();
		JsonPath jsonPath2=new JsonPath(response1);
		String place_id=jsonPath2.getString("place_id");
		System.out.println(jsonPath2.getString("place_id"));
		
	///////////////////////////////////////////////////get//////////////////////////////////////////////////////////////	
		
		System.out.println("In get");
		String response2=given()
						.baseUri("https://rahulshettyacademy.com")
						.header("Content-Type","application/json")
						.queryParam("key","qaclick123")
						.queryParam("place_id",place_id)
						.when()
						.get("/maps/api/place/get/json")
						.then()
						.extract()
						.asString();
		JsonPath jsonPath3=new JsonPath(response2);
		System.out.println(jsonPath3.getString("website"));
		
		
		/////////////////////////////////put///////////////////////////////////////////
		System.out.println("In put");
		PutPlaceId putPlaceId =new PutPlaceId();
		putPlaceId.setAddress("70 winter walk, USA");
		putPlaceId.setPlace_id(place_id);
		putPlaceId.setKey("qaclick123");
		
		String putResponse=given()
		.baseUri("https://rahulshettyacademy.com")
		.header("Content-Type","application/json")
		.queryParam("key","qaclick123")
		.queryParam("place_id",place_id)
		.when()
		.body(putPlaceId)
		.put("/maps/api/place/update/json")
		.then()
		//.log()
		//.all()
		.extract()
		.asString()
		
		;
		
		JsonPath putJsonResponse= new JsonPath(putResponse);
 
		
		
		
		
		  /////////////////////////////////////////////////////delete//////////////////
		  System.out.println("In Delete");
		  DeletePlaceId deletePlaceId=new DeletePlaceId();
		  deletePlaceId.setPlace_id(place_id);
		    
		  given() 
		   .baseUri("https://rahulshettyacademy.com")
    	  .header("Content-Type","application/json")
		  .queryParam("key","qaclick123")
		  .queryParam("place_id",place_id) .when()
		  .body("{\r\n"
		  		+ "    \"place_id\":\""+place_id+"\"   	 	\r\n"
		  		+ "}\r\n"
		  		+ "")
		  .post()
		  .then()
		  .assertThat()
		  .statusCode(200)
		  .assertThat()
		  .header("Connection", equalTo("Keep-Alive"));
		  
		 ////////////////////////////////////////////////get////////////////////////////////
		 System.out.println("In get");
		 String response5=given()
					.baseUri("https://rahulshettyacademy.com")
					.header("Content-Type","application/json")
					.queryParam("key","qaclick123")
					.queryParam("place_id",place_id)
					.when()
					.get("/maps/api/place/get/json")
					.then()
					//.log()
					//.all()
					.extract()
					.asString()
					;
	JsonPath jsonPath5=new JsonPath(response5);
	System.out.println(jsonPath5.getString("address"));
	
		
	
	
				
	}
}




