package daveayan.gherkinsalad;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Util {
	private static Log log = LogFactory.getLog(Util.class);
	private static int screen_shot_count = 0;
	
	public static void takeScreenShotWith(TakesScreenshot driver) {
		screen_shot_count++;
		String file_name = Path.TO_SCREENSHOTS + "screenshot_" + screen_shot_count + ".png";
		try {
			File screenshot_file = driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot_file, new File(file_name));
			log.info("Screenshot taken : " + file_name);
		} catch (Throwable th) {
			log.info(th.getMessage());
			log.info("Unable to take screenshot : " + file_name);
		}
	}
}