package daveayan.gherkinsalad.browser.actions;

public interface TextEnterable extends BrowserElement {
	public void enter_text_if_enabled(String text);
	public void append_text_if_enabled(String text);
}