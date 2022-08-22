package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TCAcknowledge extends BaseInit {

	public static void FedExTCAcknowledge() throws Exception  {
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		String svc = driver.findElement(By.id("lblServiceID")).getText();
		
		if( svc.equals("SD"))
		{
		WebElement Tick =wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTick")));
		jse.executeScript("arguments[0].click();",Tick);
		System.out.println("Submit the TCK stage");
		Thread.sleep(4000);
	    }
		
		if(svc.equals("LOC"))
		{
			WebElement Tick =wait.until(ExpectedConditions.elementToBeClickable(By.id("GreenTick")));
			jse.executeScript("arguments[0].click();",Tick);
			System.out.println("Submit the TCK stage");
			Thread.sleep(4000);
		}
	}
}
