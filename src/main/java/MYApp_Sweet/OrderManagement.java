package MYApp_Sweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class OrderManagement {
    private static OrderManagement instance;
    public Map<Integer, Order> orders = new HashMap<>();
    private static final String ORDER_FILE = "orders.txt";
    public static OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }
    private OrderManagement() {
        loadOrders();
    }

    private void loadOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int id;
                    STATUS status;
                    try {
                        id = Integer.parseInt(parts[0].trim());
                        status = STATUS.valueOf(parts[1].trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid order data format: " + line);
                        continue;
                    }
                    orders.put(id, new Order(id, status));
                    System.out.println("Loaded order ID: " + id);
                } else {
                    System.out.println("Invalid order data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveOrders() {
        try (FileWriter writer = new FileWriter(ORDER_FILE)) {
            for (Order order : orders.values()) {
                writer.write(serializeOrder(order) + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String serializeOrder(Order order) {
        return order.getId() + "," + order.getStatus();
    }
    public void processOrder(int orderId) {
        if (orders.containsKey(orderId)) {
            if (orders.get(orderId).getStatus()==STATUS.WAITING) {
                Order order = orders.get(orderId);
                order.setStatus(STATUS.ACCEPTED);
                saveOrders();
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
        saveOrders();
    }

    public void deleteOrder(int orderId) {
        orders.remove(orderId);
        saveOrders();
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
