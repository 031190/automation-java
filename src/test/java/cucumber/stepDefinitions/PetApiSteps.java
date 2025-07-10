package cucumber.stepDefinitions;
import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import com.testapi.pojo.petApiPojos.Status;
import com.testapi.utils.Parser;
import com.testapi.utils.Utility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetApiSteps extends BaseTest {

    Response response;
    int id;

    @Given("the user sends a pet get request by {int}")
    public void sendGetRequest(int id){
        setup();
        PetApi petApi = new PetApi(requestSpecBuilder);
        response = petApi.getPetById(id);
    }

    @Then("verify response (.*) (.*)$")
    public void verifyResponse(int statusCode, int id) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
        if (statusCode == 200) {
            Pet pet = response.getBody().as(Pet.class);
            Assert.assertEquals(id, pet.getId());
        }
    }

    @Given("the user sends a pet post request using body as {}")
    public void createPet(String jsonFileName) {
        setup();
        PetApi petApi = new PetApi(requestSpecBuilder);
        response = petApi.getPetByStatus(List.of(Status.AVAILABLE.status,Status.PENDING.status,Status.SOLD.status));
        Pet[] petResponseBody = response.getBody().as(Pet[].class);
        Set<Long> petIds = new HashSet<>();
        for (Pet pet : petResponseBody) {
            petIds.add(pet.getId());
        }
        //get it as int since the other methods use int
        int uniqueId = Utility.generateUniqueNumber(petIds);
        id = uniqueId; // memorize it for further use
        //create the new pet
        Pet newPet = (Pet) Parser.getJsonFileAsPojoClassFromResources(jsonFileName, Pet.class);
        newPet.setId(uniqueId);
        //or you can just create it dynamically  by using the Pet setters

        //send the POST request
        response = petApi.addPet(newPet);
    }

    @Then("verify created pet {} {int}")
    public void checkPetCreated(String jsonFileName, int statusCode) throws InterruptedException {
        setup();
        //Response createPetResp = petApi.addPet(newPet);
        Assert.assertEquals(response.getStatusCode(),statusCode);
        Assert.assertEquals(response.getContentType(),"application/json");
        Thread.sleep(15000);// wait 10 sec because this open api works slow
        PetApi petApi = new PetApi(requestSpecBuilder);
        response = petApi.getPetById(id);
        Pet checkNewPet = response.getBody().as(Pet.class);
        Pet sentPet = (Pet) Parser.getJsonFileAsPojoClassFromResources(jsonFileName, Pet.class);
        sentPet.setId(id);
        Assert.assertEquals(checkNewPet.toString(), sentPet.toString());
    }
}
