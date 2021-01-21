package sample;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.groovy.control.customizers.builder.PostCompletionFactory;
import org.springframework.ui.context.Theme;
import org.testng.annotations.Test;

import POJO.Location;
//import POJO.Location;
import POJO.get_PlaceId;
import POJO.object;
import POJO.object_get;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
public class Test_1 {

	@Test(groups = {"d"})
	
	public void test() throws InvalidTokenException, AgentConnectException, IOException, ObsoleteVersionException
	{
		/*
		 * ChromeDriver chromeDriver=new ChromeDriver();
		 * chromeDriver.report().step("dghdh"); RemoteWebDriver driver =chromeDriver;
		 * if(driver instanceof ChromeDriver){ ((ChromeDriver)
		 * driver).report().step(" found", true); }
		 */
		ArrayList<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		Location location=new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		 object obj=new object(); 
		 obj.setAccuracy(50);
		 obj.setAddress("29, side layout, cohen 09"); 
		 obj.setLanguage("French-IN");
		 obj.setName("Frontline house"); 
		 obj.setPhone_number("(+91) 983 893 3937");
		 obj.setWebsite("http://google.com");
		 obj.setLocation(location);
		 obj.setTypes(types);
		 	
		System.out.println("Test1");
		
	////////////////////////////////////////////////////Add//////////////////////////////////////////////////////
		get_PlaceId response=given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("key" , "qaclick123")
		.when()
				.body(obj/*
						 * "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" +
						 * "    \"lng\": 33.427362\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n" +
						 * "  \"name\": \"Frontline house\",\r\n" +
						 * "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
						 * "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" +
						 * "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n" +
						 * "  \"website\": \"http://google.com\",\r\n" +
						 * "  \"language\": \"French-IN\"\r\n" + "}\r\n" + " \r\n" + ""
						 */)
		.post("/maps/api/place/add/json")
		.then()
		.statusCode(200)
		//.log().all()
		.extract().as(get_PlaceId.class);
		/*
		 * JsonPath json=new JsonPath(response); String
		 * place_id=json.getString("place_id");
		 * System.out.println(json.getString("place_id"));
		 */
		System.out.println("Placeid= "+response.getPlace_id());
		
		System.out.println("In get");
		
			
		///////////////////////////////////////////////////////////get///////////////////////////////////////////////////////
		object_get obj1=
					given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("key", "qaclick123")
		.queryParam("place_id", response.getPlace_id())
		.expect().defaultParser(Parser.JSON)
		.when()
		.get(" /maps/api/place/get/json")
		.then()
		.statusCode(200)
		//.log()
		//.all()
		.extract().as(object_get.class)
		;
			System.out.println(obj1.getTypes());
			//System.out.println(obj1.getLocation().getLat());
			/*JsonPath jsonPath=new JsonPath(obj1);
			System.out.println(jsonPath.getString("location.latitude"
					+ ""));*/
		//System.out.println(obj1.getAccuracy());
			
		//////////////////////////////delete/////////////////////////////////////////////////
			String placeid=response.getPlace_id();
			given()
			.baseUri("https://rahulshettyacademy.com")
			.queryParam("place_id", placeid)
			.when()
					/*
					 * .body("{\r\n" + "    \"place_id\": \\\"\" + placeid + \"\\\"   	 	\r\n" +
					 * "}\r\n" + "")
					 */.post("/maps/api/place/delete/json")
			.then()
			.statusCode(200)
			.log()
			.all();
			//////////////////////////////////////////retrive//////////////
			object_get obj2=
					given()
		.baseUri("https://rahulshettyacademy.com")
		.queryParam("key", "qaclick123")
		.queryParam("place_id", response.getPlace_id())
		.expect().defaultParser(Parser.JSON)
		.when()
		.get(" /maps/api/place/get/json")
		.then()
		.statusCode(200)
		//.log()
		//.all()
		.extract().as(object_get.class)
		;
			System.out.println("Manik"+obj2.getTypes());
		
			
			
			
	}
}


