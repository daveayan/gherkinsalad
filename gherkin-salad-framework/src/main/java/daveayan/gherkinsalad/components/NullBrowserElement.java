package daveayan.gherkinsalad.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.Browser;

public class NullBrowserElement implements Clickable, Selectable, TextEnterable, BrowserElement {
	public void has_options(String[] options) {
		throw new AssertionError("operation has_options not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean exists() {
		return false;
	}
	public boolean does_not_exist() {
		return !exists();
	}
	public boolean is_null() {
		return true;
	}
	public boolean is_not_null() {
		return ! is_null();
	}
	public boolean exists_immediate() {
		throw new AssertionError("operation exists_immediate not allowed on a NullBrowserElement '" + this + "'");
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
	public void click_if_exists() {
	}
	public void should_have_text(String... expected_texts) {
		throw new AssertionError("operation should_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_not_have_text(String... expected_texts) {
		throw new AssertionError("operation should_not_have_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public boolean has_text(String... expected_texts) {
		throw new AssertionError("operation has_text not allowed on a NullBrowserElement '" + this + "'");
	}
	public BrowserElement element_locators_are(List<By> element_locators) {
		throw new AssertionError("operation element_locators_are not allowed on a NullBrowserElement '" + this + "'");
	}
	public BrowserElement element_locators_are(By... element_locators) {
		throw new AssertionError("operation element_locators_are By... not allowed on a NullBrowserElement '" + this + "'");
	}
	public BrowserElement browser_is(Browser browser) {
		throw new AssertionError("operation browser_is not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_be_enabled() {
		throw new AssertionError("operation should_be_enabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void should_be_disabled() {
		throw new AssertionError("operation should_be_disabled not allowed on a NullBrowserElement '" + this + "'");
	}
	public void driver_is(WebDriver driver) {
	}
	public String toString() {
		return this.getClass().getName();
	}
	public void click_if_exists_and_enabled() {
	}
}