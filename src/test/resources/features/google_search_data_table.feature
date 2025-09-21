Feature: passing multiple parameters to the same stop

  @google_search_data_table @smoke
  Scenario: Searching multiple items
    Given user is on Google search page
    Then user searches the following items
      | items        |
      | loop academy |
      | java         |
      | selenium     |
      | sql          |
      | taras        |
      | suidum       |
      | halina       |
      | savlat       |
      | polina       |
    And we love Loop Academy