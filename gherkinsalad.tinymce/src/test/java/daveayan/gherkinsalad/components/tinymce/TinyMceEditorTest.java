package daveayan.gherkinsalad.components.tinymce;

import org.junit.Test;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.TextEnterable;

public class TinyMceEditorTest extends BaseTest {
	@Test
	public void firefox_tiny_mce_test() {
		super.launch_browser("firefox");
		super.goto_url("http://www.tinymce.com/tryit/full.php");
		TextEnterable tinyMceEditor = new TinyMceEditor();
		
//		tinyMceEditor.enter_text_if_enabled("enter_text_if_enabled");
		tinyMceEditor.should_have_text("enter_text_if_enabled");
		
//		tinyMceEditor.enter_text_if_enabled("");
//		tinyMceEditor.append_text_if_enabled("enter_text_if_enabled 111");
		tinyMceEditor.should_have_text("enter_text_if_enabled 111");
		
		tinyMceEditor.enter_text_if_enabled("");
	}
}