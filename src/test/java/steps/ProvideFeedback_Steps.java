
package steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.Dessert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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

            String priceStr = row.get("price");
            if (priceStr == null || priceStr.trim().isEmpty()) {
                System.out.println("Price is null or empty");
                continue; // Handle this case appropriately (e.g., log error or set a default value)
            }

            try {
                dessert.setPrice(Double.parseDouble(priceStr));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format: " + priceStr);
                continue; // Handle invalid format case
            }

            dessert.setAvailability(row.get("availability"));
            dessert.setDietaryInfo(row.get("dietaryInfo"));
            availableDesserts.add(dessert);
        }
        object4.setAvailableDesserts(availableDesserts);
    }

    @When("the user selects a product to provide feedback:")
    public void theUserSelectsAProductToProvideFeedback(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        boolean productSelected = false;  // Track if a product is successfully selected

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
                productSelected = true;  // Mark that a product has been selected
                break;  // Exit the loop as we've successfully selected a product
            }
        }

        if (!productSelected) {
            System.out.println("Error: No product selected for feedback.");
            object4.displayError("No product selected for feedback.");
        }
    }

    @When("the user submits feedback with the rating {string} and comment {string}")
    public void theUserSubmitsFeedbackWithTheRatingAndComment(String rating, String comment) {
        object4.submitFeedback(rating, comment);
    }

    @Then("the feedback log should be updated with:")
    public void theFeedbackLogShouldBeUpdatedWith(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> feedbackLogData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : feedbackLogData) {
            String itemName = row.get("itemName");
            String rating = row.get("rating");
            String comment = row.get("comment");
            String feedbackDate = row.get("feedbackDate");
            String feedbackStatus = row.get("feedbackStatus");

            object4.verifyFeedbackLog(itemName, rating, comment, feedbackDate, feedbackStatus);
        }
    }

    @Then("^the user should see a confirmation message \"([^\"]*)\"$")
    public void theUserShouldSeeAConfirmationMessage(String expectedMessage) {
        String actualMessage = object4.getConfirmationMessage();
        assertEquals("Expected: " + expectedMessage + " but got: " + actualMessage, expectedMessage, actualMessage);
    }

    @Then("^the user should see an error message \"([^\"]*)\"$")
    public void theUserShouldSeeAnErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = object4.getDisplayedErrorMessage();
        assertEquals("Error message does not match", expectedErrorMessage, actualErrorMessage);
    }


    @When("the user selects a recipe to provide feedback:")
    public void theUserSelectsARecipeToProvideFeedback(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String itemName = row.get("itemName");
            String purchaseDate = row.get("purchaseDate");

            if (!object4.isProductPurchased(itemName, purchaseDate)) {
                System.out.println("Error: Cannot provide feedback on an unpurchased product");
                object4.displayError("Cannot provide feedback on an unpurchased product");
            } else {
                System.out.println("Recipe selected: " + itemName + " with purchase date: " + purchaseDate);
                object4.selectRecipeForFeedback(itemName, purchaseDate);
            }
        }
    }
}
