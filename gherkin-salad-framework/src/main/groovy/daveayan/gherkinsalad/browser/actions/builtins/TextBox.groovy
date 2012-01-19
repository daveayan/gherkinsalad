package daveayan.gherkinsalad.browser.actions.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.browser.actions.TextEnterable;

public class TextBox extends BaseBrowserElement implements TextEnterable {
	def isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void enter_text_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		}
	}

	public void append_text_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.sendKeys(text);
		}
	}
}