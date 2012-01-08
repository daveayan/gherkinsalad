package daveayan.gherkinsalad.driver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import daveayan.gherkinsalad.datamanagement.DataElementKey;
import daveayan.gherkinsalad.datamanagement.DataSource;

public class AmazonDataDriver implements DataSource {
	private String absolute_file_path;

	public void load_up_from_file_at_location(String absolute_file_path) {
		this.absolute_file_path = absolute_file_path;
	}

	public String get_data_for(DataElementKey data_element_key) {
		int column_number = column_number_for(data_element_key.role());
		int row_number = row_number_for(data_element_key.symbolic_data_name());
		return data_at(row_number, column_number);
	}

	private String data_at(int row_number, int column_number) {
		try {
			List<String> lines = FileUtils.readLines(new File(absolute_file_path));
			String line = lines.get(row_number);
			String[] elements = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
			return elements[column_number];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}

	private int row_number_for(String symbolic_data) {
		try {
			List<String> lines = FileUtils.readLines(new File(absolute_file_path));
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				String[] elements = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",");
				if (StringUtils.equalsIgnoreCase(elements[0].trim(), symbolic_data)) {
					return i;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private int column_number_for(String text) {
		try {
			List<String> lines = FileUtils.readLines(new File(absolute_file_path));
			String role_line = lines.get(0);
			String[] roles = StringUtils.splitByWholeSeparatorPreserveAllTokens(role_line, ",");
			for (int i = 0; i < roles.length; i++) {
				String role = roles[i];
				if (StringUtils.equalsIgnoreCase(text.trim(), role.trim())) {
					return i;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}