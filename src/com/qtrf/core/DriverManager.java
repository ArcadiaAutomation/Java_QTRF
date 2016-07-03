package com.qtrf.core;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qtrf.mobile.application.MOBILE;;

public class DriverManager {
	
	@Before
	public void setUp() {
		
		LogManager.generateLog("QTRF Framework","PoC","Atlas");
		
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
	}

	
	@Test
	public void test() {
		MOBILE.initialize();
		Executor.run();
	}
	
	@After
	public void report()
	{
		LogManager.closeLog();
		LogManager.generateHTML();	
		try {
			Runtime.getRuntime().exec("cmd.exe /c "+Environment.getValue("LogDir"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
