Feature: delete Store Owner Account
  Description:
  This feature allows the admin to deactivate an existing store owner account in the system. Once deactivated, the store owner account will be marked as inactive, preventing the store owner from logging in.
  Scenario: delete store owner account
    Given an admin is logged in
    And a store owner account with username "store_owner333" exists
    When the admin delete the store owner account
    Then the store owner account should be marked as delete
    And the store owner He cannot log into the account again
