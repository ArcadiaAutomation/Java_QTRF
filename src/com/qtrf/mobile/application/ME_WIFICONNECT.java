package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidKeyCode;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.Logger;
import com.qtrf.core.TestStep;

import com.qtrf.core.DriverManagerParallel;
import static org.testng.AssertJUnit.fail;

public class ME_WIFICONNECT extends ME_WIFICONNECT_Repository{

	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	ME_WIFICONNECT_Repository selfRepository = new ME_WIFICONNECT_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public ME_WIFICONNECT(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
	}
	
    public void actionMapper(TestStep testStep)
    {
    	parameter = utility.getParameter(testStep.parameter);
    	udid = utility.getUdid(testStep.machine);    	
    	
    	switch(testStep.action.toUpperCase())
    	{
    	case "OPENAPP" : utility.openApp(udid,table.get("package"),table.get("activity"),table.get("waitActivity"));
    	break;
    	case "SETENABLEWIFI" : 
    		if (!isWifiOpen())
    		MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).click();
    	break;
    	case "WAITUNTIL" : ;System.out.println("Wait until : "+waitUntil(testStep));
    	break;
    	case "VERIFYWIFI" : System.out.println("verify wifi : "+verifyWifi());
    	break;
    	case "SETDISABLEWIFI" : 
    		if (isWifiOpen())
    		MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).click();
    	break;
    	case "CLOSEAPP" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	break;
    	default : System.out.println("Action not found");
    	}    			
    }
	
	private boolean waitUntil(TestStep testStep)
	{
		return utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
	}
	
	
	public boolean isWifiOpen()
	{
		return MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).getAttribute("checked").equals("true");
	}
	
	
	public boolean verifyWifi()
	{
			if (parameter[0].toUpperCase().equals("ENABLE"))
			{
				return isWifiOpen();
			}
			else
			{
				return !isWifiOpen();
			}
	}
}
