package com.testapi.apis;

import com.testapi.utils.ConfigLoader;
import com.testapi.restAssuredWrapper.RestAssuredRequests;
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
        String bodyLocation = "src/main/resources/" + jsonFileName;
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-type","application/json");
        requestSpecBuilder.addHeaders(headers);

        // TO BE REVIEWED - OTHER WAYS TO SET JSON BODY
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject json = (JSONObject) jsonParser.parse(new FileReader(bodyLocation));
            String body = jsonParser.parse(json.toString()).toString();
            requestSpecBuilder.setBody(body);
        } catch (Exception e) {
            System.out.println("FILE NOT FOUND");
        }
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

    public boolean validateCreateEntry(String jsonBodySent, String jsonBodyReceived) {
        File requestBodyFile = new File("src/main/resources/" + jsonBodySent);
        try {
            FileInputStream fileInputStream = new FileInputStream(requestBodyFile);
            System.out.println("Check1 + " + new String(fileInputStream.readAllBytes()));
            System.out.println("check2 + " + jsonBodyReceived);
            return jsonBodyReceived.contains(new String(fileInputStream.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //JSONObject
        //Files.readString(Paths.get(jsonBodySent))
    }

}
