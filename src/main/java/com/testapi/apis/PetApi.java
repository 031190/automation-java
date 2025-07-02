package com.testapi.apis;

import com.testapi.constants.PetApiEndpoints;
import com.testapi.pojo.petApiPojos.Status;
import com.testapi.restAssuredWrapper.ApiBuilder;
import com.testapi.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class PetApi extends ApiBuilder {
    public PetApi(RequestSpecBuilder specBuilder) {
        super(specBuilder);
    }

    public Response getPetById(int petId) {
        return buildRequest(ConfigLoader.getPetApiRoute(),"get",null,null,petId,null);
    }

    public Response getPetByStatus(List<Object> status) {
        return buildRequest(ConfigLoader.getPetApiRoute() + PetApiEndpoints.FIND_BY_STATUS,
                "get", null,null,null, Map.of("status",status));
    }
}
