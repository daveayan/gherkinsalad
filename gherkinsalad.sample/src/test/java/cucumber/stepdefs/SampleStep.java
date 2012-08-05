package cucumber.stepdefs;

import gherkinsalad.sample.GherkinSaladHomePage;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;

public class SampleStep extends BaseBrowserElement {
	@Given("^Some context is setup$")
	public void Some_context_is_setup() {
	    launch_browser("Firefox");
	    goto_url("http://daveayan.github.com/gherkinsalad/");
	}

	@When("^Some action happens$")
	public void Some_action_happens() {
	    GherkinSaladHomePage homepage = new GherkinSaladHomePage();
	    homepage.view_gherkinsalad_on_github();
	}

	@Then("^Verify something$")
	public void Verify_something() {
	    close_browser();
	}
}