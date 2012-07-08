package daveayan.gherkinsalad.components.tinymce;

import org.junit.Test;

import daveayan.gherkinsalad.components.BaseTest;
import daveayan.gherkinsalad.components.TextEnterable;

public class TinyMceEditorTest extends BaseTest {
	@Test
	public void firefox_tiny_mce_test() {
		super.launch_browser_with("firefox");
		super.goto_url("http://www.tinymce.com/tryit/full.php");
		TextEnterable tinyMceEditor = new TinyMceEditor();
		
		tinyMceEditor.enter_text_if_enabled("ABC TEST");
		
		// TO IMPLEMENT ASSERTIONS
		
		wait_for_seconds(10);
	}
}