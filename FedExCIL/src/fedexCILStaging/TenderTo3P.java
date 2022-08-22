package fedexCILStaging;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TenderTo3P extends ServiceDetail {
	
	@Test
	public static void tndrTo3P() throws Exception
	{
		Thread.sleep(10000);
		String stg = driver.findElement(By.id("lblStages")).getText();
			if(stg.contains("TENDER TO 3P"))
			{
				JavascriptExecutor jse = (JavascriptExecutor) driver;// scroll,click
				WebDriverWait wait = new WebDriverWait(driver, 50);// wait time
				
		Thread.sleep(4000);
		
		
		WebElement editjob = wait.until(ExpectedConditions.elementToBeClickable(By.id("idEditOrder")));
		jse.executeScript("arguments[0].click();",editjob);
		Thread.sleep(4000);
		
		WebElement shiplabel = wait.until(ExpectedConditions.elementToBeClickable(By.id("hlkShiplabel")));
		jse.executeScript("arguments[0].click();",shiplabel);
		Thread.sleep(5000);
		
		Select p3acc = new Select(driver.findElement(By.id("cmbCarrier")));
		p3acc.selectByVisibleText("1 (510087763)");
		Thread.sleep(4000);
		
		Select Contacttype = new Select(driver.findElement(By.id("cmbServiceType")));
		Contacttype.selectByVisibleText("FEDEX_GROUND");
		Thread.sleep(4000);
		
//		WebElement txtacc = driver.findElement(By.id("txtAccount"));
//		txtacc.clear();
//		txtacc.sendKeys("510087763");
//		txtacc.sendKeys(Keys.TAB);
//		Thread.sleep(2000);
		
		driver.findElement(By.id("btnSubmit")).click();
		Thread.sleep(8000);
		
        if(driver.findElement(By.id("btnSignIn")).isDisplayed()) {
        	WebElement Ok = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignIn")));
            jse.executeScript("arguments[0].click();",Ok);
            Thread.sleep(4000);
        }else {}
		WebElement close = driver.findElement(By.id("idanchorclose"));
		jse.executeScript("arguments[0].click();",close);
		Thread.sleep(4000);
		WebElement save = driver.findElement(By.id("btnSaveChanges"));
		jse.executeScript("arguments[0].click();",save);
		Thread.sleep(4000);
		//driver.findElement(By.id("btnSaveExit")).click();
		WebElement jobstatus = wait.until(ExpectedConditions.elementToBeClickable(By.id("idJobOverview")));
		jse.executeScript("arguments[0].click();",jobstatus);
		Thread.sleep(4000);
//		String tzone = driver.findElement(By.id("Spn3pTime")).getText();
//		String tndrtime = getTime(tzone);
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		SimpleDateFormat tndrtime = new SimpleDateFormat("hh:mm");
//		
//		tndrtime.setTimeZone(TimeZone.getTimeZone("EDT"));
//		//System.out.println(tndrtime.format(calendar.getTime()));    

		WebElement ZoneID = driver.findElement(By.id("Spn3pTime"));
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

		WebElement Time = driver.findElement(By.id("txt3pTime"));
		Time.clear();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone(ZOneID));

		Time.sendKeys(dateFormat.format(date));
		Time.sendKeys(Keys.TAB);
//		driver.findElement(By.id("txt3pTime")).sendKeys(rdytime);
//		driver.findElement(By.id("txt3pTime")).sendKeys(Keys.TAB);
		Thread.sleep(3000);
		driver.findElement(By.id("GreenTick")).click();
		//logger.info("Submit the Tender to 3p stage");
		System.out.println("Submit the Tender to 3p stage");	
			}
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
