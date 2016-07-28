package com.qtrf.mobile.application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.Logger;
import com.qtrf.core.TestStep;

import io.appium.java_client.android.AndroidDriver;
 
public class MOBILE{

	String runName;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	
	public MOBILE(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
		dir = new File(environment.getValue("appiumDir"));
	}
	
	static int count=-1;
	static Hashtable<String, Integer> udidMap= new Hashtable<String, Integer>();
	File dir;
    static Hashtable<String, DesiredCapabilities> capabilitiesList = new Hashtable<String, DesiredCapabilities>();
    static Hashtable<String, AndroidDriver> driverList = new Hashtable<String, AndroidDriver>();
    static Hashtable<String, Integer> statusList = new Hashtable<String, Integer>();
    
//    public void initialize()
//    {
//    	count = -1;
//    	udidMap.clear();
//    	capabilitiesList.clear();
//    	driverList.clear();
//    	statusList.clear();
//    }
    
    
    public void actionMapper(TestStep testStep)
    {
    	switch(testStep.action.toUpperCase())
    	{
    	case "CONNECTDEVICE" : connectDevice(utility.getUdid(testStep.machine));
    	break;
    	case "CLOSEDEVICE" : closeDevice(utility.getUdid(testStep.machine));
    	break;
    	default : System.out.println("Action not found");
    	}
    }
    
	public void connectDevice(String udid){
		
		if (udidMap.get(udid)==null)
		{
		count=count+1;
		System.out.println("Start appium node number : "+count);
		udidMap.put(udid, count);
		
		if (DriverManagerParallel.parallel)
		{
		    try {
				Runtime.getRuntime().exec("cmd.exe /c \"start node appium -p "+(4725+(2*count))+" -bp "+(4726+(2*count))+" -a 127.0.0.1 --session-override --nodeconfig D:\\Software\\Parallel\\nodeConfig"+(count+1)+".json\"",null,dir);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		else
		{
			try {
				Runtime.getRuntime().exec("cmd.exe /c \"start node appium -p "+(4725+(2*count))+" -bp "+(4726+(2*count))+" -a 127.0.0.1 --selendroid-port "+(8080+count)+" --session-override\"",null,dir);
				System.out.println("-p"+(4725+(2*count)));
				System.out.println("-bp"+(4726+(2*count)));
				System.out.println("-sd"+(8080+count));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
	    capabilitiesList.put(udid, new DesiredCapabilities());
	    capabilitiesList.get(udid).setCapability("deviceName", "any");
		capabilitiesList.get(udid).setCapability("udid", udid);	
		capabilitiesList.get(udid).setCapability("autoLaunch","false");
		capabilitiesList.get(udid).setCapability("appPackage", "any"); 
		capabilitiesList.get(udid).setCapability("appActivity", "any");
		
		statusList.put(udid, 0);
		}
	}
	
	public void closeDevice(String udid){
		Integer index = udidMap.get(udid);
		driverList.get(udid).quit();
		System.out.println("Close appium session number : "+index);
	}	
		
}