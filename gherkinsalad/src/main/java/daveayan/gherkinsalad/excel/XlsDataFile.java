package daveayan.gherkinsalad.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import daveayan.gherkinsalad.BaseAutomationObject;
import daveayan.gherkinsalad.report.Report;

public class XlsDataFile extends XlsFile {
	private static Log log = LogFactory.getLog(BaseAutomationObject.class);
	private Map<String, String> data = new HashMap<String, String>();
	
	public static XlsDataFile openXlsFile(String file_absolute_path) {
		Report.action("Opening XLS file '" + file_absolute_path + "'");
		XlsDataFile support = new XlsDataFile();
		InputStream inp;
		try {
			inp = new FileInputStream(file_absolute_path);
			support.workbook = new HSSFWorkbook(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			support.workbook = null;
		} catch (IOException e) {
			e.printStackTrace();
			support.workbook = null;
		}
		return support;
	}
	
	public String get_data_for_role_key(String role, String key) {
		String value = data.get(role.toUpperCase() + "_" + key);
		value = StringUtils.trimToEmpty(value);
		Report.action("Data for role '" + role + "', key '" + key + "' is '" + value + "'");
		return value;
	}
	
	public void switch_to_sheet(String sheet_name) {
		Report.action("Switching to sheet '" + sheet_name + "'");
		current_sheet = workbook.getSheet(sheet_name);
		load_up_data();
	}
	
	private void load_up_data() {
		for(int row = 0; row < current_sheet.getLastRowNum(); row++) {
			String role = value_in_cell("A_" + row);
			String key = value_in_cell("B_" + row);
			String value = value_in_cell("C_" + row);
			
			if(StringUtils.isNotEmpty(role)) {
				data.put(role + "_" + key, value);
			}
		}
	}
	
	protected XlsDataFile() {}
}