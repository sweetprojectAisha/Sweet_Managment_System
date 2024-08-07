package steps;

import MyApp_sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class Updateproductc {
    private ProductManagement productManagement;
    private ProductManagement.Product productUnderUpdate;
    private String expectedProductId;
    private String actualErrorMessage;

    public Updateproductc() {
        productManagement = ProductManagement.getInstance();
    }

    @Given("the following products exist:")
    public void the_following_products_exist(DataTable dataTable) {
        List<Map<String, String>> productsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : productsList) {
            try {
                int proID = Integer.parseInt(productData.get("ProID"));
                String proName = productData.get("ProName");
                String description = productData.get("Description");
                double proPrice = Double.parseDouble(productData.get("ProPrice"));
                int proQuantity = Integer.parseInt(productData.get("ProQuantity"));

                ProductManagement.Product product = new ProductManagement.Product(proID, proName, description, proPrice, proQuantity);
                productManagement.addproduct(product);

                // Verify product was added
                ProductManagement.Product addedProduct = productManagement.getProductById(proID);
                Assert.assertNotNull("Product with ID " + proID + " should be added and retrievable", addedProduct);
            } catch (Exception e) {
                System.err.println("Failed to add product with ID: " + productData.get("ProID") + " - " + e.getMessage());
                throw e; // Re-throw to fail the test if a product is not added successfully
            }
        }
    }

    @When("the owner updates the product with the following detailss:")
    public void the_owner_updates_the_product_with_the_following_detailss(DataTable dataTable) {
        Map<String, String> updateDetails = dataTable.asMaps(String.class, String.class).get(0);
        try {
            int proID = Integer.parseInt(updateDetails.get("ProID"));
            String proName = updateDetails.get("ProName");
            String description = updateDetails.get("Description");
            double proPrice = Double.parseDouble(updateDetails.get("ProPrice"));
            int proQuantity = Integer.parseInt(updateDetails.get("ProQuantity"));

            productUnderUpdate = new ProductManagement.Product(proID, proName, description, proPrice, proQuantity);

            ProductManagement.Product existingProduct = productManagement.getProductById(proID);
            if (existingProduct == null) {
                throw new IllegalStateException("Product with ID " + proID + " does not exist.");
            }

            productManagement.updateProduct2(productUnderUpdate);
        } catch (Exception e) {
            actualErrorMessage = e.getMessage();
        }
    }

    @Then("the product with ID {string} should have the updated details:")
    public void the_product_with_id_should_have_the_updated_details(String proid, DataTable dataTable) {
        Map<String, String> expectedDetails = dataTable.asMaps(String.class, String.class).get(0);
        ProductManagement.Product product = productManagement.getProductById(Integer.parseInt(proid));

        Assert.assertNotNull("Product with ID " + proid + " should not be null", product);
        Assert.assertEquals("Product name mismatch", expectedDetails.get("ProName"), product.getName());
        Assert.assertEquals("Product description mismatch", expectedDetails.get("Description"), product.getDescription());
        Assert.assertEquals("Product price mismatch", Double.parseDouble(expectedDetails.get("ProPrice")), product.getPrice(), 0.01);
        Assert.assertEquals("Product quantity mismatch", Integer.parseInt(expectedDetails.get("ProQuantity")), product.getQuantity());
    }

    @Then("the product with ID {string} should not be updated")
    public void the_product_with_id_should_not_be_updated(String proid) {
        ProductManagement.Product product = productManagement.getProductById(Integer.parseInt(proid));
        Assert.assertNotNull(product);
        //
//         Assuming you stored previous state before update attempt
//         Verify that product details haven't changed
//         For instance:
//         Assert.assertEquals("Product name should be unchanged", previousProductName, product.getName());
    }


    @Given("the expected product ID is {string}")
    public void setExpectedProductId(String productId) {
        this.expectedProductId = productId;
    }

    @When("trying to update a non-existent product")
    public void tryToUpdateNonExistentProduct() {
        try {
            if (expectedProductId == null || expectedProductId.isEmpty()) {
                throw new IllegalStateException("Expected product ID is not set or is empty.");
            }
            ProductManagement.Product nonExistentProduct = new ProductManagement.Product(Integer.parseInt(expectedProductId), "", "", 0.0, 0);
            productManagement.updateProduct2(nonExistentProduct);
        } catch (Exception e) {
            this.actualErrorMessage = e.getMessage();
        }
    }

    @Then("the system should display a product not found error message")
    public void the_system_should_display_a_product_not_found_error_message() {
        String expectedErrorMessage = "Product with ID " + expectedProductId + " does not exist.";
        Assert.assertEquals("Error message mismatch", expectedErrorMessage, getErrorMessage());
    }

    private String getErrorMessage() {
        return productManagement.getErrorMessage();
    }

}
