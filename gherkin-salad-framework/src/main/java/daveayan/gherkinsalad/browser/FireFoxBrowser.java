package daveayan.gherkinsalad.browser;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.NullWebDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FireFoxBrowser {
	public static WebDriver getDriver() {
		try {
			String config_file_location = System.getenv("GHKSALAD_CONFIG");
			System.out.println("GHKSALAD_CONFIG file is " + config_file_location);
			File config_file = new File(System.getenv("GHKSALAD_CONFIG"));
			Properties config = new Properties();
			config.load(new FileInputStream(config_file));
			System.out.println("GHKSALAD_CONFIG Properties are: " + config);
			
			Proxy proxy = new Proxy();
			proxy.setProxyAutoconfigUrl(config.getProperty("proxy"));

			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			
			WebDriver ie = new FirefoxDriver();
			return ie;
		} catch (Throwable th) {
			System.out.println(th.getMessage());
		}
		return new NullWebDriver();
	}
}