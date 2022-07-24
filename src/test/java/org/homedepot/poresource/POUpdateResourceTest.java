package org.homedepot.poresource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class POUpdateResourceTest {
	
	@Test
	public void testPO() {
		given().when().get("/updatepo//id/1")
		.then()
        .statusCode(200)
        .body("orderId",hasItem("W1444"));
	}

}
