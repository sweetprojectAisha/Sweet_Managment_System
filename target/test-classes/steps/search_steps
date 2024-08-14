package sweet.pal.AcceptanaceTest;
import sweet.pal.BeneficiaryUser;
import sweet.pal.Recipe;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import static sweet.pal.BeneficiaryUser.recipes;
import static org.junit.Assert.*;
public class search_steps
{ BeneficiaryUser b;
    public search_steps(BeneficiaryUser b)
    {
        this.b=b;
    }
    public class RecipeValidationSteps {
        private BeneficiaryUser beneficiaryUser;
        private String name;
        private String category;
        private boolean errorDisplayed;
        private boolean recipeExists;

        public RecipeValidationSteps() {
            beneficiaryUser = new BeneficiaryUser();
        }

        @When("the user fills in name {string} and category {string}")
        public void theUserFillsInNameAndCategory(String name, String category) {
            b.clickSearch();
            this.name = name;
            this.category = category;
            b.searchRecipe(name);
            beneficiaryUser.fillname_category(name, category);
        }

        @Then("an error should be displayed for {string}")
        public void anErrorShouldBeDisplayedFor(String field) {
            errorDisplayed = beneficiaryUser.isErrorDisplayed(field);
            assertTrue("Expected an error to be displayed for " + field, errorDisplayed);
        }

        @Then("no error should be displayed for {string}")
        public void noErrorShouldBeDisplayedFor(String field) {
            errorDisplayed = beneficiaryUser.isErrorDisplayed(field);
            assertFalse("Expected no error to be displayed for " + field, errorDisplayed);
        }

        @When("the user checks if a recipe with name {string} exists")
        public void theUserChecksIfARecipeWithNameExists(String searchName) {
            recipeExists = beneficiaryUser.checkIfRecipeExists(searchName);
        }

        @Then("the recipe should be found")
        public void theRecipeShouldBeFound() {
            assertTrue("Expected the recipe to be found", recipeExists);
        }

        @Then("the recipe should not be found")
        public void theRecipeShouldNotBeFound() {
            assertFalse("Expected the recipe to not be found", recipeExists);
        }




}}
