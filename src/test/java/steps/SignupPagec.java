
package steps;

import MyApp_sweet.MyApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SignupPagec {

    MyApp app;
    private List<MyApp.User> currentUsers;
    List<Map<String, String>> users;
    public SignupPagec() {
        app = MyApp.getInstance();
        users = new ArrayList<>();
    }

    @Given("the following users exist in login sys:")
    public void the_following_users_exist_in_login_sys(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,pass,type,email,phone,confirmpass;
        int age;
        MyApp.User user;
        currentUsers = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            name = columns.get("UserName");
            pass = columns.get("Password");
            type = columns.get("Type");
            email = columns.get("Email");
            phone = columns.get("Phone");
            confirmpass = columns.get("ConfirmPassword");
            age =Integer.parseInt( columns.get("Age"));
            user = new MyApp.User(name,email ,pass, confirmpass,phone,age,type);
            currentUsers.add(user);
            try {
                app.addUser(name,email ,pass, confirmpass,phone,age,type);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @When("the person signs up with the following valid details:")
    public void the_person_signs_up_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String name,pass,type,email,phone,confirmpass;
        int age;
        MyApp.User user;
        currentUsers = new ArrayList<>();

        for (Map<String, String> columns : rows) {
            name = columns.get("UserName");
            pass = columns.get("Password");
            type = columns.get("Type");
            email = columns.get("Email");
            phone = columns.get("Phone");
            confirmpass = columns.get("ConfirmPassword");
            age =Integer.parseInt( columns.get("Age"));
            user = new MyApp.User(name,email ,pass, confirmpass,phone,age,"");
            currentUsers.add(user);
            try {
                app.signupUser(user);
                assertTrue(true);
            } catch (Exception ex) {
                assertTrue(false);
            }
        }
    }

    @Then("the person should be redirected to the login page")
    public void the_person_should_be_redirected_to_the_login_page() {
        Assert.assertTrue(app.isOnLoginPage());
    }

    @When("the person signs up with the invalid password size :")
    public void the_person_signs_up_with_the_invalid_password_size(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("UserName");
            String pass = columns.get("Password");
            String email = columns.get("Email");
            String phone = columns.get("Phone");
            String confirmpass = columns.get("ConfirmPassword");
            int age = Integer.parseInt(columns.get("Age"));
            MyApp.User user = new MyApp.User(name, email, pass, confirmpass, phone, age, "user");

            try {
                app.signupUser(user);
                Assert.fail("Expected exception for invalid password size was not thrown");
            } catch (IllegalStateException ex) {
                Assert.assertTrue("Exception message does not match expected",
                        ex.getMessage().contains("Password must be at least 8 characters long."));
            }
        }
    }


    @When("the person signs up with the invalid phone size details:")
    public void the_person_signs_up_with_the_invalid_phone_size_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("UserName");
            String pass = columns.get("Password");
            String email = columns.get("Email");
            String phone = columns.get("Phone");
            String confirmpass = columns.get("ConfirmPassword");
            int age = Integer.parseInt(columns.get("Age"));
            MyApp.User user = new MyApp.User(name, email, pass, confirmpass, phone, age, "user");

            try {
                app.signupUser(user);
                Assert.fail("Expected exception for invalid password size was not thrown");
            } catch (IllegalStateException ex) {
                Assert.assertTrue("Exception message does not match expected",
                        ex.getMessage().contains("Phone number must be exactly 10 characters long."));
            }
        }
    }

    @When("the person signs up with password mismatch details:")
    public void the_person_signs_up_with_password_mismatch_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("UserName");
            String pass = columns.get("Password");
            String email = columns.get("Email");
            String phone = columns.get("Phone");
            String confirmpass = columns.get("ConfirmPassword");
            int age = Integer.parseInt(columns.get("Age"));
            MyApp.User user = new MyApp.User(name, email, pass, confirmpass, phone, age, "user");

            try {
                app.signupUser(user);
                Assert.fail("Expected exception for invalid password size was not thrown");
            } catch (IllegalStateException ex) {
                Assert.assertTrue("Exception message does not match expected",
                        ex.getMessage().contains("Password and confirm password do not match."));
            }
        }
    }

    @When("the person signs up with existing username details:")
    public void the_person_signs_up_with_existing_username_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("UserName");
            String pass = columns.get("Password");
            String email = columns.get("Email");
            String phone = columns.get("Phone");
            String confirmpass = columns.get("ConfirmPassword");
            int age = Integer.parseInt(columns.get("Age"));
            MyApp.User user = new MyApp.User(name, email, pass, confirmpass, phone, age, "user");

            try {
                app.signupUser(user);
                Assert.fail("Expected exception for invalid password size was not thrown");
            } catch (IllegalStateException ex) {
                Assert.assertTrue("Exception message does not match expected",
                        ex.getMessage().contains("User with name: " + user.getUsername() + " already exists."));
            }
        }
    }

    @When("the person signs up with empty fields details:")
    public void the_person_signs_up_with_empty_fields_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String name = columns.get("UserName");
            String pass = columns.get("Password");
            String email = columns.get("Email");
            String phone = columns.get("Phone");
            String confirmpass = columns.get("ConfirmPassword");
            int age = Integer.parseInt(columns.get("Age"));
            MyApp.User user = new MyApp.User(name, email, pass, confirmpass, phone, age, "user");

            try {
                app.signupUser(user);
                Assert.fail("Expected exception for invalid password size was not thrown");
            } catch (IllegalStateException ex) {
                Assert.assertTrue("Exception message does not match expected",
                        ex.getMessage().contains("All fields is required."));
            }
        }


    }


}
