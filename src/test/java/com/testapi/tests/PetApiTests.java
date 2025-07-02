package com.testapi.tests;

import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import com.testapi.pojo.petApiPojos.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PetApiTests extends BaseTest {
    
    @Test
    public void testPetApiGetPetById() {
        PetApi petApi = new PetApi(requestSpecBuilder);
        Response response = petApi.getPetById(5);
        Pet pet = response.getBody().as(Pet.class);
        System.out.println("Pet API test: " + pet.toString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(pet.getId(),5);
        Response response1 = petApi.getPetById(0);
        Assert.assertEquals(response1.getStatusCode(),404);
    }

    @Test
    public void testPetApiGetPetsByStatus() {
        PetApi petApi = new PetApi(requestSpecBuilder);
        String status = Status.AVAILABLE.status;
        String status2 = Status.SOLD.status;
        Response response = petApi.getPetByStatus(List.of(status,status2));
        Pet[] petResponseBody = response.getBody().as(Pet[].class);
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(Arrays.stream(petResponseBody).findFirst());
    }
}
