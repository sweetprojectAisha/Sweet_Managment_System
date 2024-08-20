Feature: Gather and display statistics on registered users by city

  Scenario:
    Given the following users are registered:
      | Name     | City     |
      | Alice    | Nablus   |
      | Bob      | Jenin    |
      | Charlie  | Nablus   |


  Scenario: Display user statistics by city
#    When I gather the user statistics by city
    Then the statistics should be displayed as follows:
      | City   | Number of Users |
      | Nablus | 2               |
      | Jenin  | 1               |
