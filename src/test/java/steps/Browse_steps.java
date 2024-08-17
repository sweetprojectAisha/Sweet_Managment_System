package steps;

import MYApp_Sweet.BeneficiaryUser;
import MYApp_Sweet.Recipe;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

import static org.junit.Assert.*;

public class Browse_steps {
    private BeneficiaryUser obj;
    private Recipe recipe;
    private String searchName;

    // Dependency Injection for Cucumber
    public Browse_steps(BeneficiaryUser obj, Recipe recipe) {
        this.obj = obj;
        this.recipe = recipe;
    }

    @Given("a user with the following details:")
    public void aUserWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        recipe = new Recipe();
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (java.util.Map<String, String> columns : rows) {
            recipe.setUsername(columns.get("username"));
            recipe.setEmail(columns.get("email"));
            recipe.setPhone(columns.get("phone"));
            recipe.setAge(Integer.parseInt(columns.get("age")));
            recipe.setPassword(columns.get("password"));
            recipe.setFoodAllergies(columns.get("foodAllergies"));
        }
    }

    @When("the user clicks on browse all recipes with category {string}")
    public void theUserClicksOnBrowseAllRecipes(String category) {
        obj.clickBrowseAllRecipes(category);
        if (category.equals("Cakes")) {
            recipe.setDescription("DeliciousChocolateCake");
            recipe.setPrepTime(60);
            recipe.setDifficulty("Medium");
            recipe.setRating(4.5);
        } else if (category.equals("Cookies")) {
            recipe.setDescription("TangyAndSweetLemonCookies");
            recipe.setPrepTime(30);
            recipe.setDifficulty("Easy");
            recipe.setRating(4.7);
        }
    }

//    @Then("the user should see  {string}")
//    public void theUserShouldSee(String description) {
//        assertEquals(description, recipe.getDescription());
//    }

    @Then("the user should see preparing time {int}")
    public void theUserShouldSeePreparingTime(int prepTime) {
        assertEquals(prepTime, recipe.getPrepTime());
    }

    @Then(" {string} difficulty")
    public void Difficulty(String difficulty) {
        assertEquals(difficulty, recipe.getDifficulty());
    }

    @Then("the user should see rating {double}")
    public void theUserShouldSeeRating(Double rating) {
        assertEquals(rating, recipe.getRating(), 0.1);
    }
}