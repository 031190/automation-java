package com.testapi.apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.testapi.restAssuredWrapper.ApiBuilder;
import com.testapi.utils.ConfigLoader;
import com.testapi.utils.Parser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class RestApi {

    RequestSpecBuilder requestSpecBuilder;
    ApiBuilder apiBuilder;

    public RestApi(RequestSpecBuilder requestSpecBuilder) {
        this.requestSpecBuilder = requestSpecBuilder;
        requestSpecBuilder.addHeaders(ConfigLoader.getRestApiKey());
        apiBuilder = new ApiBuilder(this.requestSpecBuilder);
    }

    public Response getAllEntries() {
        return apiBuilder.buildRequest(ConfigLoader.getRestApiRoute(),"get",null,null,null,null);
    }

    public Response getAllEntriesByIDs(Map<String,List<Object>> queryParams) {
        return apiBuilder.buildRequest(ConfigLoader.getRestApiRoute(),"get",null,null,null,queryParams);
    }

    public Response getEntryById(Integer id) {
        //String endpoint = ConfigLoader.getRestApiRoute() + "/" + id;
        return apiBuilder.buildRequest(ConfigLoader.getRestApiRoute(),"get",null,null,id,null);
     }

     public Response createAnEntry(String jsonFile) {
         return apiBuilder.buildRequest(ConfigLoader.getRestApiRoute(),"post",Map.of("Content-type","application/json"),Parser.getFileAsStringFromResources(jsonFile),null,null);
     }

    public boolean validateCreateEntry(String jsonFileSent, Object jsonBodyReceived) {
        String jsonSent = Parser.getFileAsStringFromResources(jsonFileSent);
        List<String> list = List.of("id","createdAt");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonFileSentNode = objectMapper.readTree(jsonSent);
            JsonNode jsonBodyReceivedNode = objectMapper.valueToTree(jsonBodyReceived);
            ((ObjectNode) jsonBodyReceivedNode).remove(list);
            return jsonBodyReceivedNode.equals(jsonFileSentNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
