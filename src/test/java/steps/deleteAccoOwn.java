package Admin;

import Admin_helperClass.UserManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class deleteAccountOwn {

    private UserManager userManager = new UserManager();
    private boolean adminLoggedIn;
    private boolean storeOwnerAccountExists;
    private boolean accountDeleted;
    private String username;

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        adminLoggedIn = true;
        System.out.println("Admin is logged in.");
    }

    @Given("a store owner account with username {string} exists")
    public void a_store_owner_account_with_username_exists(String username) {
        if (adminLoggedIn) {
            this.username = username;
            userManager.addStoreOwner(username);
            storeOwnerAccountExists = userManager.isStoreOwnerExists(username);
            System.out.println("Store owner account with username " + username + " exists.");
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @When("the admin deletes the store owner account")
    public void the_admin_deletes_the_store_owner_account() {
        if (storeOwnerAccountExists) {
            userManager.deleteStoreOwner(username);
            accountDeleted = !userManager.isStoreOwnerExists(username);
            System.out.println("Store owner account has been deleted.");
        } else {
            throw new AssertionError("Store owner account does not exist.");
        }
    }

    @Then("the store owner account should be marked as deleted")
    public void the_store_owner_account_should_be_marked_as_deleted() {
        if (accountDeleted) {
            System.out.println("Store owner account is marked as deleted.");
        } else {
            throw new AssertionError("Store owner account was not deleted.");
        }
    }

    @Then("the store owner cannot log into the account again")
    public void the_store_owner_cannot_log_into_the_account_again() {
        if (accountDeleted) {
            System.out.println("Store owner cannot log into the account again.");
        } else {
            throw new AssertionError("Account is not deleted, login should be possible.");
        }
    }
}
