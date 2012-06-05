package daveayan.gherkinsalad.components;

import java.util.List;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.lang.Nullable;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, Nullable {
	public BrowserElement element_locators_are(List<By> element_locators);
	public BrowserElement element_locators_are(By... element_locators);
	public BrowserElement browser_is(Browser browser);
	
	public boolean has_text(String... expected_texts);
	
	public void should_have_text(String... expected_texts);
	public void should_not_have_text(String... expected_texts);
	public void should_be_enabled();
	public void should_be_disabled();
	
	public boolean exists();
	public boolean does_not_exist();
	
	public boolean exists_immediate();
}