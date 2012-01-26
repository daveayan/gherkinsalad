package daveayan.gherkinsalad.datamanagement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import daveayan.gherkinsalad.datamanagement.impl.FlatCsvFile;

public class FlatCsvTest {
	DataSource ds = new FlatCsvFile();
	
	@Before public void load_up_data_source_file() {
		ds.load_up_from_file_at_location("./src/test/resources/datamanagement/test.data.csv");
	}
	
	@Test public void for_a_valid_key_returns_proper_data() {
		DataElementKey dek = null;
		String actual_data = null;
		
		dek = DataElementKey.newInstance("some_role", "sample_feature", "user_name");
		actual_data = ds.get_data_for(dek);
		Assert.assertEquals("qwerty", actual_data);
		
		dek = DataElementKey.newInstance("other_role", "other_feature", "search_term");
		actual_data = ds.get_data_for(dek);
		Assert.assertEquals("macbook", actual_data);
	}
	
	@Test public void for_a_invalid_key_returns_null() {
		DataElementKey dek = null;
		String actual_data = null;
		
		dek = DataElementKey.newInstance("no_role", "no_feature", "no_data");
		actual_data = ds.get_data_for(dek);
		Assert.assertEquals(null, actual_data);
	}
}