package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidDriver;

import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration1;
import com.qtrf.core.LogManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utility extends Miscellaneous {
	
	public static void actionMapper(ArrayList<String> testStep)
	{
		switch(testStep.get(3).toUpperCase())
		{
		case "WAIT" : wait(Integer.parseInt(getParameter(testStep.get(4))[0]));
		break;
		default : System.out.println("Action not found");
		}
	}
	
	public static String parameterFromString(String parameter)
	{
		return parameter.substring(parameter.indexOf("'")+1,parameter.indexOf("'",parameter.indexOf("'")+1));
	}
	
	public static String[] getParameter(String parameter)
	{
		String[] str = parameter.split("\\|");
		if (parameter!="")
		{
		for (int i=0;i<str.length;i++)
		{
		str[i] = parameterFromString(str[i]);
		}
		return str;
		}
		return str;
	}
	
	public static boolean isComponentExist(String udid,String path,String findOption,String value)
	{
		boolean result=false;
		switch(findOption)
		{
		case "id" : result=(!Mobile.driverList.get(Mobile.udidMap.get(udid)).findElementsById(path).isEmpty());
		break;
		case "xpath" : result=(!Mobile.driverList.get(Mobile.udidMap.get(udid)).findElementsByXPath(path).isEmpty());
		break;
		case "text" : result=(!Mobile.driverList.get(Mobile.udidMap.get(udid)).findElementsByName(path).isEmpty());
		break;
		case "class" : result=(!Mobile.driverList.get(Mobile.udidMap.get(udid)).findElementsByClassName(path).isEmpty());
		break;
		case "css" : result=(!Mobile.driverList.get(Mobile.udidMap.get(udid)).findElementsByCssSelector(path).isEmpty());	
		default: result=false;
		}
		return result;
	}
	
	public static String getUdid(String parameter)
	{
		return Config.table.get("adb:RUN_"+parameter.charAt(parameter.length()-1));
	}
	
	public static void openApp(ArrayList<String> testStep,String appPackage,String appActivity,String appWaitActivity)
	{
		
		String udid = getUdid(testStep.get(2));
		int index = Mobile.udidMap.get(udid);
		System.out.println("Start session number "+index);
		
		if (Mobile.statusList.get(udid)==0)
		{
			Mobile.capabilitiesList.get(udid).setCapability("appPackage", appPackage); 
			Mobile.capabilitiesList.get(udid).setCapability("appActivity", appActivity);	
			
			if (appWaitActivity!="any")
			{
				Mobile.capabilitiesList.get(udid).setCapability("appWaitActivity", appWaitActivity);	
			}
		
	    String url = "http://127.0.0."+(index+1)+":"+(4723+index)+"/wd/hub";
	    	      
	    wait(5);
	    
	    try {
	    	Mobile.driverList.put(udid, new AndroidDriver(new URL(url), Mobile.capabilitiesList.get(udid)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    Mobile.statusList.put(udid, 1);
	    Mobile.driverList.get(udid).launchApp();
	    System.out.println("Start appium session number : "+index);
		}
		else
		{
			Mobile.driverList.get(udid).startActivity(appPackage, appActivity);
		}
	}
	
}
