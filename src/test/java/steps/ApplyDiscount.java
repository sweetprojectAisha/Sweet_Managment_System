package steps;

import MYApp_Sweet.ProductManagement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ApplyDiscount {
    ProductManagement pm;
    int productId;

    public ApplyDiscount() {
        pm = ProductManagement.getInstance();
    }


    @When("the owner applies a discount of {int}% to the product with ID {int}")
    public void the_owner_applies_a_discount_of_to_the_product_with_id(Integer discount, Integer id) {
        try {
            productId = id;
            pm.applyDiscount(id, discount);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the product's price should be updated to {double}")
    public void the_product_s_price_should_be_updated_to(Double expnewprice) {
        double actualnewprice = pm.calculateNewprice(productId);
        assertEquals(expnewprice, actualnewprice, 0.01);

    }


    @When("the owner attempts to apply an invalid discount of {int}% to the product with ID {int}")
    public void the_owner_attempts_to_apply_an_invalid_discount_of_to_the_product_with_id(Integer invdiscount, Integer id) {
        try {
            productId = id;
            pm.applyDiscount(id, invdiscount);
            assertTrue(false);
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalStateException);
            assertTrue(ex.getMessage().contains("Invalid product ID or discount percentage."));
        }
    }

    @Then("the product's price should remain {double}")
    public void the_product_s_price_should_remain(Double newprice) {
        double actualnewprice = pm.calculateNewprice(productId);
        assertEquals(newprice, actualnewprice, 0.01);
    }
}
