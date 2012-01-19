package daveayan.gherkinsalad.datamanagement;

import org.apache.commons.lang.StringUtils;

public class DataElementKey {
	private String key = "NA";
	private String role, feature_name, symbolic_data_name;
	
	public static DataElementKey newInstance(String role, String feature_name, String symbolic_data_name) {
		DataElementKey key = new DataElementKey();
		key.create_key(role, feature_name, symbolic_data_name);
		return key;
	}
	
	def create_key(String r, String fn, String sdn) {
		this.role = r;
		this.feature_name = fn;
		this.symbolic_data_name = sdn;
		
		if(StringUtils.isBlank(role)) role = "*";
		if(StringUtils.isBlank(feature_name)) feature_name = "*";
		if(StringUtils.isBlank(symbolic_data_name)) symbolic_data_name = "*";
		
		key = role.trim() + "," + feature_name.trim() + "," + symbolic_data_name.trim();
	}
	
	def key() {
		return key;
	}

	def role() {
		return role;
	}

	def feature_name() {
		return feature_name;
	}

	def symbolic_data_name() {
		return symbolic_data_name;
	}

	public String toString() {
		return "DataElementKey with key '" + key + "'";
	}
	
	private DataElementKey() {}
}