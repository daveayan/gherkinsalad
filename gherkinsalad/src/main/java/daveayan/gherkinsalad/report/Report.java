package daveayan.gherkinsalad.report;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.Path;

public class Report {
	private static Log log = LogFactory.getLog(Report.class);
	private static DateFormat df = DateFormat.getDateTimeInstance();
	private static boolean feature_already_started = Boolean.FALSE;
	private static boolean scenario_already_started = Boolean.FALSE;
	private static boolean step_already_started = Boolean.FALSE;
	private static File report_folder = null;
	
	private static void close_step() {
		if(step_already_started) {
			report("</div></div>");
			step_already_started = Boolean.FALSE;
		}
	}
	
	private static void close_scenario() {
		if(scenario_already_started) {
			report("</div></div>");
			scenario_already_started = Boolean.FALSE;
		}
	}
	
	private static void close_feature() {
		if(feature_already_started) {
			report("</div></div>");
			feature_already_started = Boolean.FALSE;
		}
	}
	
	public static void feature(String feature) {
		close_step();
		close_scenario();
		close_feature();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"feature\">";
		html += "\n<div class=\"title\">" + feature + "<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
		report(html);
		feature_already_started = Boolean.TRUE;
	}
	
	public static void scenario(String scenario) {
		close_step();
		close_scenario();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"scenario\">";
		html += "\n<div class=\"title\">" + scenario + "<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
		report(html);
		scenario_already_started = Boolean.TRUE;
	}
	
	public static void step(String step) {
		close_step();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"step\">";
		html += "\n<div class=\"title\">" + step + "<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
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
	
	public static void error(String error) {
		report(formatted_html("action error", error));
	}
	
	public static void info(String info) {
		report(formatted_html("info", info));
	}
	
	public static void screenshot_taken(File screenshot_file, String file_name) {
		try {
			FileUtils.copyFile(screenshot_file, new File(report_folder + "/screenshots/" + file_name));
			screenshot_taken(file_name);
		} catch (IOException e) {
			error("Unable to take screenshot : " + Path.TO_SCREENSHOTS + file_name);
		}
	}
	
	public static void screenshot_taken(String filename) {
		String html = StringUtils.EMPTY;
		html += "<div class=\"screenshot\">" + "Screenshot taken <a href=\"" + Path.TO_SCREENSHOTS_SUBFOLDER + filename + "\">" + filename +"</a> <span class=\"date\">" + current_date_time() + "</span></div>";
		report(html);
	}
	
	private static String formatted_html(String id, String text) {
		String html = StringUtils.EMPTY;
		html += "<div class=\"" + id + "\">" + text + "<span class=\"date\">" + current_date_time() + "</span></div>";
		return html;
	}
	
	private static void report(String text) {
		create_file_if_not_exists();
		File report_file = new File(report_folder + "/report.html");
		try {
			System.out.println("\n" + text);
			FileUtils.writeStringToFile(report_file, "\n" + text, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void create_file_if_not_exists() {
		report_folder();
		File report_file = new File(report_folder + "/report.html");
		if(report_file.exists()) return;
		try {
			String report_header = report_header();
			FileUtils.writeStringToFile(report_file, report_header, true);
			FileUtils.copyFileToDirectory(new File(Path.TO_REPORT_FILES + "style.css"), report_folder);
			FileUtils.copyFileToDirectory(new File(Path.TO_REPORT_FILES + "html5shiv.js"), report_folder);
			FileUtils.copyFileToDirectory(new File(Path.TO_REPORT_FILES + "jquery-1.8.0.min.js"), report_folder);
			FileUtils.copyFileToDirectory(new File(Path.TO_REPORT_FILES + "gherkinsaladreport.js"), report_folder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String report_header() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<!DOCTYPE HTML>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\">");
		sb.append("<!--[if lt IE 9]><script src=\"html5shiv.js\"></script><![endif]-->");
		sb.append("<script src=\"jquery-1.8.0.min.js\"></script>");
		sb.append("<script src=\"gherkinsaladreport.js\"></script>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<header><h1>Gherkin Salad Report</h1></header>");
		sb.append("<div id=\"mainActions\">");
		sb.append("<a id=\"expandAll\" href=\"#\">Expand All</a>");
		sb.append("<a id=\"collapseAll\" href=\"#\">Collapse All</a>");
		sb.append("<a id=\"failedScenarios\" href=\"#\">Failed Scenarios</a>");
		sb.append("<a id=\"warningScenarios\" href=\"#\">Warning Scenarios</a>");
		sb.append("<a id=\"showAll\" href=\"#\">Show All</a>");
		sb.append("</div>");
		sb.append("<div class=\"clear\"></div>");
		
		return sb.toString();
	}
	
	private static String current_date_time() {
		return df.format(new Date());
	}
	
	private static void report_folder() {
		if(report_folder == null) {
			File folder = new File(Config.execution_results_storage_location);
			if(! folder.exists()) {
				folder.mkdirs();
			}
			String report_folder_name = Config.execution_results_storage_location + "/" + System.currentTimeMillis() + "/";
			report_folder = new File(report_folder_name);
			report_folder.mkdirs();
			log.info("REPORTING FOLDER is " + folder.getAbsolutePath());
		}
	}
}