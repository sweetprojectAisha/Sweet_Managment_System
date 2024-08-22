Feature: Financial Management and Best-Selling Products

  Background:
    Given the admin is logged in

  Scenario: Record a sale and update profits
    Given the following product exists:
      | ID | Name       | Description    | Price | Quantity |
      | 1  | WidgetA   | A useful widget | 10.00 | 100      |
      | 2  | Widget B   | tasty             | 20.00         |200|

  Scenario: Generate a financial report
    Given the following products have been solded:
      | ID | Name       | Quantity Sold | Selling Price |
      | 1  | Widget A   | 10            | 15.00         |
      | 2  | Widget B   | 5             | 20.00         |
    Then the total sales should be 250.0
    And the total profit should be 250.0
    Then the financial report should include:
      | Total Sales | Total Profit |
      | 250.00      | 250.00       |

  Scenario: Identify best-selling products in each store
    Given the following sales data for stores:
      | Store | Product ID | Product Name | Quantity Sold |
      | Store1| 1          | Widget A     | 50            |
      | Store2| 2          | Widget B     | 30            |
#      | Store2| 1          | Widget A     | 20            |
#      | Store2| 3          | Widget C     | 40            |
    Then the best-selling products for each store should be:
      | Store | Best-Selling Product | Quantity Sold |
      | Store1| Widget A             | 50            |
      | Store2| Widget B            | 30            |
