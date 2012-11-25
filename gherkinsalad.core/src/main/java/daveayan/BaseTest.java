package daveayan;

import java.io.File;

import org.junit.After;

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
	
	@After
	public void close_browser() {
		super.close_browser();
	}
}