package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;

public class CommonSteps extends BaseStep {
	@Given("^(?:User|user|I|You|you) launched the ([^\\.]*) browser with page structure file (.*)")
	def launch_browser_with_page_structure(browser_name, page_structure_file_name) {
		super.launch_browser_with(browser_name, page_structure_file_name);
	}
	@Given("^(?:User|user|I|You|you) uses the data source file (.*) with driver (.*)")
	def use_data_source(data_source_file_name, data_source_driver) {
		feature_data_source = load_data_source(data_source_file_name, data_source_driver);
	}
	@Given("^(?:User|user|I|You|you) visited website (.*)")
	def goto_website(url) {
		goto_url(url);
	}
	@Given("^(?:User|user|I|You|you) is a ([^\\.]*)")
	def assume_role(role_name) {
		current_role = role_name;
	}
	@Given("^(?:User|user|I|You|you) is testing feature ([^\\.]*)")
	def testing_feature(feature_name) {
		feature_under_test = feature_name;
	}
	@Then("^(?:User|user|I|You|you) closes the browser window")
	def close_browser_window() {
		close_browser();
	}
}