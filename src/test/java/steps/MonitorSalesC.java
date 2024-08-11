package steps;

import MyApp_sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class MonitorSalesC {
    ProductManagement pm;
    List<Map<String, String>> products;
    private List<ProductManagement.SoldProduct> currentSoldProducts;

    public MonitorSalesC() {
        pm = ProductManagement.getInstance();
        products = new ArrayList<>();
    }

    @When("the owner adds a sold product with the following details:")
    public void the_owner_adds_a_sold_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        double price, totalrevenue, costpercentage, discountPercentage;
        ProductManagement.SoldProduct soldprod;
        currentSoldProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("ProID"));
            price = Double.parseDouble(columns.get("PricePerUnit"));
            quantity = Integer.parseInt(columns.get("QuantitySold"));
            totalrevenue = Double.parseDouble(columns.get("TotalRevenue"));
            costpercentage = Double.parseDouble(columns.get("CostPercentage"));
            discountPercentage = Double.parseDouble(columns.get("DiscountPercentage"));
            soldprod = new ProductManagement.SoldProduct(id, price, quantity, totalrevenue, costpercentage, discountPercentage);
            currentSoldProducts.add(soldprod);
            try {
                pm.addSellProduct(id, price, quantity, totalrevenue, costpercentage, discountPercentage);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the product sold should be added to the list")
    public void the_product_sold_should_be_added_to_the_list() {
        for (ProductManagement.SoldProduct currentSoldProduct : currentSoldProducts) {
            ProductManagement.SoldProduct soldproduct = pm.getSoldProduct(currentSoldProduct.getId());
            assertNotNull(soldproduct);
            assertEquals(currentSoldProduct.getId(), soldproduct.getId());
            assertEquals(currentSoldProduct.getQuantitySold(), soldproduct.getQuantitySold());
            assertEquals(currentSoldProduct.getPrice(), soldproduct.getPrice(), 0.001);
            assertEquals(currentSoldProduct.getTotalrevenue(), soldproduct.getTotalrevenue(), 0.001);
            assertEquals(currentSoldProduct.getCostpercentage(), soldproduct.getCostpercentage(), 0.001);
        }
    }

    @When("the owner cannot add a sold product that does not exist:")
    public void the_owner_cannot_add_a_sold_product_that_does_not_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        double price, totalrevenue, costpercentage, discountPercentage;
        ProductManagement.SoldProduct soldprod;
        currentSoldProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("ProID"));
            price = Double.parseDouble(columns.get("PricePerUnit"));
            quantity = Integer.parseInt(columns.get("QuantitySold"));
            totalrevenue = Double.parseDouble(columns.get("TotalRevenue"));
            costpercentage = Double.parseDouble(columns.get("CostPercentage"));
            discountPercentage = Double.parseDouble(columns.get("DiscountPercentage"));
            soldprod = new ProductManagement.SoldProduct(id, price, quantity, totalrevenue, costpercentage, discountPercentage);
            currentSoldProducts.add(soldprod);
            try {
                pm.addSellProduct(id, price, quantity, totalrevenue, costpercentage, discountPercentage);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("The product with id " + id + " doesn't exist"));
            }
        }
    }
    @Then("the product sold should not be added to the list")
    public void the_product_sold_should_not_be_added_to_the_list() {
        for (ProductManagement.SoldProduct currentSoldProduct : currentSoldProducts) {
            ProductManagement.SoldProduct soldproduct = pm.getSoldProduct(currentSoldProduct.getId());
            assertNull(soldproduct);
        }
    }


    @Given("the following products have been sold:")
    public void the_following_products_have_been_sold(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : products) {
            int productId = Integer.parseInt(productData.get("ProID"));
            int quantitySold = Integer.parseInt(productData.get("QuantitySold"));
            double costPercentage = Double.parseDouble(productData.get("CostPercentage"));
            try {
                pm.sellProduct(productId, quantitySold, costPercentage);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Given("the following products have not been sold:")
    public void the_following_products_have_not_been_sold(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : products) {
            int productId = Integer.parseInt(productData.get("ProID"));
            int quantitySold = Integer.parseInt(productData.get("QuantitySold"));
            double costPercentage = Double.parseDouble(productData.get("CostPercentage"));
            try {
                pm.sellProduct(productId, quantitySold, costPercentage);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Product not available or insufficient quantity."));
            }
        }
    }

    @Then("the total sales revenue should be {double}")
    public void the_total_sales_revenue_should_be(Double expectedRevenue) {
        double actualRevenue = pm.calculateTotalSalesRevenue();
        assertEquals(expectedRevenue, actualRevenue, 0.01);
    }

    @Then("the total revenue should be {double}")
    public void the_total_revenue_should_be(double exprevenue) {
        double actualrevenue = pm.calculateTotalSalesRevenue();
        assertEquals(exprevenue, actualrevenue, 0.001);
    }

    @Then("the profit should be {double}")
    public void the_profit_should_be(double expprofit) {
        double actualprofit = pm.getTotalProfit();
        assertEquals(expprofit, actualprofit, 0.001);
    }

}
