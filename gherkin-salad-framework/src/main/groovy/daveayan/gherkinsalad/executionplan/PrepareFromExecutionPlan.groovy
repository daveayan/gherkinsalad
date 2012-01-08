package daveayan.gherkinsalad.executionplan


public class PrepareFromExecutionPlan {
	public static void main(String[] args) {
		println "gherkin-salad -> Preparing features from Execution Plan"
		def object = new PrepareFromExecutionPlan()
		object.clean_and_prepare()
		println args
		if(args.length != 1) {
			println "Please pass in the execution plan file"
			return
		}
		object.run(args[0])
	}
	
	def run(atdd_execution_plan) {
		println "-------------------------------------------------------"
		println "Gherkin Salad"
		println "-------------------------------------------------------"
		println ""
		enhance_groovy()
		clean_and_prepare()
		process_execution_plan(atdd_execution_plan)
		println ""
	}
	
	def process_execution_plan(atdd_execution_plan) {
		def execution_plan_file = new File(atdd_execution_plan)
		println "--> Applying execution plan '${execution_plan_file.absolutePath}' "
		if(! execution_plan_file.exists()) {
			println "--> Execution file does not exist !"
			return
		}
		def execution_plan = execution_plan_file.text
		def browser_and_features = execution_plan.split("Specifics")
		def browser_name = browser_and_features[0].split(":")[1]
		
		def plan_items = browser_and_features[1].split("Run")
		plan_items.each { plan_item ->
			if(! plan_item.isAllWhitespace()) {
				plan_item = plan_item.replace("Url", "~")
				plan_item = plan_item.replace("Data File", "~")
				def items = plan_item.split("~")
				
				def top_scenario_text = get_top_scenario_text(browser_name, items)
				def bottom_scenario_text = "Scenario: Close Browser\n"
				bottom_scenario_text += "\t\tThen User closes the browser window"
				
				process_feature_file(items[0].trim(), top_scenario_text, bottom_scenario_text)
			}
		}
	}
	
	def get_top_scenario_text(browser_name, items) {
		def scenario_text = "Scenario: Prepare browser and test data"
		scenario_text += "\n\t\tGiven User launched the " + browser_name.trim() + " browser"
		scenario_text += "\n\t\tAnd User visited website " + items[1].trim()
		if(items.length > 2) scenario_text += "\n\t\tAnd Data Management file is " + items[2].trim()
		return scenario_text
	}
	
	def clean_and_prepare() {
		def features_folder = new File("./features")
		if(features_folder.exists()) {
			features_folder.deleteDir()
		}
		features_folder.mkdir()
	}
	
	def process_feature_file(feature_file_name, def top_scenario_text, bottom_scenario_text) {
		def feature_file = new File('./src/test/resources/features/' + feature_file_name)
		if(feature_file.exists()) {
			println "--> Processing Feature File '${feature_file.absolutePath}'"
			def processed_content = feature_file.text.replace("#EXECUTION PLAN INSTRUCTION AT TOP", top_scenario_text);
			processed_content = processed_content.replace("#EXECUTION PLAN INSTRUCTION AT BOTTOM", bottom_scenario_text);
			
			def new_file_name = feature_file.name.replace(".feature", "_" + System.currentTimeMillis() + ".feature");
			
			def processed_feature_file = new File("./features/" + new_file_name)
			processed_feature_file.createNewFile()
			processed_feature_file.write(processed_content);
		} else {
			println "--> File referred to by execution plan does not exist '${feature_file.absolutePath}'"
		}
	}
	
	def enhance_groovy() {
		enhance_string()
		enhance_file()
	}
	def enhance_file() {
		File.metaClass.is_a_feature_file = { ->
			return delegate.toString().endsWith('.feature')
		}
	}
	def enhance_string() {
		String.metaClass.is_blank = { ->
			return delegate.trim().length() == 0
		}
		String.metaClass.is_not_blank = { ->
			return delegate.trim().length() > 0
		}
		String.metaClass.is_a_feature_line = { ->
			return delegate.is_not_blank() && delegate.trim().startsWith('Feature:')
		}
		String.metaClass.is_a_scenario_line = { ->
			return delegate.is_not_blank() && delegate.trim().startsWith('Scenario:')
		}
		String.metaClass.is_a_scenario_line = { ->
			return delegate.is_not_blank() && delegate.trim().startsWith('Scenario:')
		}
	}
}