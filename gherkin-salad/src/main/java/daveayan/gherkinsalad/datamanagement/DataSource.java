package daveayan.gherkinsalad.datamanagement;

public interface DataSource {
	public void load_up();
	public void load_up_from_file_at_location(String absolute_file_path);
	public void add_data(String symbolic_data, String actual_data);
	public String get_data_for(String symbolic_data);
	public String get_data_for(String user_name, String symbolic_data);
	public String get_data_for(String user_name, String feature_name, String symbolic_data);
}