#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package cucumber.stepdefs;

import ${artifactId}.GherkinSaladHomePage;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import ${package}.gherkinsalad.components.html.BaseBrowserElement;

public class SampleStep extends BaseBrowserElement {
	@Given("^Some context is setup${symbol_dollar}")
	public void Some_context_is_setup() {
	    launch_browser("Firefox");
	    goto_url("http://${package}.github.com/gherkinsalad/");
	}

	@When("^Some action happens${symbol_dollar}")
	public void Some_action_happens() {
	    GherkinSaladHomePage homepage = new GherkinSaladHomePage();
	    homepage.view_gherkinsalad_on_github();
	}

	@Then("^Verify something${symbol_dollar}")
	public void Verify_something() {
	    close_browser();
	}
}