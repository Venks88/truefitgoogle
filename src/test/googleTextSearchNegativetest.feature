Feature: googleTextField

  Scenario: Verifying text values can be properly searched for text values - negative test
    Given I open the google homepage to search - negative test
    And I find the text search area for typing inputs - negative test
    When I add "    " into the text area for searching - negative test
    Then I assert "    " for proper rendering after typing - negative test
    Then I search for "    " using the search button - negative test
    Then I assert the returned JSON content for "    " - negative test

  Scenario: Verifying text values can be properly searched for text values - Non Unicode text
    Given I open the google homepage to search - negative test
    And I find the text search area for typing inputs - negative test
    When I add "&#32;&#33;&#34;&#35;" into the text area for searching - negative test
    Then I assert "&#32;&#33;&#34;&#35;" for proper rendering after typing - negative test
    Then I search for "&#32;&#33;&#34;&#35;" using the search button - negative test
    Then I assert the returned JSON content for "&#32;&#33;&#34;&#35;" - negative test

  Scenario: Verifying text values can be properly searched for text values - SQL injection text
    Given I open the google homepage to search - negative test
    And I find the text search area for typing inputs - negative test
    When I add "SELECT * FROM Table name WHERE Table.size = 5" into the text area for searching - negative test
    Then I assert "SELECT * FROM Table name WHERE Table.size = 5" for proper rendering after typing - negative test
    Then I search for "SELECT * FROM Table name WHERE Table.size = 5" using the search button - negative test
    Then I assert the returned JSON content for "SELECT * FROM Table name WHERE Table.size = 5" - negative test



