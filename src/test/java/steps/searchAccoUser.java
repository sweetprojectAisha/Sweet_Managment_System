package Admin;

import Admin_helperClass.UserAccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class searchAccountUser {

    private boolean adminLoggedIn;
    private UserAccountService userAccountService = new UserAccountService();
    private Map<String, String> foundAccount;

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        adminLoggedIn = true;
        System.out.println("Admin is logged in.");
    }

    @When("the admin searches for a user account with email {string}")
    public void the_admin_searches_for_a_user_account_with_email(String email) {
        if (adminLoggedIn) {
            foundAccount = userAccountService.searchUserByEmail(email);
            if (foundAccount != null) {
                System.out.println("User account found: " + foundAccount);
            } else {
                System.out.println("No user account found with email: " + email);
            }
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the account details for the user with email {string} should be displayed to the admin")
    public void the_account_details_for_the_user_with_email_should_be_displayed_to_the_admin(String email) {
        if (foundAccount != null) {
            System.out.println("Displaying account details for email: " + email);
            System.out.println("Name: " + foundAccount.get("name"));
            System.out.println("Phone: " + foundAccount.get("phone"));
        } else {
            throw new AssertionError("No account details available for the provided email.");
        }
    }
}
