package cucumber.stepDefinitions;
import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class PetApiSteps extends BaseTest {

    @Given("the user sends a get request")
    public void sendGetRequest(){
        setup();
        PetApi petApi = new PetApi(requestSpecBuilder);
        Response response = petApi.getPetById(5);
        Pet pet = response.getBody().as(Pet.class);
        System.out.println("Pet API test: " + pet.toString());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("verify response")
    public void verifyResponse() {
    }
}
