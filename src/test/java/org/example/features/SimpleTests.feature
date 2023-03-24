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

  Scenario: Roomba starts in position where dirt patch is located
    Given Roomba is in position "1","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 2 |
    And Roomba follows the directions
      | N |
      | N |
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "1","4"
    And Roomba cleaned 1 patches

  Scenario: No dirt patches are placed
    Given Roomba is in position "1","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
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

  Scenario: Missing parameters in request body
    Given Roomba is in position "",""
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 2 |
    And Roomba follows the directions
      | N |
      | N |
    When Request is sent
    Then Response has "error" code

  Scenario: Roomba hoovers over same position twice
    Given Roomba is in position "1","2"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
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
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "2","3"
    And Roomba cleaned 0 patches

  Scenario: Roomba hoovers over same position twice
    Given Roomba is in position "2","4"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
    And Roomba follows the directions
      | W |
      | W |
      | S |
      | S |
      | E |
      | N |
      | N |
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "1","4"
    And Roomba cleaned 0 patches

  Scenario: Roomba hoovers over same two positions twice
    Given Roomba is in position "2","4"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
    And Roomba follows the directions
      | W |
      | W |
      | S |
      | S |
      | E |
      | N |
      | N |
      | W |
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "0","4"
    And Roomba cleaned 0 patches


  Scenario: Roomba runs to a wall
    Given Roomba is in position "2","4"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 1 | 0 |
      | 2 | 2 |
    And Roomba follows the directions
      | N |
      | N |
      | N |
      | N |
      | N |
    When Request is sent
    Then Response has "successful" code
    And Roomba is in position "2","4"
    And Roomba cleaned 0 patches

  Scenario: Room is filled with dirt patches and roomba goes through some several times
    Given Roomba is in position "0","0"
    And Room size is "5" by "5"
    When Room has dirt patches in positions
      | x | y |
      | 0 | 0 |
      | 0 | 1 |
      | 0 | 2 |
      | 0 | 3 |
      | 0 | 4 |
      | 1 | 0 |
      | 1 | 1 |
      | 1 | 2 |
      | 1 | 3 |
      | 1 | 4 |
      | 2 | 0 |
      | 2 | 1 |
      | 2 | 2 |
      | 2 | 3 |
      | 2 | 4 |
      | 3 | 0 |
      | 3 | 1 |
      | 3 | 2 |
      | 3 | 3 |
      | 3 | 4 |
      | 4 | 0 |
      | 4 | 1 |
      | 4 | 2 |
      | 4 | 3 |
      | 4 | 4 |
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
    And Roomba is in position "0","1"
    And Roomba cleaned 8 patches
