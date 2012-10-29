# GherkinSalad

This framework makes it easy to

- Create page objects and use them
- Create complex reusable component objects
- Get a jumpstart on UI automation

gS uses Java, Maven, WebDriver

## Features
- DSL to interact with page objects, perform validations and create reusable complex component objects
- Several pre built and tested component objects
- Wrapper on top of WebElement that provides several methods that help speed up automation
- Reporting module that can work with Cucumber or without it to create detailed reports
- Built in support to write page objects and automation suites that do not fail strictly

## Basic Usage
#### Create a page object
<pre><code>public class TwitterHomePage extends Component {
	public TwitterPage() {
		super.found(By.tagName("body"));
	}
}
public class TwitterLoginPage extends Component {
	public TwitterLoginPage() {
		super.found(By.tagName("body"));
	}
}
</code></pre>

This defines the page object that represents the content within the body tag - basically the entire page.

#### Creating the step definition
<pre><code>public class TwitterSteps extends Component {
	TwitterLoginPage login_page = new TwitterLoginPage();
	TwitterHomePage home_page = new TwitterHomePage();

	@Given("^User A is logged in$")
	public void login() {
		super.launch_browser("firefox");
		login_page.login_as("username", "password");
	}
	@After
	public void teardown() {
		close_browser();
	}
}
</code></pre>

The page object is created by simply using the constructor. Within the page object all the interactions will happen with the instance of browser that is currently available. The framework under the Component object handles it.

#### Within the page object
###### Working with HTML links and buttons
<pre><code>Clickable somelink = Link.find(By.id("someid"));
somelink.click_if_enabled();
</code></pre>
The first line of code will create an object that has methods to perform click for the element that can be identified by the locator specified. The second line performs the clicking of the link only if it is enabled.
For more information about Clickable check the javadocs

###### Working with Drop Downs
<pre><code>Selectable someDropDown = DropDown.find(By.id("someid"));
someDropDown.select_option_if_enabled("some text");
</code></pre>
This will select the first option in the dropdown that has the text specified. For more information about Selectable check the javadocs

###### Working with Text Box and Text Area
<pre><code>TextEnterable someTextField = TextField.find(By.id("someid"));
someTextField.enter_text_if_enabled("text to enter");
</code></pre>
This will find a **text field**, clear the existing text and enter new text in there.

<pre><code>TextEnterable someTextField = TextArea.find(By.id("someid"));
someTextField.enter_text_if_enabled("text to enter");
</code></pre>
This will find a **text area**, clear the existing text and enter new text in there.

#### More Information
More information can be found on the [wiki](https://github.com/daveayan/gherkinsalad/wiki)

#### Support
Open an issue [here](https://github.com/daveayan/gherkinsalad/issues)

OR

Contact me at gherkinsalad [ a t ] gmail

#### Copyright
Copyright (c) 2011 - 2012 Ayan Dave http://daveayan.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, including 
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the 
following conditions:

- The above copyright notice and this permission notice shall be included without any changes or alterations 
in all copies or substantial portions of the Software.
- This software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.