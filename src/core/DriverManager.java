package core;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DriverManager {
	
	@Before
	public void setUp() {
		
		Environment.setValue("testCasePath","D:\\TestCase.xlsx");
		Environment.setValue("configPath","D:\\Config.xlsx");
		Environment.setValue("testCaseSheet","InputData");
		Environment.setValue("configSheet","InputData");
		Environment.setValue("iterationSheet","Iteration");
		Environment.setValue("appiumDir","C:\\Program Files (x86)\\Appium\\node_modules");
		Environment.setValue("LogDir","D:\\ReportLog.html");
		
		TestCase.readTestCase(Environment.getValue("testCasePath"),Environment.getValue("testCaseSheet"));
		Config.readConfig(Environment.getValue("configPath"),Environment.getValue("configSheet"));
		
		if (Iteration.isIteration(Environment.getValue("testCasePath"),Environment.getValue("iterationSheet")))
		{
			Iteration.readIteration(Environment.getValue("testCasePath"),Environment.getValue("iterationSheet"));
		}	
		
		Executor.run();
		
	}

	@Test
	public void test() {

	}
	
}
