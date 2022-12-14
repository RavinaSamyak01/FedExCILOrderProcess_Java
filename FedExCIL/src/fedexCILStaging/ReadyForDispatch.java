package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ReadyForDispatch extends BaseInit {
	
	@Test
	public static void FedExRdyforDispatch() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
		Thread.sleep(9000);
		if (driver.findElement(By.id("cmbContacted")).isDisplayed()){
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("cmbContacted")));  
		    jse.executeScript("arguments[0].click();",email);
		    	Select list1 = new Select(email);
		    	  list1.selectByValue("number:377");
              System.out.println("email selected");
		}else {		
		Select Contacttype = new Select(driver.findElement(By.id("cmbContacted")));
		Contacttype.selectByVisibleText("Email");
		Thread.sleep(4000);
		}
		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblContactedValue1")));
		email.clear();
		email.sendKeys("tathya.gandhi@samyak.com");			
		Thread.sleep(4000);
		WebElement spoke = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSpokeWith")));
		spoke.clear();
		spoke.sendKeys("Abhishek");
		Thread.sleep(4000);
		
		WebElement Sendpualert = wait.until(ExpectedConditions.elementToBeClickable(By.id("GreenTickAlertPickup")));
		jse.executeScript("arguments[0].click();",Sendpualert);
		//logger.info("Submit the Rdy dsp stage");
		Thread.sleep(5000);
		
	}
	
}
