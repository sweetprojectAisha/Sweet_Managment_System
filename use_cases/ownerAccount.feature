Feature: create New Store Owner Account
  Actor: Admin
  Description: This feature allows the admin to create a new store owner account, and the store owner should receive a welcome email.
  Scenario: Add new store owner account
    Given an admin is logged in
    When the admin adds a new store owner account with username "store_owner1" and email "owner333@gmail.com"
    Then the new store owner account should be created and the store owner is expected to receive a welcome message by email.
