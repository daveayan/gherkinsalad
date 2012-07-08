package daveayan.gherkinsalad.components.jqueryui;

import java.util.Date;

import org.junit.Test;
import org.openqa.selenium.By;

import daveayan.gherkinsalad.components.BaseTest;
import daveayan.gherkinsalad.components.DatePicker;
import daveayan.gherkinsalad.components.TextEnterable;
import daveayan.gherkinsalad.components.html.TextField;

public class DatePickerTest extends BaseTest {
	@Test
	public void firefox_jquery_ui_default_date_picker_test() {
		super.launch_browser_with("Firefox");
		super.goto_url("http://jqueryui.com/demos/datepicker/");
		
		DatePicker datepicker = new JqueryUIDefaultDatePicker();
		TextEnterable date_textbox = TextField.find(By.id("datepicker"));
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/06/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/07/2012"), new Date());
		date_textbox.should_have_text("07/07/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/08/2012"), new Date());
		date_textbox.should_have_text("07/08/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date(new Date("07/09/2012"), new Date());
		date_textbox.should_have_text("07/09/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/06/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/07/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/08/2012"), new Date());
		date_textbox.should_have_text("07/06/2012");
		
		date_textbox.click_if_enabled();
		datepicker.select_date_except_weekends(new Date("07/09/2012"), new Date());
		date_textbox.should_have_text("07/09/2012");
	}
}