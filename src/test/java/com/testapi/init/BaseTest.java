package com.testapi.init;


import com.testapi.utils.ConfigLoader;

import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected RequestSpecBuilder requestSpecBuilder;

    @BeforeMethod
    public void setup() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ConfigLoader.getBaseUrl());
        if(ConfigLoader.getRelaxedHttpValidation()){
            requestSpecBuilder.setRelaxedHTTPSValidation();
        }
    }

    @AfterMethod
    public void tearDown() {
    }
}
