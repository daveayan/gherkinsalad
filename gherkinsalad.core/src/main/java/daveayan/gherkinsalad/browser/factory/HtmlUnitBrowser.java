/**
 * Copyright (c) 2012 Ayan Dave http://daveayan.com
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
 * associated documentation files (the "Software"), to deal in the Software without restriction, including 
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
 * copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the 
 * following conditions:
 * 
 * 1) The above copyright notice and this permission notice shall be included without any changes or alterations 
 * in all copies or substantial portions of the Software.
 * 2) This software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
**/
package daveayan.gherkinsalad.browser.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import daveayan.gherkinsalad.browser.Browser;

/**
 * @author daveayan
 **/
/** Use this class to launch an instance of Html Unit Browser for testing. This class uses the <a href="http://industriallogic.com/xp/refactoring/nullObject.html">Null Object</a> Pattern.
 * <p>
 * Usage: Unless absolutely needed do not use this class directly. Use the daveayan.gherkinsalad.browser.Browser object instead. 
 * This class has a private constructor. Use the getDriver method to get an instance of org.openqa.selenium.WebDriver
 * If construction of driver is not successful this method returns a NullWebDriver.
 * 
 * @see Browser
 */
public class HtmlUnitBrowser implements BrowserFactory {
	private static Log log = LogFactory.getLog(HtmlUnitBrowser.class);
	public WebDriver getDriver() {
		try {
			HtmlUnitDriver driver = new HtmlUnitDriver();
			driver.setJavascriptEnabled(true);
			return driver;
		} catch (Throwable th) {
			log.info(th.getMessage());
		}
		return new NullWebDriver();
	}
	
	private HtmlUnitBrowser() {}
}