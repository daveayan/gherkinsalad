package daveayan.gherkinsalad.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import daveayan.gherkinsalad.ArchiveExecution;

/**
 * Applies the execution plan to the current project
 * @goal archive-execution-results
 */
public class ArchiveExecutionResults extends AbstractMojo {
	public void execute() throws MojoExecutionException, MojoFailureException {
		ArchiveExecution ae = new ArchiveExecution();
		ae.archive();
	}
}