package steps;

import MyApp_sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Addproductc {
    ProductManagement pm;
    List<Map<String, String>> products;

    private List<ProductManagement.Product> currentProducts;

    public Addproductc() {
        pm = ProductManagement.getInstance();
        products = new ArrayList<>();
    }


    @Given("the user is logged in as an owner")
    public void the_user_is_logged_in_as_an_owner() {
        pm.setIs_an_owner(true);
        assertTrue(pm.isIs_an_owner());
    }

    @Given("the owner pages are open and the owner navigates to product management")
    public void the_owner_pages_are_open_and_the_owner_navigates_to_product_management() {
        assertTrue(pm.isIs_an_owner());
        pm.setNavagates_to_pmpage(true);
        assertTrue(pm.isNavagates_to_pmpage());

    }

    @When("the owner adds a product with the following valid details:")
    public void the_owner_adds_a_product_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
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
                pm.addproduct(product);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }


    }

    @Then("the product should be added to the list")
    public void the_product_should_be_added_to_the_list() {
        for (ProductManagement.Product currentProduct : currentProducts) {
            ProductManagement.Product product = pm.getProduct(currentProduct.getId());
            assertNotNull(product);
            assertEquals(currentProduct.getId(), product.getId());
            assertEquals(currentProduct.getName(), product.getName());
            assertEquals(currentProduct.getDescription(), product.getDescription());
            assertEquals(currentProduct.getQuantity(), product.getQuantity());
            assertEquals(currentProduct.getPrice(), product.getPrice(), 0.001);
        }
    }

    @When("the owner updates the product with the following details for existing product:")
    public void the_owner_updates_the_product_with_the_following_details_for_existing_product(io.cucumber.datatable.DataTable dataTable) {
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
                pm.updateProduct2(product);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the quantity of the existing product should be updated to {string}")
    public void the_quantity_of_the_existing_product_should_be_updated_to(String Quantity) {
        for (ProductManagement.Product currentProduct : currentProducts) {
            ProductManagement.Product product = pm.getProduct(currentProduct.getId());
            assertNotNull(product);
            assertEquals(currentProduct.getId(), product.getId());
            assertEquals(currentProduct.getName(), product.getName());
            assertEquals(currentProduct.getDescription(), product.getDescription());
            assertEquals(currentProduct.getQuantity(), product.getQuantity());
            assertEquals(currentProduct.getPrice(), product.getPrice(), 0.001);
        }
    }

    @When("the owner updates the product with the following details for non existing product:")
    public void the_owner_updates_the_product_with_the_following_details_for_non_existing_product
            (io.cucumber.datatable.DataTable dataTable) {
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
                pm.updateProduct2(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Cannot update. Product with ID: " + product.getId() + " does not exist."));
            }
        }
    }

    @Then("the quantity of the existing product should not be updated to {string}")
    public void the_quantity_of_the_existing_product_should_not_be_updated_to(String string) {
        for (ProductManagement.Product currentProduct : currentProducts) {
            ProductManagement.Product product = pm.getProduct(currentProduct.getId());
            Assert.assertNull(product);
        }
    }

    @When("the user tries to add a product with the following missing details:")
    public void the_user_tries_to_add_a_product_with_the_following_missing_details(io.cucumber.datatable.DataTable
                                                                                           dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id, quantity;
        String name, description;
        double price;
        ProductManagement.Product product;
        currentProducts = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            try {
                id = Integer.parseInt(columns.get("ProID"));
            } catch (Exception ex) {
                id = 0;
            }
            name = columns.get("ProName");
            description = columns.get("Description");
            try {
                price = Double.parseDouble(columns.get("ProPrice"));
            } catch (Exception ex) {
                price = 0;
            }
            try {
                quantity = Integer.parseInt(columns.get("ProQuantity"));
            } catch (Exception ex) {
                quantity = 0;
            }
            product = new ProductManagement.Product(id, name, description, price, quantity);
            currentProducts.add(product);
            try {
                pm.addproduct(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Invalid product details."));
            }
        }


    }

    @Then("the product should not be added to the list")
    public void the_product_should_not_be_added_to_the_list() {
        for (ProductManagement.Product currentProduct : currentProducts)
            assertNull(pm.getProduct(currentProduct.getId()));
    }

    @When("the user tries to add a product with the following invalid details:")
    public void the_user_tries_to_add_a_product_with_the_following_invalid_details(io.cucumber.datatable.DataTable
                                                                                           dataTable) {
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
                pm.addproduct(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Invalid product details."));
            }
        }
    }

    @When("the user tries to add a product with the following details and dublicate id:")
    public void the_user_tries_to_add_a_product_with_the_following_details_and_dublicate_id
            (io.cucumber.datatable.DataTable dataTable) {
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
                pm.addproduct(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Product with ID: " + product.getId() + " already exists."));
            }
        }
    }

}
