Feature: Track Top-Selling Items in Each Store
  Actor: Admin
  Description: This feature allows the admin to track the highest-selling items in each store.
  Scenario: Admin examines top-selling items for a store
    Given an admin is logged in
    When the admin accesses the top-selling items page
    And chooses a store from the available options
    Then the admin should view a list of the highest-selling items for the chosen store
    And the list should display product names, quantities sold, and total revenue
