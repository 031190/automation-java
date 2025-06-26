package com.testapi.tests;

import com.testapi.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiTest extends BaseTest {

    @Test
    public void sendGetRequest() throws IOException, ParseException {
        //sendGetRequest("https://dummy.restapiexample.com/api/v1/employees");
        Map<String ,String> headers = new HashMap<>();
        headers.put("Content-type","application/json");
        sendPostRequest(headers,"https://dummy.restapiexample.com/api/v1/create", "body.json");
    }
}
