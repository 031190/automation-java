package com.testapi.apis;

import com.testapi.utils.ConfigLoader;
import com.testapi.restAssuredWrapper.RestAssuredRequests;
import com.testapi.utils.Parser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class RestApi {
    private RequestSpecBuilder requestSpecBuilder;
    private RestAssuredRequests restAssuredRequests;

    public RestApi() {
        restAssuredRequests = new RestAssuredRequests();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ConfigLoader.getEnvUrl());
        requestSpecBuilder.addHeader("Content-type","application/json");
        if (ConfigLoader.getRelaxedHttpValidation()) {
            requestSpecBuilder.setRelaxedHTTPSValidation();
        }
    }

    public Response getAllEntries() {
        requestSpecBuilder.setBasePath(ConfigLoader.getRestApiEndpointGetAllEntries());
        return restAssuredRequests.getRequest(requestSpecBuilder.build());
    }

    public Response createAnEntry(String jsonFileName) {
        Map<String,String> headers = new HashMap<>();
        requestSpecBuilder.addHeaders(headers);
        // TO BE REVIEWED - OTHER WAYS TO SET JSON BODY
        requestSpecBuilder.setBody(Parser.getJsonAsStringFromResources(jsonFileName));
        requestSpecBuilder.setBasePath(ConfigLoader.getRestApiEndpointCreateEntry());
        return restAssuredRequests.postRequest(requestSpecBuilder.build());
    }

    public Response getEntries(int ... values ) {
        switch (values.length) {
            case 0: // getting all entries
                requestSpecBuilder.setBasePath(ConfigLoader.getRestApiEndpointGetAllEntries());
                break;
            case 1: // getting a specific entry
                requestSpecBuilder.setBasePath(ConfigLoader.getRestApiEndpointGetAllEntries() + "/" + values[0]);
                break;
            default: // getting some specific entries
                requestSpecBuilder.setBasePath(ConfigLoader.getRestApiEndpointGetAllEntries());
                for (int value : values) {
                    requestSpecBuilder.addQueryParam("id",value);
                }
                break;
        }
        return restAssuredRequests.getRequest(requestSpecBuilder.build());
    }

    public boolean validateCreateEntry(String jsonFileSent, String jsonBodyReceived) {
        return jsonBodyReceived.contains(Parser.getJsonAsStringFromResources(jsonFileSent));
    }

}
