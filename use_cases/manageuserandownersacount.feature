Feature: Manage user accounts

  Background:
    Given the admin is logged in

  Scenario: Add a new store owner account with valid details
    When the admin adds a store owner account with the following details:
      | Name          | Email              | Phone       | Age         |
      | John       | john@example.com   | 1234567890  | 20  |
      | ahmed    | john@example.com   | 1234567890  | 30 |
    Then the store owner account should be added successfully

  Scenario: Attempt to add a user account with missing details
    When the admin adds a owner account with the following missing details:
      | Name          | Email | Phone       | Age       |
      |               | john@example.com | 1234567890  | 30|



  Scenario: Update an existing store owner account with valid details
    When the admin updates the store owner account with the following details:
      | Name          | Email              | Phone       |Age|
      | John  | johnny@example.com | 1122334455  |    20  |
    Then the store owner account should be updated successfully

  Scenario: Delete an existing raw material supplier account
    When the admin deletes the raw material supplier account with name "ahmed"
    Then the raw material supplier account should be deleted successfully

