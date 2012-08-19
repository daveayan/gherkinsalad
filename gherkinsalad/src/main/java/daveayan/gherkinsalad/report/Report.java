package daveayan.gherkinsalad.report;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import daveayan.gherkinsalad.Path;

public class Report {
	private static DateFormat df = DateFormat.getDateTimeInstance();
	private static boolean feature_already_started = Boolean.FALSE;
	private static boolean scenario_already_started = Boolean.FALSE;
	private static boolean step_already_started = Boolean.FALSE;
	
	public static void feature(String feature) {
		String html = StringUtils.EMPTY;
		if(feature_already_started) {
			html = "</div>";
		}
		html += "<br/><div id=\"" + "feature" + "\">" + current_date_time() + " " + feature;
		report(html);
		feature_already_started = Boolean.TRUE;
	}
	
	public static void scenario(String scenario) {
		String html = StringUtils.EMPTY;
		if(scenario_already_started) {
			html = "</div>";
		}
		html += "<br/><div id=\"" + "scenario" + "\">" + current_date_time() + " " + scenario;
		report(html);
		scenario_already_started = Boolean.TRUE;
	}
	
	public static void step(String step) {
		String html = StringUtils.EMPTY;
		if(step_already_started) {
			html = "</div>";
		}
		html += "<div id=\"" + "step" + "\">" + current_date_time() + " " + step;
		report(html);
		step_already_started = Boolean.TRUE;
	}
	
	public static void ask(String task) {
		report(formatted_html("ask", task));
	}
	
	public static void warn(String task) {
		report(formatted_html("warn", task));
	}
	
	public static void task(String task) {
		report(formatted_html("task", task));
	}
	
	public static void action(String action) {
		report(formatted_html("action", action));
	}
	
	public static void error(String action) {
		report(formatted_html("error", action));
	}
	
	public static void screenshot_taken(String filename) {
		String html = "<div id=\"screenshot\">" + current_date_time() + " Screenshot taken <a href=\"" + Path.TO_SCREENSHOTS_SUBFOLDER + filename + "\">" + filename +"</a></div>";
		report(html);
	}
	
	private static String formatted_html(String id, String text) {
		return "<div id=\"" + id + "\">" + current_date_time() + " " + text + "</div>";
	}
	
	private static void report(String text) {
		create_file_if_not_exists();
		File report_file = new File(Path.TO_TARGET + "report.html");
		try {
			FileUtils.writeStringToFile(report_file, "\n" + text, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void create_file_if_not_exists() {
		File report_file = new File(Path.TO_TARGET + "report.html");
		if(report_file.exists()) return;
		try {
			FileUtils.writeStringToFile(report_file, report_header(), true);
			File css_file = new File(Path.TO_REPORT_FILES + "style.css");
			FileUtils.copyFileToDirectory(css_file, Path.TO_TARGET_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String report_header() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html><head><link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\"></head><body>");
		return sb.toString();
	}
	
	private static String current_date_time() {
		return df.format(new Date());
	}
}