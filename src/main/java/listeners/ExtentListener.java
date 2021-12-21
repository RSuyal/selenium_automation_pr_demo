package listeners;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import report.ExtentManager;
import testbase.TestBase;

public class ExtentListener extends ExtentManager implements ITestListener {


	public void onTestStart(ITestResult result) {
		test = ExtentManager.extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.get().log(Status.PASS, "PASS Test case is: " + result.getMethod().getMethodName());
		}
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.get().log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName() + " - Test Case Failed", ExtentColor.RED));
			extentTest.get().log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			String pathString = TestBase.screenShot(TestBase.driver, result.getMethod().getMethodName());
			try {
				extentTest.get().addScreenCaptureFromPath(pathString);
			} catch (IOException e) {
				extentTest.get().info("Test FAiled, cannot attach screenshot ");
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			extentTest.get().log(Status.SKIP, "SKIPPED Test case is: " + result.getMethod().getMethodName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}	
}