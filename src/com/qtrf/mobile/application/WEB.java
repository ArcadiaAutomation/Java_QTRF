package com.qtrf.mobile.application;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.Environment;
import com.qtrf.core.TestStep;

import io.appium.java_client.android.AndroidDriver;

public class WEB {

	String runName;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	
	public WEB(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
	}
	
	static int count=-1;
	static Hashtable<String, Integer> windowMap= new Hashtable<String, Integer>();
    static Hashtable<String, WebDriver> driverList = new Hashtable<String, WebDriver>();
    
    public void actionMapper(TestStep testStep,Repository repository)
    {
    	switch(testStep.action.toUpperCase())
    	{
    	case "OPENBROWSER" : openBrowser(testStep,repository);
    	break;
    	case "CLOSEBROWSER" : closeBrowser(testStep);
    	break;
    	default : System.out.println("Action not found");
    	}
    }
    
	public void openBrowser(TestStep testStep,Repository repository){
		
		String[] parameter = utility.getParameter(testStep.parameter);
		
		String window = testStep.machine;
		if (windowMap.get(window)==null)
		{
		count=count+1;
		System.out.println("Start window number : "+count);
		windowMap.put(window, count);
		}
		
		driverList.put(window, new ChromeDriver());	
		driverList.get(window).manage().window().maximize();
		driverList.get(window).get(repository.table.get("url"));
	}
	
	public void closeBrowser(TestStep testStep){
		
		String window = testStep.machine;
		
		Integer index = windowMap.get(window);
		driverList.get(window).close();
		System.out.println("Close window number : "+index);
	}
}
