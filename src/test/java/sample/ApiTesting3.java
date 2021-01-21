package sample;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import POJO.Location;
import POJO.object;
import helpers.dataProviderUtils;
import io.restassured.path.json.JsonPath;

public class ApiTesting3 {
	@Test(dataProviderClass = dataProviderUtils.class, dataProvider = "testdata", description = "\\src\\main\\resources\\DataApi.xlsx,Sheet1")
	public void apiTest(Map<String, String> mapdata) {
		ArrayList<String> types = new ArrayList<String>();
		Location location = new Location();
		object obj = new object();

		if (mapdata.get("Request Type").equalsIgnoreCase("Post_Location")) {
			float f = Float.parseFloat(mapdata.get("Accuracy"));
			int accuracy = (int) f;
			// System.out.println(accuracy);
			location.setLat(Double.parseDouble(mapdata.get("Lat")));
			location.setLng(Double.parseDouble(mapdata.get("Lng")));
			obj.setAccuracy(accuracy);
			obj.setAddress(mapdata.get("Address"));
			obj.setLanguage(mapdata.get("Language"));
			obj.setName(mapdata.get("Name"));
			obj.setPhone_number(mapdata.get("Phone_Number"));
			obj.setWebsite(mapdata.get("Website"));
			obj.setLocation(location);
			obj.setTypes(types);

			String response = given().baseUri("https://rahulshettyacademy.com").queryParam("key", "qaclick123").when()
					.body(obj).post("/maps/api/place/add/json").then()
					// .log()
					// .all()
					.extract().response().asString();
			JsonPath jsonPath = new JsonPath(response);
			System.out.println(jsonPath.getString("place_id"));
		}

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * data.get("Userdata"); // if
		 * (data.get("Request Type").equalsIgnoreCase("Post_Location")) { //
		 * System.out.println(data.get("Accuracy")); //
		 * System.out.println(data.get("Address")); //
		 * System.out.println(data.get("Lat")); // System.out.println(data.get("Lng"));
		 * // System.out.println(data.get("Name")); //
		 * System.out.println(data.get("Phone_Number")); //
		 * System.out.println(data.get("Website")); //
		 * System.out.println(data.get("type")); //
		 * System.out.println(data.get("Language")); // }
		 * 
		 * ArrayList<String> types=new ArrayList<String>(); types.add("shoe park");
		 * types.add("shop"); Location location=new Location();
		 * location.setLat(-38.383494); location.setLng(33.427362);
		 * 
		 * object obj=new object(); obj.setAccuracy(50);
		 * obj.setAddress("29, side layout, cohen 09"); obj.setLanguage("French-IN");
		 * obj.setName("Frontline house"); obj.setPhone_number("(+91) 983 893 3937");
		 * obj.setWebsite("http://google.com"); obj.setLocation(location);
		 * obj.setTypes(types);
		 * 
		 * 
		 * System.out.println("Test1");
		 * 
		 * // get_PlaceId response=
		 * 
		 * String response=given() .baseUri("https://rahulshettyacademy.com")
		 * .queryParam("key" , "qaclick123") .when() .body(obj "{\r\n" +
		 * "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" +
		 * "    \"lng\": 33.427362\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n" +
		 * "  \"name\": \"Frontline house\",\r\n" +
		 * "  \"phone_number\": \"(+91) 983 893 3937\",\r\n" +
		 * "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" +
		 * "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n" +
		 * "  \"website\": \"http://google.com\",\r\n" +
		 * "  \"language\": \"French-IN\"\r\n" + "}\r\n" + " \r\n" + "" )
		 * .post("/maps/api/place/add/json") .then() .log() .all() .extract()
		 * .response() .asString();
		 * 
		 * //.extract().as(get_PlaceId.class); JsonPath jsonPath=new JsonPath(response);
		 * System.out.println(jsonPath.getString("place_id"));
		 * 
		 * 
		 */
	}
}
