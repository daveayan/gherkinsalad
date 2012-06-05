package daveayan.gherkinsalad.components;

public interface Clickable extends BrowserElement {
	public void click_if_enabled();
	public void click_if_exists_and_enabled();
}