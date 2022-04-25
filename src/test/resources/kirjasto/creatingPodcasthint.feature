Feature: User can create podcasthint with author, name and description

  Scenario: Author to a podcasthint
    Given Podcasthint is initialized
    When podcasthint is given author "Sami Honkonen"
    Then author of the podcasthint should be "Sami Honkonen"

  Scenario: Name to a podcasthint
    Given Podcasthint is initialized
    When podcasthint is given name "Boss Level Podcast"
    Then the name of the podcasthint should be "Boss Level Podcast"

  Scenario: Description to a podcasthint
    Given Podcasthint is initialized
    When podcasthint is given description "Personal Kanban, which is an approach to dealing with the overload of stuff you need to deal with."    
    Then the podcasthint should have description "Personal Kanban, which is an approach to dealing with the overload of stuff you need to deal with."