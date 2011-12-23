package daveayan;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import daveayan.gherkinsalad.Browser;
import daveayan.gherkinsalad.actions.Clickable;
import daveayan.gherkinsalad.actions.TextEnterable;

public class ShoppingSteps {
	private static Browser browser;
	
	@Given("^User launched the browser window$")
	public void launch_browser_window() {
		browser = Browser.instance_with_name_and_page_structure_path("firefox", "./src/test/resources/shop.amazon.feature.page.structure.csv");
		browser.launch();
	}
	
	@Given("^User visited website$")
	public void goto_website() {
		browser.goto_url("http://www.amazon.com");
	}
	
	@Given("^User entered MacBook in Search Box$")
	public void enter_macbook_in_search_box() throws InterruptedException {
		wait_for_page_to_load();
		TextEnterable text_enterable_element = (TextEnterable) browser.locate_element_object_for("", "", "Search Box");
		text_enterable_element.enter_text_if_enabled("MacBook");
	}
	
	@When("^User clicks Go$")
	public void click_go() throws InterruptedException {
		wait_for_page_to_load();
		Clickable go_button = (Clickable) browser.locate_element_object_for("", "", "Go");
		go_button.click_if_enabled();
	}
	
	@Then("^User closes the browser window$")
	public void close_browser() throws InterruptedException {
		wait_for_page_to_load();
		browser.close();
	}
	
	public void wait_for_page_to_load() throws InterruptedException {
		Thread.sleep(10 * 1000);
	}
}