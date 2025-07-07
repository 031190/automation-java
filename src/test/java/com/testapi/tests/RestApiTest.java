package com.testapi.tests;

import com.testapi.apis.RestApi;
import com.testapi.init.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testapi.pojo.restApiPojos.RestApiCreateEntryResponse;
import com.testapi.pojo.restApiPojos.RestApiGetEntryBody;

import java.util.List;
import java.util.Map;

public class RestApiTest extends BaseTest {

    @Test
    public void testRestApiGetAllEntries(){
        RestApi restApi = new RestApi(requestSpecBuilder);
        Response response = restApi.getAllEntries();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testRestApiGetEntriesByIDS() {
        RestApi restApi = new RestApi(requestSpecBuilder);
        Map<String,List<Object>> queryParams = Map.of("id", List.of(3,5));
        Response response = restApi.getAllEntriesByIDs(queryParams);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testRestApiGetEntryById() {
        RestApi restApi = new RestApi(requestSpecBuilder);
        int id = 5;
        Response response = restApi.getEntryById(id);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(String.valueOf(id), response.getBody().jsonPath().get("id"));
    }

    @Test
    public void testRestApiCreateAnEntry() {
        RestApi restApi = new RestApi(requestSpecBuilder);
        Response response = restApi.createAnEntry("restApiPostRequest.json");
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiCreateEntryResponse responseBody = response.getBody().as(RestApiCreateEntryResponse.class);
        Assert.assertTrue(response.getHeaders().toString().contains("json"));
        Assert.assertTrue(restApi.validateCreateEntry("restApiPostRequest.json", responseBody));
        Response response2 = restApi.getAllEntries();
        RestApiGetEntryBody[] responseBody2 = response2.getBody().as(RestApiGetEntryBody[].class);
        System.out.println("Test: " + responseBody2[0]);
    }
}
