package MYApp_Sweet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunicationSys {
    private static CommunicationSys instance;
    private Map<Integer,CommunicationSys.User > users = new HashMap<>();
    private static final String USERS_FILE_NAME = "users.txt";
    private Map<Integer, List<Message>> messages = new HashMap<>();
    private static final String FILE_NAME = "messages.txt";
    public static CommunicationSys getInstance() {
        if (instance == null) {
            instance = new CommunicationSys();
        }
        return instance;
    }
public CommunicationSys(){
        loadUsers();
        loadMessages();
}

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 8); // Expect 8 fields

                if (parts.length == 8) {
                    String username = parts[0].trim();
                    // Skipping email, password, confirmPassword, phoneNumber, and age
                    String role = parts[6].trim();
                    String location = parts[7].trim();

                    // Assuming unique usernames as IDs (just for demonstration)
                    int id = username.hashCode(); // Generate a unique ID from username

                    User user = new User(id, username, role);
                    users.put(id, user);

                    System.out.println("User added: ID = " + id + ", Username = " + username + ", Role = " + role);
                } else {
                    System.err.println("Invalid user data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        saveMessages();
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
    private void loadMessages() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
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
                        if (!messages.containsKey(receiverId)) {
                            messages.put(receiverId, new ArrayList<>());
                        }
                        messages.get(receiverId).add(message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveMessages() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<Integer, List<Message>> entry : messages.entrySet()) {
                for (Message message : entry.getValue()) {
                    writer.write(message.getSender().getId() + "," +
                            message.getReceiver().getId() + "," +
                            message.getContent());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
