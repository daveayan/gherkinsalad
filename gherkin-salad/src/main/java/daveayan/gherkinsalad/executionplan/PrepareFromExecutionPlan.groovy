package daveayan.gherkinsalad.executionplan

import groovy.io.FileType

class PrepareFromExecutionPlan {
	public static void main(String[] args) {
		def object = new PrepareFromExecutionPlan()
		object.enhance_groovy()
		object.clean_and_prepare()
		object.process_execution_plan()
	}
	
	def process_execution_plan() {
		def atdd_execution_plan = System.getenv("ATDD_EXECUTION_PLAN")
		def execution_plan = new File(atdd_execution_plan).text
		
		def plan_items = execution_plan.split("Run")
		plan_items.each { plan_item ->
			if(! plan_item.isAllWhitespace()) {
				plan_item = plan_item.replace("With Browser", "~")
				plan_item = plan_item.replace("Page Structure", "~")
				plan_item = plan_item.replace("Url", "~")
				def items = plan_item.split("~")
				
				def top_scenario_text = get_top_scenario_text(items)
				def bottom_scenario_text = "Scenario: Close Browser\n"
				bottom_scenario_text += "\t\tThen User closes the browser window"
				
				process_feature_file(items[0].trim(), top_scenario_text, bottom_scenario_text)
			}
		}
	}
	
	def get_top_scenario_text(items) {
		if(items.length == 4) {
			def scenario_text = "Scenario: Prepare browser and test data"
			scenario_text += "\n\t\tGiven User launched the " + items[1].trim() + " browser"
			scenario_text += " with page structure file " + items[2].trim()
			scenario_text += "\n\t\tAnd User visited website " + items[3].trim()
			return scenario_text
		} 
		if(items.length == 3) {
			def scenario_text = "Scenario: Prepare browser and test data"
			scenario_text += "\n\t\tGiven User launched the " + items[1].trim() + " browser"
			scenario_text += "\n\t\tAnd User visited website " + items[2].trim()
			return scenario_text
		}
		return null;
	}
	
	def clean_and_prepare() {
		def features_folder = new File("./features")
		if(features_folder.exists()) {
			features_folder.deleteDir()
		}
		features_folder.mkdir()
	}
	
//	def prepare_from_execution_plan() {
//		def dir = new File('./src/test/resources/features')
//		dir.eachFileRecurse (FileType.FILES) { file ->
//			if(file.is_a_feature_file()) {
//				process_feature_file file
//			}
//		}
//	}
	
	def process_feature_file(feature_file_name, def top_scenario_text, bottom_scenario_text) {
		def feature_file = new File('./src/test/resources/features/' + feature_file_name)
		def processed_content = feature_file.text.replace("#EXECUTION PLAN INSTRUCTION AT TOP", top_scenario_text);
		processed_content = processed_content.replace("#EXECUTION PLAN INSTRUCTION AT BOTTOM", bottom_scenario_text);
		
		def new_file_name = feature_file.name.replace(".feature", "_" + System.currentTimeMillis() + ".feature");
		
		def processed_feature_file = new File("./features/" + new_file_name)
		processed_feature_file.createNewFile()
		processed_feature_file.write(processed_content);
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