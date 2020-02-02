package com.aliction.firstnthirds.team;

import io.quarkus.test.junit.QuarkusTest;


import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class TeamResourceTest {

    @Test
    public void testTeamEndpoint() {
        given()
            .body("{\"name\" : \"DallasFnT\", \"city\" : \"Plano\", \"state\" : \"Texas\", \"country\" : \"US\", \"status\" : { \"id\" : 2, \"provisionStatus\" : \"Created\"} }")
            // .body("{\"name\" : \"DallasFnT\", \"city\" : \"Plano\", \"state\" : \"Texas\", \"country\" : \"US\", \"status\" : 1 }")
            .contentType(MediaType.APPLICATION_JSON).post("/team")
            .then().statusCode(201);

        given()
            .when().get("/team")
            .then().statusCode(200);
      
        given()
            .when().get("/team/1")
            .then().statusCode(200);

    //     given()
    //         .when().delete("/team/1")
    //         .then().statusCode(204);

    }

}