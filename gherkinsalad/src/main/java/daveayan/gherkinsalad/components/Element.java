package daveayan.gherkinsalad.components;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import daveayan.lang.Nullable;

public class Element implements Nullable {
	private WebElement _webElement;
	
	public WebElement _nativeWebElement() {
		return _webElement;
	}
	
	public static Element newInstance(WebElement we) {
		Element e = new Element();
		e._webElement = we;
		return e;
	}
	
	public void clear() {
		_webElement.clear();
	}

	public void click() {
		_webElement.click();
	}

	public Element findElement(By arg0) {
		return Element.newInstance(_nativeWebElement().findElement(arg0));
	}
	
	public List<Element> findElements(By arg0) {
		List<WebElement> _webElements = _nativeWebElement().findElements(arg0);
		List<Element> elements = new ArrayList<Element>();
		if(_webElements != null) {
			for(WebElement _we: _webElements) {
				elements.add(Element.newInstance(_we));
			}
		}
		return elements;
	}

	public String getAttribute(String arg0) {
		return _webElement.getAttribute(arg0);
	}

	public String getCssValue(String arg0) {
		return _webElement.getCssValue(arg0);
	}

	public Point getLocation() {
		return _webElement.getLocation();
	}

	public Dimension getSize() {
		return _webElement.getSize();
	}

	public String getTagName() {
		return _webElement.getTagName();
	}

	public boolean isSelected() {
		return _webElement.isSelected();
	}

	public void sendKeys(CharSequence... arg0) {
		_webElement.sendKeys(arg0);
	}

	public void submit() {
		_webElement.submit();
	}

	public String getText() {
		return _webElement.getText();
	}

	public boolean isDisplayed() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean is_null() {
		return false;
	}

	public boolean is_not_null() {
		return ! is_null();
	}
}