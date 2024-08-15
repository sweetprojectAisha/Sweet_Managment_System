package steps;
import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BestSellingproductC {

    ProductManagement pm;

    List<Map<String, String>> products;


    private List<ProductManagement.Product> currentProducts;

    public BestSellingproductC() {
        pm = ProductManagement.getInstance();
        products = new ArrayList<>();
    }

    @Then("I should see an empty list")
    public void i_should_see_an_empty_list() {
        assertTrue(pm.getBestSellingProducts().isEmpty());
    }

    @Then("I should see the following products in order:")
    public void i_should_see_the_following_products_in_order(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedProducts = dataTable.asMaps(String.class, String.class);
        int actualProductId;
        Map<Integer, ProductManagement.Product> products = pm.getProducts();
        for (int i = 0; i < expectedProducts.size(); i++) {
            Map<String, String> expectedProduct = expectedProducts.get(i);
            ProductManagement.SoldProduct actualProduct = pm.getBestSellingProducts().get(i);

            actualProductId = actualProduct.getId();
            assertEquals(Integer.parseInt(expectedProduct.get("ProID")), actualProductId);

            assertEquals(expectedProduct.get("ProName"), products.get(actualProductId).getName());
            assertEquals(Integer.parseInt(expectedProduct.get("QuantitySold")), actualProduct.getQuantitySold());
        }
    }

}
