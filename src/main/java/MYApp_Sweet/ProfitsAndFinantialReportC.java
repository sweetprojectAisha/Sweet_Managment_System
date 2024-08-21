package MYApp_Sweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ProfitsAndFinantialReportC {
    private static ProfitsAndFinantialReportC instance;
    private double totalSales = 0.0;
    private double totalProfit = 0.0;

    private Map<String, List<Sale>> storeSales = new HashMap<>();
    private Map<String, Double> sellingPrices = new HashMap<>();
    private Map<String, Double> costPrices = new HashMap<>();
    private static final Logger logger = Logger.getLogger(ProfitsAndFinantialReportC.class.getName());
    private static final String REPORT_FILE = "reports.txt";

    private ProfitsAndFinantialReportC() {
        loadReports();
    }

    public static ProfitsAndFinantialReportC getInstance() {
        if (instance == null) {
            instance = new ProfitsAndFinantialReportC();
        }
        return instance;
    }


    private void loadReports() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String store = parts[0].trim();
                    int productId = Integer.parseInt(parts[1].trim());
                    String productName = parts[2].trim();
                    int quantitySold = Integer.parseInt(parts[3].trim());
                    double saleAmount = Double.parseDouble(parts[4].trim());
                    double profitAmount = Double.parseDouble(parts[5].trim());

                    Sale sale = new Sale(productId, productName, quantitySold, saleAmount, profitAmount);
                    List<Sale> salesList = storeSales.computeIfAbsent(store, k -> new ArrayList<>());
                    salesList.add(sale);
                } else {
                    logger.warning("Invalid report data format: " + line);
                }
            }
        } catch (IOException e) {
            logger.severe("Error loading reports: " + e.getMessage());
        }
    }

    // Save reports to a file
    private void saveReports() {
        try (FileWriter writer = new FileWriter(REPORT_FILE)) {
            for (Map.Entry<String, List<Sale>> entry : storeSales.entrySet()) {
                String store = entry.getKey();
                for (Sale sale : entry.getValue()) {
                    writer.write(serializeSale(store, sale) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            logger.severe("Error saving reports: " + e.getMessage());
        }
    }

    // Serialize a Sale object into a comma-separated string
    private String serializeSale(String store, Sale sale) {
        return store + "," +
                sale.getProductId() + "," +
                sale.getProductName() + "," +
                sale.getQuantitySold() + "," +
                sale.getSaleAmount() + "," +
                sale.getProfitAmount();
    }

    public void addProduct(Sale product) {
        logger.info("Attempting to add product: " + product.getProductName());
        if (productExists(product.getProductId())) {
            String errorMessage = "Product with ID: " + product.getProductId() + " already exists.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        if (product.getProductName() == null || product.getProductName().isEmpty() ||
                product.getQuantitySold() <= 0) {
            String errorMessage = "Invalid product details: " + product;
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }

        sellingPrices.put(String.valueOf(product.getProductId()), 0.0);
        costPrices.put(String.valueOf(product.getProductId()), 0.0);
        saveReports();
        logger.info("Product with ID: " + product.getProductId() + " added successfully.");
    }

    public void recordSale(String store, int productId, String productName, int quantitySold) {
        double sellingPrice = getSellingPrice(productId);
        double costPrice = getCostPrice(productId);
        double saleAmount = sellingPrice * quantitySold;
        double profitAmount = (sellingPrice - costPrice) * quantitySold;

        logger.info("Selling Price: " + sellingPrice);
        logger.info("Cost Price: " + costPrice);
        logger.info("Sale Amount: " + saleAmount);
        logger.info("Profit Amount: " + profitAmount);

        totalSales += saleAmount;
        totalProfit += profitAmount;

        Sale sale = new Sale(productId, productName, quantitySold, saleAmount, profitAmount);
        List<Sale> salesList = storeSales.computeIfAbsent(store, k -> new ArrayList<>());
        salesList.add(sale);
        saveReports();
        logger.info("Sale recorded: " + productName + " - Quantity: " + quantitySold);
    }

    public void setSellingPrice(int productId, double price) {
        sellingPrices.put(String.valueOf(productId), price);
    }

    public void setCostPrice(int productId, double price) {
        costPrices.put(String.valueOf(productId), price);
    }

    public double getSellingPrice(int productId) {
        if (sellingPrices.containsKey(String.valueOf(productId))) {
            return sellingPrices.get(String.valueOf(productId));
        } else {
            String errorMessage = "Selling price for product ID: " + productId + " not found.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    public double getCostPrice(int productId) {
        if (costPrices.containsKey(String.valueOf(productId))) {
            return costPrices.get(String.valueOf(productId));
        } else {
            String errorMessage = "Cost price for product ID: " + productId + " not found.";
            logger.warning(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }

    public double calculateTotalSales() {
        double total = 0.0;
        for (List<Sale> salesList : storeSales.values()) {
            for (Sale sale : salesList) {
                total += sale.getSaleAmount();
            }
        }
        return total;
    }

    public Map<String, Double> generateFinancialReport() {
        Map<String, Double> report = new HashMap<>();
        report.put("Total Sales", calculateTotalSales());
        report.put("Total Profit", getTotalProfit());
        saveReports();
        return report;
    }

    public Map<String, String> getBestSellingProductsByStore() {
        Map<String, String> bestSellingProducts = new HashMap<>();
        for (Map.Entry<String, List<Sale>> entry : storeSales.entrySet()) {
            String store = entry.getKey();
            List<Sale> salesList = entry.getValue();

            Sale bestSellingSale = null;
            for (Sale sale : salesList) {
                if (bestSellingSale == null || sale.getQuantitySold() > bestSellingSale.getQuantitySold()) {
                    bestSellingSale = sale;
                }
            }

            if (bestSellingSale != null) {
                bestSellingProducts.put(store, bestSellingSale.getProductName());
            }
        }
        return bestSellingProducts;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public boolean productExists(int productId) {
        return sellingPrices.containsKey(String.valueOf(productId)) &&
                costPrices.containsKey(String.valueOf(productId));
    }

    public static class Sale {
        private int productId;
        private String productName;
        private int quantitySold;
        private double saleAmount;
        private double profitAmount;

        public Sale(int productId, String productName, int quantitySold, double saleAmount, double profitAmount) {
            this.productId = productId;
            this.productName = productName;
            this.quantitySold = quantitySold;
            this.saleAmount = saleAmount;
            this.profitAmount = profitAmount;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantitySold() {
            return quantitySold;
        }

        public double getSaleAmount() {
            return saleAmount;
        }

        public double getProfitAmount() {
            return profitAmount;
        }
    }
}
