package daveayan.gherkinsalad.components;

public interface Selectable extends BrowserElement {
	public void select_if_enabled(String text);
	public void has_options(String[] options);
}