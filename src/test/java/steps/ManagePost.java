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
public class ManagePost {
    ManageRecipeC  mp;
    private List<ManageRecipeC.Recipe> searchResults = new ArrayList<>();
    public ManagePost() {
        mp = ManageRecipeC.getInstance();

    }

    @Given("I am on the post creation page")
    public void i_am_on_the_post_creation_page() {
       mp.setNavagates_to_postpage(true);
       assertTrue(mp.isNavagates_to_postpage());
    }

    @When("I enter the post details {string}, {string}, [{string}, {string}]")
    public void i_enter_the_post_details(String title, String content, String tag1, String tag2) {
        List<String> tags = Arrays.asList(tag1, tag2);

        try {
            mp.addPost(title, content, tags);
            ManageRecipeC.Post post = mp.getPost(title);
            assertNotNull("The post should be added to the list.", post);
            assertEquals("The content should match.", content, post.getContent());
            assertEquals("The tags should match.", tags, post.getTags());
        } catch (Exception ex) {
            fail("Exception occurred: " + ex.getMessage());
        }
    }

    @Then("I should see the post {string} in the post list")
    public void i_should_see_the_post_in_the_post_list(String title) {
        try {
            ManageRecipeC.Post post = ManageRecipeC.getInstance().getPost(title);
            assertNotNull("Post with title '" + title + "' should be in the post list.", post);
        } catch (Exception ex) {
          fail();
        }
    }




    @When("I update the post {string} with new details {string}, {string}, [{string},{string}]")
    public void i_update_the_post_with_new_details(String oldTitle, String newTitle, String newContent, String tag1, String tag2) {
        try {
            List<String> newTags = Arrays.asList(tag1, tag2);
            mp.updatePost(oldTitle, newTitle, newContent, newTags);
            ManageRecipeC.Post updatedPost = mp.getPost(newTitle);
            assertNotNull("Updated post should be in the list.", updatedPost);
            assertEquals("The content should match.", newContent, updatedPost.getContent());
            assertEquals("The tags should match.", newTags, updatedPost.getTags());
        } catch (Exception ex) {
            fail("Exception occurred: " + ex.getMessage());
        }
    }



    @When("I delete the post {string}")
    public void i_delete_the_post(String title) {
        try {
            mp.deletePost(title);
        } catch (Exception ex) {
            fail();
        }
    }

    @Then("I should not see the post {string} in the post list")
    public void i_should_not_see_the_post_in_the_post_list(String title) {
        ManageRecipeC.Post post=mp.getPost(title);
        assertNull(post);
    }

    @Given("I have the following posts:")
    public void i_have_the_following_posts(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> postsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> postMap : postsList) {
            String title = postMap.get("Title");
            String content = postMap.get("Content");
            String tags = postMap.get("Tags");

            List<String> tagsList = Arrays.asList(tags.split(", "));

            try {
                mp.addPost(title, content, tagsList);
            } catch (Exception ex) {
                fail();
            }
        }
    }


    @Then("I should see the post {string} in the search results")
    public void i_should_see_the_post_in_the_search_results(String title) {
        List<ManageRecipeC.Post> searchResults = mp.searchPosts(title);
        boolean postFound = searchResults.stream()
                .anyMatch(post -> post.getTitle().equals(title));
        assertTrue("Post with title '" + title + "' was not found in the search results.", postFound);
    }

}
