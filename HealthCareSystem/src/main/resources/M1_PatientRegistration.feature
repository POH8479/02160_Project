#Author: s151607@student.dtu.dk
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
	Scenario: Succesful registration
		Given patient to register
		And relevant info (name, surname, birth-date, home address, phone number, tribe, alive/dead, etc..)
		When information is entered
		Then patient is saved
		And incremential patient number is generated
		
	Scenario: Changing patient information
		Given patient 
		And new information
		When new information is entered
		Then saved patient infomartion is changed to the new version
		
	Scenario: Searching for patient 
		Given some patient information (name, surname, birth-date, etc..)
		When information is searched for
		Then return the patients who match the information.
  