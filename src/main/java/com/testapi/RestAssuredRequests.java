package com.testapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class RestAssuredRequests {

    private RequestSpecBuilder specBuilder;

    public RestAssuredRequests(RequestSpecBuilder specBuilder) {
        this.specBuilder = specBuilder;
    }

    public Response postRequest() {
        return RestAssured.given(specBuilder.build()).log().all().post().then().log().all().extract().response();
    }

    public Response getRequest() {
        return RestAssured.given(specBuilder.build()).log().all().get().then().log().all().extract().response();
    }

    public Response deleteRequest() {
        return RestAssured.given().log().all().delete().then().log().all().extract().response();
    }
}
