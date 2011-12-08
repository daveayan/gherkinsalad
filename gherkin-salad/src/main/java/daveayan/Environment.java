package daveayan;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebDriver;

public class Environment {
	private static Properties properties = null;
	private static final WebDriver driver = null;
	private static final String env_to_use = null, url_to_use = null;
	private static final int timeout_in_seconds = 10;
	
	public static WebDriver driver() {
		return driver;
	}
	
	public static void init() {
		load_file();
	}
	
	private static final void load_file() {
		if(properties == null) {
			properties = new Properties();
			String config_file_path = System.getenv("ATDD_HOME");
			Assert.assertFalse("Expected a valid config file path, found '" + config_file_path + "'", StringUtils.isEmpty(config_file_path));
			try {
				properties.load(FileUtils.openInputStream(new File(config_file_path)));
			} catch (IOException e) {
				e.printStackTrace();
				Assert.fail("Cannot open file '" + config_file_path + "'");
			}
		}
	}
	
	private Environment(){}
}