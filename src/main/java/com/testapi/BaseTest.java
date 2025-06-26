package com.testapi;

import static io.restassured.RestAssured.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import pojo.EmployeeResponse;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class BaseTest {

    public static void sendGetRequest(String url){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.given().baseUri(url);
        Response response = requestSpecification.get();
        EmployeeResponse body = response.getBody().as(EmployeeResponse.class);
        if (response.getStatusCode()==200) {
            System.out.println("DA");
            response.getBody().print();
        } else System.out.println("NU");
        System.out.println(body.getMessage());
    }

    public static void sendPostRequest(Map<String,String> headers, String url, String body) throws IOException, ParseException {
        String bodyLocation = "src/main/resources/" +body;
        JSONParser jsonParser = new JSONParser();
        JSONObject json = (JSONObject) jsonParser.parse(new FileReader(bodyLocation));
        json.put("salary","9999");
        String body2 = jsonParser.parse(json.toString()).toString();

        Response response = given().log().all().headers(headers).baseUri(url).body(body2).when().post().then().log().all().extract().response();
        //Assert.assertTrue(response.getStatusCode() == 200);
        String responseBody = response.getBody().asString();
        System.out.println("Mihail"+responseBody);
        //String responseBody = given().log().all().headers(headers).baseUri(url).body(body2).when().post().then().log().all().extract().response().getBody().asString();

//        System.out.println("Mihail" + responseBody);
        JSONParser jsonParser2 = new JSONParser();
        JSONObject jsonObject2 = (JSONObject) jsonParser2.parse(responseBody);
//
        System.out.println("MIHAIL" + jsonObject2.get("status"));
//        System.out.println("Mihail" + responseBody);


    }
}
