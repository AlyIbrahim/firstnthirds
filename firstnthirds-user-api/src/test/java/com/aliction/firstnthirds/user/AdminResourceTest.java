package com.aliction.firstnthirds.user;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AdminResourceTest {

    @Test
    public void testAdminEndpoint() {

         given()
             .when()
                 .body("{\"user\" : {\"id\" : 2} }")//, \"firstName\" : \"Aly\", \"lastName\" : \"Ibrahim\", \"email\" : \"aly@redhat.com\", \"city\" : \"Plano\", \"state\" : \"Texas\", \"country\" : \"United States\", \"teamId\": 1} }")
                 .contentType("application/json")
                 .post("/admin")
             .then()
                 .statusCode(201);
        //  given()
        //     .when()
        //         .delete("/admin/2")
        //     .then()
        //         .statusCode(204);             
    }

}