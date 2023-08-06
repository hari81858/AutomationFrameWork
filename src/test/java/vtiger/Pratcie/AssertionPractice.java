package vtiger.Pratcie;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionPractice {
	@Test()
	public void simpleTest()
	{
		int a=1;
		int b=1;
		System.out.println("step1");
		System.out.println("step2");
		assertEquals(b, a);
		System.out.println("step3");
		System.out.println("step4");
		System.out.println("step5");
		System.out.println("step6");

	}

	@Test()
	public void simpleTest1()
	{
		SoftAssert sa=new SoftAssert();
		
		int a=1;//expect
		int b=2;//actual
		
		System.out.println("step1");
		sa.assertEquals(b, a);
		System.out.println("step2");
		System.out.println("step3");
		
		sa.assertEquals(false, false);
		
		System.out.println("step4");
		System.out.println("step5");
		System.out.println("step6");
		
		sa.assertAll();

	}
}
