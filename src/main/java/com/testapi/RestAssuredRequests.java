package com.testapi;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRequests {

    public RequestSpecification requestSpecification;

    public RestAssuredRequests(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public Response postRequest() {
        return given(requestSpecification).log().all().post().then().log().all().extract().response();
    }

    public Response getRequest() {
        return given(requestSpecification).log().all().get().then().log().all().extract().response();
    }

    public Response deleteRequest() {
        return given().log().all().delete().then().log().all().extract().response();
    }
}
