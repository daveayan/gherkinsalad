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
package daveayan.gherkinsalad.browser;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.browser.factory.ChromeBrowser;
import daveayan.gherkinsalad.browser.factory.IeBrowser;
import daveayan.gherkinsalad.report.Report;
/** @author daveayan */
/**
 * Unless absolutely needed do not use this class directly. Use the daveayan.gherkinsalad.BaseAutomationObject object instead.
 * Use this method to initialize a browser instance, launch it, close it and navigate to a specific url. This class is built such that
 * only one running instance of browser can be handled. For example:
 * <p>
 * <pre>
 * {@code
 * Browser firefox = Browser.instance_of("Firefox");
 * firefox.launch();
 * 
 * Browser chrome = Browser.instance_of("Chrome");
 * chrome.launch();
 * }
 * </pre>
 * This will suspend the firefox browser and let the chrome browser take control.
 */
public class Browser {
	private static Log log = LogFactory.getLog(Browser.class);
	private String name;

	private static WebDriver instance;

	private static int screen_shot_count = 0;

	/**
	 * Use this method to take a screenshot of the current browser window. The screenshot is taken as a png file and saved in target/screenshots folder.
	 * The name of the file is screenshot_##.png where ## is the count that is incremented after each screenshot is taken. The count is maintained internally
	 * as int.
	 */
	public void takeScreenshot() {
		screen_shot_count++;
		String file_name = Path.TO_SCREENSHOTS + "screenshot_" + screen_shot_count + ".png";
		try {
			File screenshot_file = ((TakesScreenshot) instance).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot_file, new File(file_name));
			log.info("Screenshot taken : " + file_name);
		} catch (Throwable th) {
			log.info(th.getMessage());
			log.info("Unable to take screenshot : " + file_name);
		}
		Report.screenshot_taken(file_name);
	}

	/**
	 * Use this method to get an instance of the type of browser that is to be launched. This method does not launch the browser upon completion.
	 * This method only creates a new Browser object and returns it back. Use the launch method to actually launch the instance of the browser.
	 * 
	 * @param name Name of the browser. Currently supported are firefox, chrome and ie. Name is case insensitive.
	 * @return Never null. New instance of Browser object for the given browser name
	 */
	public static Browser instance_of(String name) {
		Browser browser = new Browser();
		browser.name = name;
		return browser;
	}

	public WebElement findElementBy(By by) {
		return instance.findElement(by);
	}

	/**
	 * Use this method to close the currently open browser.
	 */
	public void close() {
		try {
			if (instance != null) {
				instance.close();
				instance.quit();
			}
		} catch (WebDriverException wde) {
			System.err.println(wde.getMessage());
		}
		instance = null;
	}

	/**
	 * Use this method to actually launch the browser window that is represented by this object.
	 */
	public void launch() {
		if (this.is_IE()) {
			instance = IeBrowser.getDriver();
		} else if (this.is_Chrome()) {
			instance = ChromeBrowser.getDriver();
		} else if (this.is_Firefox()) {
			instance = new FirefoxDriver();
		}
	}

	/**
	 * Use this method to navigate to a specific website in the browser that is currently launched.
	 * If no browser is currently launch this method does nothing.
	 * 
	 * @param url The url to launch. If the url is null or empty this method does nothing. If the url is malformed this method may result in an exception.
	 * If the url is a valid url the launched browser instance will load the url specified.
	 */
	public void goto_url(String url) {
		if(instance != null && StringUtils.isNotEmpty(url)) {
			instance.get(url);
		}
	}

	public WebDriver driver() {
		return instance;
	}

	private boolean is_IE() {
		return "ie".equalsIgnoreCase(this.name);
	}

	private boolean is_Chrome() {
		return "chrome".equalsIgnoreCase(this.name);
	}

	private boolean is_Firefox() {
		return "firefox".equalsIgnoreCase(this.name);
	}
	
	private Browser() {}
}