package testTelecome;

public class AddCustomerLocator {
		//locators
		static String thirdPartyIframe = "//iframe[@title='3rd party ad content']";
		static String firstName = "//input[@id='fname']";
		static String lastName = "//input[@id='lname']";
		static String email = "//input[@id='email']";
		static String address = "//textarea[@placeholder='Enter your address']";
		static String mobileNumber = "//input[@id='telephoneno']";
		
		//button
		static String submitButton = "//input[@type='submit']";
		static String addCustomerLink = "//a[text()='Add Customer']";
		
		//successful message
		static String accessDetailsHeading = "//header/h1";
		static String information = "//b[text()='Please Note Down Your CustomerID']";
		static String customerId = "//td//h3";
}
