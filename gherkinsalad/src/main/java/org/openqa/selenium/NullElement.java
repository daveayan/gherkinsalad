package org.openqa.selenium;

import daveayan.gherkinsalad.components.Element;
import daveayan.gherkinsalad.components.Elements;
import daveayan.gherkinsalad.report.Report;

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
		Report.error("Operation clear() not allowed on a null web element with selector '" + selector + "'");
	}

	public void click() {
		Report.error("Operation click() not allowed on a null web element with selector '" + selector + "'");
	}

	public Element findElement(By arg0) {
		Report.error("Operation findElement(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public Elements findElements(By arg0) {
		Report.error("Operation findElements(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public String getAttribute(String arg0) {
		Report.error("Operation getAttribute(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public String getCssValue(String arg0) {
		Report.error("Operation getCssValue(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public Point getLocation() {
		Report.error("Operation getLocation() not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public Dimension getSize() {
		Report.error("Operation getSize() not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public String getTagName() {
		Report.error("Operation getTagName() not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public String getText() {
		Report.error("Operation getText() not allowed on a null web element with selector '" + selector + "'");
		return null;
	}

	public boolean isDisplayed() {
		Report.error("Operation isDisplayed() not allowed on a null web element with selector '" + selector + "'");
		return false;
	}

	public boolean isEnabled() {
		Report.error("Operation isEnabled() not allowed on a null web element with selector '" + selector + "'");
		return false;
	}

	public boolean isSelected() {
		Report.error("Operation isSelected() not allowed on a null web element with selector '" + selector + "'");
		return false;
	}

	public void sendKeys(CharSequence... arg0) {
		Report.error("Operation sendKeys(" + arg0 + ") not allowed on a null web element with selector '" + selector + "'");
	}

	public void submit() {
		Report.error("Operation submit() not allowed on a null web element with selector '" + selector + "'");
	}
}