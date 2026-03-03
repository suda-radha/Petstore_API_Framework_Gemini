package com.petstore.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//Swagger url: 
public class FirstAPITest {
	
	@Test
	public void getAvailablePets() {
		
		//Compelteurl = 'https://petstore.swagger.io/v2/pet/findByStatus?status=available'
		baseURI = "https://petstore.swagger.io/v2";
		
		given().queryParam("status", "available")
		.when().get("/pet/findByStatus")
		.then(). statusCode(200).body("status[0]", equalTo("available")).log().all();
		}
}
