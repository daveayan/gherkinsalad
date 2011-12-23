package daveayan.gherkinsalad.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import daveayan.gherkinsalad.PageElementKey;
import daveayan.lang.CanBeNull;

public interface BrowserElement extends CanBeEnabled, CanBeDisabled, CanBeNull {
	public void page_element_key_is(PageElementKey pek);
	public void element_locators_are(List<By> element_locators);
	public void driver_is(WebDriver driver);
}