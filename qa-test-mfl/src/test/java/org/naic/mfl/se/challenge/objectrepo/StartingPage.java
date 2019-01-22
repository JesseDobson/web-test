package org.naic.mfl.se.challenge.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartingPage {
	public static void clickLogin(WebDriver driver, WebDriverWait wait) {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
			
		}
}
