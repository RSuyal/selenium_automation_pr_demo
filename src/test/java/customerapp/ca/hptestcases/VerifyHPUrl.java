package customerapp.ca.hptestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import customerapp.caobject.HPObject;
import testbase.TestBase;

public class VerifyHPUrl extends TestBase{
		
		@Test
		//public static void main(String[] args)
		public static void homePageURL_Positive(){
			
			TestBase tb = new TestBase();
			tb.loadProperties();
			String expectedUrl= tb.pr.getProperty("url");
			driver.get(expectedUrl);
			String actualUrl= driver.getCurrentUrl();
			Assert.assertEquals(expectedUrl, actualUrl);
			//tb.getBrowser("firefox");
			System.out.println(tb.pr.getProperty("url"));
			System.out.println(tb.pr.getProperty("testname"));
			
		}

		

}
