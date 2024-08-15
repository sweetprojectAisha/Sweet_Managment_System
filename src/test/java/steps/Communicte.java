package steps;
import MYApp_Sweet.CommunicationSys;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Communicte {
    CommunicationSys cs;
    List<Map<String, String>> users;
    private List<CommunicationSys.User> currentUsers;

    public Communicte() {
        cs = CommunicationSys.getInstance();

    }
    @Given("the following users exist:")
    public void the_following_users_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        int id;
        String name, role;
        CommunicationSys.User user;
        currentUsers = new ArrayList<>();
        for (Map<String, String> columns : rows) {
            id = Integer.parseInt(columns.get("userId"));
            name = columns.get("username");
            role = columns.get("role");
            user = new CommunicationSys.User(id, name, role);
            currentUsers.add(user);
            try {
                cs.addUser(user);
                assertTrue(true);
            } catch (Exception ex) {
                fail();
            }
        }
    }

    @When("the exist owner with ID {int} send a message {string} to exist owner with ID {int}")
    public void the_exist_owner_with_id_send_a_message_to_exist_owner_with_id(Integer id1, String message, Integer id2) {
        try {
            cs.sendMessage(id1, id2, message);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }


    @When("the exist owner with ID {int} send a message {string} to exist user with ID {int}")
    public void the_exist_owner_with_id_send_a_message_to_exist_user_with_id(Integer id1, String message, Integer id3) {
        try {
            cs.sendMessage(id1, id3, message);
            assertTrue(true);
        } catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Then("the message should be delivered to the owner with ID {int}")
    public void the_message_should_be_delivered_to_the_owner_with_id(Integer id5) {
        CommunicationSys.User owner = cs.getUserById(id5);
        List<String> inbox = owner.getInbox();
        boolean messageDelivered = inbox.stream().anyMatch(msg -> msg.contains("Meeting at 3 PM"));
        assertTrue("Message was not delivered to the owner with ID " + id5, messageDelivered);
    }


    @Then("the owner with ID {int} should receive a notification")
    public void the_owner_with_id_should_receive_a_notification(Integer userid) {
        CommunicationSys.User user = cs.getUserById(userid);
        Assert.assertNotNull("User with ID " + userid + " does not exist.", user);
        List<String> notifications = user.getNotifications();
        assertTrue("No notifications were received by the owner with ID " + userid,
                notifications != null && !notifications.isEmpty());
    }

    @Then("the message should be delivered to the user with ID {int}")
    public void the_message_should_be_delivered_to_the_user_with_id(Integer userid) {
        CommunicationSys.User owner = cs.getUserById(userid);
        List<String> inbox = owner.getInbox();
        boolean messageDelivered = inbox.stream().anyMatch(msg -> msg.contains("Welcome to our service!"));
        assertTrue("Message was not delivered to the user with ID " + userid, messageDelivered);
    }

    @Then("the user with ID {int} should receive a notification")
    public void the_user_with_id_should_receive_a_notification(Integer userid) {
        CommunicationSys.User user = cs.getUserById(userid);
        Assert.assertNotNull("User with ID " + userid + " does not exist.", user);
        List<String> notifications = user.getNotifications();
        assertTrue("No notifications were received by the user with ID " + userid,
                notifications != null && !notifications.isEmpty());
    }
//    @When("the exist user with ID {int} sends a message {string} to exist owner with ID {int}")
//    public void the_exist_user_with_id_sends_a_message_to_exist_owner_with_id(Integer id1, String message, Integer id2) {
//        try {
//            cs.sendMessage(id1, id2, message);
//            assertTrue(true);
//        } catch (Exception ex) {
//            assertTrue(false);
//        }
//
//    }
//
//    @Then("the exist owner with ID {int} should view messages from exist user with ID {int} {string}")
//    public void the_exist_owner_with_id_should_view_messages_from_exist_user_with_id(Integer ownerId, Integer userId, String expectedMessageContent) {
//        CommunicationSys.User owner = cs.getUserById(ownerId);
//       Assert. assertNotNull("Owner with ID " + ownerId + " does not exist.", owner);
//        List<String> ownerInbox = owner.getInbox();
//        boolean messageVisibleToOwner = ownerInbox.contains(expectedMessageContent);
//        assertTrue("Owner with ID " + ownerId + " cannot view the message: " + expectedMessageContent, messageVisibleToOwner);}
//
//
//
}
