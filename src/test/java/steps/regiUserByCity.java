package Admin;

import Admin_helperClass.UserStatisticsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class registeredByCity {

    private boolean adminLoggedIn;
    private Map<String, Integer> cityUserCounts;
    private UserStatisticsService userStatisticsService = new UserStatisticsService();

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        adminLoggedIn = true;
        System.out.println("Admin is logged in.");
    }

    @When("the admin navigates to the user statistics page")
    public void the_admin_navigates_to_the_user_statistics_page() {
        if (adminLoggedIn) {
            System.out.println("Admin navigated to the user statistics page.");
            cityUserCounts = userStatisticsService.getUserCountsByCity();
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the admin should see a list of cities with the number of registered users in each city")
    public void the_admin_should_see_a_list_of_cities_with_the_number_of_registered_users_in_each_city() {
        if (cityUserCounts != null && !cityUserCounts.isEmpty()) {
            System.out.println("List of cities with registered user counts:");
            for (Map.Entry<String, Integer> entry : cityUserCounts.entrySet()) {
                System.out.println("City: " + entry.getKey() + ", User Count: " + entry.getValue());
            }
        } else {
            throw new AssertionError("No user statistics available.");
        }
    }

    @Then("the list should include city names and user counts")
    public void the_list_should_include_city_names_and_user_counts() {
        if (cityUserCounts != null && !cityUserCounts.isEmpty()) {
            for (Map.Entry<String, Integer> entry : cityUserCounts.entrySet()) {
                System.out.println("City: " + entry.getKey() + ", User Count: " + entry.getValue());
            }
        } else {
            throw new AssertionError("City names and user counts are not available.");
        }
    }
}
