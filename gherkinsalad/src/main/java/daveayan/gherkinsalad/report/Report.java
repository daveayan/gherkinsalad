package daveayan.gherkinsalad.report;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import daveayan.gherkinsalad.Path;

public class Report {
	
	public static void goal(String goal) {
		report(goal);
	}
	
	public static void ask(String task) {
		report("? " + task);
	}
	
	public static void warn(String task) {
		report("! " + task);
	}
	
	public static void task(String task) {
		report("- " + task);
	}
	
	public static void action(String action) {
		report("-- " + action);
	}
	
	public static void error(String action) {
		report("EE " + action);
	}
	
	public static void screenshot_taken(String filename) {
		action("Screen shot taken at '" + filename + "'");
	}
	
	private static void report(String text) {
		DateFormat df = DateFormat.getDateTimeInstance();
		String datetime = df.format(new Date());
		File report_file = new File(Path.TO_TARGET + "report.txt");
		try {
			FileUtils.writeStringToFile(report_file, "\n" + datetime + " -> " + text, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}