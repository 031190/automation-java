package com.testapi.tests;

import com.testapi.apis.RestApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestApiTest extends RestApi {

    @Test
    public void getAllEntries() {
        Response response = sendGetRequest();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void createAnEntry() {
        Response response = sendPostRequest();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
