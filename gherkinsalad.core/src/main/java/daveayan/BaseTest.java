package daveayan;

import java.io.File;

import org.junit.After;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.components.core.BaseBrowserElement;

public class BaseTest extends BaseBrowserElement {

	public void goto_test_page(String relative_url) {
		File f = new File(".");
		String path = "file://localhost/" + f.getAbsolutePath() + "/src/test/resources/" + relative_url;
		goto_url(path);
	}
	
	public void assert_this_text_exist_in_report() {
		
	}
	
	public void clear_report() {
		
	}
	
	public boolean is_firefox_disabled() {
		return ! is_firefox_enabled();
	}
	
	public boolean is_firefox_enabled() {
		if(Boolean.parseBoolean(Config.getProperty("browser.firefox.enabled"))) 
			return true;
		else
			return false;
	}
	
	public boolean is_chrome_disabled() {
		return ! is_chrome_enabled();
	}
	
	public boolean is_chrome_enabled() {
		if(Boolean.parseBoolean(Config.getProperty("browser.chrome.enabled"))) 
			return true;
		else
			return false;
	}
	
	@After
	public void close_browser() {
		super.close_browser();
	}
}