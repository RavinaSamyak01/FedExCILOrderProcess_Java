package fedexCILStaging;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Recover extends ServiceDetail {

	@Test
	public static void recoverAtDestination() throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
//		String tzone = driver.findElement(By.id("lblActualRecoverTimeSZone0")).getText();//lblActualRecoverTimeSZone0
//		String rectime = getTime(tzone);
//		driver.findElement(By.id("txtDeliveRecoverTime")).sendKeys(rdytime);
//		driver.findElement(By.id("txtDeliveRecoverTime")).sendKeys(Keys.TAB);
//		driver.findElement(By.id("txtDeliveRecoverDate")).sendKeys(Keys.TAB);
		Thread.sleep(6000);
		WebElement ZoneID = driver.findElement(By.id("lblActualRecoverTimeSZone0"));
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

		WebElement Time = driver.findElement(By.id("txtDeliveRecoverTime"));
		Time.clear();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));

		Time.sendKeys(dateFormat.format(date));
		Time.sendKeys(Keys.TAB);
		
		Thread.sleep(3000);
		WebElement tab = driver.findElement(By.id("lblDeliverySignature"));
		tab.click();
		WebElement Recover = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGreenTickDropped")));
		jse.executeScript("arguments[0].click();",Recover);
		Thread.sleep(5000);
		//logger.info("Submit the Recover stage");
		}
	
	public static String getTime(String timeZone)
	{
		
		LocalDateTime localNow = LocalDateTime.now(TimeZone.getTimeZone(timeZone).toZoneId());
	System.out.println(localNow);
	//DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;     
	String text = Integer.toString(localNow.getHour());
	String text1 = "";
    if(localNow.getMinute()<10) {
    	text1 = "0".concat(Integer.toString(localNow.getMinute()));
    }
    else {
    	text1 = Integer.toString(localNow.getMinute());
    }
	text = text.concat(text1);
	
	System.out.println(text);
	return text;
	}
}
