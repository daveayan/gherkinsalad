package daveayan;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.steps.BaseStep;

public class ShoppingSteps extends BaseStep {
	@Given("^User launched the browser window$")
	public void launch_browser_window() {
		launch_browser_with("firefox", "./src/test/resources/shop.amazon.feature.page.structure.csv");
	}
	
	@Given("^User visited website$")
	public void goto_website() {
		goto_url("http://www.amazon.com");
	}
	
	@Given("^User entered MacBook in Search Box$")
	public void enter_macbook_in_search_box() throws InterruptedException {
		wait_for_page_to_load();
		enter_text("MacBook", on_element_with_key("", "", "Search Box"));
	}
	
	@When("^User clicks Go$")
	public void click_go() throws InterruptedException {
		wait_for_page_to_load();
		click(on_element_with_key("", "", "Go"));
	}
	
	@Then("^User closes the browser window$")
	public void close_browser() {
		wait_for_page_to_load();
		super.close_browser();
	}
}