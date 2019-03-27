#Author: s186258@student.dtu.dk
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
Feature: Patient Registration

  @tag1
	Scenario: Succesful registration of staff
		Given staff to register
		And relevant info (name, surname,job)
		When information is entered
		Then System creates a unique serial number and email address
		
	Scenario: Changing staff information
		Given staff member
		And new information
		When new information is entered
		Then saved staff member infomartion is changed to the new version
		
	Scenario: Searching for staff member 
		Given some staff member information (name, surname, birth-date, etc..)
		When information is searched for
		Then The system returns the staff member who matchs the information given
  