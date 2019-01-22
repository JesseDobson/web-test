package org.naic.mfl.se.challenge.tests;

//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.naic.mfl.se.challenge.objectrepo.*;

public class WebTest {
    WebDriver driver;
    WebDriverWait wait;

    String existingUserEmail = "mflsqe@naic.org";
    String existingUserPassword = "mflsqe1234";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 50);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void signInTest() {
       
    	StartingPage.clickLogin(driver, wait);
    	
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String name = "Firstname";
        String surname = "Lastname";
        
        //driver.findElement(By.id("email_create")).sendKeys(email);
        
        SignupPage.emailCreate(driver, email);
        		
        //driver.findElement(By.id("SubmitCreate")).click();
        
        SignupPage.createSubmit(driver);
        
        EnterInfoPage.selectGender(driver, wait);
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
        
        
        
        //driver.findElement(By.id("customer_firstname")).sendKeys(name);
        
        EnterInfoPage.enterFirstName(driver, name);
       // driver.findElement(By.id("customer_lastname")).sendKeys(surname);
        
        EnterInfoPage.enterLastName(driver, surname);
        //driver.findElement(By.id("passwd")).sendKeys("Qwerty");
        
        EnterInfoPage.createPassword(driver, "Qwerty");
       /* Select select = new Select(driver.findElement(By.id("days")));
        select.selectByValue("1");
        select = new Select(driver.findElement(By.id("months")));
        select.selectByValue("1");
        select = new Select(driver.findElement(By.id("years")));
        select.selectByValue("2000");*/
        
        EnterInfoPage.enterDate(driver, "1", "1", "2000");
        
        //driver.findElement(By.id("company")).sendKeys("Company");
        
        EnterInfoPage.enterCompany(driver, "Company");
        
        
       /* driver.findElement(By.id("address1")).sendKeys("Qwerty, 123");
        driver.findElement(By.id("address2")).sendKeys("zxcvb");
        driver.findElement(By.id("city")).sendKeys("Qwerty");
        select = new Select(driver.findElement(By.id("id_state")));
        select.selectByVisibleText("Colorado");
        driver.findElement(By.id("postcode")).sendKeys("12345");*/
        
        EnterInfoPage.enterAddress(driver, "Qwerty, 123", "zxcvb", "Qwerty", "Colorado", "12345" );
        
        //driver.findElement(By.id("other")).sendKeys("Qwerty");
        //driver.findElement(By.id("phone")).sendKeys("12345123123");
        //driver.findElement(By.id("phone_mobile")).sendKeys("12345123123");
        //driver.findElement(By.id("alias")).sendKeys("hf");
        //driver.findElement(By.id("submitAccount")).click();
        
        EnterInfoPage.enterOther(driver, "Qwerty");
        EnterInfoPage.enterPhones(driver, "12345123123", "12345123123");
        EnterInfoPage.enterAlias(driver, "hf" );
        EnterInfoPage.clickSubmitAccount(driver);
        
        
        WebElement heading = LandingPage.returnHeading(driver, wait);
        //WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        //assertEquals(heading.getText(), "MY ACCOUNT");
        //Assert.assertEquals(heading.getText(), "MY ACCOUNT");
        Assert.assertEquals(heading.getText(), "MY ACCOUNT");
        //assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
        Assert.assertEquals(driver.findElement(By.className("account")).getText(), name + " " + surname);
        //assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        //assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
        //assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
        
        String headingText = LandingPage.getHeadingText(driver);
        
        Assert.assertEquals(headingText, "MY ACCOUNT");
    }

    @Test
    public void logInTest() {
        String fullName = "Joe Black";
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
        
        StartingPage.clickLogin(driver, wait);
        //driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        LoginPage.enterEmail(driver, existingUserEmail);
        //driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        LoginPage.enterPassword(driver, existingUserPassword);
        //driver.findElement(By.id("SubmitLogin")).click();
        LoginPage.clickLogin(driver);
        
        
        //WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        
        LandingPage.waitForVisibilityofHeader(driver,wait);

        //Assert.assertEquals("MY ACCOUNT", heading.getText());
        Assert.assertEquals("MY ACCOUNT", LandingPage.getHeadingText(driver));
        
        //Assert.assertEquals(fullName, driver.findElement(By.className("account")).getText());
        Assert.assertEquals(fullName, LandingPage.getFullName(driver));
        //Assert.assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
        Assert.assertTrue(LandingPage.getAccountWelcome(driver)); 
        //Assert.assertTrue(driver.findElement(By.className("logout")).isDisplayed());
          Assert.assertTrue(LandingPage.verifyLogoutButton(driver));	
        //Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
          Assert.assertTrue(LandingPage.verifyUrlContainsController(driver));
    }

    @Test
    public void checkoutTest() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
    	
    	  StartingPage.clickLogin(driver, wait);
       /* driver.findElement(By.id("email")).sendKeys(existingUserEmail);
        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
        driver.findElement(By.id("SubmitLogin")).click();*/
    	  
    	  StartingPage.clickLogin(driver, wait);
         
          LoginPage.enterEmail(driver, existingUserEmail);
         
          LoginPage.enterPassword(driver, existingUserPassword);
        
          LoginPage.clickLogin(driver);  
    	  
    	  
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
          
          LandingPage.clickLink(driver, wait, "Women");
          
        //driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).click();
          
          WomenPanel.openFirstItemDetail(driver);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
          
          ItemDetailPanel.addToCart(driver, wait);
       /* wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Proceed to checkout"))).click();*/
          
          
          CartUpdatePopup.proceedToCheckout(driver, wait);
          
          ShoppingCartSummary.proceedToCheckout(driver, wait);
          
          
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
          
          AddressConfirmationPage.proceed(driver, wait);
          
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
          
          ShippingOptionsPage.agree(driver, wait);
          
          
       // driver.findElement(By.name("processCarrier")).click();
          
          ShippingOptionsPage.proceed(driver, wait);
          
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
          
         PaymentDetailsPage.selectPaymentType(driver, wait, "bankwire");
          
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button"))).click();
         
         OrderConfirmationPage.confirm(driver, wait);
         
       // WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
         
         CompletedOrderPage.waitOrderConfirmationHeader(driver, wait);
         
        //Assert.assertEquals("ORDER CONFIRMATION", heading.getText());
         
         Assert.assertEquals("ORDER CONFIRMATION", CompletedOrderPage.getHeading(driver, wait));
         
        //Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
         
         Assert.assertTrue(CompletedOrderPage.verifyShipping4thStep(driver, wait));
         
         
         
        //Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
         Assert.assertTrue(CompletedOrderPage.verifyPayment5thStep(driver, wait));
         
         
        //Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
         Assert.assertTrue(CompletedOrderPage.verifyOrderCompleteMessage(driver, "Your order on My Store is complete."));
         
         
        //Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
         Assert.assertTrue(CompletedOrderPage.verifyOrderCompleteController(driver));
    }

	
}
