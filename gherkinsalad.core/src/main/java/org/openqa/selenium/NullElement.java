package org.openqa.selenium;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.Elements;

public class NullElement extends Element {
	private By selector;

	public boolean is_null() {
		return true;
	}
	
	public String toString() {
		if(selector != null)
			return selector.toString();
		else 
			return "NULL Selector";
	}
	
	public static NullElement newInstance(By selector) {
		NullElement nwe = new NullElement();
		nwe.selector = selector;
		return nwe;
	}
	public void clear() {
		error("Operation clear() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
	}

	public void click() {
		error("Operation click() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
	}

	public Element findElement(By arg0) {
		error("Operation findElement(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public Elements findElements(By arg0) {
		error("Operation findElements(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public String getAttribute(String arg0) {
		error("Operation getAttribute(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public String getCssValue(String arg0) {
		error("Operation getCssValue(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public Point getLocation() {
		error("Operation getLocation() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public Dimension getSize() {
		error("Operation getSize() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public String getTagName() {
		error("Operation getTagName() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public String getText() {
		error("Operation getText() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return null;
	}

	public boolean isDisplayed() {
		error("Operation isDisplayed() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return false;
	}

	public boolean isEnabled() {
		error("Operation isEnabled() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return false;
	}

	public boolean isSelected() {
		error("Operation isSelected() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
		return false;
	}

	public void sendKeys(CharSequence... arg0) {
		error("Operation sendKeys(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
	}

	public void submit() {
		error("Operation submit() not allowed on a null web element with selector '" + selector + "'");
		takeScreenshot();
	}
}