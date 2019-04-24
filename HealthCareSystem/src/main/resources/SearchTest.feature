#Author: Kun Zhu
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: search test
  I want to use this template for search patient profile

  @tag1
  Scenario: search for a patient profile
    Given I want to search a patient profile
    When I click search
    And type <name> for search
    And type<email> for search
    Then I will be shown the relevant info such as, <birthday>, <address>, <patient number> etc.

  @tag2
  Scenario Outline: search results
    Given I confirmed to search by <name>
    When I check for the <info>
    Then I verify the <info>
