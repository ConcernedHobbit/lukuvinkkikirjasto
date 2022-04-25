Feature: User can create videohint with url and comment

  Scenario: Link to a videohint
    Given Videohint is initialized
    When videohint is given url "https://www.youtube.com/watch?v=TzeBrDU-JaY"
    Then the videohint url should be "https://www.youtube.com/watch?v=TzeBrDU-JaY"

  Scenario: Comment to a videohint
    Given Videohint is initialized
    When videohint is given comment "Hyvä selitys merge sortin toiminnasta esimerkin avulla"
    Then the comment should be "Hyvä selitys merge sortin toiminnasta esimerkin avulla"