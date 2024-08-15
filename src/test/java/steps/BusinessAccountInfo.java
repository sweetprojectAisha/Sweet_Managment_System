package steps;
import MYApp_Sweet.BusinessAccount;
import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class BusinessAccountInfo {
    BusinessAccount ba;
    ProductManagement pm;
    List<Map<String, String>> details;
    private List<BusinessAccount.BusinessProfile> currentDetails;
    public BusinessAccountInfo() {
        ba = BusinessAccount.getInstance();
        pm=ProductManagement.getInstance();

    }

    @Given("the owner pages are open and the owner navigates to the business profile")
    public void the_owner_pages_are_open_and_the_owner_navigates_to_the_business_profile() {
        assertTrue(pm.isIs_an_owner());
        ba.setNavagates_to_salespage(true);
        assertTrue(ba.isNavagates_to_salespage());
    }

    @When("the owner adds business profile information with the following valid details:")
    public void the_owner_adds_business_profile_information_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id;
        String name, contact,address;

        BusinessAccount.BusinessProfile profile;
        currentDetails = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("BusinessID"));
            name = columns.get("BusinessName");
            contact = columns.get("Contact");
            address = columns.get("Address");
            profile = new BusinessAccount.BusinessProfile(address, contact, name, id);
            currentDetails.add(profile);
            try {
                ba.addProfile(id,address,contact,name);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the details should be added to the profile")
    public void the_details_should_be_added_to_the_profile() {
        for (BusinessAccount.BusinessProfile currentProfile : currentDetails) {
            BusinessAccount.BusinessProfile profile = ba.getProfile(currentProfile.getId());
            assertNotNull(profile);
            assertEquals(currentProfile.getId(), profile.getId());
            assertEquals(currentProfile.getBusinessName(), profile.getBusinessName());
            assertEquals(currentProfile.getAddress(), profile.getAddress());
            assertEquals(currentProfile.getContact(), profile.getContact());

        }
    }



    @When("the owner updates the info with the following valid details:")
    public void the_owner_updates_the_info_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id;
        String name, contact,address;

        BusinessAccount.BusinessProfile profile;
        currentDetails = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("BusinessID"));
            name = columns.get("BusinessName");
            contact = columns.get("Contact");
            address = columns.get("Address");
            profile = new BusinessAccount.BusinessProfile(address, contact, name, id);
            currentDetails.add(profile);
            try {
                ba.updateProfile(id,address,contact,name);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the business information should be updated successfully")
    public void the_business_information_should_be_updated_successfully() {
        for (BusinessAccount.BusinessProfile currentProfile : currentDetails) {
            BusinessAccount.BusinessProfile profile1 = ba.getProfile(currentProfile.getId());
            assertNotNull(profile1);
            assertEquals(currentProfile.getId(), profile1.getId());
            assertEquals(currentProfile.getBusinessName(), profile1.getBusinessName());
            assertEquals(currentProfile.getContact(), profile1.getContact());
            assertEquals(currentProfile.getAddress(), profile1.getAddress());
        }
    }

//
}
