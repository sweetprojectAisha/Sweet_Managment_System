package sweet.pal.AcceptanaceTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import sweet.pal.BeneficiaryUser;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectlyCommunicate_Steps {
 BeneficiaryUser object3;

 public DirectlyCommunicate_Steps(BeneficiaryUser object) {
  this.object3 = object;
 }

 @Given("the user is on the communication page")
 public void theUserIsOnTheCommunicationPage() {
  // Navigate or simulate navigating to the communication page
  System.out.println("User is on the communication page.");
 }

 @Given("the list of available contacts includes:")
 public void theListOfAvailableContactsIncludes(DataTable dataTable) {
  // Get the list of available contacts from the DataTable
  List<Map<String, String>> contacts = dataTable.asMaps(String.class, String.class);
  for (Map<String, String> contact : contacts) {
   System.out.println("Available contact: " + contact.get("contactName"));
   // Add logic to display or set available contacts, if needed
  }
 }

 @When("the user selects a contact:")
 public void theUserSelectsAContact(DataTable dataTable) {
  // Get the selected contact details from the DataTable
  List<Map<String, String>> selectedContacts = dataTable.asMaps(String.class, String.class);
  for (Map<String, String> contact : selectedContacts) {
   String contactName = contact.get("contactName");
   System.out.println("User selects contact: " + contactName);
   // Logic to simulate selecting the contact
   object3.selectContact(contactName);
  }
 }

 @When("the user sends an inquiry with the message {string}")
 public void theUserSendsAnInquiryWithTheMessage(String message) {
  // Simulate sending an inquiry with the provided message
  System.out.println("User sends an inquiry with the message: " + message);
  object3.sendInquiry(message);
 }

 @Then("the user should see a {string}")
 public void theUserShouldSeeA(String response) {
  // Simulate checking if the user sees the expected response
  System.out.println("User should see: " + response);
  String actualResponse = object3.getResponse(); // assuming a method exists in BeneficiaryUser
  assertEquals(response, actualResponse);
 }

 @Then("the communication log should be updated with:")
 public void theCommunicationLogShouldBeUpdatedWith(DataTable dataTable) {
  // Get the expected communication log from the DataTable
  List<Map<String, String>> logEntries = dataTable.asMaps(String.class, String.class);
  for (Map<String, String> entry : logEntries) {
   String expectedLogEntry = entry.get("logEntry");
   System.out.println("Expected log entry: " + expectedLogEntry);
   // Logic to check if the communication log has been updated accordingly
   List<String> actualLogEntries = object3.getCommunicationLog(); // assuming a method exists in BeneficiaryUser
   assertTrue(actualLogEntries.contains(expectedLogEntry));
  }
 }
}
