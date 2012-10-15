package daveayan.gherkinsalad.components.html;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.TextEnterable;

public class TextField extends BaseBrowserElement implements TextEnterable {
	
	public static TextField find(By locator) {
		TextField tb = new TextField();
		tb.found(locator);
		return tb;
	}
	
	public TextField name(String name) {
		super.name(name);
		return this;
	}
	
	public String getText() {
		if(isNotDisplayed()) {
			error("Element '" + this + "' is not displayed. Cannot getText on this element");
			return "NOT_DISPLAYED";
		}
		Element element = root_element();
		if(element.is_null()) {
			error("Element '" + this + "' is not displayed. Cannot getText on this element");
			return "IS_NULL";
		}
		return element.getAttribute("value");
	}
	
	public boolean isEnabled() {
		Element element = root_element();
		return element.isEnabled();
	}

	public void enter_text_if_enabled(String text) {
		Element element = root_element();
		if(this.isEnabled()) {
			wait_between_steps();
			element.clear();
			element.sendKeys(text);
			action("Entered text '" + text + "' in " + this);
		} else {
			action("Did not enter text '" + text + "' in disabled text field '" + this + "'");
		}
	}

	public void append_text_if_enabled(String text) {
		Element element = root_element();
		if(this.isEnabled()) {
			wait_between_steps();
			String current_text = element.getAttribute("value");
			element.clear();
			element.sendKeys(text + current_text);
			action("Appended text '" + text + "' in " + this);
		} else {
			action("Did not append text '" + text + "' in disabled text field '" + this + "'");
		}
	}

	public void click_if_enabled() {
		Element element = root_element();
		if(this.isEnabled()) {
			element.click();
			action("Clicked in textfield " + this);
		} else {
			action("Did not click disabled textfield " + this);
		}
	}
	
	public void click_if_exists() {
		click_if_enabled();
	}

	public void click_if_exists_and_enabled() {
		// TODO Auto-generated method stub
		
	}
}