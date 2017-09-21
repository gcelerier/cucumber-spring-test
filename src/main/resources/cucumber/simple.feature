Feature: Implement a Hello World REST service

  In order to test the availability of the server
  As a server admin
  I want a simple page to test if the server is up

  Scenario:
    Given A REST client
    When I perform a GET on /hello
    Then I should get status 200
    And I should get "Hello World!" as a response

  Scenario:
    Given A REST client
    When I perform a GET on /helloWorld
    Then I should get status 404