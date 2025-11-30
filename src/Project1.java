import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Project1 {

	public static void main(String[] args) 
	{

		// given ,when.then
		
		System.out.println("creating new address");
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type",
				"text/plain").body("{\r\n"
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
						+ "} ")
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("Content-Type", "application/json;charset=UTF-8").extract().response().asString();
		System.out.println(response);
		
		
		System.out.println("parsing json");
		JsonPath js=new JsonPath(response); // this class helps in converting from string to json
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		

		System.out.println("update with new address >verify the new address in response");
		given().log().all().queryParam("key", "qaclick123").body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("maps/api/place/update/json").
		then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
	
		System.out.println("get the updated address");
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).
		when().get("maps/api/place/get/json").
		then().assertThat().log().all().statusCode(200).body("address",equalTo("70 winter walk, USA"));
		
		
		System.out.println("deleting the address");	
		given().log().all().queryParam("key", "qaclick123").body("{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}").
		when().delete("maps/api/place/delete/json").
		then().assertThat() .log().all().statusCode(200).body("status", equalTo("OK"));
		
		
		System.out.println("validating the deleted place_id");
		given().log().all().queryParam("key", "qaclick123").body("{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\""+placeid+"\"\r\n"
				+ "}").
		when().delete("maps/api/place/delete/json").
		then().assertThat() .log().all().statusCode(404).body("msg", equalTo("Delete operation failed, looks like the data doesn't exists"));
		
	}

}
