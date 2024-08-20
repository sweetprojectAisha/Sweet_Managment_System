package steps;

import MYApp_Sweet.MyApp;
import MYApp_Sweet.OrderManagement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class LoginPagec {
  MyApp app;
  private List<MyApp.User> currentUsers;
  List<Map<String, String>> users;
  public LoginPagec() {
    app = MyApp.getInstance();
    users = new ArrayList<>();
  }
  @Given("the following users exist in table:")
  public void the_following_users_exist_in_table(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
    String name, pass, type, email, confirmPassword, phone, city;
    int age;
    MyApp.User user;
    currentUsers = new ArrayList<>();

    for (Map<String, String> columns : rows) {
      name = columns.get("UserName");
      pass = columns.get("Password");
      type = columns.get("Type");
      email = columns.getOrDefault("Email", "");
      confirmPassword = columns.getOrDefault("ConfirmPassword", pass); // Default to pass if not provided
      phone = columns.getOrDefault("Phone", "");
      age = Integer.parseInt(columns.getOrDefault("Age", "0"));
      city = columns.getOrDefault("City", "");

      System.out.println(name);
      user = new MyApp.User(name, email, pass, confirmPassword, phone, age, type, city);
      currentUsers.add(user);
      try {
        app.addUser(name, email, pass, confirmPassword, phone, age, type, city);
        assertTrue(true);
      } catch (Exception ex) {
        assertTrue(false);
      }
    }
  }

  @When("the person logged in with the following valid details:")
  public void the_person_logged_in_with_the_following_valid_details(io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
    String actualPage = app.login(loginDetails.get("UserName"), loginDetails.get("Password"));
    String expectedPage = app.getExpectedPage(loginDetails.get("UserName"));
    Assert.assertEquals(expectedPage, actualPage);
  }


  @When("the person logged in with the following invalid details:")
  public void the_person_logged_in_with_the_following_invalid_details(io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
    String actualPage = app.login(loginDetails.get("UserName"), loginDetails.get("Password"));
    Assert.assertEquals("loginPage", actualPage);
  }

  @Then("the person should be still in login_page")
  public void the_person_should_be_still_in_login_page() {
    Assert.assertTrue(app.isOnLoginPage());
  }
  @When("the person logged in with the following empty username details:")
  public void the_person_logged_in_with_the_following_empty_username_details(io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
    String actualPage = app.login(loginDetails.get("UserName"), loginDetails.get("Password"));
    Assert.assertEquals("loginPage", actualPage);
  }

  @When("the person logged in with the following empty password details:")
  public void the_person_logged_in_with_the_following_empty_password_details(io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
    String actualPage = app.login(loginDetails.get("UserName"), loginDetails.get("Password"));
    Assert.assertEquals("loginPage", actualPage);
  }

  @When("the person logged in with the following both empty details:")
  public void the_person_logged_in_with_the_following_both_empty_details(io.cucumber.datatable.DataTable dataTable) {
    Map<String, String> loginDetails = dataTable.asMap(String.class, String.class);
    String actualPage = app.login(loginDetails.get("UserName"), loginDetails.get("Password"));
    Assert.assertEquals("loginPage", actualPage);
  }
}

