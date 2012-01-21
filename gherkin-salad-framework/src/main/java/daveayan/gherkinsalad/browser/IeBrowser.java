package daveayan.gherkinsalad.browser;

import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IeBrowser {
	public static WebDriver getDriver() {
		try {
			WebDriver ie = new InternetExplorerDriver();
			return ie;
		} catch (Throwable th) {
			System.out.println(th.getMessage());
		}
		return new NullWebDriver();
	}
}