Feature: To ensure that gherkin salad is working ok I want to test using the jquery ui
	
	#EXECUTION PLAN INSTRUCTION AT TOP
	
	Scenario: Test jquery accordian
		Given 	User clicked Demos & Documentation
			And User clicked Accordion
		When 	User clicked Accordion Section 2
		When 	User clicked Accordion Section 1
		
	Scenario: Test jquery autocomplete
		Given 	User clicked Demos & Documentation
			And User clicked Autocomplete
		When 	User enters text ja in text box Autocomplete Tags
		Then 	User waits for 7 seconds
	
	Scenario: Test jquery DatePicker
		Given 	User clicked Demos & Documentation
			And User clicked Datepicker
			And Page had disabled elements ~Datepicker calendar~
		When 	User clicks Datepicker text
		Then 	User waits for 7 seconds
			And Page has enabled elements ~Datepicker calendar~
	
	#EXECUTION PLAN INSTRUCTION AT BOTTOM