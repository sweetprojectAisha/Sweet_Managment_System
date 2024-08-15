package steps;

import MYApp_Sweet.OrderManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderManagementC {
    OrderManagement om;
    private List<OrderManagement.Order> currentOrders;

    public OrderManagementC() {
        om = OrderManagement.getInstance();
    }

    @When("the ordered have been added")
    public void the_ordered_have_been_added(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id;
        OrderManagement.STATUS status;
        OrderManagement.Order order1;
        currentOrders = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("OrderID"));
            status = OrderManagement.STATUS.valueOf(columns.get("Status"));
            order1 = new OrderManagement.Order(id, status);
            currentOrders.add(order1);
            try {
                om.createOrder(id, status);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the orders should be added")
    public void the_orders_should_be_added() {
        for (OrderManagement.Order currentorder : currentOrders) {
            OrderManagement.Order order2 = om.getOrder(currentorder.getId());
            assertNotNull(order2);
            assertEquals(currentorder.getId(), order2.getId());
            assertEquals(currentorder.getStatus(), order2.getStatus());
        }
    }

    @Given("an order with ID {int} exists with status {string}")
    public void an_order_with_id_exists_with_status(Integer id, String status) {
        try {
            assertEquals(OrderManagement.STATUS.valueOf(status), om.getOrder(id).getStatus());
        } catch (Exception ex) {
            fail();
        }
    }

    @Then("process order with ID {int}")
    public void process_order_with_id(Integer id) {
        try {
            om.processOrder(id);
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        }
    }

    @Then("process order with ID {int} and invalid status {string}")
    public void process_order_with_id_and_invalid_status(Integer id, String msg) {
        try {
            om.processOrder(id);
           fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof IllegalArgumentException);
            assertTrue(ex.getMessage().contains(msg));
        }
    }

}
