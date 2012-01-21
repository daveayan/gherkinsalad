package daveayan.gherkinsalad.browser;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class NullWebElement implements WebElement {
	private By selector;
	private PageElementKey pek;
	
	public static NullWebElement newInstance(By selector, PageElementKey pek) {
		NullWebElement nwe = new NullWebElement();
		nwe.selector = selector;
		nwe.pek = pek;
		return nwe;
	}

	public void clear() {
		throw new AssertionError("Operation clear() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public void click() {
		throw new AssertionError("Operation click() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public WebElement findElement(By arg0) {
		throw new AssertionError("Operation findElement(" + arg0 + ") not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public List<WebElement> findElements(By arg0) {
		throw new AssertionError("Operation findElements(" + arg0 + ") not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public String getAttribute(String arg0) {
		throw new AssertionError("Operation getAttribute(" + arg0 + ") not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public String getCssValue(String arg0) {
		throw new AssertionError("Operation getCssValue(" + arg0 + ") not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public Point getLocation() {
		throw new AssertionError("Operation getLocation() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public Dimension getSize() {
		throw new AssertionError("Operation getSize() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public String getTagName() {
		throw new AssertionError("Operation getTagName() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public String getText() {
		throw new AssertionError("Operation getText() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public boolean isDisplayed() {
		throw new AssertionError("Operation isDisplayed() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public boolean isEnabled() {
		throw new AssertionError("Operation isEnabled() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public boolean isSelected() {
		throw new AssertionError("Operation isSelected() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public void sendKeys(CharSequence... arg0) {
		throw new AssertionError("Operation sendKeys(" + arg0 + ") not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}

	public void submit() {
		throw new AssertionError("Operation submit() not allowed on a null web element with selector '" + selector + "', page element key '" + pek + "'");
	}
}