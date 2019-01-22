package org.naic.mfl.se.challenge.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	public static WebElement element = null;
	
	public static void clickLogin(WebDriver driver) {
		
		driver.findElement(By.id("SubmitLogin")).click();
		
	}

	public static void enterEmail(WebDriver driver, String existingUserEmail) {
		driver.findElement(By.id("email")).sendKeys(existingUserEmail);
		
	}

	public static void enterPassword(WebDriver driver, String existingUserPassword) {
		driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
		
	}


	

	

	

	

	

	

	

	
	
	
	
	
	
}
