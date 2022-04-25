Feature: User can create bookhint with author, publisher and release year

  Scenario: Author to a bookhint
    Given Bookhint is initialized
    When bookhint is given author "Robert Martin"
    Then the authors name should be "Robert Martin"

  Scenario: Publisher to a bookhint
    Given Bookhint is initialized
    When bookhint is given publisher "ISBN"
    Then the publisher should be "ISBN"

  Scenario: Release Year to a bookhint
    Given Bookhint is initialized
    When bookhint is given release year 2008
    Then the release year should be 2008