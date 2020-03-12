package com.jio.test;

import java.text.ParseException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.jio.pages.HomePage;
public class ValidateResizableandDrappableTest {

	WebDriver driver;
	HomePage objHomePage;


	
	@BeforeSuite
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/");
		driver.manage().window().maximize();
		objHomePage = new HomePage(driver);
		DOMConfigurator.configure("log4j.xml");
	}
	@Test
	public void validateVacationTest() throws InterruptedException, ParseException{

		objHomePage.goToResizable();
		objHomePage.resizeTextBox(2);
	}
	@Test
	public void validateDroppable() throws InterruptedException, ParseException{

		objHomePage.goToDroppable();

		String droppbaleText=objHomePage.performDragAndDrop();
		AssertJUnit.assertEquals(droppbaleText, "Dropped!");
		String droppbaleText1=objHomePage.performDragAndDrop();
	}
	
	@AfterSuite
	public void tearDown(){
		//driver.close();
		driver.quit();
	}
}
