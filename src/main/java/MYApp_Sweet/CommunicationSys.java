package MYApp_Sweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunicationSys {
    private static CommunicationSys instance;
    private Map<Integer,CommunicationSys.User > users = new HashMap<>();
    private Map<Integer, List<Message>> messages = new HashMap<>();
    public static CommunicationSys getInstance() {
        if (instance == null) {
            instance = new CommunicationSys();
        }
        return instance;
    }


    public void addUser(User user) {

        if (users.containsKey(user.getId())) {
            throw new IllegalStateException("User with ID " + user.getId() + " already exists.");
        }
        users.put(user.getId(), user);
        System.out.println("User " + user.getUsername() + " added successfully with ID " + user.getId());
    }

    public void sendMessage(int senderId, int receiverId, String content) {
        User sender = users.get(senderId);
        User receiver = users.get(receiverId);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver does not exist.");
        }

        Message message = new Message(sender, receiver, content);

        if (!messages.containsKey(receiverId)) {
            messages.put(receiverId, new ArrayList<>());
        }

        messages.get(receiverId).add(message);
        receiver.addMessage(content);
        receiver.addNotification("You have received a new message from " + sender.getUsername());
    }

    public User getUserById(int id) {
        return users.get(id);
    }


    public static class User {
        private int id;
        private String username;
        private String role;
        private List<String> inbox;
        private List<String> notifications;


        public User(int id, String username, String role) {
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
        }
    }
    public static class Message {
        private User sender;
        private User receiver;
        private String content;


        public Message(User sender, User receiver, String content) {
            this.sender = sender;
            this.receiver = receiver;
            this.content = content;
        }

        public User getSender() {
            return sender;
        }

        public User getReceiver() {
            return receiver;
        }

        public String getContent() {
            return content;
        }
    }
    public List<Message> getMessagesForUser(int userId) {
        return messages.getOrDefault(userId, new ArrayList<>());
    }
}
