package daveayan;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.steps.BaseStep;

public class ShoppingSteps extends BaseStep {
	@Given("^User launched the (.*) browser with page structure file (.*)$")
	public void launch_browser_with_page_structure(String browser_name, String page_structure_file_name) {
		launch_browser_with(browser_name, page_structure_file_name);
	}
	
	@Given("^User visited website (.*)$")
	public void goto_website(String url) {
		goto_url(url);
	}
	
	@Given("^User entered (.*) in (.*)$")
	public void enter_macbook_in_search_box(String text, String element_name) throws InterruptedException {
		wait_for_page_to_load();
		enter(text, on_element_with_key("", "", element_name));
	}
	
	@When("^User clicks (.*)$")
	public void click_go(String element_name) throws InterruptedException {
		wait_for_page_to_load();
		click(on_element_with_key("", "", element_name));
	}
	
	@Then("^User closes the browser window$")
	public void close_browser_window() {
		wait_for_page_to_load();
		close_browser();
	}
}