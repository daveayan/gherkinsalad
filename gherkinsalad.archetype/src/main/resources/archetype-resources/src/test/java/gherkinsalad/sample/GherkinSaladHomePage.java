#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${artifactId};

import org.openqa.selenium.By;

import ${package}.gherkinsalad.components.Clickable;
import ${package}.gherkinsalad.components.html.BaseBrowserElement;
import ${package}.gherkinsalad.components.html.Link;

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