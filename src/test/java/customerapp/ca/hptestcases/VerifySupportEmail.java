package customerapp.ca.hptestcases;
import org.testng.Assert;
import org.testng.annotations.Test;

import customerapp.caobject.HPObject;
import testbase.TestBase;


public class VerifySupportEmail extends TestBase{

	@Test
	public static void verifySupportEmail(){
		HPObject HPO = new HPObject(driver);
		TestBase tb = new TestBase();
		tb.loadProperties();
		String expectedUrl= tb.pr.getProperty("url");
		driver.get(expectedUrl);
		tb.eWait(HPO.SEmailXpath);
		
		System.out.println(HPO.GetSupportEmail());
		Assert.assertEquals("admin@partrunner.com", HPO.GetSupportEmail());
		
		
	}
	
	
}
