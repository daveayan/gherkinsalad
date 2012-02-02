package daveayan.gherkinsalad.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import daveayan.gherkinsalad.executionplan.PrepareFeaturesFromExecutionPlan;

/**
 * Applies the execution plan to the current project
 * @goal apply-execution-plan
 */
public class ApplyExecutionPlanMojo extends AbstractMojo {
	/**@parameter expression="${apply-execution-plan.planfiles}" */
	private String planfiles;
	public void execute() throws MojoExecutionException
  {
		PrepareFeaturesFromExecutionPlan plan = new PrepareFeaturesFromExecutionPlan();
		String[] planfilenames = planfiles.split(",");
		for(String filename: planfilenames) {
			plan.run(filename);
		}
  }
}