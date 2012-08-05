package gherkinsalad.sample;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.Clickable;
import daveayan.gherkinsalad.components.html.BaseBrowserElement;
import daveayan.gherkinsalad.components.html.Link;

public class GherkinSaladHomePage extends BaseBrowserElement {
	Clickable view_on_github = Link.find(By.linkText("View On GitHub"));
	
	public GherkinSaladHomePage() {
		super.found(By.tagName("body"));
	}
	
	public void view_gherkinsalad_on_github() {
		view_on_github.click_if_enabled();
		wait_for_seconds(5);
	}
}