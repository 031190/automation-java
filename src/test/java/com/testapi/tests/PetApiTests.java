package com.testapi.tests;

import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import com.testapi.pojo.petApiPojos.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

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
        Response response = petApi.getPetByStatus(List.of(status));
        Pet[] petResponseBody = response.getBody().as(Pet[].class);
        System.out.println("Total pets available: " + petResponseBody.length);
        Set<Long> petIds = new HashSet<>();
        for (Pet pet : petResponseBody) {
            petIds.add(pet.getId());
        }
        long id;
        do {
            Random random = new Random();
            id = random.nextLong();

        } while (petIds.contains(id));
        System.out.println("Unique ID: " + id);
        Assert.assertEquals(response.getStatusCode(), 200);

    }
}
