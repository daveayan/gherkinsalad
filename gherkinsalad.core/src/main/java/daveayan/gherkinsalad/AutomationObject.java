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
package daveayan.gherkinsalad;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;

import com.google.common.annotations.Beta;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.components.core.Element;
import daveayan.gherkinsalad.components.core.Nullable;
import daveayan.gherkinsalad.report.ReportFactory;

/** @author daveayan */
/**
 * Base class for all the page objects and step definitions. Provides methods to:
 * <ul>
 * <li>Launch browser</li>
 * <li>Close browser</li>
 * <li>Navigate to a URL in the open browser</li>
 * <li>Take Screenshot</li>
 * <li>Wait between execution of steps or page object operations</li>
 * </ul>
 */
public abstract class AutomationObject implements Nullable {
	protected String _name = StringUtils.EMPTY;
	protected static Browser browser;
	protected Random random = new Random();
	
	protected void feature(String feature_name) {
		ReportFactory.reporter().feature("Feature: "+ feature_name);
	}
	
	protected void scenario(String scenario_name) {
		ReportFactory.reporter().scenario("Scenario: " + scenario_name);
	}
	
	protected void given(String step_name) {
		ReportFactory.reporter().given("Given " + step_name);
	}
	
	protected void when(String step_name) {
		ReportFactory.reporter().when("When " + step_name);
	}
	
	protected void then(String step_name) {
		ReportFactory.reporter().then("Then " + step_name);
	}
	
	protected void and(String step_name) {
		ReportFactory.reporter().and("And " + step_name);
	}
	
	protected void ask(String ask) {
		ReportFactory.reporter().ask(ask);
	}
	
	protected void info(String info) {
		ReportFactory.reporter().info(info);
	}
	
	protected void warn(String warn) {
		ReportFactory.reporter().warn(warn);
		browser.takeScreenshot();
	}
	
	protected void task(String task) {
		ReportFactory.reporter().task(task);
	}
	
	protected void action(String action) {
		ReportFactory.reporter().action(action);
	}
	
	protected void error(String error) {
		ReportFactory.reporter().error(error);
		takeScreenshot();
	}
	
	protected void verify_profile(String name, Element... elements) {
		System.out.println("PROFILE: " + name);
		for(Element element: elements) {
			System.out.println(element.static_info());
		}
	}
	
	public void switch_to_default_window() {
		browser.switch_to_default_window();
	}
	
	public void switch_to_window_with_name(String name) {
		browser.driver().switchTo().window(name);
	}
	
	public void execute_javascript(String script, Object... args) {
		((JavascriptExecutor) browser.driver()).executeScript(script, args);
	}
	
	public void execute_async_javascript(String script, Object... args) {
		((JavascriptExecutor) browser.driver()).executeAsyncScript(script, args);
	}
	
	public void javascript_popup_click_ok() {
		Alert alert = get_current_alert_box();
		if(alert != null) {
			action("Accepted the javascript popup '" + alert.getText() + "'");
			alert.accept();
		}
	}
	
	public void javascript_popup_dismiss() {
		if(browser.is_Chrome()) {
			javascript_popup_click_ok();
			return;
		}
		Alert alert = get_current_alert_box();
		if(alert != null) {
			action("Dismissed the javascript popup '" + alert.getText() + "'");
			alert.dismiss();
		}
	}
	
	@Beta
	public void javascript_authentication(String username, String password) {
//		Alert alert = get_current_alert_box();
//		if(alert != null) {
//			Credentials c = new UserAndPassword(username, password);
//			action("Authenticating using the javascript popup with username '" + username + "', password '" + password +"'");
//			alert.authenticateUsing(c);
//		}
	}
	
	private Alert get_current_alert_box() {
		try {
			return browser.driver().switchTo().alert();
		} catch (NoAlertPresentException nape) {
		}
		return null;
	}
	
	/**
	 * Use this method to make the current running thread wait for the number of seconds specified by property seconds.wait.after.each.step
	 */
	protected void wait_between_steps() {
		wait_for_seconds(Config.seconds_wait_after_each_step);
	}
	
	/**
	 * Use this method to make the current running thread wait for the number of seconds calculated as
	 * <p>
	 * {@code
	 * # of seconds specified by property seconds.wait.after.each.step
	 * +
	 * # of seconds passed in as input
	 * }
	 * @param seconds Number of seconds to wait in addition to the number of seconds specified by property seconds.wait.after.each.step
	 */
	protected void wait_between_steps_plus(int seconds) {
		wait_for_seconds(Config.seconds_wait_after_each_step + seconds);
	}

	/**
	 * Use this method to make the current running thread wait for the specified number of seconds
	 * @param seconds Number of seconds to wait
	 */
	protected void wait_for_seconds(int seconds) {
		if(seconds == 0) return;
		try {
			if(seconds >= 7) {
				action("User is waiting for " + seconds + " seconds");
			}
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			error(e.getMessage());
		}
	}
	
	/**
	 * Use this method to take the screenshot of the currently open browser window. This method is failsafe - If the browser is not currently open, this method does nothing.
	 */
	protected void takeScreenshot() {
		if(browser != null) {
			browser.takeScreenshot();
		}
	}
	
	/**
	 * Use this method to launch the browser. Currently supported: Firefox, Chrome, IE.
	 * The input name is used case insensitive. 
	 * @param browser_name
	 */
	public void launch_browser(String browser_name) {
		browser = Browser.instance_of(browser_name);
		browser.launch();
	}

	
	/**
	 * Use this method to close the currently open browser. This method is failsafe - If the browser is not currently open, this method does nothing.
	 */
	public void close_browser() {
		if (browser != null) {
			browser.close();
		}
	}

	/**
	 * Use this method to visit the url in the currently open browser. This method is failsafe - If the browser is not currently open, this method does nothing.
	 * @param url The url to launch. If the url is null or empty this method does nothing. If the url is malformed this method may result in an exception.
	 * If the url is a valid url the launched browser instance will load the url specified.
	 */
	public void goto_url(String url) {
		if(browser != null) {
			action("Navigating to URL '" + url + "'");
			browser.goto_url(url);
		} else {
			error("Browser is null, cannot navigate to URL '" + url + "'");
		}
	}
	
	/**
	 * Default implementation. Returns Boolean.FALSE
	 */
	public boolean is_null() {
		return false;
	}

	/**
	 * Default implementation. Returns ! is_null()
	 */
	public boolean is_not_null() {
		return !is_null();
	}
	
	public boolean is_not_null(Object object) {
		return ! is_null(object);
	}
	
	public boolean is_null(Object object) {
		if(object == null) {
			return true;
		}
		if(object instanceof Nullable) {
			return ((Nullable) object).is_null();
		}
		return false;
	}
	
	protected boolean thisIsNamedElement() {
		return StringUtils.isNotBlank(_name);
	}
}