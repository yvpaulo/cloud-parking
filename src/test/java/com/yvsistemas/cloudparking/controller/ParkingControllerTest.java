package com.yvsistemas.cloudparking.controller;

import com.yvsistemas.cloudparking.dto.ParkingCreateDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

   @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenCreateThenCheckResult() {

       var creatDTo = new ParkingCreateDTO();
       creatDTo.setColor("Vermelho");
       creatDTo.setLicense("BRR-3030");
       creatDTo.setModel("Geladeira");
       creatDTo.setState("Ma");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatDTo)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("BRR-3030"))
                .body("color", Matchers.equalTo("Vermelho"));

    }

    @Test
    void whenFindAllThenCheckResult() {
       RestAssured.given()
                .when()
                .get("/parking")
                .then()
                //.extract().response().body().prettyPrint();
                .statusCode(HttpStatus.OK.value());
              //  .body("license[0]", Matchers.equalTo("JPA-111"));
    }
}