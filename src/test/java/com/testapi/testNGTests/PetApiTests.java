package com.testapi.testNGTests;

import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import com.testapi.pojo.petApiPojos.Status;
import com.testapi.utils.Parser;
import com.testapi.utils.Utility;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;

//uses PetApi instance which needs requestSpecBuilder initialized and configured in the BaseTest
//sometimes the tests fail because the API is not always working since it is public
public class PetApiTests extends BaseTest {

    @Test(groups = {"smoke","regression"})
    @Description("Get Pet by ID")
    public void testPetApiGetPetById() {
        Allure.step("Test allure");
        PetApi petApi = new PetApi(requestSpecBuilder);
        Response response = petApi.getPetById(5);
        Pet pet = response.getBody().as(Pet.class);
        System.out.println("Pet API test: " + pet.toString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(pet.getId(),5);
        Response response1 = petApi.getPetById(0);
        Assert.assertEquals(response1.getStatusCode(),404);
    }

    @Test(groups = {"smoke","regression"})
    @Description("Get Pet by STATUS")
    public void testPetApiGetPetsByStatus() {
        PetApi petApi = new PetApi(requestSpecBuilder);
        String status = Status.AVAILABLE.status;
        Response response = petApi.getPetByStatus(List.of(status));
        Pet[] petResponseBody = response.getBody().as(Pet[].class);
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json");
        for(Pet pet : petResponseBody) {
            Assert.assertEquals(status, pet.getStatus());
        }
    }

    @Test(groups = {"regression"})
    @Description("Create a new Pet")
    // create a pet - either build a Pet obj from pojo ( using setters or make a explicit constructor for Pet ( add also implicit one - needed for mapping response)),
    //          or send it static from a json file in resources/testData
    // this test firstly get all pet ids to generate a unique one, then creates a new pet and POST, then verifies it was indeed created
    public void createNewPet() throws InterruptedException {
        //get all IDs so can generate a unique one in order to create a pet
        PetApi petApi = new PetApi(requestSpecBuilder);
        Response response = petApi.getPetByStatus(List.of(Status.AVAILABLE.status,Status.PENDING.status,Status.SOLD.status));
        Pet[] petResponseBody = response.getBody().as(Pet[].class);
        Set<Long> petIds = new HashSet<>();
        for (Pet pet : petResponseBody) {
            petIds.add(pet.getId());
        }
        //get it as int since the other methods use int
        int uniqueId = Utility.generateUniqueNumber(petIds);
        //create the new pet
        Pet newPet = (Pet) Parser.getJsonFileAsPojoClassFromResources("pet.json", Pet.class);
        newPet.setId(uniqueId);
        //or you can just create it dynamically  by using the Pet setters

        //send the POST request
        Response createPetResp = petApi.addPet(newPet);
        Assert.assertEquals(createPetResp.getStatusCode(),200);
        Assert.assertEquals(createPetResp.getContentType(),"application/json");
        Thread.sleep(15000); // wait 10 sec because this open api works slow
        Response getCreatedPet = petApi.getPetById(uniqueId);
        Pet checkNewPet = getCreatedPet.getBody().as(Pet.class);
        Assert.assertEquals(checkNewPet.toString(), newPet.toString());
    }

    @Test(groups = {"regression"})
    @Description("Update a pet")
    public void updatePet() {
        PetApi petApi = new PetApi(requestSpecBuilder);
        //find the pet to be updated or just send the update request with the new values
        Response responseFindAllPets = petApi.getPetByStatus(List.of(Status.AVAILABLE.status,Status.PENDING.status,Status.SOLD.status));
        Pet[] allPets = responseFindAllPets.getBody().as(Pet[].class);
        Pet petToBeUpdated = allPets[0];
        long idOfTheUpdatedPet = petToBeUpdated.getId();
        System.out.println("Initial Pet : " + petToBeUpdated);
        //update the Pet
        petToBeUpdated.setName("Updated Pet by Mihail");
        Response responseUpdatedPet = petApi.updatePet(petToBeUpdated);
        Assert.assertEquals(responseUpdatedPet.getStatusCode(), 200);
        //check that updated Pet is done correctly
        Response responseFindTheUpdatedPet = petApi.getPetById((int) idOfTheUpdatedPet);
        Pet updatedPet = responseUpdatedPet.getBody().as(Pet.class);
        Assert.assertEquals(updatedPet.getName(), "Updated Pet by Mihail");
    }

    @Test(groups = {"regression"})
    @Description("Delete a Pet")
    public void deletePet() {
        PetApi petApi = new PetApi(requestSpecBuilder);
        long idOfThePet = 5;
        //find the pet and check the status = available
        Response responseFindThePetToBeDeleted = petApi.getPetById((int) idOfThePet);
        Pet petToBeDeleted = responseFindThePetToBeDeleted.getBody().as(Pet.class);
        Assert.assertEquals(responseFindThePetToBeDeleted.getStatusCode(), 200);
        Assert.assertEquals(petToBeDeleted.getId(), idOfThePet);
        System.out.println("Pet to be deleted: " + petToBeDeleted);
        //delete the pet
        Response deleteThePet = petApi.deletePet((int) idOfThePet);
        Assert.assertEquals(deleteThePet.getStatusCode(), 200);
        //after delete the check 404
        Response deletedPet = petApi.getPetById((int) idOfThePet);
        Assert.assertEquals(deletedPet.getStatusCode(), 404);
    }
}
