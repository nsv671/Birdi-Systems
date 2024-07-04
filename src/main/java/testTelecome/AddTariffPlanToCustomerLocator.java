package testTelecome;

public class AddTariffPlanToCustomerLocator {
	static String customerIdInput = "//input[@id='customer_id']";
	static String addTeriffPlanButton = "//section[@id='one']//a[text()='Add Tariff Plan to Customer']";
	static String tarifPlan = "input[type='radio'] + label:before"; //"//input[@type='radio'  and @value='500']";
	static String wrongClientIput = "//*[text()='Please Input Your Correct Customer ID']";
	static String aaddTariffPlaneToCustomer = "//input[@value='Add Tariff Plan to Customer']";
	static String successfulAddTeriffMessage = "//h2[text()='Congratulation Tariff Plan assigned']";
	static String submitButton = "//input[@type='submit']";
}
