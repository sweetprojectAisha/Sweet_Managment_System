package steps;
import MYApp_Sweet.BusinessAccount;
import MYApp_Sweet.PersonalAccount;
import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class PersonalAccountInfo {
    PersonalAccount pa;
    List<Map<String, String>> details;
    private List<PersonalAccount.PersonalProfile> currentDetails;
    public PersonalAccountInfo() {
        pa = PersonalAccount.getInstance();


    }

    @When("the person adds personal profile information with the following valid details:")
    public void the_person_adds_personal_profile_information_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int age;
        String name, email,phone;

       PersonalAccount.PersonalProfile profile;
        currentDetails = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            age = Integer.parseInt(columns.get("Age"));
            name = columns.get("Name");
            phone =columns.get("phone");
            email = columns.get("email");
            profile = new PersonalAccount.PersonalProfile(name, age, email, phone);
            currentDetails.add(profile);
            try {
                pa.addProfile(name,age,email,phone);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the info should be added to the profile")
    public void the_info_should_be_added_to_the_profile() {
        for (PersonalAccount.PersonalProfile currentProfile : currentDetails) {
            PersonalAccount.PersonalProfile profile = pa.getProfile(currentProfile.getName());
            assertNotNull(profile);
            assertEquals(currentProfile.getName(), profile.getName());
            assertEquals(currentProfile.getAge(), profile.getAge());
            assertEquals(currentProfile.getEmail(), profile.getEmail());
            assertEquals(currentProfile.getPhone(), profile.getPhone());

        }
    }

    @When("the person updates the info with the following valid details:")
    public void the_person_updates_the_info_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int age;
        String name, email,phone;

        PersonalAccount.PersonalProfile profile;
        currentDetails = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            age = Integer.parseInt(columns.get("Age"));
            name = columns.get("Name");
            phone =columns.get("phone");
            email = columns.get("email");
            profile = new PersonalAccount.PersonalProfile(name, age, email, phone);
            currentDetails.add(profile);
            try {
               pa.updateProfile(name,age,email,phone);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the personal information should be updated successfully")
    public void the_personal_information_should_be_updated_successfully() {
        for (PersonalAccount.PersonalProfile currentProfile : currentDetails) {
            PersonalAccount.PersonalProfile profile1 = pa.getProfile(currentProfile.getName());
            assertNotNull(profile1);
            assertEquals(currentProfile.getName(), profile1.getName());
            assertEquals(currentProfile.getAge(), profile1.getAge());
            assertEquals(currentProfile.getPhone(), profile1.getPhone());
            assertEquals(currentProfile.getEmail(), profile1.getEmail());
        }
    }

//    @When("the person updates the info with the following invalid details:")
//    public void the_person_updates_the_info_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
//        // Write code here that turns the phrase above into concrete actions
//        // For automatic transformation, change DataTable to one of
//        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//        // Double, Byte, Short, Long, BigInteger or BigDecimal.
//        //
//        // For other transformations you can register a DataTableType.
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("the personal information update should fail due to invalid details")
//    public void the_personal_information_update_should_fail_due_to_invalid_details() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
}
