package daveayan.gherkinsalad.components;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.report.ReportFactory;

public class ReportingCheck {
	@Test public void test_reporting() {
		ReportFactory.reporter().feature("Feature 1");
			ReportFactory.reporter().scenario("Scenario 1");
				ReportFactory.reporter().given("Step 1");
					ReportFactory.reporter().task("Task 1");
						ReportFactory.reporter().action("Action 1");
						ReportFactory.reporter().info("Info 1");
						ReportFactory.reporter().screenshot_taken("screenshot 1");
					ReportFactory.reporter().task("Task 2");
				ReportFactory.reporter().when("Step 2");
			ReportFactory.reporter().scenario("Scenario 2");
				ReportFactory.reporter().then("Step 1");
					ReportFactory.reporter().task("Task 1");
						ReportFactory.reporter().action("Action 1");
						ReportFactory.reporter().screenshot_taken("screenshot 1");
						ReportFactory.reporter().error("Error 1");
				ReportFactory.reporter().given("Step 2");
		
		ReportFactory.reporter().feature("Feature 2");
			ReportFactory.reporter().scenario("Scenario 1");
				ReportFactory.reporter().when("Step 1");
				ReportFactory.reporter().then("Step 2");
			ReportFactory.reporter().scenario("Scenario 2");
				ReportFactory.reporter().when("Step 1");
				ReportFactory.reporter().then("Step 2");
	}
	
	@Before public void before() {
		File report_file = new File(Path.TO_TARGET + "report.html");
		report_file.delete();
	}
}