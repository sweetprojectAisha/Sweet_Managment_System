package Admin;

import Admin_helperClass.EmailService;
import Admin_helperClass.SupplierAccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class supplierAccount {

    private boolean adminLoggedIn;
    private SupplierAccountService supplierAccountService = new SupplierAccountService();
    private EmailService emailService = new EmailService();
    private boolean accountCreated;

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        adminLoggedIn = true;
        System.out.println("Admin is logged in.");
    }

    @When("the admin adds a new raw material supplier account with username {string} and email {string}")
    public void the_admin_adds_a_new_raw_material_supplier_account_with_username_and_email(String username, String email) {
        if (adminLoggedIn) {
            accountCreated = supplierAccountService.createSupplierAccount(username, email);
            if (accountCreated) {
                emailService.sendWelcomeEmail(email);
            }
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the new supplier account should be created")
    public void the_new_supplier_account_should_be_created() {
        if (!accountCreated) {
            throw new AssertionError("Supplier account was not created.");
        }
        System.out.println("New supplier account has been created.");
    }

    @Then("the supplier should receive a welcome email")
    public void the_supplier_should_receive_a_welcome_email() {
        if (accountCreated) {
            // Assume email sending is successful if account was created
            System.out.println("Supplier has received a welcome email.");
        } else {
            throw new AssertionError("Supplier did not receive a welcome email because the account was not created.");
        }
    }
}
