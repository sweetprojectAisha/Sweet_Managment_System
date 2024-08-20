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
public class ManageFeedback {
    ManageRecipeC  mf;
    public ManageFeedback() {
        mf = ManageRecipeC.getInstance();

    }
    @When("I navigate to the feedback page")
    public void i_navigate_to_the_feedback_page() {
        mf.setNavagates_to_feedbackpage(true);
        assertTrue(mf.isNavagates_to_feedbackpage());
    }

    @When("I submit feedback with the following details:")
    public void i_submit_feedback_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> feedbackDetails = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> feedback : feedbackDetails) {
            String title = feedback.get("title");
            String comment = feedback.get("comment");
            int rating = Integer.parseInt(feedback.get("rating"));
            mf.addFeedback(title, comment, rating);
        }
    }

    @Then("I should see a list of feedback with comments and ratings")
    public void i_should_see_a_list_of_feedback_with_comments_and_ratings() {
        List<ManageRecipeC.Feedback> feedbackList = mf.getFeedbackList();
        assertNotNull("Feedback list should not be null", feedbackList);
        List<ManageRecipeC.Feedback> expectedFeedbackList = new ArrayList<>();
        expectedFeedbackList.add(new ManageRecipeC.Feedback("Recipe One", "Delicious recipe!", 5));
        expectedFeedbackList.add(new ManageRecipeC.Feedback("Recipe Two", "Too salty", 3));
        for (int i = 0; i < expectedFeedbackList.size(); i++) {
            ManageRecipeC.Feedback expectedFeedback = expectedFeedbackList.get(i);
            ManageRecipeC.Feedback actualFeedback = feedbackList.get(i);

            assertNotNull(actualFeedback);
            assertEquals(expectedFeedback.getRecipeTitle(), actualFeedback.getRecipeTitle());
            assertEquals(expectedFeedback.getComment(), actualFeedback.getComment());
            assertEquals(expectedFeedback.getRating(), actualFeedback.getRating());
        }
    }

    @When("I respond to the feedback with:")
    public void i_respond_to_the_feedback_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> responseDetails = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> responseDetail : responseDetails) {
            String title = responseDetail.get("title");
            String response = responseDetail.get("response");
            try{
            ManageRecipeC.getInstance().respondToFeedback(title, response);
            assertTrue(true);
            }
            catch (Exception ex){
                fail();
            }
        }
    }



    @When("I delete the feedback with the comment {string}")
    public void i_delete_the_feedback_with_the_comment(String comment) {
        ManageRecipeC manageRecipeC = ManageRecipeC.getInstance();
        try {
            manageRecipeC.deleteFeedback(comment);
        } catch (Exception e) {

           fail();
        }
    }

    @Then("the feedback should be removed from the system")
    public void the_feedback_should_be_removed_from_the_system() {
        ManageRecipeC manageRecipeC = ManageRecipeC.getInstance();
        List<ManageRecipeC.Feedback> feedbackList = manageRecipeC.getFeedbackList();
        String commentToCheck = "Too salty";
        ManageRecipeC.Feedback feedbackToCheck = feedbackList.stream()
                .filter(feedback -> feedback.getComment().equals(commentToCheck))
                .findFirst()
                .orElse(null);
        assertNull("The feedback with comment '" + commentToCheck + "' was not removed.", feedbackToCheck);
    }
}
