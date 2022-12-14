package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmPuLOC extends ServiceDetail {
	
	public static void FedExConfirmPuAlertLOC() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		
		 for(int i=4; i<5;i++)
			{
			ExcelDataProvider excelDataProvider = new ExcelDataProvider();
			
			Thread.sleep(5000);
			try {
				Thread.sleep(5000);
				String task = driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div[1]/div/div[2]/div[2]/div[1]/div[5]/label")).getText();
				if (task.contains("Basic Search")) {
					Thread.sleep(5000);
					
			String JobId = excelDataProvider.getData("Sheet1", i, 1);	
			driver.findElement(By.id("txtContains")).sendKeys(JobId);
			driver.findElement(By.id("txtContains")).sendKeys(Keys.TAB);
			Thread.sleep(5000);
			
			WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGlobalSearch")));
			jse.executeScript("arguments[0].click();",Search);
			Thread.sleep(5000);
			
				}else {}
				}
				catch(Exception e){
					System.out.println("Directly move to the ComfimPuAlert");
					}
				}
		 if (driver.findElement(By.id("cmbContacted")).isDisplayed()){
				WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("cmbContacted")));  
			    jse.executeScript("arguments[0].click();",email);
			    
			    	Select list1 = new Select(email);
			    	  list1.selectByValue("number:377");
	              System.out.println("email selected");
			}else {	

			}		
			
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblContactedValue1")));
			email.clear();
			email.sendKeys("tathya.gandhi@samyak.com");			
			Thread.sleep(4000);
			
			WebElement spoke = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSpokeWith")));
			spoke.clear();
			spoke.sendKeys("Tathya");
			Thread.sleep(4000);
			
			WebElement ConfirmPu = wait.until(ExpectedConditions.elementToBeClickable(By.id("GreenTickAlertPickup")));
			jse.executeScript("arguments[0].click();",ConfirmPu);
			Thread.sleep(5000);
			System.out.println("submit the Comfirm pu stage at LOC");
	}

}
