package testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.utility;

public class Flurn_HomepageLogin {
	WebDriver driver;


	@BeforeTest
	public void BeforeTest() {
		driver=utility.getBrowser("chrome");   //Opening Chrome browser.
		driver.manage().window().maximize();  //Maximizing window.
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

	}
	@Test
	public void Login() {
		driver.get("https://pp-web.flurn.in/login");     //Opening the flurn URL in the browser.
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Assert.assertEquals("Login | Flurn", driver.getTitle());         //Verify title of the webpage.
		
		driver.findElement(By.name("phone")).sendKeys("9999999999");     //Enter the phone number.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'Get OTP')]")).click(); //find the button and click on it.
		
		driver.findElement(By.xpath("//input[@name=\"otp\"]")).sendKeys("565656");     //Enter the otp
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[@type='submit']")).click();             //Click the submit button
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
	 	driver.findElement(By.xpath("//h4[contains(text(),'Learner')]")).click(); //login as a learner.
	 	
	 	WebDriverWait wait = new WebDriverWait(driver,30);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Hi Harikrishna')]")));
	 	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    Assert.assertEquals("Your Sessions | Flurn", driver.getTitle());       //Verify title of logged in user.
	    System.out.println("Login Successfull");
		}
		                       
	@AfterTest
	public void closeBrowser() {
		driver.quit();                                         //Close the browser.
	}
}
