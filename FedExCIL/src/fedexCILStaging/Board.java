package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Board extends ServiceDetail{

	
	 @Test
		public static void onBoard() throws Exception
		{
			JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
			WebDriverWait wait = new WebDriverWait(driver, 50);//

		 WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTick")));
		 jse.executeScript("arguments[0].click();",update);
		 Thread.sleep(10000);
		 
		}
}
