package daveayan.gherkinsalad.components.builtins;

import org.openqa.selenium.WebElement;

import daveayan.gherkinsalad.components.Selectable;

public class DropDown extends BaseBrowserElement implements Selectable {
	public boolean isEnabled() {
		WebElement element = fetch_element(0);
		return element.isEnabled();
	}

	public void select_if_enabled(String text) {
		WebElement element = fetch_element(0);
		if(this.isEnabled()) {
			element.sendKeys(text);
		}
	}

	public void has_options(String[] options) {
		throw new AssertionError("DropDown::has_options(" + options + ") is not implemented yet");
	}
}