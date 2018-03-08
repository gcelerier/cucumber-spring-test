@Txn
Feature: Implement a Person REST service

  In order to keep our contacts list up to date
  As a customer representative
  I want to perform CRUD operations on Person entities

  Scenario:
    Given A REST client
    And A list of six persons on the database
    When I retrieve a list of persons
    Then I should get status 200
    And I should get a list of six persons as a response

  Scenario Outline:
    Given A REST client
    And A list of six persons on the database
    When I search for person with ID <id>
    Then I should get status 200
    And I should get a person with first name <first name> and last name <last name>

    Examples:
      | id | first name | last name |
      | 1  | Geert      | Celerier  |
      | 2  | Tristan    | Fily      |
      | 3  | Jan        | Verbeeck  |
      | 4  | Gard       | Skauge    |
      | 5  | Vincent    | Letellier |
      | 6  | Stephen    | Ranson    |
