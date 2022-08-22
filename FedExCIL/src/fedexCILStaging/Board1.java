package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Board1 extends ServiceDetail {

	@Test
	public static void onBoard1() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		try {
			Thread.sleep(10000);
			String stg = driver.findElement(By.id("lblStages")).getText();
				if(stg.contains("ON BOARD"))
				{
					Thread.sleep(4000);

	 WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTick")));
	 jse.executeScript("arguments[0].click();",update);
				}
				else {}
		}
		catch (Exception e) {
			System.out.println("Wait for Board1 processed!!");
			}
//	 if(driver.findElement(By.id("btnGreenTick")).isDisplayed()){
//		 WebElement update1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTick")));
//		 jse.executeScript("arguments[0].click();",update1);
//		 Thread.sleep(4000);
//	 }
//	 else {
//	 }
//	 
	}
		}
	
