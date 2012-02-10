package daveayan.gherkinsalad.actions;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.Clickable;
import daveayan.gherkinsalad.browser.actions.builtins.BaseBrowserElement;

public class HtmlButtonWithNoId extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		WebElement element = element();
		return element.isEnabled();
	}

	public void click_if_enabled() {
		WebElement element = element();
		if(this.isEnabled()) {
			element.click();
		}
	}
	
	private WebElement element() {
		WebElement form_element = fetch_element(0);
		return form_element.findElements(element_locators.get(1)).get(1);
	}
	
	public void click_if_exists() {
		click_if_enabled();
	}
}