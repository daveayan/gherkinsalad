package org.openqa.selenium;

import java.util.List;
import java.util.Set;

public class NullWebDriver implements WebDriver {
	public void close() {
		throw new AssertionError("Operation close() not allowed on NullWebDriver");
	}
	public WebElement findElement(By arg0) {
		throw new AssertionError("Operation findElement(By arg0) not allowed on NullWebDriver");
	}
	public List<WebElement> findElements(By arg0) {
		throw new AssertionError("Operation findElements(By arg0) not allowed on NullWebDriver");
	}
	public void get(String arg0) {
		throw new AssertionError("Operation get(String arg0) not allowed on NullWebDriver");
	}
	public String getCurrentUrl() {
		throw new AssertionError("Operation getCurrentUrl() not allowed on NullWebDriver");
	}
	public String getPageSource() {
		throw new AssertionError("Operation getPageSource() not allowed on NullWebDriver");
	}
	public String getTitle() {
		throw new AssertionError("Operation getTitle() not allowed on NullWebDriver");
	}
	public String getWindowHandle() {
		throw new AssertionError("Operation getWindowHandle() not allowed on NullWebDriver");
	}
	public Set<String> getWindowHandles() {
		throw new AssertionError("Operation getWindowHandles() not allowed on NullWebDriver");
	}
	public Options manage() {
		throw new AssertionError("Operation manage() not allowed on NullWebDriver");
	}
	public Navigation navigate() {
		throw new AssertionError("Operation navigate() not allowed on NullWebDriver");
	}
	public void quit() {
		throw new AssertionError("Operation quit() not allowed on NullWebDriver");
	}
	public TargetLocator switchTo() {
		throw new AssertionError("Operation switchTo() not allowed on NullWebDriver");
	}
}