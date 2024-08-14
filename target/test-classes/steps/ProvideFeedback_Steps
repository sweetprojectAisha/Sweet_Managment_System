package sweet.pal.AcceptanceTest;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import sweet.pal.BeneficiaryUser;
import sweet.pal.Dessert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProvideFeedback_Steps {
    private List<Dessert> availableDesserts = new ArrayList<>();
    private BeneficiaryUser object4;

    public ProvideFeedback_Steps(BeneficiaryUser object) {
        this.object4 = object;
    }

    @Given("the user is on the feedback page")
    public void theUserIsOnTheFeedbackPage() {
        System.out.println("Hello, this is our feedback page");
    }

    @Given("the list of purchased products and shared recipes includes:")
    public void theListOfPurchasedProductsAndSharedRecipesIncludes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            Dessert dessert = new Dessert();
            dessert.setDessertName(row.get("dessertName"));
            dessert.setPrice(Double.parseDouble(row.get("price")));
            dessert.setAvailability(row.get("availability"));
            dessert.setDietaryInfo(row.get("dietaryInfo"));
            availableDesserts.add(dessert);
        }
        // You might want to store this information in the BeneficiaryUser class if necessary
        object4.setAvailableDesserts(availableDesserts);
    }

    @When("the user selects a recipe to provide feedback:")
    public void theUserSelectsARecipeToProvideFeedback(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String itemName = row.get("itemName");
            String purchaseDate = row.get("purchaseDate");

            // Use the BeneficiaryUser object to verify if the product was purchased
            if (!object4.isProductPurchased(itemName, purchaseDate)) {
                System.out.println("Error: Cannot provide feedback on an unpurchased product");
                object4.displayError("Cannot provide feedback on an unpurchased product");
            } else {
                System.out.println("Product selected: " + itemName + " with purchase date: " + purchaseDate);
                object4.selectRecipeForFeedback(itemName, purchaseDate);
            }
        }
    }

    @When("the user submits feedback with the rating {string} and comment {string}")
    public void theUserSubmitsFeedbackWithTheRatingAndComment(String rating, String comment) {
        // Use the BeneficiaryUser object to handle feedback submission
        object4.submitFeedback(rating, comment);
    }

    @Then("the feedback log should be updated with:")
    public void theFeedbackLogShouldBeUpdatedWith(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> feedbackLogData = dataTable.asMaps(String.class, String.class);

        // Iterate over the feedback log data and verify the entries using BeneficiaryUser
        for (Map<String, String> row : feedbackLogData) {
            String itemName = row.get("itemName");
            String rating = row.get("rating");
            String comment = row.get("comment");
            String feedbackDate = row.get("feedbackDate");
            String feedbackStatus = row.get("feedbackStatus");

            // Compare this data with what's stored in BeneficiaryUser's feedback log
            object4.verifyFeedbackLog(itemName, rating, comment, feedbackDate, feedbackStatus);
        }
    }

    @Then("the user should see a confirmation message {string}")
    public void theUserShouldSeeAConfirmationMessage(String expectedMessage) {
        String actualMessage = object4.getConfirmationMessage(); // Replace with actual logic to retrieve the message

        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Confirmation message: " + actualMessage);
        } else {
            throw new AssertionError("Expected: " + expectedMessage + " but got: " + actualMessage);
        }
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = object4.getDisplayedErrorMessage();
        if (actualErrorMessage.equals(expectedErrorMessage)) {
            System.out.println("Error message verified: " + actualErrorMessage);
        } else {
            System.out.println("Expected error message: " + expectedErrorMessage);
            System.out.println("Actual error message: " + actualErrorMessage);
            throw new AssertionError("Error message does not match");
        }
    }
}
