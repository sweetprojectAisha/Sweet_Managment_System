package steps;
import MYApp_Sweet.ProductManagement;
import MYApp_Sweet.ProfitsAndFinantialReportC;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class ProfitsAndFinantialReport {
    ProfitsAndFinantialReportC pf;
    List<Map<String, String>> products;
    private List<ProfitsAndFinantialReportC.Sale> currentSoldProducts;
    public ProfitsAndFinantialReport() {
        pf = ProfitsAndFinantialReportC.getInstance();
        products = new ArrayList<>();

    }


    @Given("the following product exists:")
    public void the_following_product_exists(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        String name,description;
        double price;
        ProfitsAndFinantialReportC.Sale sales;
        currentSoldProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("ID"));
            price = Double.parseDouble(columns.get("Price"));
            quantity = Integer.parseInt(columns.get("Quantity"));
            description = columns.get("Description");
            name = columns.get("Name");
            sales = new ProfitsAndFinantialReportC.Sale(id,name,10,0.0,0.0);
            currentSoldProducts.add(sales);
            try {
                pf.addProduct(sales);

                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the total sales should be {double}")
    public void the_total_sales_should_be(double totalseles) {
      double actualtotalsales=pf.calculateTotalSales();
      assertEquals(totalseles,actualtotalsales,0.001);
    }

    @Then("the total profit should be {double}")
    public void the_total_profit_should_be(double profit) {
        double actualtotalprofit=pf.getTotalProfit();
        assertEquals(profit,actualtotalprofit,0.001);
    }

    @Given("the following products have been solded:")
    public void the_following_products_have_been_solded(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            int id = Integer.parseInt(columns.get("ID"));
            String name = columns.get("Name");
            int quantitySold = Integer.parseInt(columns.get("Quantity Sold"));
            double sellingPrice = Double.parseDouble(columns.get("Selling Price"));

            try {
                pf.setSellingPrice(id, sellingPrice);
                pf.recordSale("Store1", id, name, quantitySold);

            } catch (Exception e) {
                fail("Failed to record sale: " + e.getMessage());
            }
        }
    }

    @Then("the financial report should include:")
    public void the_financial_report_should_include(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Double> expectedReport = dataTable.asMaps(String.class, Double.class).get(0);
        Map<String, Double> actualReport = pf.generateFinancialReport();
        assertEquals(expectedReport.get("Total Sales"), actualReport.get("Total Sales"), 0.001);
        assertEquals(expectedReport.get("Total Profit"), actualReport.get("Total Profit"), 0.001);
    }

    @Given("the following sales data for stores:")
    public void the_following_sales_data_for_stores(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String store = columns.get("Store");
            int id = Integer.parseInt(columns.get("Product ID"));
            String name = columns.get("Product Name");
            int quantitySold = Integer.parseInt(columns.get("Quantity Sold"));

            try {
//                if (!pf.productExists(id)) {
//                    pf.addProduct(new ProfitsAndFinantialReportC.Sale(id, name, quantitySold, 0.0, 0.0));
                pf.recordSale(store, id, name, quantitySold);


            } catch (Exception e) {
                fail();
            }
        }
    }

    @Then("the best-selling products for each store should be:")
    public void the_best_selling_products_for_each_store_should_be(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> expectedBestSellers = new HashMap<>();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            expectedBestSellers.put(columns.get("Store"), columns.get("Best-Selling Product"));
        }

        Map<String, String> actualBestSellers = pf.getBestSellingProductsByStore();
        for (Map.Entry<String, String> entry : expectedBestSellers.entrySet()) {
            assertEquals(entry.getValue(), actualBestSellers.get(entry.getKey()));
        }
    }

}
