package steps;

import MyApp_sweet.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;


public class LoginPagec {

    MyApp app;
    public LoginPagec(MyApp app) {
        this.app=app;
    }

    @Given("the person is on the login page")
    public void the_person_is_on_the_login_page() {
        app.is_in_loginpage=true;
        Assert.assertTrue(app.is_in_loginpage);
    }

    @Given("the list of valid users includes:")
    public void the_list_of_valid_users_includes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> users=dataTable.asMaps(String.class,String.class);
        app.setValidUsers(users);

    }

    @When("the user enters {string} and {string} as {string}")
    public void the_user_enters_and_as(String username, String password, String userType) {
      app.enterCredentials(username,password);
    }

    @When("the user submits the login form")
    public void the_user_submits_the_login_form() {
      app.loginsubmit();
    }

    @Then("the user should see {string}")
    public void the_user_should_see(String resMessage) {
        String actualMessage = app.getMessage(); // Ensure `getMessage` returns the right messages
        System.out.println("Expected Message: " + resMessage);
        System.out.println("Actual Message: " + actualMessage);
        Assert.assertEquals("Error message does not match", resMessage, actualMessage);


    }

    @Then("the user should be redirected to the {string}")
    public void the_user_should_be_redirected_to_the(String expectedPage) {
        String actualPage = app.getExppage();
        System.out.println("Expected Page: " + expectedPage);
        System.out.println("Actual Page: " + actualPage);
        Assert.assertEquals(expectedPage, actualPage);
    }

    @Then("the user should see {string} for empty fields")
    public void the_user_should_see_for_empty_fields(String resultMessage) {
        String actualMessage = app.getMessage();
        Assert.assertEquals(resultMessage, actualMessage);
    }

    @When("the user enters {string}{string} and {string}{string}")
    public void theUserEntersAnd(String string1, String string2, String string3, String string4) {
        String username = string1 + string2;
        String password = string3 + string4;
        app.enterCredentials(username, password);
    }



    @When("the user enters {string}{string} and {string}")
    public void theUserEntersAnd(String string1, String string2, String password) {
        String username = string1 + string2;
        app.enterCredentials(username, password);
    }

    @When("the user enters {string} and {string}{string}")
    public void theUserEntersWithSplitPassword(String username, String passwordPart1, String passwordPart2) {
        String password = passwordPart1 + passwordPart2;
        app.enterCredentials(username, password);
    }
}

