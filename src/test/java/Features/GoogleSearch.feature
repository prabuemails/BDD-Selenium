Feature: Search the given keyword in google

  @Google
  Scenario Outline: Search for given Keyword '<keyword>'
    Given navigate to google site
    When search for '<keyword>'
    Then user should get results for '<keyword>'

    Examples:
    |keyword|
    |apple  |