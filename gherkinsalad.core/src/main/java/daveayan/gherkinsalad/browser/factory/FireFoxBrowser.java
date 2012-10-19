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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.report.Report;

/**
 * @author daveayan
 **/
/** Use this class to launch an instance of Firefox Browser for testing. This class uses the <a href="http://industriallogic.com/xp/refactoring/nullObject.html">Null Object</a> Pattern.
 * <p>
 * Usage: Unless absolutely needed do not use this class directly. Use the daveayan.gherkinsalad.browser.Browser object instead. 
 * This class has a private constructor. Use the getDriver method to get an instance of org.openqa.selenium.WebDriver
 * If construction of driver is not successful this method returns a NullWebDriver.
 * 
 * @see Browser
 */
public class FireFoxBrowser {
	private static Log log = LogFactory.getLog(FireFoxBrowser.class);
	
	public static WebDriver getDriver() {
		try {
			Report.action("Opening Firefox");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			if(StringUtils.isNotEmpty(Config.proxy_url)) {
				Proxy proxy = new Proxy();
				proxy.setProxyAutoconfigUrl("http://youdomain/config");
				capabilities.setCapability(CapabilityType.PROXY, proxy);
				Report.action("With proxy '" + Config.proxy_url + "'");
			}
			WebDriver driver = new FirefoxDriver(capabilities);
			return driver;
		} catch (Throwable th) {
			log.info(th.getMessage());
		}
		return new NullWebDriver();
	}
	
	private FireFoxBrowser() {}
}