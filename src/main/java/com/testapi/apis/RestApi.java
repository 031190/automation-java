package com.testapi.apis;

import Utils.ConfigLoader;
import com.testapi.RestAssuredRequests;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RestApi  {

    private final String url = "https://api.restful-api.dev";
    private final String endpoint = "/objects";
    RequestSpecBuilder requestSpecBuilder;

    public RestApi() {
        requestSpecBuilder = new RequestSpecBuilder();
        //requestSpecBuilder.setBaseUri(url);
        requestSpecBuilder.setBaseUri(ConfigLoader.getEnvUrl());
        requestSpecBuilder.setBasePath(endpoint);
    }

    public Response sendGetRequest() {
        RestAssuredRequests request = new RestAssuredRequests(requestSpecBuilder);
        return request.getRequest();
    }

    public Response sendPostRequest() {
        String bodyLocation = "src/main/resources/restApiPostRequest.json";
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-type","application/json");
        requestSpecBuilder.addHeaders(headers);
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject json = (JSONObject) jsonParser.parse(new FileReader(bodyLocation));
            String body = jsonParser.parse(json.toString()).toString();
            requestSpecBuilder.setBody(body);
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        }

        RestAssuredRequests request = new RestAssuredRequests(requestSpecBuilder);
        return request.postRequest();
    }

}
