package MyApp_sweet;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

public class ProductManagement {
    private static ProductManagement instance;
    private static final Logger logger = Logger.getLogger(ProductManagement.class.getName());
    public boolean is_an_owner;
    public boolean navagates_to_pmpage;
    private Map<Integer, Product> prodeatails = new HashMap<>();
    public String errormessage = "";

    private ProductManagement(){}

    public static ProductManagement getInstance() {
        if(instance == null)instance = new ProductManagement();
        return instance;
    }


    public static class Product {
        public int id;
        public String name;
        public double price;
        public int quantity;
        public String description;

        public Product(int id, String name, String description, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }


        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


    public void addproduct(Product product) {
        logger.info("Attempting to add product: " + product);

        if (productExists(product.getId())) {
            logger.warning("Product with ID: " + product.getId() + " already exists.");
            errormessage = "Product with this ID already exists.";
            return;
        }

        if (product.getId() <= 0 || product.getName() == null || product.getName().isEmpty() ||
                product.getDescription() == null || product.getDescription().isEmpty() ||
                product.getPrice() <= 0 || product.getQuantity() <= 0) {
            logger.warning("Invalid product details: " + product);
            errormessage = "Invalid product details.";
            return;
        }

        prodeatails.put(product.getId(), product);
        logger.info("Product with ID: " + product.getId() + " added successfully.");
    }





    public void updateProduct(Integer id, int newQuantity) {
        Product existingProduct = prodeatails.getOrDefault(id, null);
        if (existingProduct == null) {
            errormessage = "Product with ID " + id + " does not exist.";
            throw new IllegalStateException(errormessage);
        }
        existingProduct.setQuantity(newQuantity);
        prodeatails.put(id, existingProduct);
        logger.info("Product with ID: " + id + " updated successfully.");
    }



    public String getErrorMessage() {
        return errormessage;
    }

    public boolean productExists(int id) {
        return prodeatails.containsKey(id);
    }

    public Product getProductById(int id) {
        logger.info("Attempting to retrieve product with ID: " + id);
        return prodeatails.getOrDefault(id, null);
    }

    public Map<Integer, Product> getProducts() {
        return prodeatails;
    }
}
