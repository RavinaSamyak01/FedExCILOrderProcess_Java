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
import org.testng.annotations.Test;

public class WaitForDeptarture extends ServiceDetail {
	
    @Test
	public static void waitForDept() throws Exception
	{
    	JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);//
		
				Thread.sleep(6000);
				WebElement ZoneID = driver.findElement(By.id("lblEditDepartureTimeSZone"));
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

				WebElement Time = driver.findElement(By.id("txtDepartureTime"));
				Time.clear();
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));

				Time.sendKeys(dateFormat.format(date));
				Time.sendKeys(Keys.TAB);
				Thread.sleep(6000);
				WebElement Greytick = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnWaitGreyTick")));
				jse.executeScript("arguments[0].click();",Greytick);
				//logger.info("Submit the Wait for Departure stage");
				Thread.sleep(4000);

	}
}
