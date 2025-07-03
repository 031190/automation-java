package com.testapi.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testapi.constants.PetApiEndpoints;
import com.testapi.pojo.petApiPojos.Pet;
import com.testapi.pojo.petApiPojos.Status;
import com.testapi.restAssuredWrapper.ApiBuilder;
import com.testapi.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PetApi extends ApiBuilder {
    public PetApi(RequestSpecBuilder specBuilder) {
        super(specBuilder);
    }

    public Response getPetById(Integer petId) {
        return buildRequest(ConfigLoader.getPetApiRoute(),"get",null,null,petId,null);
    }

    public Response getPetByStatus(List<Object> status) {
        return buildRequest(ConfigLoader.getPetApiRoute() + PetApiEndpoints.FIND_BY_STATUS,
                "get", null,null,null, Map.of("status",status));
    }

    public Response addPet(Object body) {
        return buildRequest(ConfigLoader.getPetApiRoute(),"post",Map.of("Content-type","application/json"),
                body,null,null);
    }

    public Response updatePet(Object body) {
        return buildRequest(ConfigLoader.getPetApiRoute(),"put",Map.of("Content-type","application/json"),
                body,null,null);
    }

    public Response daletePet(Integer petId) {
        return buildRequest(ConfigLoader.getPetApiRoute(),"delete",Map.of("Content-type","application/json"),
                null,petId,null);
    }

//    public Pet createPetBodyFromJsonFile(String jsonFileName) {
//        ObjectMapper mapper = new ObjectMapper();
//        File file = new File(jsonFileName);
//        Pet pet = mapper.readValue(file, Pet.class);
//    }

}
