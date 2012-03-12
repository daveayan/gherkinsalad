package daveayan.gherkinsalad.executionplan

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import daveayan.gherkinsalad.Config
import daveayan.gherkinsalad.Path
import daveayan.gherkinsalad.datamanagement.XlsFileSupport

class PrepareFeaturesFromExecutionPlan {
	private static Log log = LogFactory.getLog(PrepareFeaturesFromExecutionPlan.class)
	private static int count = 1;
	public static void main(String[] args) {
		PrepareFeaturesFromExecutionPlan object = new PrepareFeaturesFromExecutionPlan()
		object.run("networks.atdd.qa.csv")
	}
	
	def run(file_name) {
		if(file_name.endsWith(".csv")) {
			process_execution_plan_csv(file_name)
		} else {
			process_execution_plan_xls(file_name)
		}
	}
	
	def process_execution_plan_xls(file_name) {
		String[] file_name_parts = file_name.split("-in-")
		println """
#########################################################
# GHERKIN SALAD
# Applying execution plan
# ${file_name_parts[0]} in ${file_name_parts[1]}
#########################################################
"""
		XlsFileSupport support = XlsFileSupport.openXlsFile(Path.TO_EXECUTION_PLAN + file_name_parts[0])
		if(support.file_exists()) {
			support.switch_to_sheet(file_name_parts[1])
			if(support.sheet_exists()) {
				int row_number = 0;
				while(true) {
					String feature_file_name 		= support.value_in_cell("A_" + row_number)
					String browser 							= support.value_in_cell("B_" + row_number)
					String page_structure 			= support.value_in_cell("C_" + row_number)
					String env 									= support.value_in_cell("D_" + row_number)
					String data_file 						= support.value_in_cell("E_" + row_number)
					if(feature_file_name.contains("QUEUE UP")) { 
						break
					}
					apply_execution_plan_line_item(feature_file_name, "default", browser, page_structure, env, data_file, "DEFAULT")
					row_number ++
				}
			} else {
			println "Skipping file ${file_name_parts[0]} in environment ${file_name_parts[1]} as the sheet does not exist"
			}
		} else {
			println "Skipping file ${file_name_parts[0]} as it does not exist"
		}
	}
	
	def process_execution_plan_csv(file_name) {
		println """
#########################################################
# GHERKIN SALAD
# Applying execution plan
# ${file_name}
#########################################################
"""
		def execution_plan_file = new File(Path.TO_EXECUTION_PLAN + file_name)
		if(execution_plan_file.exists()) {
			execution_plan_file.eachLine { line ->
				if(! line.startsWith("feature file")) {
					def line_items = line.split(",")
					apply_execution_plan_line_item(line_items[0], line_items[1], line_items[2], line_items[3], line_items[4], line_items[5], line_items[6])
				}
			}
		} else {
			println "Skipping file ${execution_plan_file} as it does not exist"
		}
	}
	
	def apply_execution_plan_line_item(feature_file, feature_name, browser_name, page_structure_file_name, env_name, data_file_name, data_source_driver) {
		if(allow_browser_execution(browser_name)) {
			def top_scenario_text = top_scenario_text(feature_name, browser_name, page_structure_file_name, env_name, data_file_name, data_source_driver)
			def bottom_scenario_text = bottom_scenario_text()
			process_feature_file(feature_file, top_scenario_text, bottom_scenario_text)
		} else {
			println "Skipping ${browser_name} execution of feature ${feature_file}"
		}
	}
	
	def allow_browser_execution(browser) {
		if(browser.trim().equalsIgnoreCase("ie") && Config.ie_enabled) {
			return true
		}
		if(browser.trim().equalsIgnoreCase("chrome") && Config.chrome_enabled) {
			return true
		}
		if(browser.trim().equalsIgnoreCase("firefox") && Config.firefox_enabled) {
			return true
		}
		return false
	}
	
	def process_feature_file(feature_file_name, top_scenario_text, bottom_scenario_text) {
		def feature_file = new File(Path.TO_FEATURES + feature_file_name)
		if(feature_file.exists()) {
			println "--> Processing '${feature_file.absolutePath}'"
			def processed_content = feature_file.text.replace("#EXECUTION PLAN INSTRUCTION AT TOP", top_scenario_text);
			processed_content = processed_content.replace("#EXECUTION PLAN INSTRUCTION AT BOTTOM", bottom_scenario_text);
			
			def file_relative_path_and_name = feature_file_name.toString()
			def new_file_name = get_new_file_name(file_relative_path_and_name)

			def processed_feature_file = new File("./features/" + new_file_name)
			println "       Created '${processed_feature_file}'" + processed_feature_file
			processed_feature_file.createNewFile()
			processed_feature_file.write(processed_content);
		} else {
			println "--> File referred to by execution plan does not exist '${feature_file.absolutePath}'"
		}
	}
	
	def get_new_file_name(file_relative_path_and_name) {
		def file_name = null
		def path_elements = file_relative_path_and_name.split("/")
		path_elements.each {
			if(it.endsWith(".feature")) {
				file_name = it
			}
		}
		
		def padded_count = count.toString().padLeft(4, "0")		
		def new_file_name = padded_count + "_" + file_name
		count = count + 1

		return new_file_name
	}
	
	def create_intermediate_folders(file_relative_path_and_name) {
		def path_elements = file_relative_path_and_name.split("/")
		def path_to_create = "./features/"
		path_elements.each {
			if(! it.endsWith(".feature")) {
				path_to_create += it + "/"
			}
		}
		def folders_to_create = new File(path_to_create)
		folders_to_create.mkdirs()
	}
	
	def top_scenario_text(feature_name, browser_name, page_structure_file_name, env_name, data_file_name, data_source_driver) {
		def scenario_text = "Scenario: Prepare browser and test data"
		scenario_text += "\n\t\tGiven User launched the " + browser_name.trim() + " browser with page structure file " + page_structure_file_name.trim()
		if(! env_name.trim().equals("NA")) {
			String url = Config.env(env_name)
			scenario_text += "\n\t\tAnd User visited website " + url.trim()
		}
		if(!data_file_name.trim().equals("NA")) {
			if(! data_source_driver.trim().equals("NA")) {
				if(data_source_driver.trim().equalsIgnoreCase("default")) {
					scenario_text += "\n\t\tAnd User uses the data source file " + data_file_name.trim() + " with driver " + Config.default_datasource_driver.trim()
				} else {
					scenario_text += "\n\t\tAnd User uses the data source file " + data_file_name.trim() + " with driver " + data_source_driver.trim()
				}
			}
		}
		if(! feature_name.trim().equals("NA")) {
			scenario_text += "\n\t\tAnd User is testing feature " + feature_name.trim()
		}
		return scenario_text
	}
	
	def bottom_scenario_text() {
		def bottom_scenario_text = "Scenario: Close Browser\n"
		bottom_scenario_text += "\t\tThen User closes the browser window"
		return bottom_scenario_text
	}
}