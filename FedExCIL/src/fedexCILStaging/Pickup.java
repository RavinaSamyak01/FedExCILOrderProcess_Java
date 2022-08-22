package fedexCILStaging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pickup extends BaseInit{
	
	public static void FedExPickup() throws Exception {
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
		for(int i=3; i<2;i++)
		{
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		
		Thread.sleep(4000);
		try {
			Thread.sleep(8000);
			String task = driver.findElement(By.xpath("/html/body/div[2]/section/div[2]/div[1]/div/div[2]/div[2]/div[1]/div[5]/label")).getText();
			if (task.contains("Basic Search")) {
				Thread.sleep(9000);
				
		String JobId = excelDataProvider.getData("Sheet1", i, 1);	
		driver.findElement(By.id("txtContains")).sendKeys(JobId);
		driver.findElement(By.id("txtContains")).sendKeys(Keys.TAB);
		Thread.sleep(10000);
		
		WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGlobalSearch")));
		jse.executeScript("arguments[0].click();",Search);
		Thread.sleep(10000);
		
			}else {}
			}
			catch(Exception e){
				System.out.println("Directly move to the Pickup");
				}
			}
		Thread.sleep(5000);
		WebElement ZoneID = driver.findElement(By.id("lblEditActuPickupTimeSZone"));
		String ZOneID = ZoneID.getText();
		System.out.println("ZoneID of is==" + ZOneID);

		if (ZOneID.equalsIgnoreCase("EDT")) {
			ZOneID = "America/New_York";
		} else if (ZOneID.equalsIgnoreCase("CDT")) {
			ZOneID = "CST";
		} else if (ZOneID.equalsIgnoreCase("PDT")) {
			ZOneID = "PST";
		}else if (ZOneID.equalsIgnoreCase("MDT")){
			   ZOneID = "MST";
		}
		
		WebElement Time = driver.findElement(By.id("txtEditActualPickupTime"));
		Time.clear();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));

		Time.sendKeys(dateFormat.format(date));
		Time.sendKeys(Keys.TAB);
		Thread.sleep(4000);
		
		WebElement Tab = driver.findElement(By.id("lblEditActuPickupTimeSZone"));
		 jse.executeScript("arguments[0].click();",Tab);
		 Thread.sleep(4000);
		 
		 String svc = driver.findElement(By.id("lblServiceID")).getText();
		 if(svc.equals("SD")) {
		 WebElement KScargo = driver.findElement(By.id("chkCargoFromConfirmation"));
		 jse.executeScript("arguments[0].click();", KScargo);
		 Thread.sleep(2000);
		 
		 
		WebElement ConfirmPu = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTickDropped")));  
	    jse.executeScript("arguments[0].click();",ConfirmPu);
	}
		 if(svc.equals("LOC"))
		 {
				WebElement ConfirmPu = wait.until(ExpectedConditions.elementToBeClickable(By.id("GreenTickDropped")));  
			    jse.executeScript("arguments[0].click();",ConfirmPu);			 
		 }
}
	}
