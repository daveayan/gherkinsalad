package daveayan.gherkinsalad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Config {
	private static Log log = LogFactory.getLog(Config.class);
	
	private static Properties user_config = null;
	private static Properties default_config = null;
	
	public static String chrome_webdriver_location = null;
	
	public static String proxy_url = null;
	
	public static int seconds_wait_after_each_step = 0;
	public static int seconds_timeout = 30;
	public static int seconds_poll_interval = 1;
	
	public static String execution_results_storage_location = null;
	
	public static String getProperty(String key) {
		String value = user_config.getProperty(key);
		if(value == null) {
			value = default_config.getProperty(key);
		}
		return value;
	}
	
	public static String env(String env_name) {
		String url = user_config.getProperty("env." + env_name);
		if(url == null) {
			url = default_config.getProperty("env." + env_name);
		}
		if(url == null) return env_name;
		return url;
	}
	
	private static Properties load_properties_from_file(File file) {
		Properties config = new Properties();
		try {
			config.load(new FileInputStream(file));

			if(seconds_wait_after_each_step != -1) {
				String seconds = config.getProperty("seconds.wait.after.each.step");
				if(seconds != null) seconds_wait_after_each_step = Integer.parseInt(seconds);
			}
			if(seconds_timeout != -1) {
				String seconds = config.getProperty("seconds.timeout");
				if(seconds != null) seconds_timeout = Integer.parseInt(seconds);
			}
			if(seconds_poll_interval != -1) {
				String seconds = config.getProperty("seconds.poll.interval");
				if(seconds != null) seconds_poll_interval = Integer.parseInt(seconds);
			}
			
			if(proxy_url != null) proxy_url = config.getProperty("proxy.url");
			
			if(execution_results_storage_location == null) execution_results_storage_location = config.getProperty("execution.results.storage.location");
			
			String os_name= System.getProperty("os.name");
			if(os_name.trim().contains("Mac")) {
				chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/mac.chromedriver";
			} else if(os_name.trim().contains("Linux")) {
				chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/linux.chromedriver";
			} else {
				chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/win.chromedriver.exe";
			}
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return config;
	}
	
	private static void garnish_properties() {
		if(seconds_wait_after_each_step == -1) seconds_wait_after_each_step = 0;
		if(seconds_timeout == -1) seconds_timeout = 30;
		if(seconds_poll_interval == -1) seconds_poll_interval = 1;
		if(execution_results_storage_location == null) execution_results_storage_location = Path.TO_TARGET;
	}
	
	public static void load_properties_from(String user_properties_file_path, String default_properties_file_path) {
		File user_specific_properties_file = new File(user_properties_file_path);
		if(user_specific_properties_file.exists()) {
			log.info("User specific configuration exists, Using " + user_specific_properties_file.getAbsolutePath());
			user_config = load_properties_from_file(user_specific_properties_file);
			log.info("GHKSALAD user specific config is: " + user_config);
		}
		log.info("Loading remaining properties from default properties file");
		default_config = load_properties_from_file(new File(default_properties_file_path));
		log.info("GHKSALAD default specific config is: " + default_config);
		garnish_properties();
	}
	
	static {
		String user_name = System.getProperty("user.name").trim();
		log.info("User Name is : " + user_name);
		load_properties_from(Path.TO_SYSTEM_RESOURCES + user_name + ".ghksalad.properties", Path.TO_SYSTEM_RESOURCES + "default.ghksalad.properties");
	}
}