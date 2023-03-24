Feature: Test Roomba cleaning API for Platform Science

  Scenario: Roomba hoovers over one patch twice and returns one patch cleaned
    Given Roomba is in position "1","2"
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
      | S |
      | E |
      | E |
      | S |
      | W |
      | N |
      | W |
      | W |
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "1","3"
    And Roomba cleaned 1 patches

  Scenario: Roomba is positioned outside room boundaries and sends error response code
    Given Roomba is in position "5","2"
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

  Scenario: Room size is set to 0 by 0 dimensions and sends error response code
    Given Roomba is in position "5","2"
    And Room size is "0" by "0"
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

  Scenario: Room size is set to 0 by 0 dimensions and sends error response code
    Given Roomba is in position "5","2"
    And Room size is "0" by "0"
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
