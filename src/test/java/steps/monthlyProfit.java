package Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Admin_helperClass.FinancialReportGenerator;


public class monthlyProfit {

    private boolean adminLoggedIn;
    private boolean reportGenerated;
    private Admin_helperClass.FinancialReportGenerator reportGenerator; // Integration with the actual report generator class

    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
        adminLoggedIn = true;
        reportGenerator = new Admin_helperClass.FinancialReportGenerator(); // Initialize the report generator
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

    @When("chooses the monthly view option")
    public void chooses_the_monthly_view_option() {
        if (adminLoggedIn) {
            System.out.println("Monthly view option selected.");
            reportGenerated = reportGenerator.generateMonthlyProfitReport(); // Use the report generator
        } else {
            throw new AssertionError("Admin is not logged in.");
        }
    }

    @Then("the admin should see a report detailing the monthly profits for all stores")
    public void the_admin_should_see_a_report_detailing_the_monthly_profits_for_all_stores() {
        if (reportGenerated) {
            System.out.println("Report generated: Monthly profits for all stores.");
        } else {
            throw new AssertionError("Report was not generated.");
        }
    }

    @Then("the report should include a comparison with the profits from the prior month")
    public void the_report_should_include_a_comparison_with_the_profits_from_the_prior_month() {
        if (reportGenerated) {
            int currentMonthProfit = reportGenerator.getCurrentMonthProfit(); // Retrieve profit data
            int priorMonthProfit = reportGenerator.getPriorMonthProfit();

            int profitDifference = currentMonthProfit - priorMonthProfit;

            System.out.println("Current Month Profit: $" + currentMonthProfit);
            System.out.println("Prior Month Profit: $" + priorMonthProfit);
            System.out.println("Difference: $" + profitDifference);

            if (profitDifference > 0) {
                System.out.println("Profits have increased by $" + profitDifference + " compared to the prior month.");
            } else if (profitDifference < 0) {
                System.out.println("Profits have decreased by $" + Math.abs(profitDifference) + " compared to the prior month.");
            } else {
                System.out.println("Profits are the same as the prior month.");
            }
        } else {
            throw new AssertionError("Report was not generated, so no comparison is available.");
        }
    }

    @Then("the list should include city names and user counts")
    public void the_list_should_include_city_names_and_user_counts() {
        if (reportGenerated) {
            System.out.println("Report includes city names and user counts.");
            // Retrieve and print city names and user counts from the report generator
            reportGenerator.getCityUserCounts().forEach((city, count) ->
                    System.out.println("City: " + city + ", User Count: " + count)
            );
        } else {
            throw new AssertionError("Report was not generated, so no city names or user counts are available.");
        }
    }
}
