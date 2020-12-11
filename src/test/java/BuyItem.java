
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
		testSetId = 1752)

@TestMethodOrder(OrderAnnotation.class) // << this annotation is for using @order, or adding order to my test-cases
public class BuyItem {
	
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
	@SpiraTestCase(testCaseId = 23105)
	// Test if the browser is opening the Automation Practice page and signed in
	public void testwebsite() {

		//Main page
		driver.get("http://automationpractice.com/index.php");
		element = driver.findElement(By.xpath("//*[@id=\'page\']/div[2]"));
		assertTrue(element.isDisplayed());
		
		//Sign in
		driver.findElement(By.xpath("//*[@id=\'header\']/div[2]/div/div/nav/div[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\'email\']")).sendKeys("hechinrmit@gmail.com");
		driver.findElement(By.xpath("//*[@id=\'passwd\']")).sendKeys("test123");
		driver.findElement(By.xpath("//*[@id=\'SubmitLogin\']/span")).click();
		
		WebElement myAccount = driver.findElement(By.xpath("//*[@id=\'columns\']/div[3]"));
		assertTrue(myAccount.isDisplayed());

	}
	
	@Test
	@Order(2)
	@SpiraTestCase(testCaseId = 23106)
	//Test 'Women' button
	public void buttonClick() {
		
		driver.findElement(By.xpath("//*[@id=\'block_top_menu\']/ul/li[1]/a")).click();
		element = driver.findElement(By.xpath("//*[@id=\'columns\']"));
		assertTrue(element.isDisplayed());
		
	}
	
	@Test
	@Order(3)
	@SpiraTestCase(testCaseId = 23107)
	//Test selecting list view button
	public void selectItem() {
		
		driver.findElement(By.xpath("//*[@id=\'list\']")).click();
		element = driver.findElement(By.xpath("//*[@id=\'columns\']"));
		assertTrue(element.isDisplayed());
		
	}
	
	@Test
	@Order(4)
	@SpiraTestCase(testCaseId = 23108)
	//Test adding item to cart
	public void addItemToCart() {
		
		driver.findElement(By.xpath("//*[@id=\'center_column\']/ul/li[1]/div/div/div[3]/div/div[2]/a[1]/span")).click();
		driver.findElement(By.xpath("//*[@id=\'layer_cart\']/div[1]/div[2]/div[4]/a/span")).click();
		WebElement cartSummary = driver.findElement(By.xpath("//*[@id=\'columns\']"));
		assertTrue(cartSummary.isDisplayed());
		
	}
	
	@Test
	@Order(5)
	@SpiraTestCase(testCaseId = 23109)
	//Test address and comment feature
	public void address() {
		
		WebElement page = driver.findElement(By.xpath("//*[@id=\'center_column\']/p[2]/a[1]/span"));
		page.click();
		WebElement addressPage = driver.findElement(By.xpath("//*[@id=\'center_column\']/form/div"));
		assertTrue(addressPage.isDisplayed());
		
		//Insert comment into comment field
		element = driver.findElement(By.xpath("//*[@id=\"ordermsg\"]/textarea"));
		element.sendKeys("Take good care of it please");
		driver.findElement(By.xpath("//*[@id=\'center_column\']/form/p/button/span")).click();
		WebElement nextpage = driver.findElement(By.xpath("//*[@id=\'carrier_area\']"));
		assertTrue(nextpage.isDisplayed());
		
	}
	
	@Test
	@Order(6)
	@SpiraTestCase(testCaseId = 23110)
	//Test shipping feature
	public void shipping() {
		
		//Proceed to checkout without ticking terms of service
		driver.findElement(By.xpath("//*[@id=\'form\']/p/button/span")).click();
		element = driver.findElement(By.xpath("//*[@id=\'order\']/div[2]/div/div/div/div/p"));
		assertTrue(element.isDisplayed());
		
		//Close error box
		driver.findElement(By.xpath("//*[@id=\'order\']/div[2]/div/div/a")).click();
		assertTrue(element.isDisplayed());
		
		//Ticking terms of service box
		driver.findElement(By.xpath("//*[@id=\'cgv\']")).click();
		
		//Proceed to checkout
		driver.findElement(By.xpath("//*[@id=\'form\']/p/button/span")).click();
		WebElement paymentPage = driver.findElement(By.xpath("//*[@id=\'columns\']/div[3]"));	
		assertTrue(paymentPage.isDisplayed());
		
	}
	
	@Test
	@Order(7)
	@SpiraTestCase(testCaseId = 23111)
	//Test payment feature
	public void payment() {
		
		//Select pay with bank wire option
		driver.findElement(By.xpath("//*[@id=\'HOOK_PAYMENT']/div[1]/div/p/a")).click();
		element = driver.findElement(By.xpath("//*[@id=\'center_column\']"));
		assertTrue(element.isDisplayed());
		
		//Confirming order
		driver.findElement(By.xpath("//*[@id=\'cart_navigation\']/button/span")).click();
		WebElement orderConfirmation = driver.findElement(By.xpath("//*[@id=\'center_column\']/div"));
		assertTrue(orderConfirmation.isDisplayed());
		WebElement confirmationText = driver.findElement(By.xpath("//*[@id=\'center_column\']/div/p/strong"));
		expectedResult = "Your order on My Store is complete.";
		actualResult = confirmationText.getText();
		assertEquals(expectedResult, actualResult);
		
	}
	
	@AfterAll
	// closing or quitting the browser after the test
	public static void closeBrowser() {
		driver.quit();
	}
}