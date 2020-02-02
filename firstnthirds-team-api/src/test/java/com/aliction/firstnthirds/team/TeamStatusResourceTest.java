package com.aliction.firstnthirds.team;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class TeamStatusResourceTest {

    @Test
    public void testTeamStatusEndpoint() {
        given()
            .when().body("{ \"provisionStatus\" : \"Bomb\" }").contentType(MediaType.APPLICATION_JSON).post("/teamStatus")
            .then().statusCode(201);

        given()
            .when().get("/teamStatus")
            .then().statusCode(200);
      
        given()
            .when().get("/teamStatus/1")
            .then().statusCode(200);

        given()
            .when().delete("/teamStatus/4")
            .then().statusCode(204);

    }

}