package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utility.BrowserUtility;
import Utility.ConfigReader;
import Utility.DriverFactory;
import pages.LoginPage;

public class LoginPageTest extends BrowserUtility {

	WebDriver driver;
	LoginPage loginPage;

	@Test()
	public void verifyLoginFunctionalityWithCorrectCredentials() throws IOException {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		driver = DriverFactory.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		loginPage.loginToApplication(ConfigReader.getProperty("CorrectUsername"),
				ConfigReader.getProperty("CorrectPassword"));
		loginPage.verifyLoginFunctionalityWithCorrectCredentials();

	}

	@Test()
	public void verifyLoginFunctionalityWithInCorrectCredentials() throws IOException {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		driver = DriverFactory.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		loginPage.loginToApplication(ConfigReader.getProperty("InCOrrectUsername"),
				ConfigReader.getProperty("InCorrectPasswordassword"));
		loginPage.verifyLoginFunctionalityWithInCorrectCredentials(ConfigReader.getProperty("errorMessage"));

	}

	@Test()
	public void verifyEntertedUsernameAndPasswordAreNotRetained() throws IOException {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		driver = DriverFactory.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		loginPage.loginToApplication(ConfigReader.getProperty("InCOrrectUsername"),
				ConfigReader.getProperty("InCorrectPasswordassword"));
		loginPage.verifyLoginFunctionalityWithInCorrectCredentials(ConfigReader.getProperty("errorMessage"));
		loginPage.verifyEnteredUsernameAndPasswordValuesByUserAreNotRetained(ConfigReader.getProperty("errorMessage"),
				ConfigReader.getProperty("InCOrrectUsername"), ConfigReader.getProperty("InCorrectPasswordassword"));
	}
	
	
	@Test()
	public void verifyFunctionalityOfForgotPasswordButton() throws IOException
	{
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		driver = DriverFactory.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		loginPage.verifyForgotPasswordButtonFunctionality(ConfigReader.getProperty("forgotPasswordLink"));
	}
	
	@Test
	public void verifyThePasswordRecoveryFunctionality() throws IOException
	{
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		driver = DriverFactory.getDriver();
		driver.get("https://demo.nopcommerce.com/");
		loginPage.verifyThePasswordecoveryFunctionality(ConfigReader.getProperty("recoverySuccessMessage"));
	}

}
