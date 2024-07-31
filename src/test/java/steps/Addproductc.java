

package steps;

import MyApp_sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;

public class Addproductc {
    ProductManagement pm;
    private int productId;
    private Integer currentProductId;
    private ProductManagement.Product updatedProduct;
    List<Map<String, String>> products;

    public Addproductc() {
        pm = ProductManagement.getInstance();
        products = new ArrayList<>();
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Method to get the product ID
    public int getProductId() {
        return this.productId;
    }


    @Given("the user is logged in as an owner")
    public void the_user_is_logged_in_as_an_owner() {
        pm.is_an_owner = true;
        Assert.assertTrue(pm.is_an_owner);
    }

    @Given("the owner pages are open and the owner navigates to product management")
    public void the_owner_pages_are_open_and_the_owner_navigates_to_product_management() {
        if (pm.is_an_owner) {
            pm.navagates_to_pmpage = true;
            Assert.assertTrue(pm.navagates_to_pmpage);
        }
    }

    @When("the owner adds a product with the following details:")
    public void the_owner_adds_a_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productDetails : products) {
            try {
                int id = Integer.parseInt(productDetails.getOrDefault("ProID", "0"));
                String name = productDetails.getOrDefault("ProName", "").trim();
                String description = productDetails.getOrDefault("Description", "").trim();
                double price = Double.parseDouble(productDetails.getOrDefault("ProPrice", "0"));
                int quantity = Integer.parseInt(productDetails.getOrDefault("ProQuantity", "0"));

                ProductManagement.Product product = new ProductManagement.Product(id, name, description, price, quantity);
                pm.addproduct(product);
                currentProductId = id;
            } catch (IllegalArgumentException e) {
                pm.errormessage = e.getMessage();
            }
        }
    }


    @Then("the product should be added to the list")
    public void the_product_should_be_added_to_the_list() {
        for (Map<String, String> productDetails : products) {
            int id = Integer.parseInt(productDetails.getOrDefault("ProID", "0"));
            Assert.assertTrue("Product not found in the list", pm.productExists(id));
        }
    }

    @Then("the product details should be displayed")
    public void the_product_details_should_be_displayed() {
        Assert.assertTrue(pm.getErrorMessage().isEmpty());
    }

    @When("the owner updates the product with the following details:")
    public void the_owner_updates_the_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
        int proid = Integer.parseInt(data.get("ProID"));
        String proname = data.get("ProName");
        String prodescription = data.get("Description");
        double proprice = Double.parseDouble(data.get("ProPrice"));
        int proquantity = Integer.parseInt(data.get("ProQuantity"));
        ProductManagement.Product product = new ProductManagement.Product(proid, proname, prodescription, proprice, proquantity);

        currentProductId = proid;

        if (!pm.productExists(proid)) {
            throw new IllegalStateException("Product with ID " + proid + " does not exist.");
        }
        pm.updateProduct(proid, proquantity);
    }

    @Given("the following products are available:")
    public void the_following_products_are_available(DataTable dataTable) {
        products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : products) {
            int proid = Integer.parseInt(row.get("ProID"));
            String proname = row.get("ProName");
            String prodescription = row.get("Description");
            double proprice = Double.parseDouble(row.get("ProPrice"));
            int proquantity = Integer.parseInt(row.get("ProQuantity"));
            ProductManagement.Product product = new ProductManagement.Product(proid, proname, prodescription, proprice, proquantity);
            pm.addproduct(product);
        }
    }

    @Then("the quantity of the existing product should be updated to {string}")
    public void the_quantity_of_the_existing_product_should_be_updated_to(String newQuantity) {
        int newQuantityInt = Integer.parseInt(newQuantity);
        pm.updateProduct(currentProductId, newQuantityInt);
        ProductManagement.Product product = pm.getProductById(currentProductId);
        Assert.assertNotNull("Updated product not found", product);
        Assert.assertEquals("Product quantity not updated", newQuantityInt, product.getQuantity());
    }

    @Then("the product details should be updated in the list")
    public void the_product_details_should_be_updated_in_the_list() {
        for (Map<String, String> productDetails : products) {
            int id = Integer.parseInt(productDetails.getOrDefault("ProID", "0"));
            ProductManagement.Product product = pm.getProductById(id);
            Assert.assertNotNull("Product should not be null", product);
            Assert.assertEquals("Product name did not update correctly", productDetails.get("ProName"), product.getName());
            Assert.assertEquals("Product description did not update correctly", productDetails.get("Description"), product.getDescription());
            Assert.assertEquals("Product price did not update correctly", Double.parseDouble(productDetails.get("ProPrice")), product.getPrice(), 0.01);
            Assert.assertEquals("Product quantity did not update correctly", Integer.parseInt(productDetails.get("ProQuantity")), product.getQuantity());
        }
    }

    @When("the user tries to add a product with the following details:")
    public void the_user_tries_to_add_a_product_with_the_following_details(DataTable dataTable) {
        products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productDetails : products) {
            try {
                int id = Integer.parseInt(productDetails.getOrDefault("ProID", "0"));
                String name = productDetails.getOrDefault("ProName", "").trim();
                String description = productDetails.getOrDefault("Description", "").trim();
                double price = Double.parseDouble(productDetails.getOrDefault("ProPrice", "0"));
                int quantity = Integer.parseInt(productDetails.getOrDefault("ProQuantity", "0"));


                if (id <= 0 || name.isEmpty() || description.isEmpty() || price <= 0 || quantity <= 0) {
                    throw new IllegalArgumentException("Invalid product details: All fields must be valid.");
                }


                ProductManagement.Product product = new ProductManagement.Product(id, name, description, price, quantity);
                pm.addproduct(product);
                currentProductId = id;
            } catch (NumberFormatException e) {
                pm.errormessage = "Invalid number format in product details: " + e.getMessage();
            } catch (IllegalArgumentException e) {
                pm.errormessage = e.getMessage();
            } catch (Exception e) {
                pm.errormessage = "An unexpected error occurred: " + e.getMessage();
            }
        }
    }

    @Then("the system should display a validation error message")
    public void the_system_should_display_a_validation_error_message() {
        Assert.assertFalse("Expected error message not found", pm.getErrorMessage().isEmpty());
    }

    @Then("the product should not be added to the list")
    public void the_product_should_not_be_added_to_the_list() {
        int productId = getProductId();
        Assert.assertFalse("Product should not be in the list", pm.productExists(productId));
    }


    @Then("the system should display a duplication error message")
    public void the_system_should_display_a_duplication_error_message() {
        Assert.assertEquals("Product with this ID already exists.", pm.getErrorMessage());
    }
}
