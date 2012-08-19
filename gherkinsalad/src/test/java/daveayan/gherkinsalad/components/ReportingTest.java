package daveayan.gherkinsalad.components;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import daveayan.gherkinsalad.Path;
import daveayan.gherkinsalad.report.Report;

public class ReportingTest {
	@Test public void test_reporting() {
		Report.feature("Some Feature");
		Report.scenario("Scenario 1");
		Report.step("Given some given in scenario 1");
		Report.action("Clicked Something 1");
		Report.action("Clicked Something 1");
		Report.action("Entered Something in Something");
		Report.step("And some given in scenario 1");
		Report.action("Selected Something 1");
		Report.action("Entered Something in Something");
		Report.step("When some when in scenario 1");
		Report.action("Clicked Something 1");
		Report.step("Then some then in scenario 1");
		Report.action("Something exists 1");
		Report.action("Something exists 1");
		Report.step("And some then in scenario 1");
		Report.action("Something exists 1");
		Report.action("Something exists 1");
		Report.action("Something exists 1");
		Report.warn("Did not verify this 1");
		Report.step("And some more then in scenario 1");
		Report.action("Something exists 1");
		Report.warn("Did not verify this 1");
		Report.warn("Did not verify this 1");
		Report.error("Something is not right 1");
		
		Report.scenario("Scenario 2");
		Report.step("Given some given in scenario 2");
		Report.step("And some given in scenario 2");
		Report.step("When some when in scenario 2");
		Report.step("Then some then in scenario 2");
		Report.step("And some then in scenario 2");
		Report.step("And some more then in scenario 2");
		
		Report.scenario("Scenario 3");
		Report.step("Given some given in scenario 3");
		Report.step("And some given in scenario 3");
		Report.step("When some when in scenario 3");
		Report.step("Then some then in scenario 3");
		Report.step("And some then in scenario 3");
		Report.step("And some more then in scenario 3");
	}
	
	@Before public void before() {
		File report_file = new File(Path.TO_TARGET + "report.html");
		report_file.delete();
	}
}