package com.testapi.tests;

import com.testapi.apis.RestApi;
import com.testapi.init.BaseTest;
import com.testapi.utils.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.testapi.pojo.RestApiCreateEntryResponse;
import com.testapi.pojo.RestApiGetEntryBody;

public class RestApiTest extends BaseTest {

    @Test
    public void testCheckAllEntries() {
        System.out.println("Here1");
        RestApi restApi = new RestApi(requestSpecBuilder);
        Response response = restApi.getEntries();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testCreateNewEntry() {
        RestApi restApi = new RestApi(requestSpecBuilder);
        Response response = restApi.createAnEntry("restApiPostRequest.json");
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiCreateEntryResponse responseBody = response.getBody().as(RestApiCreateEntryResponse.class);
        Assert.assertTrue(response.getHeaders().toString().contains("json"));
        Assert.assertTrue(Parser.isJson(responseBody.getData().toString()));
        Assert.assertTrue(restApi.validateCreateEntry("restApiPostRequest.json", responseBody.getData().toString()));
        //Assert.assertTrue(validateCreateEntry("restApiPostRequest.json", response.getBody().asString()));
    }

    @Test
    public void testCheckSomeEntries() {
        RestApi restApi = new RestApi(requestSpecBuilder);
        Response response = restApi.getEntries(3,5,10);
        Assert.assertEquals(response.getStatusCode(),200);
        RestApiGetEntryBody[] responseBody = response.getBody().as(RestApiGetEntryBody[].class);
        System.out.println("Test: " + responseBody[0]);
        //System.out.println(responseBody[0].getDataObj().getCapacityGb());
    }
}
