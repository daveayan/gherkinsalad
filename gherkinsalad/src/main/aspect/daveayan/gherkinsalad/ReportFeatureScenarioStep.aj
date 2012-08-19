package daveayan.gherkinsalad;


import gherkin.formatter.model.Step;

import org.junit.runners.ParentRunner;

import cucumber.runtime.StepDefinitionMatch;
import daveayan.gherkinsalad.report.Report;
import daveayan.mirage.ReflectionUtils;

public aspect ReportFeatureScenarioStep {
	pointcut running_feature() : execution(public * cucumber.junit.FeatureRunner.run(..));
	pointcut running_scenario() : execution(public * cucumber.junit.ExecutionUnitRunner.run(..));
	pointcut running_step() : execution(public * cucumber.junit.JUnitReporter.match(..));
	
// Not used. These are here for reference only
//	pointcut completing_feature() : execution(public * cucumber.junit.FeatureRunner.run(..));
//	pointcut completing_scenario() : execution(public * cucumber.junit.ExecutionUnitRunner.run(..));
//	pointcut completing_step() : execution(public * cucumber.junit.JUnitReporter.result(..));
	
	before() : running_feature() {
		ParentRunner pr = (ParentRunner) thisJoinPoint.getTarget();
		Report.feature(pr.getDescription().toString());
	}
	
	before() : running_scenario() {
		ParentRunner pr = (ParentRunner) thisJoinPoint.getTarget();
		Report.scenario(pr.getDescription().toString());
	}
	
	before() : running_step() {
		StepDefinitionMatch m = (StepDefinitionMatch) thisJoinPoint.getArgs()[0];
		Step step = (Step) ReflectionUtils.getFieldInObject(m, "step");
		Report.step(step.getKeyword() + " " + m.getStepName());
	}
	
// Not used. These are here for reference only	
//	after() : completing_feature() {
//		ParentRunner pr = (ParentRunner) thisJoinPoint.getTarget();
//		System.out.println("AFTER FEATURE : -->" + pr.getDescription() + "<--");
//	}
//	
//	after() : completing_scenario() {
//		ParentRunner pr = (ParentRunner) thisJoinPoint.getTarget();
//		System.out.println("AFTER SCENARIO : -->" + pr.getDescription() + "<--");
//	}
//	
//	after() : completing_step() {
//		System.out.println("AFTER STEP ");
//	}
}