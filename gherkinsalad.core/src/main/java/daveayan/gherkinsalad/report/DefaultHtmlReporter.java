package daveayan.gherkinsalad.report;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.io.IOUtils;

import daveayan.gherkinsalad.Config;
import daveayan.gherkinsalad.Path;

public class DefaultHtmlReporter implements Reporter {
	private static Log log = LogFactory.getLog(DefaultHtmlReporter.class);
	private static DateFormat df = DateFormat.getDateTimeInstance();
	private static DateFormat df_for_file = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
	private static boolean feature_already_started = Boolean.FALSE;
	private static boolean scenario_already_started = Boolean.FALSE;
	private static boolean step_already_started = Boolean.FALSE;
	private static File report_folder = null;
	private static String report_file_name = "/index.html";
	
	private void close_step() {
		if(step_already_started) {
			report("</div></div>");
			step_already_started = Boolean.FALSE;
		}
	}
	
	private void close_scenario() {
		if(scenario_already_started) {
			report("</div></div>");
			scenario_already_started = Boolean.FALSE;
		}
	}
	
	private void close_feature() {
		if(feature_already_started) {
			report("</div></div>");
			feature_already_started = Boolean.FALSE;
		}
	}
	
	public void feature(String feature) {
		close_step();
		close_scenario();
		close_feature();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"feature\">";
		html += "\n<div class=\"title\">" + feature + " [ <span class=\"scenario-count\">0</span> scenario(s) ]<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
		report(html);
		feature_already_started = Boolean.TRUE;
	}
	
	public void scenario(String scenario) {
		close_step();
		close_scenario();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"scenario\">";
		html += "\n<div class=\"title\">" + scenario + "<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
		report(html);
		scenario_already_started = Boolean.TRUE;
	}
	
	public void given(String step_name) {
		step(step_name);
	}
	
	public void when(String step_name) {
		step(step_name);
	}
	
	public void then(String step_name) {
		step(step_name);
	}
	
	public void and(String step_name) {
		step(step_name);
	}
	
	public void ask(String task) {
		report(formatted_html("ask", task));
	}
	
	public void warn(String task) {
		report(formatted_html("warn", task));
	}
	
	public void task(String task) {
		report(formatted_html("task", task));
	}
	
	public void action(String action) {
		report(formatted_html("action", action));
	}
	
	public void error(String error) {
		report(formatted_html("action error", error));
	}
	
	public void info(String info) {
		report(formatted_html("info", info));
	}
	
	public void screenshot_taken(File screenshot_file, String file_name) {
		try {
			FileUtils.copyFile(screenshot_file, new File(report_folder + "/screenshots/" + file_name));
			screenshot_taken(file_name);
		} catch (IOException e) {
			error("Unable to take screenshot : " + Path.TO_SCREENSHOTS + file_name);
		}
	}
	
	public void screenshot_taken(String filename) {
		String html = StringUtils.EMPTY;
		html += "<div class=\"screenshot action\">" + "Screenshot taken <a class=\"image\" href=\"" + Path.TO_SCREENSHOTS_SUBFOLDER + filename + "\">" + filename +"</a> <span class=\"date\">" + current_date_time() + "</span></div>";
		report(html);
	}
	
	private String formatted_html(String id, String text) {
		String html = StringUtils.EMPTY;
		html += "<div class=\"" + id + "\">" + text + "<span class=\"date\">" + current_date_time() + "</span></div>";
		return html;
	}
	
	private void step(String step) {
		close_step();
		
		String html = StringUtils.EMPTY;
		html += "<div class=\"step\">";
		html += "\n<div class=\"title\">" + step + "<span class=\"date\">" + current_date_time() + "</span></div>";
		html += "\n<div class=\"content\">";
		report(html);
		step_already_started = Boolean.TRUE;
	}
	
	private void report(String text) {
		create_file_if_not_exists();
		File report_file = new File(report_folder + report_file_name);
		try {
			System.out.println("\n" + text);
			FileUtils.writeStringToFile(report_file, "\n" + text, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void create_file_if_not_exists() {
		report_folder();
		File report_file = new File(report_folder + report_file_name);
		if(report_file.exists()) return;
		stream_classpath_resource_to_file("/gherkinsaladreport-index.html", report_folder + report_file_name);
		stream_classpath_resource_to_file("/style.css", report_folder + "/style.css");
		stream_classpath_resource_to_file("/html5shiv.js", report_folder + "/html5shiv.js");
		stream_classpath_resource_to_file("/jquery-1.8.0.min.js", report_folder + "/jquery-1.8.0.min.js");
		stream_classpath_resource_to_file("/gherkinsaladreport.js", report_folder + "/gherkinsaladreport.js");
	}
	
	private void stream_classpath_resource_to_file(String path_to_classpath_resource, String file_to_write) {
		InputStream stream = DefaultHtmlReporter.class.getResourceAsStream(path_to_classpath_resource);
		try {
			String contents = IOUtils.readFully(stream);
			FileUtils.writeStringToFile(new File(file_to_write), contents);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String current_date_time_file_name() {
		return df_for_file.format(new Date());
	}
	
	private String current_date_time() {
		return df.format(new Date());
	}
	
	private void report_folder() {
		if(report_folder == null) {
			File folder = new File(Config.execution_results_storage_location);
			if(! folder.exists()) {
				folder.mkdirs();
			}
			String report_folder_name = Config.execution_results_storage_location + "/" + current_date_time_file_name() + "/";
			report_folder = new File(report_folder_name);
			report_folder.mkdirs();
			log.info("REPORTING FOLDER is " + folder.getAbsolutePath());
		}
	}
}