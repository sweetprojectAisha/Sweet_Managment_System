package steps;

import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.Recipe;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

public class Browse_steps {
    private BeneficiaryUser obj;
    private Recipe recipe;

    // Dependency Injection for Cucumber
    public Browse_steps(BeneficiaryUser obj) {
        this.obj = obj;
    }

    @Given("the user has the following recipes:")
    public void theUserHasTheFollowingRecipes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        List<Recipe> recipes = new ArrayList<>();

        for (Map<String, String> row : data) {
            String name = row.get("sweetname");
            String category = row.get("category");
            String description = row.get("description");
            String prepTimeStr = row.get("prepTime");
            String difficulty = row.get("difficulty");
            String ratingStr = row.get("rating");
            int prepTime = 0;
            double rating = 0.0;

            try {
                if (prepTimeStr != null && !prepTimeStr.isEmpty()) {
                    prepTime = Integer.parseInt(prepTimeStr);
                }
                if (ratingStr != null && !ratingStr.isEmpty()) {
                    rating = Double.parseDouble(ratingStr);
                }

                Recipe recipe = new Recipe(name, category, description, prepTime, difficulty, rating,"","");
                recipes.add(recipe);
                System.out.println("Added recipe with sweetname: " + recipe.getSweetname());
            } catch (NumberFormatException ex) {
                System.err.println("Error parsing number: " + ex.getMessage());
            }
        }
        obj.setRecipes(recipes);
        assertNotNull("Recipes should not be null", obj.getRecipes());
        assertFalse("Recipes list should not be empty", obj.getRecipes().isEmpty()); // Ensure list is not empty
    }

    @When("the user clicks on browse all recipes")
    public void theUserClicksOnBrowseAllRecipes() {
        if (obj.getRecipes() != null && !obj.getRecipes().isEmpty()) {
            recipe = obj.getRecipes().get(0); // Get the first recipe as a default action
        }if (recipe != null) {
            recipe = obj.clickBrowseAllRecipes(recipe.getCategory());
        }
    }

    @When("filters by category {string}")
    public void filtersByCategory(String category) {
        recipe = obj.clickBrowseAllRecipes(category);
    }

    @When("the user searches for a recipe with sweet name {string}")
    public void theUserSearchesForARecipeWithSweetName(String sweetname) {
        recipe = obj.searchRecipeBySweetname(sweetname); // Assuming BeneficiaryUser has a searchRecipeBySweetname method
        assertNotNull("Recipe should not be null when searching by sweet name '" + sweetname + "'", recipe);
        // Additional checks can be added if necessary
        System.out.println("Search result: " + recipe.getSweetname()); }

    @Then("the user should see recipe with description {string}")
    public void theUserShouldSeeRecipeWithDescription(String expectedDescription) {
        assertNotNull("Recipe object should not be null", recipe); // Ensure recipe is not null
        assertEquals(expectedDescription, recipe.getDescription());
    }


    @Then("the user should see preparation time {int}")
    public void theUserShouldSeePreparationTime(int expectedPrepTime) {
        assertEquals(expectedPrepTime, recipe.getPrepTime());
    }

    @Then("the user should see difficulty level {string}")
    public void theUserShouldSeeDifficultyLevel(String expectedDifficulty) {
        assertEquals(expectedDifficulty, recipe.getDifficulty());
    }

    @Then("the user should see rating {double}")
    public void theUserShouldSeeRating(double expectedRating) {
        assertEquals(expectedRating, recipe.getRating(), 0.1);
    }

    @Then("the user should see category {string}")
    public void theUserShouldSeeCategory(String expectedCategory) {
        assertEquals(expectedCategory, recipe.getCategory());
    }
    @Given("a user with the following details:")
    public void aUserWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        List<Recipe> recipes = new ArrayList<>();

        for (Map<String, String> row : data) {
            String username = row.get("username");
            String email = row.get("email");
            String phone = row.get("phone");
            String age = row.get("age");
            String password = row.get("password");
            String foodAllergies = row.get("foodAllergies");
            //    obj = new BeneficiaryUser(username, email, phone, age, password, foodAllergies);

            // If needed, you can add recipes for this user
            // Example: recipes.add(new Recipe("sweetname", "category", "description", prepTime, difficulty, rating));
            obj.setRecipes(recipes); // Assuming BeneficiaryUser has a setRecipes method
        }
        assertNotNull(obj); // Verify that the user object is created
        assertEquals("validuser", obj.getUsername()); // Verify username if you have a specific value
        assertEquals("validemail", obj.getEmail()); // Verify email
        assertEquals("0599873421", obj.getPhone()); // Verify phone
        assertEquals("21", obj.getAge()); // Verify age
        assertEquals("12345678a@", obj.getPassword()); // Verify password
        assertEquals("Gluten", obj.getFoodAllergies());

    }
    @When("filters by category Cakes")
    public void filtersByCategoryCakes() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the user should see recipe with description Delicious chocolate cake")
    public void theUserShouldSeeRecipeWithDescriptionDeliciousChocolateCake() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the user should see difficulty level Medium")
    public void theUserShouldSeeDifficultyLevelMedium() {
        // Write code here that turns the phrase above into concrete actions

    }
    @When("the user searches for a recipe with sweet name lemon cookies")
    public void theUserSearchesForARecipeWithSweetNameLemonCookies() {
        recipe = obj.searchRecipeBySweetname("lemon cookies");
        assertNotNull("Recipe should not be null when searching by sweet name 'lemon cookies'", recipe);
    }

    @Then("the user should see category Cookies")
    public void theUserShouldSeeCategoryCookies() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Cookies", recipe.getCategory());
    }

    @Then("the user should see description Tangy and sweet lemon cookies")
    public void theUserShouldSeeDescriptionTangyAndSweetLemonCookies() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Tangy and sweet lemon cookies", recipe.getDescription());
    }

    @Then("the user should see difficulty level Easy")
    public void theUserShouldSeeDifficultyLevelEasy() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Easy", recipe.getDifficulty());
    }

    @When("the user searches for a recipe with sweet name chocolate cake")
    public void theUserSearchesForARecipeWithSweetNameChocolateCake() {
        recipe = obj.searchRecipeBySweetname("chocolate cake");
        assertNotNull("Recipe should not be null when searching by sweet name 'chocolate cake'", recipe);
    }

    @Then("the user should see category Cakes")
    public void theUserShouldSeeCategoryCakes() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Cakes", recipe.getCategory());
    }

    @Then("the user should see description Delicious chocolate cake")
    public void theUserShouldSeeDescriptionDeliciousChocolateCake() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Delicious chocolate cake", recipe.getDescription());
    }

    @When("filters by category Cookies")
    public void filtersByCategoryCookies() {
        recipe = obj.clickBrowseAllRecipes("Cookies");
        assertNotNull("Recipe should not be null when filtering by category 'Cookies'", recipe);
    }

    @Then("the user should see recipe with description Tangy and sweet lemon cookies")
    public void theUserShouldSeeRecipeWithDescriptionTangyAndSweetLemonCookies() {
        assertNotNull("Recipe should not be null", recipe); // Ensure recipe is not null
        assertEquals("Tangy and sweet lemon cookies", recipe.getDescription());
    }





}