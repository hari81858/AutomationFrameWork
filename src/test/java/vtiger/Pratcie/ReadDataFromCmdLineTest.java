package vtiger.Pratcie;

import org.testng.annotations.Test;

public class ReadDataFromCmdLineTest {
	@Test
	public void readDataFromCmd()
	{
		String UN = System.getProperty("username");
		System.out.println(UN);
		String PWD = System.getProperty("password");
		System.out.println(PWD);
	}
}

