package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;

public class CommonSteps extends BaseStep {
	@Given("^(?:User|user|I|You|you) launched the ([^\\.]*) browser with page structure file (.*)$")
	public void launch_browser_with_page_structure(String browser_name, String page_structure_file_name) {
		super.launch_browser_with(browser_name, page_structure_file_name);
	}
	@Given("^(?:User|user|I|You|you) uses the data source file (.*) with driver (.*)$")
	public void use_data_source(String data_source_file_name, String data_source_driver) {
		feature_data_source = load_data_source(data_source_file_name, data_source_driver);
	}
	@Given("^(?:User|user|I|You|you) visited website (.*)$")
	public void goto_website(String url) {
		goto_url(url);
	}
	@Given("^(?:User|user|I|You|you) is a ([^\\.]*)$")
	public void assume_role(String role_name) {
		current_role = role_name;
	}
	@Given("^(?:User|user|I|You|you) is testing feature ([^\\.]*)$")
	public void testing_feature(String feature_name) {
		feature_under_test = feature_name;
	}
	@Then("^(?:User|user|I|You|you) closes the browser window$")
	public void close_browser_window() {
		close_browser();
	}
	@Given("^(?:User|user|I|You|you) waited for ([^\\.]*) seconds$")
	@Then("^(?:User|user|I|You|you) waits for ([^\\.]*) seconds$")
	public void wait(String seconds) {
		int s = Integer.parseInt(seconds);
		wait_for_seconds(s);
	}
}