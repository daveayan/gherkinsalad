package daveayan.gherkinsalad.report;

import daveayan.gherkinsalad.Config;
import daveayan.mirage.ReflectionUtils;

public class ReportFactory {
	private static ReportFactory factory = new ReportFactory();
	private Reporter reporter = null;
	
	public static Reporter reporter() {
		return factory.getReporter();
	}
	
	private Reporter getReporter() {
		if(reporter == null) {
			reporter = (Reporter) ReflectionUtils.getInstanceOfClass(ReflectionUtils.asClass(Config.reporter_class));
		}
		return reporter;
	}
	
	private ReportFactory() {}
}