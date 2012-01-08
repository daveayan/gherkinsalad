Feature: So that I can find a laptop at the lowest price possible I want to check the price on amazon.com
	
	#EXECUTION PLAN INSTRUCTION AT TOP

	Scenario: Setup
		Given 	User is a Developer
			And Data Management driver is daveayan.gherkinsalad.driver.AmazonDataDriver
			And Data Management file is amazon-shopping-data.csv

	Scenario: Search for MacBook on amazon.com
		Given 	User entered data Item to Shop in text box Search Box
		When 	User clicks Go
		Then 	Search Results has text ~MacBook ; Apple ; Laptop~
			And Search Results does not have text ~gherkin ; salad~
			And Page does not have text ~gherkin ; salad~
			And Page has enabled elements ~Search Box ; Go~
			
	Scenario: Search for MacBook on amazon.com, electronics department
		Given 	User selected option text Electronics in drop down Department
			And User entered text MacBook in text box Search Box
		When 	User clicks Go
		Then 	Search Results has text ~MacBook ; Apple ; Laptop~
			And Search Results does not have text ~gherkin ; salad~
			And Page does not have text ~gherkin ; salad~
		
	#EXECUTION PLAN INSTRUCTION AT BOTTOM