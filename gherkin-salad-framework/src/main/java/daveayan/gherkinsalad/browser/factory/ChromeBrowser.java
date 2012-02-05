package daveayan.gherkinsalad.browser.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser {
	private static Log log = LogFactory.getLog(ChromeBrowser.class);
	public static WebDriver getDriver() {
		try {
			String config_file_location = System.getenv("GHKSALAD_CONFIG");
			log.info("GHKSALAD_CONFIG file is " + config_file_location);
			File config_file = new File(System.getenv("GHKSALAD_CONFIG"));
			Properties config = new Properties();
			config.load(new FileInputStream(config_file));
			log.info("GHKSALAD_CONFIG Properties are: " + config);
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingChromeDriverExecutable(new File(config.getProperty("chrome.webdriver.location")))
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