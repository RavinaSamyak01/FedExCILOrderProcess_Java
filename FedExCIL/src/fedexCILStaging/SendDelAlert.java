package fedexCILStaging;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SendDelAlert extends BaseInit {
    @Test
	public static void delAlert() throws Exception
	{
		Thread.sleep(4000);
		
		Select Contacttype = new Select(driver.findElement(By.id("cmbContacted")));
		Contacttype.selectByVisibleText("Email");
		Thread.sleep(1000);
		
		driver.findElement(By.id("lblContactedValue1")).clear();
		driver.findElement(By.id("lblContactedValue1")).sendKeys("tathya.gandhi@samyak.com");			
		
		driver.findElement(By.id("txtSpokeWith")).clear();
		driver.findElement(By.id("txtSpokeWith")).sendKeys("Tathya");
		Thread.sleep(1000);
		
		driver.findElement(By.id("GreenTickAlertPickup")).click();
		//logger.info("Submit the Send Del Alert");
		Thread.sleep(5000);
	}
}
