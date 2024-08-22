
package steps;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.Recipe;

public class Filter_steps {

    public BeneficiaryUser obj;
    Recipe recipe;
    public  Filter_steps(BeneficiaryUser object,Recipe recipe )
    {
        this.obj=object;
        this.recipe=recipe;
    }


    @Given("the user wants recipes with:")
    public void theUserWantsRecipesWith(io.cucumber.datatable.DataTable dataTable) {
        recipe = new Recipe();
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            recipe.setUsername(columns.get("username"));
            recipe.setEmail(columns.get("email"));
            recipe.setPhone(columns.get("phone"));

            String ageValue = columns.get("age");
            if (ageValue != null && !ageValue.isEmpty()) {
                try {
                    recipe.setAge(Integer.parseInt(ageValue));
                } catch (NumberFormatException e) {
                    // Handle the exception if needed, or log it
                    System.out.println("Invalid age value: " + ageValue);
                }
            } else {
                // Set a default value or handle the missing age appropriately
                recipe.setAge(0);  // Example: default age to 0
            }

            recipe.setPassword(columns.get("password"));
            recipe.setFoodAllergies(columns.get("foodAllergies"));
            recipe.setDietaryNeeds("DietaryNeeds");
        }
    }

    @When("the user filters recipes by food allergies and ingredient")
    public void theUserFiltersRecipesByFoodAllergiesAndIngredient() {
        obj.filter_by_FoodAllergiesAndIngredient(recipe.getFoodAllergies(),recipe.getDietaryNeeds());


    }
    @Then("the user should see recipes without:")
    public void theUserShouldSeeRecipesWithout(io.cucumber.datatable.DataTable dataTable) {
        obj.recipe_without_FoodAllergies(recipe.getFoodAllergies());

    }
    @Then("the user should see recipes with:")
    public void theUserShouldSeeRecipesWith(io.cucumber.datatable.DataTable dataTable) {
        // Process the DataTable and apply the logic as needed
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            String recipeName = columns.get("recipeName");
            // Add logic to verify that the recipe exists as expected
            obj.checkIfRecipeExists(recipeName);
        }
    }


    @Given("the user details:")
    public void theUserDetails(io.cucumber.datatable.DataTable dataTable) {


    }
    @When("the user filters recipes by dietary needs")
    public void theUserFiltersRecipesByDietaryNeeds() {
        obj.recipe_with_ditearyNeeds(recipe.getDietaryNeeds());

    }
    @Then("the user should see recipes that are:")
    public void theUserShouldSeeRecipesThatAre(io.cucumber.datatable.DataTable dataTable) {
        obj.recipe_with_ditearyNeeds(recipe.getDietaryNeeds());

    }
    @When("the user filters recipes by food allergies")
    public void theUserFiltersRecipesByFoodAllergies() {
        obj.recipe_without_FoodAllergies(recipe.getFoodAllergies());

    }
    @When("the user filters recipes by both dietary needs and food allergies")
    public void theUserFiltersRecipesByBothDietaryNeedsAndFoodAllergies() {
        obj.Filter_by_both(recipe.getDietaryNeeds(),recipe.getFoodAllergies());

    }

    @When("the user filters recipes")
    public void theUserFiltersRecipes() {
        obj.filterRecipe();

    }
    @Then("the user should see all available recipes")
    public void theUserShouldSeeAllAvailableRecipes() {
        obj.filterRecipe();

    }

    @When("the user updates dietary needs to:")
    public void theUserUpdatesDietaryNeedsTo(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            // Assuming the dietary needs are stored in a Map or similar structure within BeneficiaryUser
            obj.updateDietaryNeeds(columns.get("dietaryNeeds"));
        }
    }

    @When("the user updates food allergies to:")
    public void theUserUpdatesFoodAllergiesTo(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            // Assuming the food allergies are stored in a List or similar structure within BeneficiaryUser
            obj.updateFoodAllergies(columns.get("foodAllergies"));
        }
    }

    @Given("the user searches for:")
    public void theUserSearchesFor(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            String name = columns.get("name");
            String category = columns.get("category");

            obj.fillname_category(name, category);
        }
    }

    @When("the user applies food allergies filter")
    public void theUserAppliesFoodAllergiesFilter() {
        // Assuming you have a method to apply the food allergies filter within BeneficiaryUser
        obj.applyFoodAllergiesFilter();
    }
    @When("the user applies dietary needs filter")
    public void theUserAppliesDietaryNeedsFilter() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the user filters recipes by dietary needs and ingredient")
    public void theUserFiltersRecipesByDietaryNeedsAndIngredient() {
        // Write code here that turns the phrase above into concrete actions

    }
}
