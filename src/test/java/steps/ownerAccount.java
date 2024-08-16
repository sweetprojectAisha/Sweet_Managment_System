package Admin;

import Admin_helperClass.AdminService;
import Admin_helperClass.EmailService;
import Admin_helperClass.StoreOwnerAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ownerAccount {

    private AdminService adminService = new AdminService();
    private EmailService emailService = new EmailService();
    private StoreOwnerAccount storeOwnerAccount;

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        if (adminService.login()) {
            System.out.println("Admin is logged in.");
        }
    }

    @When("the admin adds a new store owner account with username {string} and email {string}")
    public void the_admin_adds_a_new_store_owner_account_with_username_and_email(String username, String email) {
        if (adminService.isLoggedIn()) {
            storeOwnerAccount = adminService.createStoreOwnerAccount(username, email);
            System.out.println("Store owner account created with username: " + username + " and email: " + email);
        } else {
            throw new AssertionError("Admin is not logged in. Cannot create store owner account.");
        }
    }

    @Then("the new store owner account should be created and the store owner is expected to receive a welcome message by email.")
    public void the_new_store_owner_account_should_be_created_and_the_store_owner_is_expected_to_receive_a_welcome_message_by_email() {
        if (storeOwnerAccount != null) {
            System.out.println("New store owner account has been created.");
            emailService.sendWelcomeEmail(storeOwnerAccount.getEmail());
        } else {
            throw new AssertionError("Store owner account was not created.");
        }
    }
}
