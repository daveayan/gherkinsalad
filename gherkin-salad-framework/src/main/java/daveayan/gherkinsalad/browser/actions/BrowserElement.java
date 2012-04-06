package daveayan.gherkinsalad.browser.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.browser.PageElementKey;
import daveayan.lang.Nullable;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, Nullable {
	public void page_element_key_is(PageElementKey pek);
	public void element_locators_are(List<By> element_locators);
	public void driver_is(WebDriver driver);
	
	public boolean has_text(String... expected_texts);
	
	public void should_have_text(String... expected_texts);
	public void should_not_have_text(String... expected_texts);
	
	public boolean exists();
	public boolean does_not_exist();
	
	public boolean exists_immediate();
}