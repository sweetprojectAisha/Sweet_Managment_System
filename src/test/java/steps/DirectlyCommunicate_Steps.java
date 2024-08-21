
package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.Recipe;
import MYApp_Sweet.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DirectlyCommunicate_Steps {
    private BeneficiaryUser object3;

    public DirectlyCommunicate_Steps(BeneficiaryUser object) {
        this.object3 = object;
    }

    @Given("the user is on the communication page")
    public void theUserIsOnTheCommunicationPage() {
        // Simulate navigating to the communication page
        System.out.println("User is on the communication page.");
    }

    @Given("the list of available contacts includes:")
    public void theListOfAvailableContactsIncludes(io.cucumber.datatable.DataTable dataTable) {
        // Get the list of available contacts from the DataTable
        List<Map<String, String>> contacts = dataTable.asMaps(String.class, String.class);
        List<user> users = new ArrayList<>();
        for (Map<String, String> row : contacts) {
            try {
                // Retrieve the userId as a string
                String userIdStr = row.get("userId");

                // Check if userIdStr is null or empty
                if (userIdStr == null || userIdStr.isEmpty()) {
                    System.err.println("Skipping contact due to invalid userId: " + userIdStr);
                    continue; // Skip this entry and move to the next one
                }

                // Parse the userId string to an integer
                int id = Integer.parseInt(userIdStr);
                String name = row.get("username");
                String role = row.get("role");

                // Create a new user object
                user user = new user(id, name, role);

                // Add the user to the list
                users.add(user);

                // Assert that the user was added successfully
                assertTrue(users.contains(user));
            } catch (NumberFormatException e) {
                System.err.println("Invalid userId: " + row.get("userId"));
                fail("Failed to parse userId: " + row.get("userId"));
            } catch (Exception ex) {
                fail("Unexpected error: " + ex.getMessage());
            }
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
        String actualResponse = object3.getResponse();
        assertTrue("Expected response to contain: " + response, actualResponse.contains(response));
    }


    @Then("the communication log should be updated with:")
    public void theCommunicationLogShouldBeUpdatedWith(DataTable dataTable) {
        // Get the expected communication log from the DataTable
        List<Map<String, String>> logEntries = dataTable.asMaps(String.class, String.class);
        List<String> actualLogEntries = object3.getCommunicationLog();
        for (Map<String, String> entry : logEntries) {
            String contactName = entry.get("contactName");
            String message = entry.get("message");
            String communicationStatus = entry.get("status");
            String expectedLogEntry = "Sent to " + contactName + ": " + message;

            System.out.println("Expected log entry: " + expectedLogEntry);
            // Check if the communication log has been updated accordingly
            boolean found = false;
            for (String logEntry : actualLogEntries) {
                if (logEntry.equals(expectedLogEntry)) {
                    found = true;
                    break;
                }
            }
            assertTrue("Log entry for contact " + contactName + " not found", found);
        }
    }
}
