Feature: delete raw material supplier Account
  Description:This feature allows the admin to delete an existing raw material supplier account in the system. Once delete, the raw material supplier account will be marked as inactive, preventing the raw material supplier from logging in.
  Scenario: delete raw material supplier account
    Given an admin is logged in
    And a store owner account with username "supplier333" exists
    When the admin delete the raw material supplier account
    Then the raw material supplier account should be marked as delete
    And the raw material supplier He cannot log into the account again
