package daveayan.gherkinsalad;

import org.junit.Test;

public class ConfigTest {
	@Test public void check_properties() {
		System.out.println("Config.chrome_webdriver_location = " + Config.chrome_webdriver_location);
		System.out.println("Config.seconds_wait_after_each_step = " + Config.seconds_wait_after_each_step);
		System.out.println("Config.seconds_timeout = " + Config.seconds_timeout);
		System.out.println("Config.seconds_poll_interval = " + Config.seconds_poll_interval);
		System.out.println("Config.validate_css = " + Config.validate_css);
		System.out.println("Config.validate_position = " + Config.validate_position);
		System.out.println("Config.execution_results_storage_location = " + Config.execution_results_storage_location);
		System.out.println("Config.getProperty(browser.chrome.class) = " + Config.getProperty("browser.chrome.class"));
		System.out.println("Config.getProperty(browser.firefox.class) = " + Config.getProperty("browser.firefox.class"));
	}
}