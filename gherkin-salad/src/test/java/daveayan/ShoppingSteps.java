package daveayan;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.steps.BaseStep;

public class ShoppingSteps extends BaseStep {
	@Given("^User launched the FireFox browser$")
	public void launch_browser() {
		String browser_name = "FireFox";
		launch_browser(browser_name);
	}
	
	@Given("^User launched the FireFox browser with page structure file ./src/test/resources/shop.amazon.feature.page.structure.csv$")
	public void launch_browser_with_page_structure() {
		String browser_name = "FireFox";
		String page_structure_file = "./src/test/resources/shop.amazon.feature.page.structure.csv";
		launch_browser_with(browser_name, page_structure_file);
	}
	
	@Given("^User visited website http://www.amazon.com$")
	public void goto_website() {
		String url = "http://www.amazon.com";
		goto_url(url);
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