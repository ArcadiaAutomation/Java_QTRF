package com.qtrf.core;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import com.qtrf.mobile.application.Executor;
import com.qtrf.mobile.application.MOBILE;;

public class DriverManagerParallel {
	
	public static boolean parallel = false;
		
	@BeforeTest
	@Parameters({"parallel"})
	public void setUp(String parallel)
	{
		DriverManagerParallel.parallel=Boolean.getBoolean(parallel);
	}
	
	@Test
	@Parameters({"runName","testCaseDir","configDir","logDir"})
	public void test(String runName,String testCaseDir,String configDir,String logDir) 
	//public void test() 
	{
		//String runName = "test01";
		TestCase testCase = new TestCase(runName);	
		Config config = new Config(runName);
		Iteration iteration = new Iteration(runName);
		Environment environment = new Environment();
		
		//environment.setValue("testCasePath","D:\\TestCase.xlsx");
		environment.setValue("testCasePath",testCaseDir);
		//environment.setValue("configPath","D:\\Config.xlsx");
		environment.setValue("configPath",configDir);
		environment.setValue("testCaseSheet","InputData");
		environment.setValue("configSheet","InputData");
		environment.setValue("iterationSheet","Iteration");
		environment.setValue("appiumDir","C:\\Program Files (x86)\\Appium\\node_modules");
		//environment.setValue("logDir","D:\\ReportLog.html");
		environment.setValue("logDir",logDir);
		
		Logger logger = new Logger(environment.getValue("logDir"));
		LogManager.logTable.put(runName, logger);
		Executor executor = new Executor(runName);
		
		logger.generateLog("QTRF Framework","PoC","Arcadia.Atlas");
		
		testCase.readTestCase(environment.getValue("testCasePath"),environment.getValue("testCaseSheet"));
		config.readConfig(environment.getValue("configPath"),environment.getValue("configSheet"));
		
		if (iteration.isIteration(environment.getValue("testCasePath"),environment.getValue("iterationSheet")))
		{
			iteration.readIteration(environment.getValue("testCasePath"),environment.getValue("iterationSheet"));
		}	

//		mobile.initialize();
		executor.run(testCase,config,environment);
	}
	
	@AfterTest
	@Parameters({"runName","logDir"})
	public void report(String runName,String logDir)
	//public void report()
	{
		//String runName = "test01";
		//String logDir = "D:\\ReportLog.html";
		
		LogManager.logTable.get(runName).closeLog();
		LogManager.logTable.get(runName).generateHTML();	
		try {
			Runtime.getRuntime().exec("cmd.exe /c "+logDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
