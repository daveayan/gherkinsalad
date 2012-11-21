package daveayan.gherkinsalad.components;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.report.Report;

public class ReportingTest {
	@Test public void test_reporting() {
		Report.feature("Feature 1");
			Report.scenario("Scenario 1");
				Report.step("Step 1");
					Report.task("Task 1");
						Report.action("Action 1");
						Report.info("Info 1");
						Report.screenshot_taken("screenshot 1");
					Report.task("Task 2");
				Report.step("Step 2");
			Report.scenario("Scenario 2");
				Report.step("Step 1");
					Report.task("Task 1");
						Report.action("Action 1");
						Report.screenshot_taken("screenshot 1");
						Report.error("Error 1");
				Report.step("Step 2");
		
		Report.feature("Feature 2");
			Report.scenario("Scenario 1");
				Report.step("Step 1");
				Report.step("Step 2");
			Report.scenario("Scenario 2");
				Report.step("Step 1");
				Report.step("Step 2");
	}
	
	@Before public void before() {
		File report_file = new File(Path.TO_TARGET + "report.html");
		report_file.delete();
	}
}