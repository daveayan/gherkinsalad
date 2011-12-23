Feature: Shop for MacBook at Amazon
	Scenario: So that I can find a laptop at the lowest price possible I want to check the price on amazon.com
		Given 	User launched the browser window
			And User visited website
			And User entered MacBook in Search Box
		When 	User clicks Go
		Then 	User should see the text MacBook in Search Results
			And User closes the browser window