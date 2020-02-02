package com.aliction.firstnthirds.event;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class EventResourceTest {

    @Test
    public void testEventEndpoint() {

        given()
            .when().body("{\"name\": \"Top Golf\", \"type\": \"Social\", \"date\": \"2019-05-06 16:00:00\", \"duration\": 90, \"location\": \"Frisco\", \"description\": \"Mini Golf\", \"pictures_url\": \"https-drive.google.com\", \"status\" : { \"id\": 4, \"status\": \"Created\" }, \"teamId\": 1 }")
            .contentType(MediaType.APPLICATION_JSON).post("/event")
            .then().statusCode(201);

        given()
            .when().body("{\"name\": \"Top Golf\", \"type\": \"Social\", \"date\": \"2019-05-06 16:00:00\", \"duration\": 90, \"location\": \"Frisco\", \"description\": \"Mini Golf\", \"pictures_url\": \"https-drive.google.com\", \"status\" : { \"id\": 4, \"status\": \"Created\" }, \"teamId\": 7 }")
            .contentType(MediaType.APPLICATION_JSON).post("/event")
            .then().statusCode(404);

        given()
          .when().get("/event")
          .then()
             .statusCode(200);

        given()
            .when().get("/event/2")
            .then().statusCode(200);

        given()
            .when().delete("/event/1")
            .then().statusCode(204);


    }

}