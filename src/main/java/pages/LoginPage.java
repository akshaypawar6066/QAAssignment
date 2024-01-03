package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utility.ConfigReader;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginIcon;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement Password;

	@FindBy(xpath = "//button[normalize-space()='Log in']")
	private WebElement loginButton;

	@FindBy(xpath = "//a[normalize-space()='Log out']")
	private WebElement logoutButton;

	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement errorMessage;

	@FindBy(xpath = "//a[normalize-space()='Forgot password?']")
	private WebElement forgotPasswordButton;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement recoveryEmailField;

	@FindBy(xpath = "//button[normalize-space()='Recover']")
	private WebElement recoverButton;

	@FindBy(xpath = "//p[@class='content']")
	private WebElement suceesMessage;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public void loginToApplication(String uerName, String loginPassword) throws IOException {
		wait.until(ExpectedConditions.visibilityOf(loginIcon));
		loginIcon.click();
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.sendKeys(uerName);
		wait.until(ExpectedConditions.visibilityOf(Password));
		Password.sendKeys(loginPassword);
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		loginButton.click();

	}

	public void verifyLoginFunctionalityWithCorrectCredentials() {
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		if (logoutButton.isDisplayed()) {
			System.out.println("Able to Login the application Successfully");
		} else {
			System.out.println("Not able to Login the application Succeffully");
			Assert.assertFalse(true, "Login Failed.");
		}

	}

	public void verifyLoginFunctionalityWithInCorrectCredentials(String expectedMessage) {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		if (errorMessage.getText().contains(expectedMessage)) {
			System.out.println("Not able to Login the application using invalid credentails.");
			Assert.assertTrue(true, "Not able to login.");
		} else {
			Assert.fail("Able to login the application using invalid credentails.");
		}

	}

	public void verifyEnteredUsernameAndPasswordValuesByUserAreNotRetained(String expectedMessage,
			String enteredUsername, String enteredPassword) {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		if (errorMessage.getText().contains(expectedMessage)) {
			System.out.println("Not able to Login the application using invalid credentails.");
			Assert.assertTrue(true, "Not able to login.");
		} else {
			Assert.fail("Able to login the application using invalid credentails.");
		}

		String actualUsernameValue = userName.getAttribute("value");
		String actualPasswordValue = Password.getAttribute("value");
		Assert.assertEquals(actualUsernameValue, enteredUsername, "Username field value is not retained");
	}

	public void verifyForgotPasswordButtonFunctionality(String expectedUrl) {
		loginIcon.click();
		wait.until(ExpectedConditions.visibilityOf(forgotPasswordButton)).click();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, expectedUrl, currentUrl);
	}

	public void verifyThePasswordecoveryFunctionality(String expectedMessage) throws IOException {
		loginIcon.click();
		wait.until(ExpectedConditions.visibilityOf(forgotPasswordButton)).click();
		wait.until(ExpectedConditions.visibilityOf(recoveryEmailField)).sendKeys(ConfigReader.getProperty("CorrectUsername"));
		wait.until(ExpectedConditions.visibilityOf(recoverButton)).click();
		Assert.assertEquals(suceesMessage.getText(), expectedMessage,  "Not able to recover the Password by entering username/Email field.");

	}
	
	

}
