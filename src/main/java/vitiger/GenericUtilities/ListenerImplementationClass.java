package vitiger.GenericUtilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provide implementation to ITestListener Interface of TestNG
 * Example of Abstract method
 * @author HariKrishna mittanosala
 *
 */
public class ListenerImplementationClass implements ITestListener {
	
	 ExtentReports report;
	 ExtentTest test;
	 

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String METHODNAME = result.getMethod().getMethodName();
		System.out.println("------- Exeution stared----"+METHODNAME);
		
		test=report.createTest(METHODNAME);
	}
	
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String METHODNAME = result.getMethod().getMethodName();
		//System.out.println("------- pass ----"+METHODNAME);
		
		test.log(Status.PASS, "------- pass ----"+METHODNAME);

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String METHODNAME = result.getMethod().getMethodName();
		//System.out.println("------- failed -----"+METHODNAME);
		
		test.log(Status.FAIL, "------- failed -----"+METHODNAME);
		
		//System.out.println(result.getThrowable());
		
		test.log(Status.INFO,result.getThrowable());
		
		WebDriverUtility wUtil=new WebDriverUtility();
		JavaUtility jUtil=new JavaUtility();
		
		String screenShotName=METHODNAME+jUtil.getSystemDate();
		
		//Take screenShot for failed test scripts- to attach with bug rising
		try {
			wUtil.takeScreenShot(BaseClass.sdriver, screenShotName);
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String METHODNAME = result.getMethod().getMethodName();
		//System.out.println("------- skipped -----"+METHODNAME);
		
		test.log(Status.SKIP, "------- skipped -----"+METHODNAME);
		
		//System.out.println(result.getThrowable());
		
		test.log(Status.INFO, result.getThrowable());


	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("------ sute exection stared ------");
		
		//Configure the Extent Report
		ExtentSparkReporter htmlreport=new ExtentSparkReporter(".\\ExtentReports"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlreport.config().setDocumentTitle("Vtiger Exeution report");
		htmlreport.config().setReportName("Build 3 Vtiger Execution report");
		htmlreport.config().setTheme(Theme.DARK);
		
		
		//Generate report
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Browser", "Chrome");
		report.setSystemInfo("Base Platform", "Testing-Env");
		report.setSystemInfo("Base URL", "http://localhost:8888");
		report.setSystemInfo("Base OS", "Windows");
		report.setSystemInfo("Reporter", "Harikrishna");
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("------ sute exection ended ------");
		
		//Generate report
		report.flush();

	}
	
	

}
