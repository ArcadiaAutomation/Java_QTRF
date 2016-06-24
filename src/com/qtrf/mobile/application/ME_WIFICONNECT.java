package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidKeyCode;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;

public class ME_WIFICONNECT extends ME_WIFICONNECT_Repository{

	static String[] parameter;
	static String udid;	
	
    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	udid = getUdid(testStep.get(2));    	
    	ME_WIFICONNECT_Repository.ini();
    	
    	switch(testStep.get(3).toUpperCase())
    	{
    	case "OPENAPP" : openApp(testStep,table.get("package"),table.get("activity"),table.get("waitActivity"));
    	break;
    	case "SETENABLEWIFI" : 
    		if (MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).getAttribute("checked").equals("false"))
    		MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).click();
    	break;
    	case "WAITUNTIL" : waitUntil();
    	break;
    	case "VERIFYWIFI" : System.out.println("verify wifi : "+verifyWifi());
    	break;
    	case "SETDISABLEWIFI" : 
    		if (MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).getAttribute("checked").equals("true"))
    		MOBILE.driverList.get(udid).findElement(By.id(table.get("wifiToggle"))).click();
    	break;
    	case "CLOSEAPP" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	break;
    	default : System.out.println("Action not found");
    	}    			
    }
	
	public static void waitUntil()
	{
		wait(10);
		while (!MOBILE.driverList.get(udid).findElementsById(table.get("connecting")).isEmpty())
		{
			wait(1);
		}
	}
	
	public static boolean verifyWifi()
	{
		if (MOBILE.driverList.get(udid).findElementsById(table.get("ssid")).isEmpty())
		{
			System.out.println("Wifi not found");
			return false;
		}
		else
		{
		if (!MOBILE.driverList.get(udid).findElementsById(table.get("connected")).isEmpty())
		{
			System.out.println(parameter[0]);
			if (parameter[0].toUpperCase()=="ENABLE")
			{
				System.out.println("THIS 1");
				return false;
			}
			else
			{
				System.out.println("THIS 2");
				return true;
			}
		}
		else
		{
			if (MOBILE.driverList.get(udid).findElementsById(table.get("ssid")).get(0)=="@AIS_SMART")
			{
				System.out.println("THIS 3");
				return true;
			}
			else
			{
				System.out.println("THIS 4");
				return false;
			}
		}
		}
	}
    
}
