package Admin_helperClass;


import java.util.HashMap;
import java.util.Map;

public class FinancialReportGenerator {

    private boolean reportGenerated;
    private int currentMonthProfit;
    private int priorMonthProfit;
    private Map<String, Integer> cityUserCounts;

    // Constructor
    public FinancialReportGenerator() {
        // Initialize any required resources
        this.reportGenerated = false;
        this.currentMonthProfit = 0;
        this.priorMonthProfit = 0;
        this.cityUserCounts = new HashMap<>();
    }

    // Method to simulate generating a monthly profit report
    public boolean generateMonthlyProfitReport() {
        // Simulate report generation logic
        // In a real implementation, this method would generate a report and store relevant data
        this.currentMonthProfit = 50000;  // Example value
        this.priorMonthProfit = 45000;    // Example value
        this.cityUserCounts.put("New York", 1500);
        this.cityUserCounts.put("Los Angeles", 1200);

        // Indicate that the report has been generated
        this.reportGenerated = true;
        return this.reportGenerated;
    }

    // Method to get the current month's profit
    public int getCurrentMonthProfit() {
        if (reportGenerated) {
            return this.currentMonthProfit;
        } else {
            throw new IllegalStateException("Report has not been generated.");
        }
    }

    // Method to get the prior month's profit
    public int getPriorMonthProfit() {
        if (reportGenerated) {
            return this.priorMonthProfit;
        } else {
            throw new IllegalStateException("Report has not been generated.");
        }
    }

    // Method to get city names and user counts
    public Map<String, Integer> getCityUserCounts() {
        if (reportGenerated) {
            return new HashMap<>(this.cityUserCounts);
        } else {
            throw new IllegalStateException("Report has not been generated.");
        }
    }
    public Map<String, Object> generateReport(String startDate, String endDate) {
        this.reportGenerated = true;
        Map<String, Object> report = new HashMap<>();
        report.put("totalProfits", 100000);
        report.put("totalExpenses", 50000);
        Map<String, Integer> storeProfits = new HashMap<>();
        storeProfits.put("Store A", 30000);
        storeProfits.put("Store B", 70000);
        report.put("storeProfits", storeProfits);

        return report;
    }

    public boolean isReportGenerated() {
        return reportGenerated;
    }
}
