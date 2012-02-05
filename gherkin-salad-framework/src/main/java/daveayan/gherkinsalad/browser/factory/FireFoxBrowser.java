package daveayan.gherkinsalad.browser.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowser {
	private static Log log = LogFactory.getLog(FireFoxBrowser.class);
	public static WebDriver getDriver() {
		try {
			WebDriver ie = new FirefoxDriver();
			return ie;
		} catch (Throwable th) {
			log.info(th.getMessage());
		}
		return new NullWebDriver();
	}
}