Feature: Out of boundaries Test Roomba cleaning API for Platform Science

  Scenario: Dirt patches are placed outside room boundaries
    Given Roomba is in position "1","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 5 | 0 |
      | 5 | 2 |
      | 5 | 3 |
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
    And Roomba cleaned 0 patches

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

