package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidKeyCode;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;
import com.qtrf.core.Executor;



public class ME_IN_ALLFORONE extends ME_IN_ALLFORONE_Repository {

	static String[] parameter;
	static String udid;

    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	udid = getUdid(testStep.get(2));
    	ini();
    	
    	switch(testStep.get(3).toUpperCase())
    	{
    	case "OPENAPP" : 
    	try {
    	openApp(testStep,table.get("package"),table.get("activity"),table.get("waitActivity"));
		LogManager.addStep("openApp", "Switch application", "Switch application", "pass", "");
		} catch (Exception e) {
			LogManager.addStep("openApp", "Switch application", e.toString(), "fail", "");	
		}
    	break;
    	case "OPENSUBAPP" : 
    	try {
    	openSubApp(testStep);
		LogManager.addStep("openSubApp", "Open sub application", "Open sub application", "pass", "");
		} catch (Exception e) {
			LogManager.addStep("openSubApp", "Open sub application", e.toString(), "fail", "");	
		}
    	break;
    	case "COMPONENTISEXIST" : 
    	try{
    	isComponentExist(testStep);
		LogManager.addStep("ComponentIsExist", "Verify component : true", "Verify component : true", "pass", "");
		} catch (Exception e) {
			LogManager.addStep("ComponentIsExist", "Verify component : true", e.toString(), "fail", "");	
		}    		
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "SETTEXT" : setText(testStep);
    	break;
    	case "SETOTP" : setOTP(testStep);
    	break;
    	case "CLOSEAPP" : closeApp();
    	break;
    	default : System.out.println("Action not found");
    	}
    }
    
    private static boolean isComponentExist(ArrayList<String> testStep)
    {
    	if(Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Verify component : true");
    		return true;
    	}
    	else
    	{
    		System.out.println("Verify component : false");
    		return false;
    	}
    }

    private static void clickComponent(ArrayList<String> testStep)
    {
    	Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
    }

    
    private static void setText(ArrayList<String> testStep)
    {
    	Utility.setText(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]);
    }
    
    private static void setOTP(ArrayList<String> testStep)
    {
    	Utility.setText(udid, table.get(parameter[0]), typeTable.get(parameter[0]), ME_MOOD.otpNumber);
    }
    
    private static void openSubApp(ArrayList<String> testStep)
    {
    	switch (parameter[0].toUpperCase())
    	{
    	case "ESERVICE":eService(testStep);
    	break;
    	default:System.out.println("Sub application not found");
    	}
    }
    
    private static void eService(ArrayList<String> testStep)
    {
    	ArrayList<String> virtualTestStep = new ArrayList<String>();
    	String mobileNumber = Config.getConfig().get("adb:RUN_"+testStep.get(2).charAt(testStep.get(2).length()-1)+"_Number");
    	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE",testStep.get(2),"COMPONENTCLICK","Component='eService'|Value='true'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE",testStep.get(2),"SetText","Component='mobileNumber'|Value='"+mobileNumber+"'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE",testStep.get(2),"COMPONENTCLICK","Component='sentOTP'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"OpenApp","NewOpen='true'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"COMPONENTISEXIST","Component='Sender'|Value='true'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"COMPONENTCLICK","Component='Sender'|Value='true'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"SELECTMESSAGE","index='1'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"CloseApp","NewOpen='true'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE",testStep.get(2),"SetOTP","Component='otpNumber'","","");
    	Executor.applicationMapping(virtualTestStep);
    	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE",testStep.get(2),"COMPONENTCLICK","Component='submitOTP'","","");
    	Executor.applicationMapping(virtualTestStep);    	
    }
    
    private static void closeApp()
    {
    	MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	MOBILE.driverList.get(udid).findElement(By.name("Yes")).click();
    }
}
