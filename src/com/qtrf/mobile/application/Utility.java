package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidDriver;

import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
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
	
	public static String sheetName(String parameter)
	{
		return parameter.substring(parameter.indexOf("[")+1,parameter.indexOf("[",parameter.indexOf("]")+1));
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
		case "id" : result=(!MOBILE.driverList.get(udid).findElementsById(path).isEmpty());
		break;
		case "xpath" : result=(!MOBILE.driverList.get(udid).findElementsByXPath(path).isEmpty());
		break;
		case "text" : result=(!MOBILE.driverList.get(udid).findElementsByName(path).isEmpty());
		break;
		case "class" : result=(!MOBILE.driverList.get(udid).findElementsByClassName(path).isEmpty());
		break;
		case "css" : result=(!MOBILE.driverList.get(udid).findElementsByCssSelector(path).isEmpty());	
		default: result=false;
		}
		if (Boolean.valueOf(value))
		{	
		 	return result;
		}
		else
		{
			return !result;
		}
	}
	
	public static void clickComponent(String udid,String path,String findOption)
	{
		switch(findOption)
		{
		case "id" : MOBILE.driverList.get(udid).findElementById(path).click();wait(2);
		break;
		case "xpath" : MOBILE.driverList.get(udid).findElementByXPath(path).click();wait(2);
		break;
		case "text" : MOBILE.driverList.get(udid).findElementByName(path).click();wait(2);
		break;
		case "class" : MOBILE.driverList.get(udid).findElementByClassName(path).click();wait(2);
		break;
		case "css" : MOBILE.driverList.get(udid).findElementByCssSelector(path).click();wait(2);
		default: System.out.println("Element not found");
		}
	}
	
	public static String getUdid(String parameter)
	{
		return Config.table.get("adb:RUN_"+parameter.charAt(parameter.length()-1));
	}
	
	public static void setText(String udid,String path,String findOption,String text)
	{
		switch(findOption)
		{
		case "id" : MOBILE.driverList.get(udid).findElementById(path).sendKeys(text);
		MOBILE.driverList.get(udid).hideKeyboard();
		wait(2);
		break;
		case "xpath" : MOBILE.driverList.get(udid).findElementByXPath(path).sendKeys(text);
		MOBILE.driverList.get(udid).hideKeyboard();
		wait(2);
		break;
		case "text" : MOBILE.driverList.get(udid).findElementByName(path).sendKeys(text);
		MOBILE.driverList.get(udid).hideKeyboard();
		wait(2);
		break;
		case "class" : MOBILE.driverList.get(udid).findElementByClassName(path).sendKeys(text);
		MOBILE.driverList.get(udid).hideKeyboard();
		wait(2);
		break;
		case "css" : MOBILE.driverList.get(udid).findElementByCssSelector(path).sendKeys(text);	
		MOBILE.driverList.get(udid).hideKeyboard();
		wait(2);
		break;
		default: System.out.println("Element not found");
		}
	}
	
	public static ArrayList<String> cloneTestStep(String application,String machine,String action,String parameter,String onErrorResumeNext,String remark)
	{
		ArrayList<String> virtualTestStep = new ArrayList<String>();
		virtualTestStep.add("");
		virtualTestStep.add(application);
		virtualTestStep.add(machine);
		virtualTestStep.add(action);
		virtualTestStep.add(parameter);
		virtualTestStep.add(onErrorResumeNext);
		virtualTestStep.add(remark);
		return virtualTestStep;
	}
	
	public static ArrayList<String> cloneTestStep(ArrayList<String> testStep)
	{
		ArrayList<String> virtualTestStep = new ArrayList<String>();
		virtualTestStep.addAll(testStep);
		return virtualTestStep;
	}

	public static void openApp(ArrayList<String> testStep,String appPackage,String appActivity,String appWaitActivity)
	{
		
		String udid = getUdid(testStep.get(2));
		int index = MOBILE.udidMap.get(udid);

		if (MOBILE.statusList.get(udid)==0)
		{
			MOBILE.capabilitiesList.get(udid).setCapability("appPackage", appPackage); 
			MOBILE.capabilitiesList.get(udid).setCapability("appActivity", appActivity);	
			
			if (appWaitActivity!="any")
			{
				MOBILE.capabilitiesList.get(udid).setCapability("appWaitActivity", appWaitActivity);	
			}
		
	    String url = "http://127.0.0."+(index+1)+":"+(4723+index)+"/wd/hub";
	    	      
	    wait(4);
	    
	    try {
	    	MOBILE.driverList.put(udid, new AndroidDriver(new URL(url), MOBILE.capabilitiesList.get(udid)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    
	    MOBILE.statusList.put(udid, 1);
	    MOBILE.driverList.get(udid).launchApp();
	    System.out.println("Start appium session number : "+index);
		}
		else
		{
			MOBILE.driverList.get(udid).startActivity(appPackage, appActivity);
		}
	}
	
	public static int divideSec(String startTime)
	{
		int result=Integer.parseInt(getCurrentSec())-Integer.parseInt(startTime);
		if (result<0)
		{
		return 60+result;
		}
		else
		{
		return result;
		}
	}
	
}
