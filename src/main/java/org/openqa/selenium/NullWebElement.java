package org.openqa.selenium;

import java.util.List;

public class NullWebElement implements WebElement {
	private By selector;

	public static NullWebElement newInstance(By selector) {
		NullWebElement nwe = new NullWebElement();
		nwe.selector = selector;
		return nwe;
	}

	public void clear() {
		throw new AssertionError("Operation clear() not allowed on a null web element with selector '" + selector + "'");
	}

	public void click() {
		throw new AssertionError("Operation click() not allowed on a null web element with selector '" + selector + "'");
	}

	public WebElement findElement(By arg0) {
		throw new AssertionError("Operation findElement(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public List<WebElement> findElements(By arg0) {
		throw new AssertionError("Operation findElements(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public String getAttribute(String arg0) {
		throw new AssertionError("Operation getAttribute(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public String getCssValue(String arg0) {
		throw new AssertionError("Operation getCssValue(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public Point getLocation() {
		throw new AssertionError("Operation getLocation() not allowed on a null web element with selector '" + selector + "'");
	}

	public Dimension getSize() {
		throw new AssertionError("Operation getSize() not allowed on a null web element with selector '" + selector + "'");
	}

	public String getTagName() {
		throw new AssertionError("Operation getTagName() not allowed on a null web element with selector '" + selector + "'");
	}

	public String getText() {
		throw new AssertionError("Operation getText() not allowed on a null web element with selector '" + selector + "'");
	}

	public boolean isDisplayed() {
		throw new AssertionError("Operation isDisplayed() not allowed on a null web element with selector '" + selector + "'");
	}

	public boolean isEnabled() {
		throw new AssertionError("Operation isEnabled() not allowed on a null web element with selector '" + selector + "'");
	}

	public boolean isSelected() {
		throw new AssertionError("Operation isSelected() not allowed on a null web element with selector '" + selector + "'");
	}

	public void sendKeys(CharSequence... arg0) {
		throw new AssertionError("Operation sendKeys(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public void submit() {
		throw new AssertionError("Operation submit() not allowed on a null web element with selector '" + selector + "'");
	}
}