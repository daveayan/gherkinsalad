package daveayan.gherkinsalad.browser.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.Selectable;

public class DropDown extends BaseBrowserElement implements Selectable {
	def isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void select_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.sendKeys(text);
		}
	}
}