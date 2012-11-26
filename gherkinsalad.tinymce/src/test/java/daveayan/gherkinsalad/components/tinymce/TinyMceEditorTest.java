package daveayan.gherkinsalad.components.tinymce;

import org.junit.Test;

import daveayan.BaseTest;
import daveayan.gherkinsalad.components.html.TextEnterable;

public class TinyMceEditorTest extends BaseTest {
	@Test
	public void firefox_tiny_mce_test() {
		super.launch_browser("firefox");
		super.goto_url("http://www.tinymce.com/tryit/full.php");
		TextEnterable tinyMceEditor = new TinyMceEditor();
		
		tinyMceEditor.append_text_if_enabled("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ");
		
		System.out.println(tinyMceEditor.getText());
	}
	
	@Test
	public void chrome_tiny_mce_test() {
		super.launch_browser("chrome");
		super.goto_url("http://www.tinymce.com/tryit/full.php");
		TextEnterable tinyMceEditor = new TinyMceEditor();
		
		tinyMceEditor.append_text_if_enabled("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB ");
		
		System.out.println(tinyMceEditor.getText());
	}
}