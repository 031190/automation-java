package cucumber.stepDefinitions;
import com.testapi.apis.PetApi;
import com.testapi.init.BaseTest;
import com.testapi.pojo.petApiPojos.Pet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

public class PetApiSteps extends BaseTest {

    Response response;

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
}
