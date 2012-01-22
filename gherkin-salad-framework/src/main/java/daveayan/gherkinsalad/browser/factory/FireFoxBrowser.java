package daveayan.gherkinsalad.browser.factory;

import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowser {
	public static WebDriver getDriver() {
		try {
			WebDriver ie = new FirefoxDriver();
			return ie;
		} catch (Throwable th) {
			System.out.println(th.getMessage());
		}
		return new NullWebDriver();
	}
}