#Author: your.email@your.domain.com
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
Feature: Title of your feature
  I want to use this template for my feature file

  #@register
  Scenario: Register

    Given Get Login Page Url
    When Open register url
    And Input to Firstname textbox
    And Input to Lastname textbox
    And Input to email textbox
    And Input to password textbox
    And Input to comfirm password textbox
    When Click to register button
    Then Get register success message
    
  @register
  Scenario Outline: Register
  	# Link
    Given Get Login Page Url
    When Open register url
    
    # Textbox
    And Input to "First name:" textbox with value "<FrirstName>"
    And Input to "Last name:" textbox with value "<LastName>"
    And Input to "Email:" textbox with value "<Email>"
    And Input to "Password:" textbox with value "<Password>"
    And Input to "Confirm password:" textbox with value "<Confirmpassword>"
    
    # Button
    When Click to "register" button
    
    # Message
    Then Success message displayed with "Your registration completed"
    
    Examples: 
    |FrirstName	|LastName	|Email								|Password	|Confirmpassword|
    |Frank			|Cy				|test001@gmail.com		|123Aa@		|123Aa@					|

