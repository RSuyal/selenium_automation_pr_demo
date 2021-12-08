package report;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import testbase.TestBase;

public class ExtentManager {
	
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	 public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	 
	 public static void setExtent() {
	  htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+TestBase.getCurrentTime()+".html");
	  htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/main/java/config/extent_config.xml");
	  //htmlReporter.config().setDocumentTitle("Automation Test Report");
	  //htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
	  //htmlReporter.config().setTheme(Theme.DARK);
	  
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);
	  
	  extent.setSystemInfo("HostName", "MyHost");
	  extent.setSystemInfo("ProjectName", "PR Web Automation");
	  extent.setSystemInfo("Tester", "Rakesh");
	  extent.setSystemInfo("OS", "Win10");
	  extent.setSystemInfo("Browser", "Chrome");
	 }
	 public static void endReport() {
	  extent.flush();
	 }
		
	}
