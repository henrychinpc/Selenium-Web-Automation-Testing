
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
		testSetId = 1750)

@TestMethodOrder(OrderAnnotation.class) // << this annotation is for using @order, or adding order to my test-cases
public class SearchTest {
	
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
	@SpiraTestCase(testCaseId = 23122)
	// Test if the browser is opening the Automation Practice page and the main page is showing
	public void testwebsite() {

		driver.get("http://automationpractice.com/index.php");
		element = driver.findElement(By.xpath("//*[@id=\'page\']/div[2]"));
		assertTrue(element.isDisplayed());

	}
	
	@Test
	@Order(2)
	@SpiraTestCase(testCaseId = 23124)
	// Test if 'Dress' button is working and displays only dresses
	public void testDressButton() {
		
		//Clicking 'Dress' button
		element = driver.findElement(By.xpath("//*[@id=\'block_top_menu\']/ul/li[2]/a"));
		element.click();
		
		//Checking if it only shows 5 items
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement dressCount = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[3]/div[2]/div[2]"));
		expectedResult = "Showing 1 - 5 of 5 items";
		actualResult = dressCount.getText();
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	@Order(3)
	@SpiraTestCase(testCaseId = 23125)
	// Test if items shown are only dresses
	public void testDressItems() {
			
		WebElement dress1 = driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));
		WebElement dress2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/h5/a"));
		WebElement dress3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[2]/h5/a"));
		WebElement dress4 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[4]/div/div[2]/h5/a"));
		WebElement dress5 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[2]/h5/a"));
		assertTrue(dress1.getText().contains("Dress"));
		assertTrue(dress2.getText().contains("Dress"));
		assertTrue(dress3.getText().contains("Dress"));
		assertTrue(dress4.getText().contains("Dress"));
		assertTrue(dress5.getText().contains("Dress"));
	}
	
	@Test
	@Order(4)
	@SpiraTestCase(testCaseId = 23126)
	// Test search bar
	public void searchDressItem() {
			
		//Finding search bar
		element = driver.findElement(By.xpath("//*[@id=\'search_query_top\']"));
		element.sendKeys("dresses");
		element.submit();
		
		//Using search bar to search for dresses
		WebElement shownResult = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]"));
		expectedResult = "\"dresses\"";
		actualResult = shownResult.getText();
		assertEquals(expectedResult, actualResult.toLowerCase());
	}
    
	@Test
	@Order(5)
	@SpiraTestCase(testCaseId = 23127)
	// Test items shown
	public void searchResult() {

		//Checking if it only shows 5 items
		WebElement dressCount = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]/div[2]/div[2]"));
		expectedResult = "Showing 1 - 5 of 5 items";
		actualResult = dressCount.getText();
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	@Order(6)
	@SpiraTestCase(testCaseId = 23129)
	// Test items shown
	public void searchResultItem() {

		//Checking if it only shows dresses
		WebElement dress1 = driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div[2]/h5/a"));
		WebElement dress2 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/h5/a"));
		WebElement dress3 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[2]/h5/a"));
		WebElement dress4 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[4]/div/div[2]/h5/a"));
		WebElement dress5 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[5]/div/div[2]/h5/a"));
		WebElement dress6 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[6]/div/div[2]/h5/a"));
		WebElement dress7 = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[7]/div/div[2]/h5/a"));
		assertTrue(dress1.getText().contains("Dress"));
		assertTrue(dress2.getText().contains("Dress"));
		assertTrue(dress3.getText().contains("Dress"));
		assertTrue(dress4.getText().contains("Dress"));
		assertTrue(dress5.getText().contains("Dress"));
		assertTrue(dress6.getText().contains("Dress"));
		assertTrue(dress7.getText().contains("Dress"));
	}
	
	@AfterAll
	// closing or quitting the browser after the test
	public static void closeBrowser() {
		driver.quit();
	}
}
