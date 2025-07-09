Feature: PetApi tests

  Scenario Outline: Get Pet request by ID
    Given the user sends a pet get request by <id>
    Then verify response <statusCode> <id>
    Examples:
      | id | statusCode |
      | 5  | 200        |
      | 20 | 404        |
