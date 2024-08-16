Feature: View Monthly Profit Report for All Stores
  Actor: Admin
  Description: This feature allows the admin to access a monthly profit report for all stores, with a comparison to the profits from the previous month.

  Scenario: Admin examines the monthly profit report for all stores
    Given the admin is logged in
    When the admin navigates to the financial reports page
    And chooses the monthly view option
    Then the admin should see a report detailing the monthly profits for all stores
    And the report should include a comparison with the profits from the prior month
