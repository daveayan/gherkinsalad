Feature: To ensure that gherkin salad is working ok I want to test using the basic html controls on w3schools site
	
	#EXECUTION PLAN INSTRUCTION AT TOP
	
	Scenario: Go to website
		Given 	User visited website http://www.w3schools.com/html/html_forms.asp
	
	Scenario: Test text entry in text boxes
		Given 	User entered text gherkin in text box First Name
		When 	User enters text salad in text box Last Name
		
	Scenario: Test the text entry in password field
		Given 	User entered text gherkin in text box Password
		When 	User enters text salad in text box Password
		
	Scenario: Test the check boxes
		Given 	User checked text I have a bike
		When 	User checks text I have a car
		
	Scenario: Test the radio buttons
		Given 	User selected radio button text Male
		When 	User selects radio button text Female
	
	#Scenario: Test the submit button click
	#	Given 	User entered text gherkin-salad in text box User Name
	#	When 	User clicks Submit
	#	Then 	Page has text ~user=gherkin-salad~
		
	#EXECUTION PLAN INSTRUCTION AT BOTTOM