package Admin;

import Admin_helperClass.SupplierAccountManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class deleteAccountSupp {

    private SupplierAccountManager accountManager = new SupplierAccountManager();

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        accountManager.logInAdmin();
        System.out.println("Admin is logged in.");
    }

    @Given("a raw material supplier account with username {string} exists")
    public void a_raw_material_supplier_account_with_username_exists(String username) {
        if (accountManager.isAdminLoggedIn()) {
            accountManager.createSupplierAccount(username);
            System.out.println("Raw material supplier account with username " + username + " exists.");
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @When("the admin delete the raw material supplier account")
    public void the_admin_delete_the_raw_material_supplier_account() {
        if (accountManager.isSupplierAccountExists()) {
            accountManager.deleteSupplierAccount();
            System.out.println("Raw material supplier account has been deleted.");
        } else {
            throw new AssertionError("Raw material supplier account does not exist.");
        }
    }

    @Then("the raw material supplier account should be marked as delete")
    public void the_raw_material_supplier_account_should_be_marked_as_delete() {
        if (accountManager.isAccountDeleted()) {
            System.out.println("Raw material supplier account is marked as deleted.");
        } else {
            throw new AssertionError("Raw material supplier account was not deleted.");
        }
    }

    @Then("the raw material supplier cannot log into the account again")
    public void the_raw_material_supplier_cannot_log_into_the_account_again() {
        if (accountManager.isAccountDeleted()) {
            System.out.println("Raw material supplier cannot log into the account again.");
        } else {
            throw new AssertionError("Account is not deleted, login should be possible.");
        }
    }
}
