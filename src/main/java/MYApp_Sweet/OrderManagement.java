package MYApp_Sweet;

import java.util.HashMap;
import java.util.Map;
public class OrderManagement {
    private static OrderManagement instance;
    private Map<Integer, Order> orders = new HashMap<>();
    public static OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }
    public void processOrder(int orderId) {
        if (orders.containsKey(orderId)) {
            if (orders.get(orderId).getStatus()==STATUS.WAITING) {
                Order order = orders.get(orderId);
                order.setStatus(STATUS.ACCEPTED);
            }
            else {
                throw new IllegalArgumentException("Order with id " + orderId + " state isn't WAITING, so it cannot be processed.");
            }
        } else {
            throw new IllegalArgumentException("Order not found.");
        }
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public void createOrder(int orderId, STATUS status) {
        orders.put(orderId, new Order(orderId, status));
    }

    public void deleteOrder(int orderId) {
        orders.remove(orderId);
    }
    public static enum STATUS{
        ACCEPTED,
        WAITING,
        INITIAL
    }

    public static class Order {
        private int id;
        private STATUS status;

        public Order(int id, STATUS status) {
            this.id = id;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public STATUS getStatus() {
            return status;
        }

        public void setStatus(STATUS status) {
            this.status = status;
        }
    }
}
