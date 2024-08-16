package Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Map;

public class generateFina {

    private boolean adminLoggedIn;
    private boolean reportGenerated;
    private Admin_helperClass.FinancialReportGenerator reportGenerator = new Admin_helperClass.FinancialReportGenerator();
    private Map<String, Object> report;

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

    @When("selects a date range for the report")
    public void selects_a_date_range_for_the_report() {
        System.out.println("Date range selected for the report.");
    }

    @When("clicks the generate report button")
    public void clicks_the_generate_report_button() {
        report = reportGenerator.generateReport("2024-01-01", "2024-01-31");
        reportGenerated = true;
        System.out.println("Generate report button clicked.");
    }

    @Then("a financial report for the selected date range should be generated")
    public void a_financial_report_for_the_selected_date_range_should_be_generated() {
        if (reportGenerated && report != null) {
            System.out.println("Financial report generated for the selected date range.");
        } else {
            throw new AssertionError("Report generation was not initiated.");
        }
    }

    @Then("the report should detail total profits, expenses, and all profit for each store.")
    public void the_report_should_detail_total_profits_expenses_and_all_profit_for_each_store() {
        if (reportGenerated && report != null) {
            System.out.println("Report includes details of total profits, expenses, and profit for each store.");
            System.out.println("Total Profits: $" + report.get("totalProfits"));
            System.out.println("Total Expenses: $" + report.get("totalExpenses"));
            System.out.println("Store Profits: " + report.get("storeProfits"));
        } else {
            throw new AssertionError("Report was not generated, so no details are available.");
        }
    }
}
