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
Feature: create a staff account
  I want to use this template for creating a staff account

  @tag1
  Scenario: creat an account
    Given A new staff is emplyed
    When I create a staff account with <name>, <position>, <birthday>, <contact info>
    Then An account is created
    And saved to database

  @tag2
  Scenario Outline: data 
    Given I want to write a step with <name>, <position>, <birthday>, <contact info>
    When I check for the <value> in step
    Then I verify the <status> in step
