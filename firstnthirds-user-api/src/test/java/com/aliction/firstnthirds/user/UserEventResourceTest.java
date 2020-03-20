package com.aliction.firstnthirds.user;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserEventResourceTest {

    @Test
    public void testUserEventEndpoint() {

         given()
             .when()
                 .body("{ \"user\" : { \"id\": 2 }, \"eventId\" : 4, \"role\" : { \"id\" : 3 }, \"status\": { \"id\" : 4 } }")
                 .contentType("application/json")
                 .post("/userevent")
             .then()
                 .statusCode(201);
        
         given()
            .when()
                .delete("/userevent/4")
            .then()
                .statusCode(204);             
    }

}