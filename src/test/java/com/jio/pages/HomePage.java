package com.jio.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	WebDriver driver;
	static Logger logger = Logger.getLogger(HomePage.class);

	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/aside[1]/ul/li/a[text()='Resizable']")
	WebElement resizableMenu;
	@FindBy(how=How.XPATH,using="//*[@id='sidebar']/aside[1]/ul/li/a[text()='Droppable']")
	WebElement droppableMenu;
	@FindBy(how=How.XPATH,using="//*[@id='resizable']/div[contains(@class,'ui-icon')]")
	WebElement resizableIcon;

	@FindBy(how=How.ID,using="resizable")
	WebElement textBox;

	@FindBy(how=How.XPATH,using="//*[@id='draggable']/p")
	WebElement draggable;
	@FindBy(how=How.XPATH,using="//*[@id='droppable']/p")
	WebElement droppable;

	@FindBy(how=How.CLASS_NAME,using="entry-title")
	WebElement entryTitle;
	Actions action;
	WebDriverWait wait;
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait= new WebDriverWait(driver, 10);
		action = new Actions(driver);
	}


	public void goToResizable(){
		resizableMenu.click();
		wait.until(ExpectedConditions.textToBePresentInElement(entryTitle, "Resizable"));
	}


	public void resizeTextBox(int noOfTime) {

		for (int i=1;i<=noOfTime;i++){
			logger.info("Resize Number "+i+":");
			action.clickAndHold(resizableIcon).moveByOffset(300-(i*100), 300-(i*50)).release().perform();
			logger.info("height - "+textBox.getCssValue("height")+"; width - "+textBox.getCssValue("width"));
		}


	}


	public void goToDroppable() {
		droppableMenu.click();
		wait.until(ExpectedConditions.visibilityOf(entryTitle));
	}

	public String performDragAndDrop(){
		
		if (droppable.getText().equals("Dropped!")){
			int yOffset= droppable.getLocation().y + 100;
			action.dragAndDropBy(draggable, 200,yOffset).build().perform();
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].innerHTML='Drop Here'", droppable);
		}else{
			action.dragAndDrop(draggable, droppable).build().perform();
		}
		return droppable.getText();
	}
}
