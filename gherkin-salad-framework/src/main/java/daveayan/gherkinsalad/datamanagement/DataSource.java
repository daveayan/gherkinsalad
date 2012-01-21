package daveayan.gherkinsalad.datamanagement;

public interface DataSource {
	public void load_up_from_file_at_location(String absolute_file_path);
	public String get_data_for(DataElementKey data_element_key);
}