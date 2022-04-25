Feature: User can create bloghint with url and author

  Scenario: Link to a bloghint
    Given Bloghint is initialized
    When bloghint is given url "https://dev.to/napicellatwit/consistency-models-52l"
    Then the bloghint url should be "https://dev.to/napicellatwit/consistency-models-52l"

  Scenario: Author to a bloghint
    Given Bloghint is initialized
    When bloghint is given author "Nicola Apicella"
    Then the blogpost author should be "Nicola Apicella"