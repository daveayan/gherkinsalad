package daveayan.gherkinsalad.browser.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IeBrowser {
	private static Log log = LogFactory.getLog(IeBrowser.class);
	public static WebDriver getDriver() {
		try {
			WebDriver ie = new InternetExplorerDriver();
			return ie;
		} catch (Throwable th) {
			log.info(th.getMessage());
		}
		return new NullWebDriver();
	}
}