Feature: So that I can find a laptop at the lowest price possible I want to check the price on amazon.com
	
	Scenario: Prepare browser and test data
		Given User launched the FireFox browser with page structure file ./src/test/resources/shop.amazon.feature.page.structure.csv
		And User visited website http://www.amazon.com

	Scenario: Search for MacBook on amazon.com
		Given 	User entered MacBook in Search Box
		When 	User clicks Go
		Then 	User should see the text MacBook in Search Results
		
	Scenario: Close Browser
		Then User closes the browser window