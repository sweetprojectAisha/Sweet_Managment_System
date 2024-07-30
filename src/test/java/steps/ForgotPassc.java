package steps;

import MyApp_sweet.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ForgotPassc {

    MyApp app;

    public ForgotPassc(MyApp app) {
        this.app = app;
    }

    @Given("the list of registered users includes:")
    public void the_list_of_registered_users_includes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
        app.setValidUsers(users);
    }

    @When("the user enters {string} in the email field")
    public void the_user_enters_in_the_email_field(String email) {
      app.emailForPassChanging(email);
    }

    @When("the user submits the password reset request")
    public void the_user_submits_the_password_reset_request() {
        app.submitForgotPass();
    }

    @When("the user submits the password reset request with empty email field")
    public void the_user_submits_the_password_reset_request_with_empty_email_field() {
        app.emailForPassChanging("");
        app.submitForgotPass();
    }

    @Then("the user should remain on the {string}")
    public void the_user_should_remain_on_the(String exppage) {
       Assert.assertEquals(exppage,app.getExppage());
    }

}
