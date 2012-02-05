package daveayan.gherkinsalad

import java.io.File
import java.io.FileInputStream
import java.util.Properties

import org.apache.commons.io.FileUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

class ArchiveExecution {
	private static Log log = LogFactory.getLog(ArchiveExecution.class)
	public static void main(String[] args) {
		def archive = new ArchiveExecution()
		archive.archive()
	}

	def archiving_is_enabled() {
		def config_file_location = System.getenv("GHKSALAD_CONFIG");
		log.info("GHKSALAD_CONFIG file is " + config_file_location);
		def config_file = new File(System.getenv("GHKSALAD_CONFIG"));
		def config = new Properties();
		config.load(new FileInputStream(config_file));
		log.info("GHKSALAD_CONFIG Properties are: " + config);
		boolean value = Boolean.parseBoolean(config.getProperty("archive.results"))
		println value
		return value
	}

	def archive() {
		if(archiving_is_enabled()) {
			def archive_dir_path = create_new_archive_directory()
			copy_directory("./features", "${archive_dir_path}/features")
			copy_directory("./target/screenshots", "${archive_dir_path}/screenshots")
			copy_file("./target/cucumber-reports.html", "${archive_dir_path}/cucumber-reports.html")
		} else {
			println "Archiving is not enabled. Skipping."
		}
	}

	def copy_directory(source_path, target_path) {
		def source = new File(source_path)
		def target = new File(target_path)
		FileUtils.copyDirectory(source, target)
		println "Archived folder ${target}"
	}

	def copy_file(source_path, target_path) {
		def source = new File(source_path)
		def target = new File(target_path)
		FileUtils.copyFile(source, target)
		println "Archived file ${target}"
	}

	def create_new_archive_directory() {
		def c1= new GregorianCalendar()
		def datetime = String.format('%tY-%<tm-%<td', c1) + "_" + String.format('%tT', c1)
		def archive_dir_path = "./archive/${datetime}"
		def archive_dir = new File(archive_dir_path)
		archive_dir.mkdir()
		println "Created folder ${archive_dir}"
		return archive_dir_path
	}
}