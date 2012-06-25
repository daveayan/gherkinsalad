package daveayan.gherkinsalad.components;

public interface Selectable extends BrowserElement {
	public void select_option_if_enabled(String option);
	public void select_code_if_enabled(String code);
	public void has_options(String[] options);
}