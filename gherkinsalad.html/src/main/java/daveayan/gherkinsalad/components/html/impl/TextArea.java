package daveayan.gherkinsalad.components.html.impl;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.core.Component;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.html.TextEnterable;

public class TextArea extends Component implements TextEnterable {
	
	public static TextArea find(By locator) {
		TextArea ta = new TextArea();
		ta.found(locator);
		return ta;
	}
	
	public TextArea name(String name) {
		super.name(name);
		return this;
	}

	public void click_if_enabled() {
		Element element = root_element();
		if(this.isEnabled()) {
			element.click();
		} else {
			action("Did not click '" + this + "' since it is not enabled");
		}
	}
	
	public void click_if_exists() {
		Element element = root_element();
		if(this.isDisplayed()) {
			element.click();
		} else {
			action("Did not click '" + this + "' since it is not displayed");
		}
	}
	
	public void click_if_exists_and_enabled() {
		Element element = root_element();
		if(this.isEnabled() && this.isDisplayed()) {
			element.click();
		} else {
			action("Did not click '" + this + "' since it is not enabled and not displayed");
		}
	}

	public void enter_text_if_enabled(String text) {
		Element element = root_element();
		if(this.isEnabled()) {
			wait_between_steps();
			element.clear();
			element.sendKeys(text);
			action("Entered text '" + text + "' in " + this);
		} else {
			action("Did not enter text in element '" + this + "' since it is not enabled");
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
			action("Did not append text in element '" + this + "' since it is not enabled");
		}
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
}