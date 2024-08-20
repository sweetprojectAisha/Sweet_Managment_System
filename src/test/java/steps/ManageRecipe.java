package steps;
import MYApp_Sweet.DisplayStatisticsByCityC;
import MYApp_Sweet.ManageRecipeC;
import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class ManageRecipe {
 ManageRecipeC  mr;
    private List<ManageRecipeC.Recipe> searchResults = new ArrayList<>();
    public ManageRecipe() {
        mr = ManageRecipeC.getInstance();

    }

    @Given("I am on the recipe creation page")
    public void i_am_on_the_recipe_creation_page() {
       mr.setNavagates_to_recipepage(true);
       assertTrue(mr.isNavagates_to_recipepage());
    }

    @When("I enter the recipe details {string}, [{string}, {string}, {string}], {string}, [{string}]")
    public void i_enter_the_recipe_details(String title, String ingredientsString, String preparation, String servings, String cookingTime, String tagsString) {
        List<String> ingredients = Arrays.asList(ingredientsString.split(", "));
        List<String> tags = Arrays.asList(tagsString.split(", "));
        try {
            mr.addRecipe(title, ingredientsString, preparation, servings, cookingTime, tags);
            assertTrue(true);
        }catch (Exception ex){
            fail();
        }

    }


    @Then("I should see the recipe {string} in the recipe list")
    public void i_should_see_the_recipe_in_the_recipe_list(String title) {
        ManageRecipeC.Recipe recipe=mr.getRecipe(title);
        assertNotNull("Recipe with title " + title + " should be in the recipe list.", recipe);
    }


    @When("I update the recipe {string} with new details {string}, [{string}, {string}, {string}], {string}, [{string}]")
    public void i_update_the_recipe_with_new_details(String title,String newTitle, String ingredientsString, String preparation, String servings, String cookingTime, String tagsString) {
        List<String> ingredients = Arrays.asList(ingredientsString.split(", "));
        List<String> tags = Arrays.asList(tagsString.split(", "));
        try {

            mr.updateRecipe(title, newTitle, ingredients, preparation, servings, cookingTime, tags);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Failed to update recipe: " + ex.getMessage());
        }

    }

    @When("I delete the recipe {string}")
    public void i_delete_the_recipe(String title) {
        try {
            mr.deleteRecipe(title);
            assertTrue(true);
        }catch (Exception ex){
            fail();
        }
    }

    @Then("I should not see the recipe {string} in the recipe list")
    public void i_should_not_see_the_recipe_in_the_recipe_list(String title) {
        ManageRecipeC.Recipe recipe=mr.getRecipe(title);
      assertNull(recipe);
    }

    @Given("I have the following recipes:")
    public void i_have_the_following_recipes(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> recipesList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> recipeMap : recipesList) {
            String title = recipeMap.get("Title");
            String ingredients = recipeMap.get("Ingredients");
            String preparation = recipeMap.get("Preparation");
            String servings = recipeMap.get("Servings");
            String cookingTime = recipeMap.get("Cooking Time");
            String tags = recipeMap.get("Tags");
            List<String> ingredientsList = List.of(ingredients.split(", "));
            List<String> tagsList = List.of(tags.split(", "));
            try{
            mr.addRecipe(title, ingredients, preparation, servings, cookingTime, tagsList);
            assertTrue(true);
            }catch (Exception ex){
                fail();
            }
        }
    }


    @Then("I should see the recipe {string} in the search results")
    public void i_should_see_the_recipe_in_the_search_results(String recipeTitle) {
        searchResults = mr.searchRecipes(recipeTitle);
        boolean recipeFound = searchResults.stream()
                .anyMatch(recipe -> recipe.getTitle().equals(recipeTitle));
        assertTrue("Recipe with title '" + recipeTitle + "' was not found in the search results.", recipeFound);
    }

}

