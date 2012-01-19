package daveayan.gherkinsalad.browser.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.lang.IsNullable;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, IsNullable {
	def page_element_key_is(PageElementKey pek);
	def element_locators_are(List<By> element_locators);
	def driver_is(WebDriver driver);
	
	def has_text(String[] expected_texts);
	def does_not_have_text(String[] expected_texts);
	
	def exists();
	def does_not_exist();
	
	def exists_immediate();
}