package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtility {

	  
	  DriverFactory df;
	  WebDriver driver;
	  


	@BeforeMethod
	public void launchBrowser() throws IOException
	{
//		Properties prop = new Properties();
//		FileInputStream filePath=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
//		prop.load(filePath);
//		String BrowserName=prop.getProperty("browser");
//		System.out.println("Browser name is:"+BrowserName);
		
		df=new DriverFactory();
		df.initBrowser(ConfigReader.getProperty("browser"));
		driver=DriverFactory.getDriver();
		
	}

	private void captureScreenshot(ITestResult result) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);

			String screenshotName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";
			File destination = new File(System.getProperty("user.dir") + "/target/Screenshots/" + screenshotName);

			Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Screenshot captured: " + destination.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void closeBrowser(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result);
		}
		
		if (driver != null) {
			driver.quit();
		}

	}
}
