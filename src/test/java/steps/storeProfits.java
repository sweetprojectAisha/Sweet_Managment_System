
package Admin;

import Admin_helperClass.StoreProfitService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Map;

public class storeProfits {

    private boolean adminLoggedIn;
    private StoreProfitService storeProfitService = new StoreProfitService();
    private Map<String, Double> storeProfits;

    @Given("an admin is logged in")
    public void an_admin_is_logged_in() {
        adminLoggedIn = true;
        System.out.println("Admin is logged in.");
    }

    @When("the admin navigates to the financial reports page")
    public void the_admin_navigates_to_the_financial_reports_page() {
        if (adminLoggedIn) {
            System.out.println("Admin navigated to the financial reports page.");
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the admin should see a list of all stores with their corresponding profits")
    public void the_admin_should_see_a_list_of_all_stores_with_their_corresponding_profits() {
        storeProfits = storeProfitService.getStoreProfits();
        if (storeProfits != null && !storeProfits.isEmpty()) {
            System.out.println("Store Profits:");
            for (Map.Entry<String, Double> entry : storeProfits.entrySet()) {
                System.out.println("Store: " + entry.getKey() + ", Profit: $" + entry.getValue());
            }
        } else {
            throw new AssertionError("No store profits available.");
        }
    }
}
