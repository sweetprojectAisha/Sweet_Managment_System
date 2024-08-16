Feature: Collect and Present Data on Registered Users by City
  Actor: Admin
  Description: This feature enables the admin to collect and present statistics on registered users by city, offering insights into the distribution and demographics of users across different areas.
  Scenario: Admin views statistics on registered users by city
    Given an admin is logged in
    When the admin navigates to the user statistics page
    Then the admin should see a list of cities with the number of registered users in each city
    And the list should include city names and user counts
