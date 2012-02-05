package daveayan.gherkinsalad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
	private static Log log = LogFactory.getLog(Config.class);
	
	public static String chrome_webdriver_location = null;
	
	public static int seconds_wait_after_each_step = 0;
	public static int seconds_timeout = 30;
	public static int seconds_poll_interval = 1;
	
	public static boolean archive_results = false;
	public static boolean skip_ie = false;
	
	static {
		try {
			String config_file_location = System.getenv("GHKSALAD_CONFIG");
			log.info("GHKSALAD_CONFIG file is " + config_file_location);
			File config_file = new File(System.getenv("GHKSALAD_CONFIG"));
			Properties config = new Properties();
			config.load(new FileInputStream(config_file));
			log.info("GHKSALAD_CONFIG Properties are: " + config);
			
			chrome_webdriver_location = config.getProperty("chrome.webdriver.location");
			seconds_wait_after_each_step = Integer.parseInt(config.getProperty("seconds.wait.after.each.step"));
			seconds_timeout = Integer.parseInt(config.getProperty("seconds.timeout"));
			seconds_poll_interval = Integer.parseInt(config.getProperty("seconds.poll.interval"));
			archive_results = Boolean.parseBoolean(config.getProperty("archive.results"));
			skip_ie = Boolean.parseBoolean(config.getProperty("skip.internet.explorer"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}