package daveayan.gherkinsalad.browser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser {
	public static WebDriver getDriver() {
		try {
			String config_file_location = System.getenv("GHKSALAD_CONFIG");
			System.out.println("GHKSALAD_CONFIG file is " + config_file_location);
			File config_file = new File(System.getenv("GHKSALAD_CONFIG"));
			Properties config = new Properties();
			config.load(new FileInputStream(config_file));
			System.out.println("GHKSALAD_CONFIG Properties are: " + config);
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingChromeDriverExecutable(new File(config.getProperty("chrome.webdriver.location")))
					.usingAnyFreePort().build();
			service.start();
			WebDriver instance = new ChromeDriver(service, DesiredCapabilities.chrome());
			System.out.println("Found webdriver instance : " + instance);
			return instance;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Returning back null web driver");
		return new NullWebDriver();
	}
}