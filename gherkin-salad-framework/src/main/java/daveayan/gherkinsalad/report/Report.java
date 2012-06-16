package daveayan.gherkinsalad.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Report {
	public static void action(String action) {
		report(action);
	}
	
	public static void screenshot_taken(String filename) {
		report("Screen shot taken at '" + filename + "'");
	}
	
	private static void report(String text) {
		File report_file = new File("report.txt");
		try {
			FileUtils.writeStringToFile(report_file, "\n" + text, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}