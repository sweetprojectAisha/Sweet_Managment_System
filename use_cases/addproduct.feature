

Feature: Add Product

  Background:
    Given the user is logged in as an owner
    And the owner pages are open and the owner navigates to product management

  Scenario Outline: Add a new product with valid details
    When the owner adds a product with the following valid details:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the product should be added to the list


    Examples:
      | proid | proname      | prodescription | proprice | proquantity |
      | 1     | cake         | tasty          | 999.99   | 10         |
      | 2     | cookie       | well_backed    | 49.99    | 50         |

  Scenario Outline: Increase quantity of an existing product
    When the owner updates the product with the following details for existing product:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the quantity of the existing product should be updated to "<new_quantity>"


    Examples:
      | proid | proname      | prodescription | proprice | proquantity | new_quantity |
      | 1     | cake         | tasty          | 999.99   | 10          | 20           |
      | 2     | cookie       | well_backed    | 49.99    | 50          | 70           |

  Scenario Outline: Increase quantity of non existing product
    When the owner updates the product with the following details for non existing product:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |
    Then the quantity of the existing product should not be updated to "<new_quantity>"


    Examples:
      | proid | proname      | prodescription | proprice | proquantity | new_quantity |
      | 3     | cake         | tasty          | 999.99   | 10          | 20           |
      | 4     | cookie       | well_backed    | 49.99    | 50          | 70           |

  Scenario Outline: Add a product with missing fields
    When the user tries to add a product with the following missing details:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |

    And the product should not be added to the list

    Examples:
      | proid | proname | prodescription   | proprice | proquantity |
      | 3     |         | tasty            | 499.99   | 20         |
      | 4     | cake    |                  | 79.99    | 15         |
      | 5     | cookie  | well_backed      |          | 30         |
      | 6     | chococake| tasty           | 89.99    |           |

  Scenario Outline: Add a product with invalid data
    When the user tries to add a product with the following invalid details:
      | ProID   | ProName        | Description   | ProPrice   | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |

    And the product should not be added to the list

    Examples:
      | proid | proname       | prodescription   | proprice  | proquantity |
      | 7     | cake          | tasty            | -999.99   | 10         |
      | 8     | cookie        | well_backed      | 49.99     | -10        |
#      | 9     | cookie        | well_backed      | 49.99     | 10000      |

  Scenario Outline: Add a product with a duplicate ID
    When the user tries to add a product with the following details and dublicate id:
      | ProID   | ProName        | Description   | ProPrice | ProQuantity |
      | <proid> | <proname>      | <prodescription> | <proprice> | <proquantity> |



    Examples:
      | proid | proname       | prodescription   | proprice | proquantity |
      | 1     | New cake      | tasty            | 799.99   | 5          |
      | 2     | New cookie    | well_backed      | 59.99    | 20         |
