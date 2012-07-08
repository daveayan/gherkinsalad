package daveayan.gherkinsalad.components;

public interface TextEnterable extends Clickable {
	public void enter_text_if_enabled(String text);
	public void append_text_if_enabled(String text);
}