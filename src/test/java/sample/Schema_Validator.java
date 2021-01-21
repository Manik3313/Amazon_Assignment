package sample;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath; 
class Schema_Validator
{
	public static void main(String[] args)
	{
		
		String response=given()
		.baseUri("https://rahulshettyacademy.com")
		.when()
		.get(" /maps/api/place/get/json")
		.then()
		.assertThat()
		.body( matchesJsonSchemaInClasspath("response.json"))
		.statusCode(200)
		.extract()
		.response()
		.asString()
		;
		
		
		//assertThat(response, matchesJsonSchemaInClasspath("response.json"));
	}
}
