package MyApp_sweet;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

public class ProductManagement {
    private static ProductManagement instance;
    private static final Logger logger = Logger.getLogger(ProductManagement.class.getName());
    private boolean is_an_owner;
    private boolean navagates_to_pmpage;
    private Map<Integer, Product> prodeatails = new HashMap<>();
    public String errormessage = "";

    private ProductManagement(){}

    public static ProductManagement getInstance() {
        if(instance == null)instance = new ProductManagement();
        return instance;
    }


    public static class Product {
        private int id;
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

    public boolean isIs_an_owner() {
        return is_an_owner;
    }

    public void setIs_an_owner(boolean is_an_owner) {
        this.is_an_owner = is_an_owner;
    }

    public boolean isNavagates_to_pmpage() {
        return navagates_to_pmpage;
    }

    public void setNavagates_to_pmpage(boolean navagates_to_pmpage) {
        this.navagates_to_pmpage = navagates_to_pmpage;
    }

    public void addproduct(Product product) {
        logger.info("Attempting to add product: " + product);
        if (productExists(product.getId())) {
            errormessage = "Product with ID: " + product.getId() + " already exists.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }

        if (product.getId() <= 0 || product.getName() == null || product.getName().isEmpty() ||
                product.getDescription() == null || product.getDescription().isEmpty() ||
                product.getPrice() <= 0 || product.getQuantity() <= 0) {
            logger.warning("Invalid product details: " + product);
            errormessage = "Invalid product details.";
            throw new IllegalStateException(errormessage);
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

    public void updateProduct2(Product product) {
        int id = product.getId();
        if (!prodeatails.containsKey(id)) {
            errormessage = "Cannot update. Product with ID: " + id + " does not exist.";
            logger.severe(errormessage);
            throw new IllegalStateException(errormessage);
        } else {
            prodeatails.put(id, product);
            logger.info("Product with ID: " + id + " updated successfully.");
        }
    }



public Product getProduct(int id){
        return prodeatails.get(id);
}



    public String getErrorMessage() {
        return errormessage;
    }


    public boolean productExists(int id) {
        return prodeatails.containsKey(id);
    }

    public Product getProductById(int id) {
        logger.info("Attempting to retrieve product with ID: " + id);
        Product product = prodeatails.get(id); // or however you are retrieving the product
        if (product == null) {
            logger.info("Product with ID " + id + " not found.");
        } else {
            logger.info("Product with ID " + id + " found: " + product.toString());
        }
        return product;
    }


    public Map<Integer, Product> getProducts() {
        return prodeatails;
    }
    public String getProductErrorMessage(int id) {
        return "Product with ID " + id + " does not exist.";
    }

    public void setProductNotFoundMessage(int productId) {
        this.errormessage = "Product with ID " + productId + " does not exist";
    }

}
