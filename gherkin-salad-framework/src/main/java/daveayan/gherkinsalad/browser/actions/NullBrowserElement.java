package daveayan.gherkinsalad.browser.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.PageElementKey;

public class NullBrowserElement implements Clickable, Selectable, TextEnterable, BrowserElement {
	private PageElementKey pek;
	
	public boolean exists() {
		throw new AssertionError("operation exists not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean does_not_exist() {
		throw new AssertionError("operation does_not_exist not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean isEnabled() {
		throw new AssertionError("operation isEnabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean isDisabled() {
		throw new AssertionError("operation isDisabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void enter_text_if_enabled(String text) {
		throw new AssertionError("operation enter_text_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void append_text_if_enabled(String text) {
		throw new AssertionError("operation append_text_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void select_if_enabled(String text) {
		throw new AssertionError("operation select_if_enabled not allowed on a NullBrowserElement '" + this + "' and text '" + text + "'");
	}
	public void click_if_enabled() {
		throw new AssertionError("operation click_if_enabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void has_text(String[] expected_texts) {
		throw new AssertionError("operation has_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public void does_not_have_text(String[] expected_texts) {
		throw new AssertionError("operation does_not_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	
	public void element_locators_are(List<By> element_locators) {
	}
	public void driver_is(WebDriver driver) {
	}
	public void page_element_key_is(PageElementKey pek) {
		this.pek = pek;
	}
	public String toString() {
		return this.getClass().getName() + " : " + pek.toString();
	}
}