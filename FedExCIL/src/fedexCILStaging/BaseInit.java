package fedexCILStaging;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseInit extends FedExCILOrderCreation {
	public static Properties storage = null;
	public static WebDriver driver;
	public static Logger logger;

	String driverPath = ("D:\\Tathya\\selenium\\chromedriver_win32\\chromedriver.exe");
	
	@BeforeSuite
	public static void base() throws Exception
	{
		
		System.out.println("lunching chrome browser");
		//System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get("http://10.20.104.71:8072/");
		//String logFilename = this.getClass().getSimpleName();
		//logger = Logger.getLogger(logFilename);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.get("http://10.20.104.71:8072/");
		Login("tathya", "tathya");
		
		// Go to Operation --> TaskLog
		Thread.sleep(5000);
		  WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

		Actions actions = new Actions(driver);
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("a_operations"))));
        WebElement operation = wait.until(ExpectedConditions.elementToBeClickable(By.id("a_operations")));
        Thread.sleep(3000);
		//new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("a_operations"))));
		actions.moveToElement(operation).click().build().perform();
		//new WebDriverWait(driver, 8).until(ExpectedConditions.visibilityOf(driver.findElement(By.id("a_TaskLog"))));
		new WebDriverWait(driver, 8).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("a_TaskLog"))));
		actions.click(driver.findElement(By.id("a_TaskLog"))).build().perform();
		
		
		 //Move to ServiceDetail Class
		ServiceDetail.SvcDetail();
	}
		  @AfterSuite
		  public void logout() throws InterruptedException {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				

				//Logout
				 WebElement logout = driver.findElement(By.partialLinkText("LOGOUT"));
				 jse.executeScript("arguments[0].click();",logout);
			       System.out.println("Logout");
				   //logger.info("Logout");
//				   
//				
				Thread.sleep(5000);
				driver.close();
				
	}
  public static void Login(String username, String password) throws InterruptedException {
		driver.findElement(By.id("inputUsername")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		WebElement Signin = driver.findElement(By.xpath("//*[@id=\"btnLogin\"]"));
		Signin.click();

	}
	}


