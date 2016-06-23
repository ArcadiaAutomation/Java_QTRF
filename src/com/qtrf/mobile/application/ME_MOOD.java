package com.qtrf.mobile.application;

import com.qtrf.core.LogManager;
import io.appium.java_client.android.AndroidKeyCode;
import com.qtrf.core.Environment;
import com.qtrf.core.Config;
import com.qtrf.core.Iteration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;

public class ME_MOOD extends ME_MOOD_Repository {

	static String[] parameter;
	static String udid;
	static String otpNumber;
	
    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	udid = getUdid(testStep.get(2));
    	ini();
    	
    	switch(testStep.get(3).toUpperCase())
    	{
    	case "OPENAPP" : openApp(testStep,table.get("package"),table.get("activity"),table.get("waitActivity"));
    	break;
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "SELECTMESSAGE" : selectMessage(Integer.parseInt(parameter[0]));
    	break;
    	case "CLOSEAPP" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	break; 	
    	default : System.out.println("Action not found");
    	}
    }
    
    private static boolean isComponentExist(ArrayList<String> testStep)
    {
    	if(Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Component exist");
    		return true;
    	}
    	else
    	{
    		System.out.println("Component not exist");
    		return false;
    	}
    }
    
    private static void clickComponent(ArrayList<String> testStep)
    {
    	Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
    }
    
    private static String selectMessage(int index)
    {
    	List<WebElement> messageList = MOBILE.driverList.get(udid).findElementsById(table.get("messageList"));
    	for (int i=0;i<messageList.size();i++)
    	{
    	System.out.println(messageList.get(i).getAttribute("text"));
    	}
    	otpNumber=getOTP(messageList.get(messageList.size()-index).getAttribute("text"));
    	return otpNumber;
    }
    
	 private static String getOTP(String OTP)
	 {
		 int index = OTP.indexOf(":");
		 if (index!=-1)
		 {
		 System.out.println(OTP.substring(index+2,index+6));
		 return OTP.substring(index+2,index+6);
		 }
		 else
		 {
		System.out.println("OTP Not found in message");
		return "OTP Not found in message";
		 }
	 }
    
}

