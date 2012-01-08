package daveayan.gherkinsalad.steps;

import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;

public class CommonSteps extends BaseStep {
	@Given("^User launched the ([^\\.]*) browser$")
	public void launch_browser_with_page_structure(String browser_name) {
		launch_browser(browser_name);
	}
	@Given("^User uses the data source file ([^\\.]*) with driver ([^\\.]*)$")
	public void use_data_source(String data_source_file_name, String data_source_driver) {
		feature_data_source = load_data_source(data_source_file_name, data_source_driver);
	}
	@Given("^User visited website (.*)$")
	public void goto_website(String url) {
		goto_url(url);
	}
	@Given("^Data Management driver is (.*)$")
	public void data_management_driver(String dmd) {
		data_management_driver = dmd;
	}
	@Given("^Data Management file is (.*)$")
	public void data_management_file(String dmf) {
		data_management_file = dmf;
	}
	@Given("^Data is loaded$")
	public void load_data_management_module() {
		feature_data_source = load_data_source(data_management_file, data_management_driver);
	}
	@Given("^User is a ([^\\.]*)$")
	public void assume_role(String role_name) {
		current_role = role_name;
	}
	@Given("^User is testing feature ([^\\.]*)$")
	public void testing_feature(String feature_name) {
		feature_under_test = feature_name;
	}
	@Then("^User closes the browser window$")
	public void close_browser_window() {
		wait_for_page_to_load();
		close_browser();
	}
}