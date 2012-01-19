package daveayan.gherkinsalad;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.gherkinsalad.browser.actions.BrowserElement;

public class MockBrowserElement implements BrowserElement {
	public WebDriver driver;
	public List<By> element_locators;
	public PageElementKey pek;
	public boolean isNull() {
		return false;
	}
	def element_locators_are(List<By> element_locators) {
		this.element_locators = element_locators;
	}
	def driver_is(WebDriver driver) {
		this.driver = driver;
	}
	def isEnabled() {
		return false;
	}
	def isDisabled() {
		return false;
	}
	def page_element_key_is(PageElementKey pek) {
		this.pek = pek;
	}
	def has_text(String[] expected_texts) {
	}
	def does_not_have_text(String[] expected_texts) {
	}
	def exists() {
		return false;
	}
	def does_not_exist() {
		return false;
	}
	def exists_immediate() {
		return false;
	}
	def is_null() {
		return false;
	}
	def is_not_null() {
		return !is_null();
	}
}