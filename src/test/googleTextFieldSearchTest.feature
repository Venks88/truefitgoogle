Feature: googleTextField

  Scenario: Verifying text values can be properly searched for text values
    Given I open the google homepage to search
    And I find the text search area for typing inputs
    When I add "Hello" into the text area for searching
    Then I assert "Hello" for proper rendering after typing
    Then I search for "Hello" using the search button
    Then I assert the returned JSON content for "Hello"

  Scenario: Verifying text values can be properly searched for text values - UTF-8 text
    Given I open the google homepage to search
    And I find the text search area for typing inputs
    When I add "私tわたしワタシ" into the text area for searching
    Then I assert "私tわたしワタシ" for proper rendering after typing
    Then I search for "私tわたしワタシ" using the search button
    Then I assert the returned JSON content for "私tわたしワタシ"

