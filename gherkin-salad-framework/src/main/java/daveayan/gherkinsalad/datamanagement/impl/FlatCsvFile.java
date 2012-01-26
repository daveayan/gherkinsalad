package daveayan.gherkinsalad.datamanagement.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import daveayan.gherkinsalad.datamanagement.DataElementKey;
import daveayan.gherkinsalad.datamanagement.DataSource;

public class FlatCsvFile implements DataSource {
	public String get_data_for(DataElementKey arg0) {
		return data.getProperty(arg0.key());
	}
	public void load_up_from_file_at_location(String arg0) {
		try {
			File data_file = new File(arg0);
			List<String> lines = FileUtils.readLines(data_file);
			if(lines != null) {
				Iterator<String> iter = lines.iterator();
				while(iter.hasNext()) {
					String line = iter.next();
					if(! empty_row(line)) {
						add_to_properties(line);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	private void add_to_properties(String line) {
		String[] line_items = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
		String key = line_items[0].trim() + "," + line_items[1].trim() + "," + line_items[2].trim();
		String value = line_items[3].trim();
		data.put(key, value);
	}
	private boolean empty_row(String line) {
		String[] line_items = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
		if(line_items[0] == null || line_items[0].trim().length() == 0) {
			return true;
		}
		return false;
	}
	private Properties data = new Properties();
}