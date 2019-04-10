#Author: s174406@student.dtu.dk
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
Feature: User Management

  @tag1
  Scenario: A User
    Given A User
    When I use the system
    Then I am not able to see any patients health data
    And I am not able to see any staff members personal data

  @tag2
  Scenario Outline: A doctor/nurse
    Given A doctor/nurse
    When I use the system
    Then I can see all patients in my departments health data
    And I cannot see or change staff members personal data
 
  @tag2
  Scenario Outline: Admin
    Given An admin user
    When I use the system
    Then I can see all patients in all departments health data
    And I can see and change staff members personal data
    And I can see all departments



    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
