package steps;

import MyApp_sweet.ProductManagement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Updateproductc {
    ProductManagement pm;
    private List<ProductManagement.Product> currentProducts;

    public Updateproductc() {
        pm = ProductManagement.getInstance();
        pm.setIs_an_owner(false);
        pm.setNavagates_to_salespage(false);
        pm.setTotalProfit(0.0);
        pm.setTotalSales(0.0);
    }

//    @Given("the following products exist:")
//    public void the_following_products_exist(io.cucumber.datatable.DataTable dataTable) {
//
//    }

    @When("the owner updates the product with the following valid details:")
    public void the_owner_updates_the_product_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
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

    @Then("the product should be updated in list")
    public void the_product_should_be_updated_in_list() {
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


//    @Then("the product with ID {string} should have the updated details:")
//    public void the_product_with_id_should_have_the_updated_details(String string, io.cucumber.datatable.DataTable dataTable) {
//        // Write code here that turns the phrase above into concrete actions
//        // For automatic transformation, change DataTable to one of
//        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
//        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
//        // Double, Byte, Short, Long, BigInteger or BigDecimal.
//        //
//        // For other transformations you can register a DataTableType.
//        throw new io.cucumber.java.PendingException();
//    }

    @When("the owner updates the product with the following invalid details:")
    public void the_owner_updates_the_product_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
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
                pm.updateProduct2(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Invalid product details."));
            }
        }
    }

//    @Given("the expected product ID is {string}")
//    public void the_expected_product_id_is(String string) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @When("the owner updates a non existing product with the following details:")
    public void the_owner_updates_a_non_existing_product_with_the_following_details(io.cucumber.datatable.DataTable
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
                pm.updateProduct2(product);
                assertTrue(false);
            } catch (Exception ex) {
                assertTrue(ex instanceof IllegalStateException);
                assertTrue(ex.getMessage().contains("Cannot update. Product with ID: " + product.getId() + " does not exist."));
            }
        }
    }

//    @When("the owner updates the product with the following missing details:")
//    public void the_owner_updates_the_product_with_the_following_missing_details(io.cucumber.datatable.DataTable
//                                                                                         dataTable) {
//        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
//        int id = 0, quantity = 0;
//        String name = null, description = null;
//        double price = 0;
//        ProductManagement.Product product;
//        currentProducts = new ArrayList<>();
//        for (Map<String, String> columns : rows) {
//            try {
//                id = Integer.parseInt(columns.get("ProID"));
//            } catch (Exception ex) {
//                id = 0;
//            }
//            name = columns.get("ProName");
//            description = columns.get("Description");
//            try {
//                price = Double.parseDouble(columns.get("ProPrice"));
//            } catch (Exception ex) {
//                price = 0;
//            }
//            try {
//                quantity = Integer.parseInt(columns.get("ProQuantity"));
//            } catch (Exception ex) {
//                quantity = 0;
//            }
//            product = new ProductManagement.Product(id, name, description, price, quantity);
//            currentProducts.add(product);
//            try {
//                pm.updateProduct2(product);
//                assertTrue(false);
//            } catch (Exception ex) {
//                assertTrue(ex instanceof IllegalStateException);
//                assertTrue(ex.getMessage().contains("Cannot update. Product with ID: " + product.getId() + " does not exist."));
//            }
//        }
//    }
}
