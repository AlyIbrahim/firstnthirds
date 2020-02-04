package com.aliction.firstnthirds.user;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void testHelloEndpoint() {
        // given()
        //   .when().get("/hello")
        //   .then()
        //      .statusCode(200)
        //      .body(is("Hello Aly"));

         given()
             .when()
                 .body("{\"firstName\" : \"Aly\", \"lastName\" : \"Ibrahim\", \"email\" : \"aly@redhat.com\", \"city\" : \"Plano\", \"state\" : \"Texas\", \"country\" : \"US\", \"teamId\": 2}")
                 .contentType("application/json")
                 .post("/user")
             .then()
                 .statusCode(201);
        //  given()
        //      .when()
        //          .body("{\"id\" : \"1\", \"firstName\" : \"Aly\", \"lastName\" : \"Ibrahim\", \"email\" : \"aly@redhat.com\", \"city\" : \"Plano\", \"state\" : \"Texas\", \"country\" : \"US\"}")
        //          .contentType("application/json")
        //          .delete("/user")
        //      .then()
        //          .statusCode(204);
        
         given()
            .when()
                .delete("/user/4")
            .then()
                .statusCode(204);             
    }

}