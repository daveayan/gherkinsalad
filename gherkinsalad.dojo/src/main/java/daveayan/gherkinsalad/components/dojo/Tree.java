package daveayan.gherkinsalad.components.dojo;

import org.openqa.selenium.By;

import daveayan.gherkinsalad.Strings;
import daveayan.gherkinsalad.components.core.Component;

public class Tree extends Component {
	
	public String get_selected_option_name() {
		return "";
	}
	
	public Strings get_selected_option_path() {
		return Strings.new_instance();
	}
	
	public void select_item(String... path_elements) {
		
	}
	
	public void expand_item(String... path_elements) {
		
	}
	
	public void collapse_tree() {
		
	}
	
	public boolean is_collapsed() {
		return Boolean.TRUE;
	}
	
	public boolean is_expanded() {
		return Boolean.TRUE;
	}
	
	public static Tree find(By locator) {
		Tree t = new Tree();
		t.found(locator);
		return t;
	}
}