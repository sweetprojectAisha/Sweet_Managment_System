package MyApp_sweet;

import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
    private static ProductManagement instance;
    private static final Logger logger = Logger.getLogger(ProductManagement.class.getName());
    private boolean is_an_owner;
    private boolean navagates_to_pmpage;
    private boolean navagates_to_salespage;
    private double totalSales = 0;
    private double totalProfit = 0;

    private Map<Integer, Product> prodeatails = new HashMap<>();
    private Map<Integer, SoldProduct> soldprodeatails = new HashMap<>();
    public String errormessage = "";

    private ProductManagement() {
    }

    public static ProductManagement getInstance() {
        if (instance == null) {
            instance = new ProductManagement();
        }
        return instance;
    }


    public static class Product {
        private int id;
        public String name;
        public double price;
        public int quantity;
        public String description;
        private int quantitySold;



        public Product(int id, String name, String description, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
            quantitySold = 0;
        }

        public int getQuantitySold() {
            return quantitySold;
        }

        public void setQuantitySold(int quantitySold) {
            this.quantitySold = quantitySold;
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

    public boolean isNavagates_to_salespage() {
        return navagates_to_salespage;
    }

    public void setNavagates_to_salespage(boolean navagates_to_salespage) {
        this.navagates_to_salespage = navagates_to_salespage;
    }

    public double calculateTotalSalesRevenue() {
        return totalSales;
    }


    public double calculateTotalCost() {
        return prodeatails.values().stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    public void calculateTotalSales() {
        totalSales = prodeatails.values().stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }

    public void calculateTotalProfit() {
        double totalCost = calculateTotalCost();
        totalProfit = totalSales - totalCost;
    }

    public double getTotalProfit() {
        return totalProfit;
    }


    public void sellProduct(int productId, int quantitySold, double costPercentage) {
        Product product = prodeatails.get(productId);
        if (product != null && product.getQuantity() >= quantitySold) {
            double saleAmount = product.getPrice() * quantitySold;
            double costAmount = saleAmount * costPercentage / 100;
            double profit = saleAmount - costAmount;
            totalSales += saleAmount;
            totalProfit += profit;
            product.setQuantity(product.getQuantity() - quantitySold);
            product.setQuantitySold(product.getQuantitySold() + quantitySold);
            prodeatails.put(productId, product);

            logger.info("Sold " + quantitySold + " units of product ID: " + productId);
            logger.info("Sale amount: " + saleAmount);
            logger.info("Estimated cost amount: " + costAmount);
            logger.info("Calculated profit: " + profit);
            logger.info("Total sales updated to: " + totalSales);
            logger.info("Total profit updated to: " + totalProfit);
        } else {
            errormessage = "Product not available or insufficient quantity.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }
    }


    public void addSellProduct(int productId, double pricePerUnit, int quantitySold, double totalrevenue, double costPercentage, double discountPercentage) {
        if (productExists(productId)) {
            SoldProduct soldprod = new SoldProduct(productId, pricePerUnit, quantitySold, totalrevenue, costPercentage, discountPercentage);
            soldprodeatails.put(soldprod.getId(), soldprod);
        } else {
            errormessage = "The product with id " + productId + " doesn't exist";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }
    }

    public List<SoldProduct> getBestSellingProducts() {
        List<SoldProduct> productList = new ArrayList<>(soldprodeatails.values());
        productList.sort((p1, p2) -> Integer.compare(p2.getQuantitySold(), p1.getQuantitySold()));
        return productList;
    }


    public void applyDiscount(int productId, double discountPercentage) {
        Product product = prodeatails.get(productId);
        if (product != null && discountPercentage > 0 && discountPercentage <= 100) {
            double originalPrice = product.getPrice();
            double discountAmount = originalPrice * (discountPercentage / 100);
            double newPrice = originalPrice - discountAmount;
            product.setPrice(newPrice);

            logger.info("Applied " + discountPercentage + "% discount to product ID: " + productId);
            logger.info("Original price: " + originalPrice + ", New price: " + newPrice);
        } else {
            errormessage = "Invalid product ID or discount percentage.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }
    }

    public double calculateNewprice(int productId) {
        return prodeatails.get(productId).getPrice();
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
            if (product.getId() <= 0 || product.getName() == null || product.getName().isEmpty() ||
                    product.getDescription() == null || product.getDescription().isEmpty() ||
                    product.getPrice() <= 0 || product.getQuantity() <= 0) {
                logger.warning("Invalid product details: " + product);
                errormessage = "Invalid product details.";
                throw new IllegalStateException(errormessage);
            }
            prodeatails.put(id, product);
            logger.info("Product with ID: " + id + " updated successfully.");
        }
    }

    public void deleteProduct(int id) {
        logger.info("Attempting to delete product with ID: " + id);
        if (!productExists(id)) {
            errormessage = "Product with ID " + id + " does not exist.";
            logger.warning(errormessage);
            throw new IllegalStateException(errormessage);
        }

        if (soldprodeatails.containsKey(id)){
            soldprodeatails.remove(id);
        }
        prodeatails.remove(id);
        if(prodeatails.isEmpty()){
            totalSales = 0.0;
            totalProfit = 0.0;
        }
        logger.info("Product with ID: " + id + " deleted successfully.");
    }


    public Product getProduct(int id) {
        return prodeatails.get(id);
    }

    public SoldProduct getSoldProduct(int id) {
        return soldprodeatails.get(id);
    }


    public String getErrorMessage() {
        return errormessage;
    }


    public boolean productExists(int id) {
        return prodeatails.containsKey(id);
    }

    public Product getProductById(int id) {
        logger.info("Attempting to retrieve product with ID: " + id);
        Product product = prodeatails.get(id);
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

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Map<Integer, Product> getProdeatails() {
        return prodeatails;
    }

    public void setProdeatails(Map<Integer, Product> prodeatails) {
        this.prodeatails = prodeatails;
    }

    public Map<Integer, SoldProduct> getSoldprodeatails() {
        return soldprodeatails;
    }

    public void setSoldprodeatails(Map<Integer, SoldProduct> soldprodeatails) {
        this.soldprodeatails = soldprodeatails;
    }

    public String getProductErrorMessage(int id) {
        return "Product with ID " + id + " does not exist.";
    }

    public void setProductNotFoundMessage(int productId) {
        this.errormessage = "Product with ID " + productId + " does not exist";
    }

    public static class SoldProduct {
        private int id;
        public double price;
        private int quantitySold;
        private double totalrevenue;
        private double costpercentage;
        private double discountPercentage;

        public SoldProduct(int id, double price, int quantitySold, double totalrevenue, double costpercentage, double discountPercentage) {
            this.id = id;
            this.price = price;
            this.quantitySold = quantitySold;
            this.totalrevenue = totalrevenue;
            this.costpercentage = costpercentage;
            this.discountPercentage = discountPercentage;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantitySold() {
            return quantitySold;
        }

        public void setQuantitySold(int quantitySold) {
            this.quantitySold = quantitySold;
        }

        public double getTotalrevenue() {
            return totalrevenue;
        }

        public void setTotalrevenue(double totalrevenue) {
            this.totalrevenue = totalrevenue;
        }

        public double getCostpercentage() {
            return costpercentage;
        }

        public void setCostpercentage(double costpercentage) {
            this.costpercentage = costpercentage;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }
    }




}
