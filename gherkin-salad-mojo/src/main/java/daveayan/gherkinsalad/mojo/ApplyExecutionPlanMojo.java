package daveayan.gherkinsalad.mojo;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import daveayan.gherkinsalad.executionplan.PrepareFromExecutionPlan;

/**
 * Applies the execution plan to the current project
 * @goal apply-execution-plan
 */
public class ApplyExecutionPlanMojo extends AbstractMojo {
	/**@parameter expression="${apply-execution-plan.planfilename}" */
	private String planfilename;
	/**@parameter expression="${apply-execution-plan.planfilepath}" */
	private String planfilepath;
	public void execute() throws MojoExecutionException
  {
		PrepareFromExecutionPlan plan = new PrepareFromExecutionPlan();
		if(StringUtils.isNotBlank(planfilepath)) {
			plan.run(planfilepath);
		} else if(StringUtils.isNotBlank(planfilename)) {
			plan.run("./src/test/resources/executionplan/" + planfilename);
		}
  }
}