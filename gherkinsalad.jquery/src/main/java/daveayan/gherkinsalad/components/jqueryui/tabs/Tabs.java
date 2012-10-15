package daveayan.gherkinsalad.components.jqueryui.tabs;

public interface Tabs {
	public void select_tab(String tab_name);
	
	public void selected_tab_should_be(String expected_selected_tab);
	
	public void selected_tab_should_not_be(String expected_selected_tab);
	
	public void remove_tab(String tab_to_remove);
}