package com.testapi.tests;

import com.testapi.apis.RestApi;
import com.testapi.utils.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testapi.pojo.RestApiCreateEntryResponse;
import com.testapi.pojo.RestApiGetEntryBody;

public class RestApiTest {

    private RestApi restApi;
    public RestApiTest() { restApi = new RestApi(); }

    @Test
    public void testCheckAllEntries() {
        Response response = restApi.getEntries();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testCreateNewEntry() {
        Response response = restApi.createAnEntry("restApiPostRequest.json");
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiCreateEntryResponse responseBody = response.getBody().as(RestApiCreateEntryResponse.class);
        Assert.assertTrue(response.getHeaders().toString().contains("json"));
        Assert.assertTrue(restApi.validateCreateEntry("restApiPostRequest.json",responseBody.getData().toString()));
        //Assert.assertTrue(validateCreateEntry("restApiPostRequest.json", response.getBody().asString()));
    }

    @Test
    public void testCheckSomeEntries() {
        Response response = restApi.getEntries(3,5,10);
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiGetEntryBody[] responseBody = response.getBody().as(RestApiGetEntryBody[].class);
        System.out.println("Test: " + responseBody[0]);
        //System.out.println(responseBody[0].getDataObj().getCapacityGb());
    }
}
