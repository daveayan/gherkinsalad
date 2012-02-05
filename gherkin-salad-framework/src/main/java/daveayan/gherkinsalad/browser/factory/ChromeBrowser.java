package daveayan.gherkinsalad.browser.factory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import daveayan.gherkinsalad.Config;

public class ChromeBrowser {
	private static Log log = LogFactory.getLog(ChromeBrowser.class);
	public static WebDriver getDriver() {
		try {
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingChromeDriverExecutable(new File(Config.chrome_webdriver_location))
					.usingAnyFreePort().build();
			service.start();
			WebDriver instance = new ChromeDriver(service, DesiredCapabilities.chrome());
			log.info("Found webdriver instance : " + instance);
			return instance;
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Returning back null web driver");
		return new NullWebDriver();
	}
}