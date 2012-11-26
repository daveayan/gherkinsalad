package daveayan.gherkinsalad.report;

import java.io.File;

import daveayan.gherkinsalad.report.Reporter;

public class UnitTestReportCapturer implements Reporter {
	public static StringBuffer buffer = new StringBuffer();
	
	public void feature(String feature_name) {
//		buffer.append("\n" + feature_name);
	}

	public void scenario(String scenario_name) {
//		buffer.append("\n" + scenario_name);
	}

	public void given(String step_name) {
//		buffer.append("\n" + step_name);
	}

	public void when(String step_name) {
//		buffer.append("\n" + step_name);
	}

	public void then(String step_name) {
//		buffer.append("\n" + step_name);
	}

	public void and(String step_name) {
//		buffer.append("\n" + step_name);
	}

	public void ask(String ask) {
//		buffer.append("\n" + ask);
	}

	public void info(String info) {
//		buffer.append("\n" + info);
	}

	public void warn(String warn) {
//		buffer.append("\n" + warn);
	}

	public void task(String task) {
//		buffer.append("\n" + task);
	}

	public void action(String action) {
		buffer.append("\n" + action);
	}

	public void error(String error) {
		buffer.append("\n" + error);
	}

	public void screenshot_taken(String filename) {
//		buffer.append("\n" + filename);
	}

	public void screenshot_taken(File screenshot_file, String file_name) {
//		buffer.append("\n" + screenshot_file + " - " + file_name);
	}
}