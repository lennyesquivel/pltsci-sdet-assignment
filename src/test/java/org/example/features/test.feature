Feature: test feature
  Scenario: test scenario
    Given Roomba is in position 1,2
    And Room size is 5 by 5
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
    Then Roomba is in position 1,3
    And Roomba cleaned 1 patches
