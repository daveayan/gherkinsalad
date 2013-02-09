package daveayan.gherkinsalad;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.openqa.selenium.io.IOUtils;

public class ReportStreamingTest {
	@Test public void stream_from_core_jar_should_be_readable() throws IOException {
		InputStream stream = this.getClass().getResourceAsStream("/defaultreport/gherkinsaladreport.js");
		String string = IOUtils.readFully(stream);
		System.out.println(string);
	}
}