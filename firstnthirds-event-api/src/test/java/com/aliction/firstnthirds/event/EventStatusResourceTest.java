package com.aliction.firstnthirds.event;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class EventStatusResourceTest {

    @Test
    public void testEventStatusEndpoint() {

        given()
            .when().body("{ \"status\": \"CREATED\" }")
            .contentType(MediaType.APPLICATION_JSON).post("/eventStatus")
            .then().statusCode(201);

        given()
          .when().get("/eventStatus")
          .then()
             .statusCode(200);

        given()
            .when().get("/eventStatus/2")
            .then().statusCode(200);

        // given()
        //     .when().delete("/eventStatus/1")
        //     .then().statusCode(204);


    }

}