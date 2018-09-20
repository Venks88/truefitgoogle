Feature: googleTextField

  Scenario: Verifying text values and types in the google text field valid text
    Given I open the google homepage
    And I find the text search area
    When I add "Hello" into the text area
    Then I assert "Hello" for proper rendering

  Scenario: Verifying text values and types in the google text field invalid text
    Given I open the google homepage
    And I find the text search area
    When I add "    " into the text area
    Then I assert "    " for proper rendering

  Scenario: Verifying text values and types in the google text field UTF-8 text
    Given I open the google homepage
    And I find the text search area
    When I add "私tわたしワタシ" into the text area
    Then I assert "私tわたしワタシ" for proper rendering

  Scenario: Verifying text values and types in the google text field ASCII text
    Given I open the google homepage
    And I find the text search area
    When I add "¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼" into the text area
    Then I assert "¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼" for proper rendering
