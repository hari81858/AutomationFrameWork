package vtiger.Pratcie;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractiec {
	
	@Test(retryAnalyzer = vitiger.GenericUtilities.RetryAnalyserImplementation.class)
	public void sample()
	{
		Assert.fail();
		System.out.println("Hi");
	}

}
