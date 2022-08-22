package fedexCILStaging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FedExCILOrderCreation {
	public static Properties storage = new Properties();

	static WebDriver driver;
	static StringBuilder msg = new StringBuilder();
	static String jobid;
	static double OrderCreationTime;
	public static ExtentReports report;
	public static ExtentTest test;

	@BeforeMethod
	public void login(Method method) throws InterruptedException, IOException {
		storage = new Properties();
		FileInputStream fi = new FileInputStream(".\\TestFiles\\config.properties");
		storage.load(fi);
		startTest();
		DesiredCapabilities capabilities = new DesiredCapabilities();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		// options.addArguments("headless");
		options.addArguments("--incognito");
		options.addArguments("--test-type");
		options.addArguments("--no-proxy-server");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--start-maximized");

		//options.addArguments("--headless");
		// options.addArguments("window-size=1366x788");
		capabilities.setPlatform(Platform.ANY);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(options);
		// Default size
		Dimension currentDimension = driver.manage().window().getSize();
		int height = currentDimension.getHeight();
		int width = currentDimension.getWidth();
		System.out.println("Current height: " + height);
		System.out.println("Current width: " + width);
		System.out.println("window size==" + driver.manage().window().getSize());

		// Set new size
		Dimension newDimension = new Dimension(1366, 788);
		driver.manage().window().setSize(newDimension);

		// Getting
		Dimension newSetDimension = driver.manage().window().getSize();
		int newHeight = newSetDimension.getHeight();
		int newWidth = newSetDimension.getWidth();
		System.out.println("Current height: " + newHeight);
		System.out.println("Current width: " + newWidth);

		String Env = storage.getProperty("Env");
		System.out.println("Env " + Env);
		String baseUrl = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			baseUrl = storage.getProperty("PREPRODURL");
		} else if (Env.equalsIgnoreCase("STG")) {
			baseUrl = storage.getProperty("STGURL");
		} else if (Env.equalsIgnoreCase("DEV")) {
			baseUrl = storage.getProperty("DEVURL");
		}
		Thread.sleep(2000);
		driver.get(baseUrl);

		Thread.sleep(5000);
	
		 //public void testMethodName(Method method)
		 



		     String testName = method.getName();
		      test = report.startTest(testName);

		 }
	
	public static void startTest() {
		// You could find the xml file below. Create xml file in your project and copy
		// past the code mentioned below

		System.setProperty("extent.reporter.pdf.start", "true");
		System.setProperty("extent.reporter.pdf.out", "./Report/PDFExtentReport/ExtentPDF.pdf");

		// report.loadConfig(new File(System.getProperty("user.dir")
		// +"\\extent-config.xml"));
		report = new ExtentReports("./Report/ExtentReport/ExtentReportResults.html", true);
		// test = report.startTest();
	}

	public static void endTest() {
		report.endTest(test);
		report.flush();
	}

	@Test
	public static void fedEXCILOrder() throws Exception {
		
		long start, end;
		WebDriverWait wait = new WebDriverWait(driver, 5);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		// Read data from Excel
		File src = new File(".\\TestFiles\\FedExCILTestResult.xlsx");
		FileInputStream fis = new FileInputStream(src);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sh1 = workbook.getSheet("Sheet1");

		for (int i = 1; i<5; i++) {
			DataFormatter formatter = new DataFormatter();
			String file = formatter.formatCellValue(sh1.getRow(i).getCell(0));
			// String TFolder=".//TestFiles//";
			driver.findElement(By.id("MainContent_ctrlfileupload"))
					.sendKeys("C:\\Users\\tgandhi\\FedExCIL\\FedExCIL\\FedExCIL\\TestFiles\\" + file + ".txt");
			Thread.sleep(1000);
			driver.findElement(By.id("MainContent_btnProcess")).click();
			// --start time
			start = System.nanoTime();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent_lblresult")));
			String Job = driver.findElement(By.id("MainContent_lblresult")).getText();
			end = System.nanoTime();
			OrderCreationTime = (end - start) * 1.0e-9;
			System.out.println("Order Creation Time (in Seconds) = " + OrderCreationTime);
			msg.append("Order Creation Time (in Seconds) = " + OrderCreationTime + "\n");

			try {
				if (Job.contains("<LoadTenderResult>")) {

					// System.out.println(Job);

					Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
					Matcher matcher = pattern.matcher(Job);
					matcher.find();
					jobid = matcher.group();
					System.out.println("JOB# " + jobid);

					File src1 = new File(".\\TestFiles\\FedExCILTestResult.xlsx");
					FileOutputStream fis1 = new FileOutputStream(src1);
					Sheet sh2 = workbook.getSheet("Sheet1");
					sh2.getRow(i).createCell(1).setCellValue(jobid);
					workbook.write(fis1);
					fis1.close();
					msg.append("JOB # " + jobid + "\n");
					getScreenshot(driver, "FedExCILResponse");

				} else {
					msg.append("Response== " + Job + "\n");
					msg.append("Order not created==FAIL" + "\n");
					getScreenshot(driver, "FedExCILResponse");

				}
			} catch (Exception e) {
				msg.append("Order not created==FAIL" + "\n");
				msg.append("Response== " + Job + "\n");
				getScreenshot(driver, "FedExCILResponse");

			}

		}
		Thread.sleep(5000);
	    BaseInit.base();	
	

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			// test.log(LogStatus.FAIL, "Test Case Failed is " +
			// result.getThrowable().getMessage());
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			// To capture screenshot path and store the path of the screenshot in the string
			// "screenshotPath"
			// We do pass the path captured by this mehtod in to the extent reports using
			// "logger.addScreenCapture" method.
			String screenshotPath = getFailScreenshot(driver, result.getName());
			// To add it in the extent report
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Case Pass is " + result.getName());
			String screenshotPath = getScreenshot(driver, result.getName());
			// To add it in the extent report
			test.log(LogStatus.PASS, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
	}

	
	@AfterSuite
	public void SendEmail() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, 50);// wait time

//		//Logout
//		 WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("LOGOUT")));
//		 jse.executeScript("arguments[0].click();",logout);
//	       System.out.println("Logout");
//		   //logger.info("Logout");

	       Thread.sleep(5000);
			report.flush();
			Thread.sleep(5000);
			endTest();
			
//		Thread.sleep(5000);
//		driver.close();
		
		
		System.out.println("====Sending Email====");
		
		String Env = storage.getProperty("Env");

		String subject = "Selenium Automation Script: " + Env + " FedEx_CIL EDI - Shipment Creation";

		//String File = ".//TestFiles//FedExCILResponse.png";
	      String File =   System.getProperty("user.dir")+"\\Report\\ExtentReport\\ExtentReportResults.html";


		try {
			
			//Email.sendMail("ravina.prajapati@samyak.com,asharma@samyak.com,parth.doshi@samyak.com,tathya.gandhi@samyak.com", subject,
			  Email.sendMail("tathya.gandhi@samyak.com,ravina.prajapati@samyak.com", subject,	
			msg.toString(), File);
		} catch (Exception ex) {
			Logger.getLogger(FedExCILOrderCreation.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
 
	@AfterTest
	public void Complete() throws Exception {
		driver.close();
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	public static String getFailScreenshot(WebDriver driver, String screenshotName) throws Exception {
		// String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/Report/FailedTestsScreenshots/" + screenshotName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
