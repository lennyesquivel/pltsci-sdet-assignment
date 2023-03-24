Feature: Object types Test Roomba cleaning API for Platform Science

  Scenario: Unexpected object type in roomba position is sent in body, error response code expected
    Given Roomba is in position "s","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
      | 2 | 3 |
    And Roomba follows the directions
      | N |
      | N |
      | E |
    When Request is sent
    Then Response has "error" code

  Scenario: Unexpected object type in room size is sent in body, error response code expected
    Given Roomba is in position "0","2"
    And Room size is "5" by "p"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
      | 2 | 3 |
    And Roomba follows the directions
      | N |
      | N |
      | E |
    When Request is sent
    Then Response has "error" code

  Scenario: Unexpected object type in patches positions is sent in body, error response code expected
    Given Roomba is in position "0","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | h |
      | 2 | 2 |
      | 2 | 3 |
    And Roomba follows the directions
      | N |
      | N |
      | E |
    When Request is sent
    Then Response has "error" code

  Scenario: Wrong direction is sent in body, error response code expected
    Given Roomba is in position "0","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | h |
      | 2 | 2 |
      | 2 | 3 |
    And Roomba follows the directions
      | T |
      | N |
      | E |
    When Request is sent
    Then Response has "error" code
