package daveayan.gherkinsalad.report;

import java.io.File;

import daveayan.gherkinsalad.report.Reporter;

public class UnitTestReportCapturer implements Reporter {
	public static StringBuffer buffer = new StringBuffer();
	
	public void feature(String feature_name) {
		buffer.append(feature_name);
	}

	public void scenario(String scenario_name) {
		buffer.append(scenario_name);
	}

	public void given(String step_name) {
		buffer.append(step_name);
	}

	public void when(String step_name) {
		buffer.append(step_name);
	}

	public void then(String step_name) {
		buffer.append(step_name);
	}

	public void and(String step_name) {
		buffer.append(step_name);
	}

	public void ask(String ask) {
		buffer.append(ask);
	}

	public void info(String info) {
		buffer.append(info);
	}

	public void warn(String warn) {
		buffer.append(warn);
	}

	public void task(String task) {
		buffer.append(task);
	}

	public void action(String action) {
		buffer.append(action);
	}

	public void error(String error) {
		buffer.append(error);
	}

	public void screenshot_taken(String filename) {
		buffer.append(filename);
	}

	public void screenshot_taken(File screenshot_file, String file_name) {
		buffer.append(screenshot_file + " - " + file_name);
	}
}