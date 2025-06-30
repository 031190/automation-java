package com.testapi.restAssuredWrapper;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRequests {

    public Response postRequest(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).log().all().post().then().log().all().extract().response();
    }

    public Response getRequest(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).log().all().get().then().log().all().extract().response();
    }

    public Response deleteRequest(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).log().all().delete().then().log().all().extract().response();
    }
}
