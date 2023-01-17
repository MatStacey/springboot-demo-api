Feature: the version can be retrieved
  Scenario: client makes call to GET /greeting
    When the client calls /greeting
    Then the client receives status code of 200