package daveayan.gherkinsalad;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import daveayan.gherkinsalad.browser.Browser;
import daveayan.gherkinsalad.components.BrowserElement;
import daveayan.gherkinsalad.components.NullBrowserElement;

public abstract class BaseAutomationObject {
	private static Log log = LogFactory.getLog(BaseAutomationObject.class);
	protected static String current_role = StringUtils.EMPTY;
	protected static Browser browser;
	
	protected void wait_between_steps() {
		wait_for_seconds(Config.seconds_wait_after_each_step);
	}
	
	public BrowserElement fetch(Class<?> clazz) {
		BrowserElement returnObject = new NullBrowserElement();
		try {
			Object object = clazz.newInstance();
			returnObject = (BrowserElement) object;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		returnObject.browser_is(browser);
		return returnObject;
	}
	
	protected void wait_for_seconds(int seconds) {
		try {
			log.info("User is waiting for " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		}
	}
	
	protected void takeScreenshot() {
		browser.takeScreenshot();
	}
	
	public void launch_browser_with(String name) {
		browser = Browser.instance_with_name_and_page_structure_name(name);
		browser.launch();
	}

	public void close_browser() {
		current_role = StringUtils.EMPTY;
		if (browser != null) {
			browser.close();
		}
	}

	public void goto_url(String url) {
		browser.goto_url(url);
	}
}