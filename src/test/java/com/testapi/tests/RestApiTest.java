package com.testapi.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testapi.apis.RestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.RestApiCreateEntryResponse;
import pojo.RestApiGetEntryBody;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RestApiTest extends RestApi {

    @Test
    public void testCheckAllEntries() {
        Response response = getEntries();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testCreateNewEntry() {
        Response response = createAnEntry("restApiPostRequest.json");
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiCreateEntryResponse responseBody = response.getBody().as(RestApiCreateEntryResponse.class);
        Assert.assertTrue(response.getHeaders().toString().contains("json"));
        Assert.assertTrue(validateCreateEntry("restApiPostRequest.json",responseBody.getData().toString()));
        //Assert.assertTrue(validateCreateEntry("restApiPostRequest.json", response.getBody().asString()));
    }

    @Test
    public void testCheckSomeEntries() {
        Response response = getEntries(3,5,10);
        Assert.assertEquals(response.getStatusCode(),200);
        ArrayList<RestApiGetEntryBody> respType = null;
        Class<RestApiGetEntryBody> ArrayList;
        RestApiGetEntryBody[] responseBody = response.getBody().as(RestApiGetEntryBody[].class);
        System.out.println("Test: " + responseBody[0]);
        //System.out.println(responseBody[0].getDataObj().getCapacityGb());
    }
}
