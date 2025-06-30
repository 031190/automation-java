package com.testapi.init;

import com.testapi.apis.RestApi;
import com.testapi.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected RequestSpecBuilder requestSpecBuilder;

    @BeforeTest
    public void setup() {
        System.out.println("Here2");
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ConfigLoader.getEnvUrl());
        requestSpecBuilder.addHeader("Content-type","application/json");
    }

    @AfterMethod
    public void tearDown() {
    }
}
