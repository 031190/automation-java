Feature: PetApi tests

  Scenario Outline: Get Pet request by ID
    Given the user sends a pet get request by <id>
    Then verify response <statusCode> <id>
    Examples:
      | id | statusCode |
      | 5  | 200        |
      | 20 | 404        |

  Scenario Outline: Create new Pet with json file
    Given the user sends a pet post request using body as <file>
    Then verify created pet <file> <statusCode>
    Examples:
      | file     | statusCode |
      | pet.json | 200        |