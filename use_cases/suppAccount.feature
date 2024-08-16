Feature: create New raw material supplier Account
  Actor: Admin
  Description: This feature allows the admin to create a new raw material supplier account, and the raw material supplier expected to receive a welcome message by email.
  Scenario: add new raw material supplier account
    Given an admin is logged in
    When the admin adds a new raw material supplier account with username "supplier1" and email "supplier222@gmail.com"
    Then the new supplier account should be created
    And the supplier should receive a welcome email
