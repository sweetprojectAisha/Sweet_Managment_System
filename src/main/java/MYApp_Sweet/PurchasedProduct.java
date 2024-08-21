
package MYApp_Sweet;
import java.util.ArrayList;
import java.util.List;
public class PurchasedProduct {
    private String itemName;
    private String purchaseDate;
    private List<Feedback> feedbackList;
    private double price;
    private String availability;
    private String dietaryInfo;
    // Constructor
    public PurchasedProduct(String itemName, String purchaseDate, double price, String availability, String dietaryInf) {
        this.itemName = itemName;
        this.purchaseDate = purchaseDate;
        this.feedbackList = new ArrayList<>();
        this.price = price;
        this.availability = availability;
        this.dietaryInfo = dietaryInf;
    }

    public PurchasedProduct(String itemName, double price, String currentDate)
    {
        this.itemName = itemName;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    // Getter and Setter for itemName
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter and Setter for purchaseDate
    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDietaryInfo() {
        return dietaryInfo;
    }

    public void setDietaryInfo(String dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Getter for feedbackList
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    // Method to add feedback to the feedbackList
    public void addFeedback(Feedback feedback) {
        this.feedbackList.add(feedback);
    }

    public String getAvailability()
    {
        return availability;
    }
    PurchasedProduct purchasedProduct;
    public PurchasedProduct addPurchasedProduct(String itemName, double price, String currentDate)
    {
        this.itemName=itemName;
        this.price=price;
        this.purchaseDate=currentDate;
        return purchasedProduct;
    }
}
