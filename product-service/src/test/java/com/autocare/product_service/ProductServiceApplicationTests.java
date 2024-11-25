package com.autocare.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	static {
		mongoDBContainer.start();
	}

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	private static final String requestBody = """
				{
				  "name":"Head lights",
				  "description":"Two headlights",
				  "price":30000
				}
				""";

	@Test
	void shouldCreateProduct() {
		RestAssured.given().contentType("application/json").body(requestBody).when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("name", equalTo("Head lights"))
				.body("description", equalTo("Two headlights"))
				.body("price", equalTo(30000.0F));
	}

	@Test
	void shouldAddValidateFirstProduct(){
		RestAssured
				.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201);

		RestAssured.when()
				.get("/api/product")
				.then()
				.statusCode(200)
				.body("$", Matchers.hasSize(greaterThan(0)))
				.body("[0].id", notNullValue())
				.body("[0].name", equalTo("Head lights"))
				.body("[0].description", equalTo("Two headlights"))
				.body("[0].price", equalTo(30000.0F));
	}

}
