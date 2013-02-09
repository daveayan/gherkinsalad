package daveayan.gherkinsalad;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import daveayan.gherkinsalad.browser.factory.BrowserFactory;
import daveayan.gherkinsalad.report.ReportFactory;

public class FireFoxBrowserToUseForTests implements BrowserFactory {
	private static Log log = LogFactory.getLog(FireFoxBrowserToUseForTests.class);
	
	public WebDriver getDriver() {
		try {
			ReportFactory.reporter().action("Opening Firefox");
			
			FirefoxBinary binary = new FirefoxBinary(new File("/Applications/Firefox 3.app/Contents/MacOS/firefox"));
			FirefoxProfile profile = new FirefoxProfile();
			
			WebDriver driver = new FirefoxDriver(binary, profile);
			return driver;
		} catch (Throwable th) {
			log.info(th.getMessage());
		}
		return new NullWebDriver();
	}
	
	private FireFoxBrowserToUseForTests() {}
}
