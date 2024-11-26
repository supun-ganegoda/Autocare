package com.autocare.order_service;

import com.autocare.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

/*
after adding the inventory service, when writing the test cases it need to call the inventory service everytime,
this is costly and need to run the inventory service to run the tests. to isolate testing and reduce the cost.
we can use mockito or wiremock to mock the response getting from the inventory service.
*/
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	static {
		mySQLContainer.start();
	}

	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	private static final String requestBody = """
			{
			   "skuCode":"Headlights",
			   "price":15000,
			   "quantity":1
			 }
				""";


	@Test
	void shouldCreateOrder() {
		InventoryClientStub.stubInventoryCall("Headlights", 1);
		String response = RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();
		assertThat(response, Matchers.equalTo("order placement successful"));
	}

}
