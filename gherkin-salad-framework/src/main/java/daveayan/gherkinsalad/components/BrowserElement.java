package daveayan.gherkinsalad.components;

import org.openqa.selenium.By;

import daveayan.lang.Nullable;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, Nullable {
	public BrowserElement found(By element_locator);
	
	public String getText();
	
	public boolean isDisplayed();
	public boolean isNotDisplayed();
	public boolean has_text(String... expected_texts);
	
	public void should_be_enabled();
	public void should_be_disabled();
	
	public void should_be_displayed();
	public void should_not_be_displayed();
	
	public void should_have_text(String... expected_texts);
	public void should_not_have_text(String... expected_texts);
}