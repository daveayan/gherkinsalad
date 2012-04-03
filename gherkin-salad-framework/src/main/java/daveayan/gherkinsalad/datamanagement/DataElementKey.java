package daveayan.gherkinsalad.datamanagement;

import org.apache.commons.lang.StringUtils;

public class DataElementKey {
	private String key = "NA";
	private String role, symbolic_data_name;
	
	public static DataElementKey newInstance(String role, String symbolic_data_name) {
		DataElementKey key = new DataElementKey();
		key.create_key(role, symbolic_data_name);
		return key;
	}
	
	private void create_key(String r, String sdn) {
		this.role = r;
		this.symbolic_data_name = sdn;
		
		if(StringUtils.isBlank(role)) role = "*";
		if(StringUtils.isBlank(symbolic_data_name)) symbolic_data_name = "*";
		
		key = role.trim() + "," + symbolic_data_name.trim();
	}
	
	public String key() {
		return key;
	}

	public String role() {
		return role;
	}

	public String symbolic_data_name() {
		return symbolic_data_name;
	}

	public String toString() {
		return "DataElementKey with key '" + key + "'";
	}
	
	private DataElementKey() {}
}