Feature: Monitor Profits and Generate Financial Reports
  Actor: Admin
  Description: This feature enables the admin to track each store's profits and create financial reports.

  Scenario: Admin views store profits
    Given an admin is logged in
    When the admin navigates to the financial reports page
    Then the admin should see a list of all stores with their corresponding profits
