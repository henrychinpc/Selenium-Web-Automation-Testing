
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

/**
 * 
 * @author Homy
 * @version 1.0
 * @since 09/2019 This code is a base code for RMIT Software Testing/ Selenium Lab Assessment.
 *        You may need to change the webpage target and test methods based on your assessment spec.
 *        You can send email to amirhomayoon.ashrafzadeh@rmit.edu.au if you have any question
 *        Alternatively use your course Canvas forum Assessment specification is
 *        available on Canvas/Assignment
 * 
 */

/*
 * You must "junit 5 extension.jar" from SpiraTeam to your
 * project/properties/java build path, Library tab as an External Jar
 * 
 */

@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login = "HPCChin", 
		rssToken = "{B0E99289-5622-44C8-90E4-17050EE3F50E}", 
		projectId = 675,
		releaseId = 1478, 
		testSetId = 1749)

@TestMethodOrder(OrderAnnotation.class) // << this annotation is for using @order, or adding order to my test-cases
public class SignInTest {
	
	private static ChromeDriver driver;
	private String expectedResult;
	private String actualResult;
	private WebElement element;
	private WebDriverWait wait;
	

	@BeforeAll
	public static void setup() {
		
		System.setProperty("Webdriver.chrome.driver", "chromedriver");

		driver = new ChromeDriver();

	}

	@Test
	@Order(1)
	@SpiraTestCase(testCaseId = 23069)
	// Test if the browser is opening the Automation Practice page and if the 'Sign in' button is available
	public void testwebsite() {

		driver.get("http://automationpractice.com/index.php");
		element = driver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a"));
		expectedResult = "Sign in";
		actualResult = element.getText();
		assertEquals(expectedResult, actualResult);

	}
    
	@Test
	@Order(2)
	@SpiraTestCase(testCaseId = 23070)
	// Test if the sign in button works and the sign in form has all necessary elements
	public void signInPageTest() {

		driver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement signInForm = driver.findElement(By.xpath("//*[@id=\'login_form\']"));
		WebElement signInEmail = driver.findElement(By.xpath("//*[@id=\'email\']"));
		WebElement signInPassword = driver.findElement(By.xpath("//*[@id=\'passwd\']"));
		WebElement signInButton = driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span"));
		assertTrue(signInForm.isDisplayed());
		assertTrue(signInEmail.isDisplayed());
		assertTrue(signInPassword.isDisplayed());
		assertTrue(signInButton.isDisplayed());
	}
	
	@Test
	@Order(3)
	@SpiraTestCase(testCaseId = 23072)
	// Test signing in with missing credentials
	public void signInWithoutCredentials() {
		
		
		//Signing in without email and password
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]"));
		assertTrue(errorMessage.isDisplayed());
		
		//Signing in with email only
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("hechinrmit@gmail.com");
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		WebElement errorMessage1 = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]"));
		assertTrue(errorMessage1.isDisplayed());
		
		element = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]/ol/li"));
		expectedResult = "Password is required.";
		actualResult = element.getText();
		assertEquals(expectedResult, actualResult);
		
		//Signing in with password only
		driver.findElement(By.xpath("//*[@id=\'email\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys("test123");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		WebElement errorMessage2 = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]"));
		assertTrue(errorMessage2.isDisplayed());
		
		element = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]/ol/li"));
		expectedResult = "An email address required.";
		actualResult = element.getText();
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	@Order(4)
	@SpiraTestCase(testCaseId = 23073)
	// Test signing in with wrong email format
	public void signInWithWrongEmailFormat() {
		
		//Signing in with wrong email format
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("hechinrmit");
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		WebElement errorMessage1 = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]"));
		assertTrue(errorMessage1.isDisplayed());
		
		element = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]/ol/li"));
		expectedResult = "Invalid email address.";
		actualResult = element.getText();
		assertEquals(expectedResult, actualResult);
		
		//Signing in with wrong email format
		driver.findElement(By.xpath("//*[@id=\'email\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("hechinrmit@email");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		WebElement errorMessage2 = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]"));
		assertTrue(errorMessage2.isDisplayed());
				
		element = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]/ol/li"));
		expectedResult = "Invalid email address.";
		actualResult = element.getText();
		assertEquals(expectedResult, actualResult);

	}
	
	@Test
	@Order(5)
	@SpiraTestCase(testCaseId = 23074)
	// Test if signing in is successful with correct credentials
	public void properSignIn() {
		
		//Signing in with wrong email format
		driver.findElement(By.xpath("//*[@id=\'email\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("hechinrmit@gmail.com");
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).clear();
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys("test123");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		
		WebElement myAccount = driver.findElement(By.xpath("//*[@id=\'columns\']/div[3]"));
		assertTrue(myAccount.isDisplayed());

	}
	
	@AfterAll
	// closing or quitting the browser after the test
	public static void closeBrowser() {
		driver.quit();
	}
}
