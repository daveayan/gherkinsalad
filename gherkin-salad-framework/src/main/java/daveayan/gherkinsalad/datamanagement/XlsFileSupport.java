package daveayan.gherkinsalad.datamanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class XlsFileSupport {
	Workbook workbook = null;
	Sheet current_sheet = null;
	
	public boolean file_does_not_exists() {
		return ! file_exists();
	}
	
	public boolean file_exists() {
		return workbook != null;
	}
	
	public boolean sheet_does_not_exists() {
		return ! sheet_exists();
	}
	
	public boolean sheet_exists() {
		return current_sheet != null;
	}
	
	public String value_in_cell(String column_row) {
		String[] column_and_row = column_row.split("_");
		int column_number = column_number_from_name(column_and_row[0]);
		int row_number = Integer.parseInt(column_and_row[1]);
		return value_in_cell(column_number, row_number);
	}
	
	public String value_in_cell(int column_number, int row_number) {
		Row row = current_sheet.getRow(row_number);
		Cell cell = row.getCell(column_number);
		if(cell != null)
			return cell.getStringCellValue();
		return StringUtils.EMPTY;
	}
	
	public void switch_to_sheet(String sheet_name) {
		current_sheet = workbook.getSheet(sheet_name);
	}
	
	public static XlsFileSupport openXlsFile(String file_absolute_path) {
		XlsFileSupport support = new XlsFileSupport();
		InputStream inp;
		try {
			inp = new FileInputStream(file_absolute_path);
			support.workbook = new HSSFWorkbook(inp);
		} catch (FileNotFoundException e) {
			support.workbook = null;
		} catch (IOException e) {
			support.workbook = null;
		}
		return support;
	}
	
	private int column_number_from_name (String name) {
		int column_number = 0;
		
		if(name == null || name.trim().equals("")) 
			return column_number;
		char[] chars = name.toCharArray(); 
		for(int i = 0; i < chars.length ; i++) {
			int multiplier = chars.length - 1 - i;
			int char_abs_value = chars[i] - 'A' + 1;
			column_number += char_abs_value * (Math.pow(26, multiplier));
		}
		
		return column_number - 1;
	}
	
	private XlsFileSupport() {}
}