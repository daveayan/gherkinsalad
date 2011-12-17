package daveayan;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Environment {
	protected static final Log logger = LogFactory.getLog(Environment.class);
	private static Properties properties = null;
	private static WebDriver driver = null;
	private static String env_to_use = null, url_to_use = null, browser_to_use = null;
	private static int timeout_in_seconds = 10;
	
	public static void launch() {
		if(driver == null) {
			if(StringUtils.equalsIgnoreCase(browser_to_use, "ie")) {
				logger.info("Getting InternetExplorerDriver");
				driver = new InternetExplorerDriver();
			} else if(StringUtils.equalsIgnoreCase(browser_to_use, "chrome")) {
				logger.info("Getting ChromeDriver");
				driver = new ChromeDriver();
			} else if(StringUtils.equalsIgnoreCase(browser_to_use, "firefox")) {
				logger.info("Getting FirefoxDriver");
				driver = new FirefoxDriver();
			}
		}
	}
	
	public static WebDriver driver() {
		return driver;
	}
	
	public static void close_browser() {
		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}
	
	public static void init() {
		load_file();
	}
	
	public static String urlToUse() {
		logger.info("url_to_use : " + url_to_use);
		return url_to_use;
	}
	
	private static final void load_file() {
		if(properties == null) {
			logger.info("properties not loaded yet");
			properties = new Properties();
			String config_file_path = System.getenv("ATDD_HOME");
			logger.info("config_file_path : " + config_file_path);
			Assert.assertFalse("Expected a valid config file path, found '" + config_file_path + "'", StringUtils.isEmpty(config_file_path));
			try {
				properties.load(FileUtils.openInputStream(new File(config_file_path)));
				logger.info(properties);
				browser_to_use = properties.getProperty("browser.to.use");
				env_to_use = properties.getProperty("env.to.use");
				url_to_use = properties.getProperty("url.to.use");
				timeout_in_seconds = Integer.parseInt(properties.getProperty("timeout.seconds"));
			} catch (IOException e) {
				e.printStackTrace();
				Assert.fail("Cannot open file '" + config_file_path + "'");
			}
		}
	}
	
	private Environment(){}
}