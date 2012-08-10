package daveayan.gherkinsalad.components;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NullElement;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import daveayan.gherkinsalad.Config;
import daveayan.lang.Nullable;

public class Element implements Nullable {
	private WebElement _webElement;
	
	private WebElement _nativeWebElement() {
		return _webElement;
	}
	
	public static Element newInstance(WebElement we) {
		Element e = new Element();
		e._webElement = we;
		return e;
	}
	
	public boolean hasAllTexts(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(! this.has(e)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean hasAnyText(String... expectedTexts) {
		for(String e: expectedTexts) {
			if(this.has(e)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean has(String text) {
		if(_webElement != null) {
			return _webElement.getText().contains(text);
		}
		return false;
	}
	
	public boolean is(String text) {
		if(_webElement != null) {
			return StringUtils.equals(text, _webElement.getText());
		}
		return false;
	}
	
	// Methods from WebElement interface
	public void clear() {
		_webElement.clear();
	}

	public void click() {
		_webElement.click();
	}

	public Element findElement(final By by) {
		WebElement in = _nativeWebElement();
		if (in == null || in instanceof NullElement) {
			throw new AssertionError("Cannot find any element '" + by + "' on a null web element '" + in + "'");
		}
		Wait<WebElement> wait = new FluentWait<WebElement>(in).withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
				.pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement _webElement = null;
		try {
			_webElement = wait.until(new Function<WebElement, WebElement>() {
				public WebElement apply(WebElement find_within) {
					return find_within.findElement(by);
				}
			});
		} catch (TimeoutException toe) {
			return NullElement.newInstance(by);
		}
		return Element.newInstance(_webElement);
	}
	
	public Elements findElements(final By by) {
		WebElement in = _nativeWebElement();
		Wait<WebElement> wait = new FluentWait<WebElement>(in).withTimeout(Config.seconds_timeout, TimeUnit.SECONDS)
				.pollingEvery(Config.seconds_poll_interval, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		List<WebElement> _webElements;
		try {
			_webElements = wait.until(new Function<WebElement, List<WebElement>>() {
				public List<WebElement> apply(WebElement find_within) {
					return find_within.findElements(by);
				}
			});
		} catch (TimeoutException toe) {
			return Elements.nullInstance();
		}
		Elements elements = new Elements();
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