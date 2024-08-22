package MYApp_Sweet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommunicationSys {
    private static CommunicationSys instance;
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, List<Message>> messages = new HashMap<>();
    private static final String USERS_FILE_NAME = "users.txt";
    private static final String MESSAGES_FILE_NAME = "messages.txt";

    // Create a logger for this class
    private static final Logger LOGGER = Logger.getLogger(CommunicationSys.class.getName());

    // Private constructor for Singleton pattern
    private CommunicationSys() {
        loadUsers();
        loadMessages();
    }

    // Get singleton instance
    public static CommunicationSys getInstance() {
        if (instance == null) {
            instance = new CommunicationSys();
        }
        return instance;
    }

    // Load users from file
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 8); // Expect 8 fields
                if (parts.length == 8) {
                    String username = parts[0].trim();
                    String role = parts[6].trim();
                    int id = username.hashCode(); // Generate a unique ID from username
                    User user = new User(id, username, role);
                    users.put(id, user);
                    
                    // Log the user addition
                    LOGGER.log(Level.INFO, "User added: ID = {0}, Username = {1}, Role = {2}", new Object[]{id, username, role});
                } else {
                    // Log an error if user data is invalid
                    LOGGER.log(Level.WARNING, "Invalid user data format: {0}", line);
                }
            }
        } catch (IOException e) {
            // Log the exception
            LOGGER.log(Level.SEVERE, "Error loading users from file", e);
        }
    }

    // Add a new user
    public void addUser(User user) {
        if (users.containsKey(user.getId())) {
            throw new IllegalStateException("User with ID " + user.getId() + " already exists.");
        }
        users.put(user.getId(), user);
        
        // Log the successful user addition
        LOGGER.log(Level.INFO, "User {0} added successfully with ID {1}", new Object[]{user.getUsername(), user.getId()});
    }

    // Send a message from sender to receiver
    public void sendMessage(int senderId, int receiverId, String content) {
        User sender = users.get(senderId);
        User receiver = users.get(receiverId);
        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver does not exist.");
        }
        Message message = new Message(sender, receiver, content);
        messages.computeIfAbsent(receiverId, k -> new ArrayList<>()).add(message);
        receiver.addMessage(content);
        saveMessages();
        receiver.addNotification("You have received a new message from " + sender.getUsername());
    }

    // Get user by ID
    public User getUserById(int id) {
        return users.get(id);
    }

    // Load messages from file
    private void loadMessages() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MESSAGES_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    int senderId = Integer.parseInt(parts[0]);
                    int receiverId = Integer.parseInt(parts[1]);
                    String content = parts[2];
                    User sender = users.get(senderId);
                    User receiver = users.get(receiverId);
                    if (sender != null && receiver != null) {
                        Message message = new Message(sender, receiver, content);
                        messages.computeIfAbsent(receiverId, k -> new ArrayList<>()).add(message);
                    }
                }
            }
        } catch (IOException e) {
            // Log the exception
            LOGGER.log(Level.SEVERE, "Error loading messages from file", e);
        }
    }

    // Save messages to file
    private void saveMessages() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MESSAGES_FILE_NAME))) {
            for (List<Message> messageList : messages.values()) {
                for (Message message : messageList) {
                    writer.write(message.getSender().getId() + "," +
                            message.getReceiver().getId() + "," +
                            message.getContent());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            // Log the exception
            LOGGER.log(Level.SEVERE, "Error saving messages to file", e);
        }
    }

    // Get messages for a user
    public List<Message> getMessagesForUser(int userId) {
        return messages.getOrDefault(userId, new ArrayList<>());
    }

    // User inner class
    public static class User {
        private final int id;
        private String username;
        private String role;
        private final List<String> inbox = new ArrayList<>();
        private final List<String> notifications = new ArrayList<>();

        public User(int id, String username, String role) {
            this.id = id;
            this.username = username;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }

        public List<String> getInbox() {
            return new ArrayList<>(inbox);
        }

        public List<String> getNotifications() {
            return new ArrayList<>(notifications);
        }

        public void addMessage(String message) {
            inbox.add(message);
        }

        public void addNotification(String notification) {
            notifications.add(notification);
        }
    }

    // Message inner class
    public static class Message {
        private final User sender;
        private final User receiver;
        private final String content;

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
}
