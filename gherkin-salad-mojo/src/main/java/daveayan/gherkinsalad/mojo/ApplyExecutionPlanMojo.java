package daveayan.gherkinsalad.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import daveayan.gherkinsalad.executionplan.PrepareFromExecutionPlan;

/**
 * Applies the execution plan to the current project
 * @goal apply-execution-plan
 */
public class ApplyExecutionPlanMojo extends AbstractMojo {
	public void execute() throws MojoExecutionException
  {
		PrepareFromExecutionPlan plan = new PrepareFromExecutionPlan();
		plan.run();
  }
}