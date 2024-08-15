package steps;

import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class Deleteproductc {
    ProductManagement pm;
    List<Map<String, String>> products;

    private List<ProductManagement.Product> currentProducts;

    public Deleteproductc() {
        pm = ProductManagement.getInstance();
        products = new ArrayList<>();
    }

    @When("the owner deletes a product with the following details:")
    public void the_owner_deletes_a_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        String name, description;
        double price;
        ProductManagement.Product product;
        currentProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("ProID"));
            name = columns.get("ProName");
            description = columns.get("Description");
            price = Double.parseDouble(columns.get("ProPrice"));
            quantity = Integer.parseInt(columns.get("ProQuantity"));
            product = new ProductManagement.Product(id, name, description, price, quantity);
            currentProducts.add(product);
            try {
                pm.deleteProduct(id);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the product should be removed from the list")
    public void the_product_should_be_removed_from_the_list() {
        for (ProductManagement.Product product : currentProducts) {
            assertNull(pm.getProductById(product.getId()));
        }
    }

    @When("the owner attempts to delete a product with the following details:")
    public void the_owner_attempts_to_delete_a_product_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        String name, description;
        double price;
        ProductManagement.Product product;
        currentProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("ProID"));
            name = columns.get("ProName");
            description = columns.get("Description");
            price = Double.parseDouble(columns.get("ProPrice"));
            quantity = Integer.parseInt(columns.get("ProQuantity"));
            product = new ProductManagement.Product(id, name, description, price, quantity);
            currentProducts.add(product);
            try {
                pm.deleteProduct(id);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Product with ID " + id + " does not exist."));
            }
        }
    }

    @Then("the product should not be found, and no deletion should occur")
    public void the_product_should_not_be_found_and_no_deletion_should_occur() {
        for (ProductManagement.Product product : currentProducts) {
            int id = product.getId();
            assertNull(pm.getProductById(id));
        }
    }

}
