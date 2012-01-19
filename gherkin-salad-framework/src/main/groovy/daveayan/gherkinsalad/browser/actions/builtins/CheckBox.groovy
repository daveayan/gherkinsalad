package daveayan.gherkinsalad.browser.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.Clickable;

public class CheckBox extends BaseBrowserElement implements Clickable {
	def isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void click_if_enabled() {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.click();
		}
	}
}