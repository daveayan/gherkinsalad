package daveayan.gherkinsalad.components;

import java.util.List;

import org.openqa.selenium.By;

import daveayan.lang.Nullable;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, Nullable {
	public BrowserElement found(List<By> element_locators);
	public BrowserElement found(By... element_locators);
	
	public boolean has_text(String... expected_texts);
	
	public void should_have_text(String... expected_texts);
	public void should_not_have_text(String... expected_texts);
	public void should_be_enabled();
	public void should_be_disabled();
	public void should_have_hover_text(String text);
	
	public boolean exists();
	public boolean does_not_exist();
	
	public boolean exists_immediate();
}