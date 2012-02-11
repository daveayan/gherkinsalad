package daveayan.gherkinsalad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
	private static Log log = LogFactory.getLog(Config.class);
	
	private static Properties config = null;
	
	public static String chrome_webdriver_location = null;
	
	public static int seconds_wait_after_each_step = 0;
	public static int seconds_timeout = 30;
	public static int seconds_poll_interval = 1;
	
	public static boolean archive_results = false;
	
	public static boolean chrome_enabled = false;
	public static boolean firefox_enabled = false;
	public static boolean ie_enabled = false;
	
	public static String default_datasource_driver = null;
	
	public static String env(String env_name) {
		String url = config.getProperty("env." + env_name);
		if(url == null) return env_name;
		return url;
	}
	
	private static boolean load_properties_from_file(File file) {
		try {
			config = new Properties();
			config.load(new FileInputStream(file));
			log.info("GHKSALAD-CONFIG Properties are: " + config);

			seconds_wait_after_each_step = Integer.parseInt(config.getProperty("seconds.wait.after.each.step"));
			seconds_timeout = Integer.parseInt(config.getProperty("seconds.timeout"));
			seconds_poll_interval = Integer.parseInt(config.getProperty("seconds.poll.interval"));
			archive_results = Boolean.parseBoolean(config.getProperty("archive.results"));
			chrome_enabled = Boolean.parseBoolean(config.getProperty("browser.chrome.enabled"));
			firefox_enabled = Boolean.parseBoolean(config.getProperty("browser.firefox.enabled"));
			ie_enabled = Boolean.parseBoolean(config.getProperty("browser.ie.enabled"));
			default_datasource_driver = config.getProperty("default.datasource.driver");
			
			if(chrome_enabled) {
				String os_name= System.getProperty("os.name");
				if(os_name.trim().contains("Mac")) {
					chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/chromedriver";
				} else {
					chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/chromedriver.exe";
				}
			}
			return true;
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return false;
	}
	
	static {
		String user_name = System.getProperty("user.name");
		log.info("User Name is : " + user_name);
		File user_specific_properties_file = new File(user_name + ".ghksalad.properties");
		if(user_specific_properties_file.exists()) {
			log.info("User specific configuration exists, Using " + user_specific_properties_file.getAbsolutePath());
			load_properties_from_file(user_specific_properties_file);
		} else {
			log.info("User specific configuration does not exist, Using Default");
			load_properties_from_file(new File(Path.TO_SYSTEM_RESOURCES + "/default.ghksalad.properties"));
		}
	}
}