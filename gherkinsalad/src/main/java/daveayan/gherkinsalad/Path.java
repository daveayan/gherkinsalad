package daveayan.gherkinsalad;

import java.io.File;

public class Path {
	public static final String TO_REPORT_FILES = "./src/main/resources/";
	public static final String TO_M2_TEST_RESOURCES 	= "./src/test/resources/";
	
	public static final String TO_TEST_RESOURCES 	= "./resources/";
	public static final String TO_SYSTEM_RESOURCES = TO_M2_TEST_RESOURCES + "system/";

	public static final String TO_TARGET 					= "./target/";
	public static final String TO_SCREENSHOTS 		= TO_TARGET + "screenshots/";
	
	public static final File TO_TARGET_FOLDER 					= new File(TO_TARGET);
}