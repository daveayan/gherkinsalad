package daveayan.gherkinsalad.browser.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.PageElementKey;

public class NullBrowserElement implements Clickable, Selectable, TextEnterable, BrowserElement {
	private PageElementKey pek;
	
	def is_null() {
		return true;
	}
	def is_not_null() {
		return !is_null();
	}
	def exists() {
		throw new AssertionError("operation exists not allowed on a NullBrowserElement '" + this + "'");
	}
	def does_not_exist() {
		throw new AssertionError("operation does_not_exist not allowed on a NullBrowserElement '" + this + "'");
	}
	def exists_immediate() {
		throw new AssertionError("operation exists_immediate not allowed on a NullBrowserElement '" + this + "'");
	}
	def isEnabled() {
		throw new AssertionError("operation isEnabled not allowed on a NullBrowserElement '" + this + "'");
	}
	def isDisabled() {
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
	def has_text(String[] expected_texts) {
		throw new AssertionError("operation has_text not allowed on a NullBrowserElement '" + this + "'");
	}
	def does_not_have_text(String[] expected_texts) {
		throw new AssertionError("operation does_not_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	
	def element_locators_are(List<By> element_locators) {
	}
	def driver_is(WebDriver driver) {
	}
	def page_element_key_is(PageElementKey pek) {
		this.pek = pek;
	}
	public String toString() {
		return this.getClass().getName() + " : " + pek.toString();
	}
}