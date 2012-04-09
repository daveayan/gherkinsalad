package daveayan.gherkinsalad;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.BrowserElement;

public class MockBrowserElement implements BrowserElement {
	public WebDriver driver;
	public List<By> element_locators;
	public PageElementKey pek;
	public boolean isNull() {
		return false;
	}
	public void element_locators_are(List<By> element_locators) {
		this.element_locators = element_locators;
	}
	public void element_locators_are(By... element_locators) {
		this.element_locators = Arrays.asList(element_locators);
	}
	public void driver_is(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isEnabled() {
		return false;
	}
	public boolean isDisabled() {
		return false;
	}
	public void page_element_key_is(PageElementKey pek) {
		this.pek = pek;
	}
	public void should_have_text(String... expected_texts) {
	}
	public void should_not_have_text(String... expected_texts) {
	}
	public boolean exists() {
		return false;
	}
	public boolean does_not_exist() {
		return false;
	}
	public boolean exists_immediate() {
		return false;
	}
	public boolean is_null() {
		return false;
	}
	public boolean is_not_null() {
		return ! is_null();
	}
	public boolean has_text(String... expected_texts) {
		return true;
	}
	public void browser_is(Browser browser) {
	}
	public void should_be_enabled() {
	}
	public void should_be_disabled() {
	}
}