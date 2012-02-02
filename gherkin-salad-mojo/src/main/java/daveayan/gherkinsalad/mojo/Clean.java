package daveayan.gherkinsalad.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.FileUtils;

/**
 * Applies the execution plan to the current project
 * @goal gherkin-salad-clean
 */
public class Clean extends AbstractMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("#########################################################");
		System.out.println("# GHERKIN SALAD"); 
		System.out.println("# Cleaning features folder");
		System.out.println("#########################################################");
		File features_folder = new File("./features");
		try {
			FileUtils.deleteDirectory(features_folder);
			FileUtils.forceMkdir(features_folder);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}