package daveayan.gherkinsalad;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;

import daveayan.gherkinsalad.report.impl.DefaultHtmlReporter;

public class Config {
	private static Log log = LogFactory.getLog(Config.class);
	
	private static Properties user_config = new Properties();
	private static Properties default_config = new Properties();
	
	public static String chrome_webdriver_location = null;
	
	public static int seconds_wait_after_each_step = 0;
	public static int seconds_timeout = 30;
	public static int seconds_poll_interval = 1;
	
	public static boolean validate_css = Boolean.FALSE;
	public static boolean validate_position = Boolean.FALSE;
	
	public static String execution_results_storage_location = null;
	
	public static String reporter_class = "daveayan.gherkinsalad.report.impl.DefaultHtmlReporter";
	
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
			
			String seconds_1 = config.getProperty("seconds.wait.after.each.step");
			if(seconds_1 != null) seconds_wait_after_each_step = Integer.parseInt(seconds_1);

			String seconds_2 = config.getProperty("seconds.timeout");
			if(seconds_2 != null) seconds_timeout = Integer.parseInt(seconds_2);
			
			String seconds_3 = config.getProperty("seconds.poll.interval");
			if(seconds_3 != null) seconds_poll_interval = Integer.parseInt(seconds_3);
			
			String validate_1 = config.getProperty("validate.css");
			if(validate_1 != null) validate_css = Boolean.parseBoolean(validate_1);
			
			String validate_2 = config.getProperty("validate.position");
			if(validate_2 != null) validate_position = Boolean.parseBoolean(validate_2);
			
			String execution_1 = config.getProperty("execution.results.storage.location");
			if(StringUtils.isNotBlank(execution_1)) execution_results_storage_location = new String(execution_1);
			
			String reporter_1 = config.getProperty("reporter");
			if(StringUtils.isNotBlank(reporter_1)) reporter_class = new String(reporter_1);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return config;
	}
	
	private static void garnish_properties() {
		if(seconds_wait_after_each_step == -1) seconds_wait_after_each_step = 0;
		if(seconds_timeout == -1) seconds_timeout = 30;
		if(seconds_poll_interval == -1) seconds_poll_interval = 1;
		if(StringUtils.isBlank(execution_results_storage_location)) execution_results_storage_location = Path.TO_TARGET;
		if(StringUtils.isBlank(reporter_class)) reporter_class = "daveayan.gherkinsalad.report.DefaultHtmlReporter";
		if(StringUtils.isEmpty(default_config.getProperty("browser.firefox.class"))) default_config.put("browser.firefox.class", "daveayan.gherkinsalad.browser.factory.FireFoxBrowser");
		if(StringUtils.isEmpty(default_config.getProperty("browser.chrome.class"))) default_config.put("browser.chrome.class", "daveayan.gherkinsalad.browser.factory.ChromeBrowser");
		if(StringUtils.isEmpty(default_config.getProperty("browser.ie.class"))) default_config.put("browser.ie.class", "daveayan.gherkinsalad.browser.factory.IeBrowser");
		
		setup_chrome_driver();
	}
	
	public static void load_properties_from(String user_properties_file_path, String default_properties_file_path) {
		log.info("Loading remaining properties from default properties file");
		File default_properties_file = new File(default_properties_file_path);
		if(default_properties_file.exists()) {
			default_config = load_properties_from_file(new File(default_properties_file_path));
			log.info("GHKSALAD default specific config is: " + default_config);
		}
		File user_specific_properties_file = new File(user_properties_file_path);
		if(user_specific_properties_file.exists()) {
			log.info("User specific configuration exists, Using " + user_specific_properties_file.getAbsolutePath());
			user_config = load_properties_from_file(user_specific_properties_file);
			log.info("GHKSALAD user specific config is: " + user_config);
		}
		
		garnish_properties();
	}
	
	private static void setup_chrome_driver() {
		String os_name= System.getProperty("os.name");
		if(os_name.trim().contains("Mac")) {
			chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/mac.chromedriver";
			stream_classpath_resource_to_file("/chrome/mac.chromedriver", Path.TO_SYSTEM_RESOURCES + "/mac.chromedriver");
		} else if(os_name.trim().contains("Linux")) {
			chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/linux.chromedriver";
			stream_classpath_resource_to_file("/chrome/linux.chromedriver", Path.TO_SYSTEM_RESOURCES + "/linux.chromedriver");
		} else {
			chrome_webdriver_location = Path.TO_SYSTEM_RESOURCES + "/win.chromedriver.exe";
			stream_classpath_resource_to_file("/chrome/win.chromedriver.exe", Path.TO_SYSTEM_RESOURCES + "/win.chromedriver.exe");
		}
	}
	
	private static void stream_classpath_resource_to_file(String path_to_classpath_resource, String file_to_write) {
		File to_file = new File(file_to_write);
		if(to_file.exists()) {
			return;
		}
		
		InputStream stream = DefaultHtmlReporter.class.getResourceAsStream(path_to_classpath_resource);
		try {
			byte[] contents = IOUtils.toByteArray(stream);
			FileUtils.writeByteArrayToFile(new File(file_to_write), contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static {
		String user_name = System.getProperty("user.name").trim();
		log.info("User Name is : " + user_name);
		load_properties_from(Path.TO_SYSTEM_RESOURCES + user_name + ".ghksalad.properties", Path.TO_SYSTEM_RESOURCES + "default.ghksalad.properties");
	}
}