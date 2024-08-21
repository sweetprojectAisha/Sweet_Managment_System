
package MYApp_Sweet;

import java.util.Date;

public class Feedback {
    private String itemName;
    private String rating;
    private String comment;
    private String feedbackDate;
    private String feedbackStatus;

    public Feedback(String itemName, String rating, String comment, String feedbackStatus) {
        this.itemName = itemName;
        this.rating = rating;
        this.comment = comment;
        this.feedbackDate = new Date().toString(); // Store the current date as the feedback date
        this.feedbackStatus = feedbackStatus;
    }

    public Feedback() {

    }

    // Getter and Setter methods
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    @Override
    public String toString() {
        return "Feedback for " + itemName + " on " + feedbackDate + ": \n" +
                "Rating: " + rating + "\n" +
                "Comment: " + comment + "\n" +
                "Status: " + feedbackStatus;
    }

    public boolean matches(String itemName, String rating, String comment, String feedbackDate, String feedbackStatus) {
        // Intentional error: Simulate a mismatch if the rating is "5"
        if ("5".equals(rating)) {
            return false;  // Force a mismatch
        }

        return this.itemName.equals(itemName) &&
                this.rating.equals(rating) &&
                this.comment.equals(comment) &&
                this.feedbackDate.equals(feedbackDate) &&
                this.feedbackStatus.equals(feedbackStatus);
    }
}
