#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package cucumber.stepdefs;

import org.openqa.selenium.By;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;
import daveayan.gherkinsalad.components.html.Link;

public class SampleStep extends BaseBrowserElement {
	Clickable view_on_github = Link.find(By.linkText("View On GitHub"));
	
	@Given("^Some context is setup${symbol_dollar}")
	public void Some_context_is_setup() {
	    launch_browser("Firefox");
	    goto_url("http://daveayan.github.com/gherkinsalad/");
	}

	@When("^Some action happens${symbol_dollar}")
	public void Some_action_happens() {
		view_on_github.click_if_enabled();
		wait_for_seconds(5);
	}

	@Then("^Verify something${symbol_dollar}")
	public void Verify_something() {
	    close_browser();
	}
}