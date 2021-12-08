package testbase;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import report.ExtentManager;



public class TestBase {
	
	public static WebDriver driver;
	public FileInputStream fis;
	public File f1;
	public Properties pr;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public ITestResult result;
		
	@BeforeSuite
	 public void BeforeSuite() {
		ExtentManager.setExtent();
	 }
	 
	 @AfterSuite
	 public void AfterSuite() {
		 ExtentManager.endReport();
	 }
	 
		
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formator = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	}
	
	
	
	public static WebDriver getBrowser(String browserName) {
		

		if(System.getProperty("os.name").contains("Windows")) {
			if(browserName.equalsIgnoreCase("chrome") || 
					browserName.equalsIgnoreCase("chromebrowser") ||
					browserName.equalsIgnoreCase("chrome browser")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver= new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("firefox") ||
					browserName.equalsIgnoreCase("firefox browser") ||
					browserName.equalsIgnoreCase("firefoxbrowser")){
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver= new FirefoxDriver(); 								
			}
			else if(browserName.equalsIgnoreCase("iebrowser") || 
					browserName.equalsIgnoreCase("ie browser") ||
					browserName.equalsIgnoreCase("internetexplorer") ||  
					browserName.equalsIgnoreCase("ie") ||
					browserName.equalsIgnoreCase("internet explorer") ||
					browserName.equalsIgnoreCase("internet explorer browser")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/drivers/iedriver.exe");
				driver= new InternetExplorerDriver(); 	
			}
		}

		else if(System.getProperty("os.name").contains("Mac")) {
			if(browserName.equalsIgnoreCase("chrome") || 
					browserName.equalsIgnoreCase("chromebrowser") ||
					browserName.equalsIgnoreCase("chrome browser")){
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir")+"/drivers/chromedriver");
				driver= new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("firefox") ||
					browserName.equalsIgnoreCase("firefox browser") ||
					browserName.equalsIgnoreCase("firefoxbrowser")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/geckodriver");
				driver= new FirefoxDriver(); 								
			}
			else if (browserName.equalsIgnoreCase("iebrowser") || 
					browserName.equalsIgnoreCase("ie browser") ||
					browserName.equalsIgnoreCase("internetexplorer") ||  
					browserName.equalsIgnoreCase("ie") ||
					browserName.equalsIgnoreCase("internet explorer") ||
					browserName.equalsIgnoreCase("internet explorer browser")){
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/iedriver.exe");
				driver= new InternetExplorerDriver(); 	

			}
		}
		return driver;
	}
	
	public void loadProperties() {
		pr = new Properties();
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
		try {
			fis = new FileInputStream(f1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pr.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\OR.properties");
		try {
			fis = new FileInputStream(f1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			pr.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@BeforeTest
		public void setDriver() {
			String browserName= "firefox";
			new TestBase().getBrowser(browserName);
						
		}
	
	public void eWait(String xPath) {
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
		
	}
	
	/*
	@Test
	//public static void main(String[] args)
	public static void main(){
		
		TestBase tb = new TestBase();
		tb.loadProperties();
		String expectedUrl= tb.pr.getProperty("url");
		driver.get(expectedUrl+"test");
		String actualUrl= driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
		//tb.getBrowser("firefox");
		System.out.println(tb.pr.getProperty("url"));
		System.out.println(tb.pr.getProperty("testname"));
		
		
	}
	*/
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	
	public void captureScreen(WebDriver driver, String imageName)
	{
		try {
			TakesScreenshot ts =(TakesScreenshot)driver;
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat formator= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File("./screenshots/"+imageName+"_"+formator.format(calendar.getTime())+".png");			
			FileUtils.copyFile(source, target);
			System.out.println("screenshot generated");
			} 
		catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static String getCurrentTime() {
	 
		     String currentDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());  
		     return currentDate;  
 
	}
	
	public static String screenShot(WebDriver driver,String filename) {
		  String dateName = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		  File finalDestination= new File(destination);
		  try {
		   FileUtils.copyFile(source, finalDestination);
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.getMessage();
		  }
		  return destination;
	}
}
