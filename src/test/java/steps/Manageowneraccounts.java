package steps;
import MYApp_Sweet.ManageOwnerAccountsC;
import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class Manageowneraccounts {
    ManageOwnerAccountsC mo;
    List<Map<String, String>> owners;
String uname;
    private List<ManageOwnerAccountsC.UserAccount> currentOwners;
    public Manageowneraccounts() {
        mo = ManageOwnerAccountsC.getInstance();
        owners = new ArrayList<>();
    }


    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
        mo.setIs_an_admin(true);
        assertTrue(mo.isIs_an_admin());
    }

    @When("the admin adds a store owner account with the following details:")
    public void the_admin_adds_a_store_owner_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name, email,phone;
        int age;
         ManageOwnerAccountsC.UserAccount account;
        currentOwners = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name =columns.get("Name");
            email = columns.get("Email");
            phone = columns.get("Phone");
            age = Integer.parseInt(columns.get("Age"));
            account = new ManageOwnerAccountsC.UserAccount(name,email,phone,age);
            currentOwners.add(account);
            try {
                mo.addUserAccount(account);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the store owner account should be added successfully")
    public void the_store_owner_account_should_be_added_successfully() {
        for (ManageOwnerAccountsC.UserAccount currentowner : currentOwners) {
            ManageOwnerAccountsC.UserAccount account = mo.getUser(currentowner.getName());
            assertEquals(currentowner.getName(), account.getName());
            assertEquals(currentowner.getEmail(), account.getEmail());
            assertEquals(currentowner.getPhone(), account.getPhone());
            assertEquals(currentowner.getAge(), account.getAge());

        }
    }

    @When("the admin adds a owner account with the following missing details:")
    public void the_admin_adds_a_owner_account_with_the_following_missing_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name, email,phone;
        int age;
        ManageOwnerAccountsC.UserAccount account;
        currentOwners = new ArrayList<>();
        for (Map<String, String> columns : rows) {

            name =columns.get("Name");
            email = columns.get("Email");
            phone = columns.get("Phone");
            age = Integer.parseInt(columns.get("Age"));
            account = new ManageOwnerAccountsC.UserAccount(name,email,phone,age);
            currentOwners.add(account);
            try {
                mo.addUserAccount(account);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Invalid user account details."));
            }
        }

    }

    @When("the admin updates the store owner account with the following details:")
    public void the_admin_updates_the_store_owner_account_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name, email,phone;
        int age;
        ManageOwnerAccountsC.UserAccount account;
        currentOwners = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            name =columns.get("Name");
            email = columns.get("Email");
            phone = columns.get("Phone");
            age = Integer.parseInt(columns.get("Age"));
            account = new ManageOwnerAccountsC.UserAccount(name,email,phone,age);
            currentOwners.add(account);
            try {
               mo.updateUserAccount(account);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the store owner account should be updated successfully")
    public void the_store_owner_account_should_be_updated_successfully() {
        for (ManageOwnerAccountsC.UserAccount currentowner : currentOwners) {
            ManageOwnerAccountsC.UserAccount account = mo.getUser(currentowner.getName());
            assertNotNull(account);
            assertEquals(currentowner.getName(), account.getName());
            assertEquals(currentowner.getEmail(), account.getEmail());
            assertEquals(currentowner.getPhone(), account.getPhone());
            assertEquals(currentowner.getAge(), account.getAge());
        }
    }




    @When("the admin deletes the raw material supplier account with name {string}")
    public void the_admin_deletes_the_raw_material_supplier_account_with_name(String name) {
uname=name;
        try {
            mo.deleteUserAccount(name);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the raw material supplier account should be deleted successfully")
    public void the_raw_material_supplier_account_should_be_deleted_successfully() {

            assertNull(mo.getUserr(uname));

    }

}
