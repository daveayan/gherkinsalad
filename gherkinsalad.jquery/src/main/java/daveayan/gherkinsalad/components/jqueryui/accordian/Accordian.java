package daveayan.gherkinsalad.components.jqueryui.accordian;

public interface Accordian {
	public void select_section(String section_name);

	public void selected_section_should_be(String expected_selected_section);

	public void selected_section_should_not_be(String expected_selected_section);
}