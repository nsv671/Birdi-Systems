package testTelecome;

import java.time.Duration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomer {
	WebDriver driver;
	HashSet<String> customerIds = new HashSet<>();
 
	@Test(dataProvider="getCustomerData", priority = 1)
	public void addCustomer(String firstName, String lastName, String email, String address, String mobileNumber) throws InterruptedException {
		
		nevigateToHomePage();
		driver.findElement(By.xpath(AddCustomerLocator.addCustomerLink)).click();
		
		//AD
		closeGoogleAD();
		
		enterDetailsInCustomerForm(firstName, lastName, email, address, mobileNumber);
		
		driver.findElement(By.xpath(AddCustomerLocator.submitButton)).click();
		
		//verify successful message;
		String headerMessage = "Access Details to Guru99 Telecom";
		String headerUIMessage  = driver.findElement(By.xpath(AddCustomerLocator.accessDetailsHeading)).getText();
		Assert.assertEquals(headerUIMessage, headerMessage);
		
		String information = "Please Note Down Your CustomerID";
		String UIinformation  = driver.findElement(By.xpath(AddCustomerLocator.information)).getText();
		Assert.assertEquals(UIinformation, information);
		
		String customerId = driver.findElement(By.xpath(AddCustomerLocator.customerId)).getText();
		System.out.printf("Customer ID - %5s \n", customerId);
		
		customerIds.add(customerId);
		
		driver.close();
	}
	
	@Test(dependsOnMethods = {"addCustomer"}, priority=2)
	public void addTariffPlan() {
		nevigateToHomePage();
		
		driver.findElement(By.xpath(AddTariffPlanToCustomerLocator.addTeriffPlanButton)).click();
		
		//AD
		closeGoogleAD();
		
		Iterator<String> iterator = customerIds.iterator();
		
		while(iterator.hasNext()) {
			String clientId = iterator.next();
			driver.findElement(By.xpath(AddTariffPlanToCustomerLocator.customerIdInput)).sendKeys(clientId);
			driver.findElement(By.xpath(AddTariffPlanToCustomerLocator.submitButton)).click();
			
			if(isElementPresent(AddTariffPlanToCustomerLocator.wrongClientIput)) {
				System.out.println("Wrong client id proveded");
				Assert.fail("Please Input Your Correct Customer ID");
			}
			System.out.println("Before scroll");
//			WebElement radioButton = driver.findElement(By.cssSelector(AddTariffPlanToCustomerLocator.tarifPlan));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButton);
//			radioButton.click();
			System.out.println("After scroll");
			
			System.out.println("Before scroll");
			WebElement submitButton = driver.findElement(By.xpath(AddTariffPlanToCustomerLocator.aaddTariffPlaneToCustomer));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
			submitButton.click();
			System.out.println("After scroll");
			
			String successfulMessage = "Congratulation Tariff Plan assigned";
			String UiSuccessfulMessage = driver.findElement(By.xpath(AddTariffPlanToCustomerLocator.successfulAddTeriffMessage)).getText();
			
			Assert.assertEquals(UiSuccessfulMessage, successfulMessage);
		}
	}
	
	private void enterDetailsInCustomerForm(String firstName, String lastName, String email, String address, String mobileNumber) {
		driver.findElement(By.xpath(AddCustomerLocator.firstName)).sendKeys(firstName);
		driver.findElement(By.xpath(AddCustomerLocator.lastName)).sendKeys(lastName);
		driver.findElement(By.xpath(AddCustomerLocator.email)).sendKeys(email);
		driver.findElement(By.xpath(AddCustomerLocator.address)).sendKeys(address);
		driver.findElement(By.xpath(AddCustomerLocator.mobileNumber)).sendKeys(mobileNumber);
		System.out.println("Form fillup with customer data successful");
	}
	
	@DataProvider
	public Object[][] getCustomerData() {
		Object[][] data = new Object[1][5];
		
		//data set-1
		data[0][0] = "abc";
		data[0][1] = "cab";
		data[0][2] = "abc@cab.com";
		data[0][3] = "Address";
		data[0][4] = "1234561230";
		
		return data;
	}

	private void closeGoogleAD() {
		List<WebElement> AD = driver.findElements(By.xpath(AddCustomerLocator.thirdPartyIframe));
		if(!AD.isEmpty()) {
			System.out.println("*".repeat(100));
			driver.switchTo().frame(driver.findElement(By.xpath(AddCustomerLocator.thirdPartyIframe)));
			driver.findElement(By.id("dismiss-button")).click();
			System.out.println("Ad close successfully");
			driver.switchTo().defaultContent();
		}
		System.out.println("*".repeat(100));
	}
	
	private boolean isElementPresent(String locator) {
		boolean flag = false;
		
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if(!elements.isEmpty()) {
			flag = true;
		}
		
		return flag;
	}
	
	private void nevigateToHomePage() {
		String URL = "https://demo.guru99.com/telecom/index.html";
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get(URL);
	}
}
