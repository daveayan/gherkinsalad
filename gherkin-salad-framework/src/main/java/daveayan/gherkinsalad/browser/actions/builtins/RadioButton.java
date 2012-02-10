package daveayan.gherkinsalad.browser.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.Clickable;

public class RadioButton extends BaseBrowserElement implements Clickable {
	public boolean isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void click_if_enabled() {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.click();
		}
	}
	public void click_if_exists() {
		click_if_enabled();
	}
}