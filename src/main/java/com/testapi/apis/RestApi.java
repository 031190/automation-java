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
        return apiBuilder.buildRequest(ConfigLoader.getRestApiEndpoint(),"get",null,null,null,null);
    }

    public Response getAllEntriesByIDs(List<Map<String,Object>> queryParams) {
        return apiBuilder.buildRequest(ConfigLoader.getRestApiEndpoint(),"get",null,null,null,queryParams);
    }

    public Response getEntryById(Object id) {
        String endpoint = ConfigLoader.getRestApiEndpoint() + "/" + id;
        return apiBuilder.buildRequest(endpoint,"get",null,null,null,null);
     }

     public Response createAnEntry(String jsonFile) {
         return apiBuilder.buildRequest(ConfigLoader.getRestApiEndpoint(),"post",Map.of("Content-type","application/json"),Parser.getJsonAsStringFromResources(jsonFile),null,null);
     }

    public boolean validateCreateEntry(String jsonFileSent, Object jsonBodyReceived) {
        String jsonSent = Parser.getJsonAsStringFromResources(jsonFileSent);
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
