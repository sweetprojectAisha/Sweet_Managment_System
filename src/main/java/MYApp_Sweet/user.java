
package MYApp_Sweet;

import java.util.ArrayList;
import java.util.List;


public class user {


    private int id;
    private String username;
    private String role;
    private List<String> inbox;
    private List<String> notifications;


    public user(int id, String username, String role)
    {
        this.id = id;
        this.username = username;
        this.role = role;
        this.inbox = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public List<String> getInbox() {
        return inbox;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void addMessage(String message) {
        inbox.add(message);
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }}
