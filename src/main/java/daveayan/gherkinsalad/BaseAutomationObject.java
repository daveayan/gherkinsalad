package daveayan.gherkinsalad;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import daveayan.gherkinsalad.browser.Browser;

/**
 * 
 * 
 * @author daveayan
 *
 */
public abstract class BaseAutomationObject {
	private static Log log = LogFactory.getLog(BaseAutomationObject.class);
	protected static Browser browser;
	
	protected void wait_between_steps() {
		wait_for_seconds(Config.seconds_wait_after_each_step);
	}
	
	protected void wait_between_steps_plus(int seconds) {
		wait_for_seconds(Config.seconds_wait_after_each_step + seconds);
	}

	protected void wait_for_seconds(int seconds) {
		if(seconds == 0) return;
		try {
			if(seconds >= 7) {
				log.info("User is waiting for " + seconds + " seconds");
			}
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		}
	}
	
	protected void takeScreenshot() {
		browser.takeScreenshot();
	}
	
	public void launch_browser_with(String browser_name) {
		browser = Browser.instance_of(browser_name);
		browser.launch();
	}

	public void close_browser() {
		if (browser != null) {
			browser.close();
		}
	}

	public void goto_url(String url) {
		browser.goto_url(url);
	}
}