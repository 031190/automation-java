package com.testapi.restAssuredWrapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ApiBuilder {

    private final RequestSpecBuilder requestSpecBuilder;

    public ApiBuilder(RequestSpecBuilder specBuilder) {
        requestSpecBuilder = specBuilder;
    }

    // used to get reqSpecBuilder values from a certain point/currently using the one created in BaseTest - since it keeps values between tests if manipulated
    private RequestSpecification getRequestSpecification(){
        return requestSpecBuilder.build();
    }

    public Response buildRequest(String endpoint, String method, Map<String,String> headers, Object body, Integer pathParameter, Map<String,List<Object>> queryParams) {
        // create a copy of original req spec builder - to not keep save the values each time this method is called
        RequestSpecBuilder rspec = new RequestSpecBuilder();
        rspec.addRequestSpecification(getRequestSpecification());

        String endPoint = endpoint;
        if (pathParameter != null) {
            endPoint += "/" + pathParameter;
        }

        if (endPoint != null) {
            rspec.setBasePath(endPoint);
        }

        if (headers != null) {
            rspec.addHeaders(headers);
        }

        if (body != null) {
            rspec.setBody(body);
        }

        if (queryParams != null) {
            for (Map.Entry<String,List<Object>> entry : queryParams.entrySet()) {
                for (Object object : entry.getValue()) {
                    rspec.addQueryParam(entry.getKey(),object);
                }
            }

        }

        Response response;
        switch (method.toLowerCase()){
            case "get":
                response = given().spec(rspec.build()).log().all().get().then().log().all().extract().response();
                break;
            case "post":
                response = given().spec(rspec.build()).log().all().post().then().log().all().extract().response();
                break;
            case "delete":
                response = given().spec(rspec.build()).log().all().delete().then().log().all().extract().response();
                break;
            case "put":
                response = given().spec(rspec.build()).log().all().put().then().log().all().extract().response();
                break;
            case "patch":
                response = given().spec(rspec.build()).log().all().patch().then().log().all().extract().response();
                break;
            default:
                response = null;
                break;
        }
        return response;
    }
}
