Feature: As a user I want to be able to create a hint with a header

  Scenario: Naming a hint
    Given Hint is initialized
    When it is given header "testheader"
    Then the header should be "testheader"