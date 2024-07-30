


package steps;

import MyApp_sweet.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
public class SignupPagec {

    MyApp app;

    public SignupPagec(MyApp app) {
        this.app = app;
    }
    @Given("the person is on the sign_up page")
    public void the_person_is_on_the_sign_up_page() {
        app.is_in_signuppage=true;
        Assert.assertTrue(app.is_in_signuppage);
    }

    @Given("the list of registered user includes:")
    public void the_list_of_registered_user_includes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);
        app.setValidUsers(users);
    }

    @When("the user fills in sign_up form with:")
    public void the_user_fills_in_sign_up_form_with(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> userDetails = dataTable.asMaps(String.class, String.class).get(0);
        String name = userDetails.get("name");
        String email = userDetails.get("email");
        String phone = userDetails.get("phone");
        String age = userDetails.get("age");
        String password = userDetails.get("password");
        String confPassword = userDetails.get("confPassword");

        app.fillSignUppage(name, email, phone, age, password, confPassword);
    }

    @When("the user submits the sign_up form")
    public void the_user_submits_the_sign_up_form() {
        app.submitSignup();
    }

    @Then("the login table should include:")
    public void the_login_table_should_include(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedUsers = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> expectedUser : expectedUsers) {
            String username = expectedUser.get("username");
            String password = expectedUser.get("password");
            String userType = expectedUser.get("userType");

            if (app.getValidationErrors().isEmpty()) {
                Assert.assertTrue(app.isUserInLoginTable(username, password, userType));
            } else {
                Assert.assertFalse(app.isUserInLoginTable(username, password, userType));
            }
        }
    }





}
