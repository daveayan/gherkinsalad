Feature: So that I can find a laptop at the lowest price possible I want to check the price on amazon.com
	
	#EXECUTION PLAN INSTRUCTION AT TOP

	Scenario: Search for MacBook on amazon.com
		Given 	User entered MacBook in Search Box
		When 	User clicks Go
		Then 	User should see the text MacBook in Search Results
		
	#EXECUTION PLAN INSTRUCTION AT BOTTOM