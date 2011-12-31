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
	public void element_locators_are(List<By> element_locators) {
		this.element_locators = element_locators;
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
}