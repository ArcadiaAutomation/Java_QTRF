//<<<<<<< HEAD
package com.qtrf.mobile.application;

import com.qtrf.core.Logger;
import com.qtrf.core.Miscellaneous;
import com.qtrf.core.TestStep;

import io.appium.java_client.android.AndroidKeyCode;
import com.qtrf.core.Environment;
import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;

import static org.testng.AssertJUnit.fail;

import org.openqa.selenium.WebElement;

public class ME_MOOD extends ME_MOOD_Repository {

	public static Hashtable<String, String> otpTable= new Hashtable<String, String>();
	
	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	ME_MOOD_Repository selfRepository = new ME_MOOD_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public ME_MOOD(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
	}
	
    public void actionMapper(TestStep testStep)
    {
    	udid = utility.getUdid(testStep.machine);
    	parameter = utility.getParameter(testStep.parameter);
    	
    	switch(testStep.action.toUpperCase())
    	{
    	case "OPENAPP" : utility.openApp(udid,table.get("package"),table.get("activity"),table.get("waitActivity"));
    	break;
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "SELECTMESSAGE" : selectMessage(Integer.parseInt(parameter[0]));
    	break;
    	case "VERIFYMESSAGE" : verifyMessage(Integer.parseInt(parameter[0]),parameter[1]);
    	break;
    	case "VERIFYMESSAGESENDING" : verifyMessageSending(testStep);
    	break;
    	case "WAITUNTIL" : waitUntil(testStep);
    	break;   	
    	case "GETOTP" : getOTP(testStep);
    	break;
    	case "BACK" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	break;
    	case "CLOSEAPP" : 
    		MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    		Miscellaneous.wait(2);
    		MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    		Miscellaneous.wait(2);
    	break; 	
    	default : System.out.println("Action not found");
    	}
    }
    
    private boolean isComponentExist(TestStep testStep)
    {
    	if(utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
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
    
    private void clickComponent(TestStep testStep)
    {
    	utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
    }
    
    private String selectMessage(int index)
    {
    	List<WebElement> messageList = MOBILE.driverList.get(udid).findElementsById(table.get("messageList"));
    	return messageList.get(messageList.size()-index).getAttribute("text");
    }
    
    private void verifyMessage(int index,String expect)
    {
    	if (selectMessage(index).indexOf(expect)!=-1)
    	{
    		LogManager.logTable.get(runName).addStep("verifyMessage",expect , selectMessage(index), "pass", "");
    	}
    	else
    	{
    		LogManager.logTable.get(runName).addStep("verifyMessage",expect , selectMessage(index), "fail", "");
    		fail();
    	}
    }    
    
	private String getOTP(TestStep testStep)
	 {
		 String OTP=selectMessage(Integer.parseInt(parameter[0]));
		
		 int position = OTP.indexOf("OTP");
		 if (position!=-1)
		 {
		 System.out.println("OTP : "+OTP.substring(position+6,position+10));
		 otpTable.put(runName, OTP.substring(position+6,position+10));
		 return otpTable.get(runName);
		 }
		 else
		 {
		 System.out.println("OTP Not found in message");
		 return "OTP Not found in message";
		 }
	 }
	 
	public void verifyMessageSending(TestStep testStep)
	{
    	String[] parameter = {"Exist","verifyMessageSending","59"};
    	waitUntil(parameter);
	}
	
	private boolean waitUntil(TestStep testStep)
	{
		return utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
	}
	
	private boolean waitUntil(String[] parameter)
	{
		return utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
	}

}

//=======
//package com.qtrf.mobile.application;
//
//import com.qtrf.core.LogManager;
//import io.appium.java_client.android.AndroidKeyCode;
//import com.qtrf.core.Environment;
//import com.qtrf.core.Config;
//import com.qtrf.core.Iteration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.openqa.selenium.By;
//
//
//import org.openqa.selenium.WebElement;
//
//public class ME_MOOD extends ME_MOOD_Repository {
//
//	static String[] parameter;
//	static String udid;
//	static String otpNumber;
//	
//    public static void actionMapper(ArrayList<String> testStep)
//    {
//    	parameter = Utility.getParameter(testStep.get(4));
//    	udid = getUdid(testStep.get(2));
//    	ini();
//    	
//    	switch(testStep.get(3).toUpperCase())
//    	{
//    	case "OPENAPP" : openApp(testStep,table.get("package"),table.get("activity"),table.get("waitActivity"));
//    	break;
//    	case "COMPONENTISEXIST" : isComponentExist(testStep);
//    	break;
//    	case "COMPONENTCLICK" : clickComponent(testStep);
//    	break;
//    	case "SELECTMESSAGE" : selectMessage(Integer.parseInt(parameter[0]));
//    	break;
//    	case "VERIFYMESSAGE" : verifyMessage(Integer.parseInt(parameter[0]),parameter[1]);
//    	break;
//    	case "VERIFYMESSAGESENDING" : verifyMessageSending(testStep);
//    	break;
//    	case "WAITUNTIL" : waitUntil(testStep);
//    	break;   	
//    	case "GETOTP" : getOTP(Integer.parseInt(parameter[0]));
//    	break;
//    	case "BACK" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//    	break;
//    	case "CLOSEAPP" : 
//    		MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//    		wait(2);
//    		MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//    	break; 	
//    	default : System.out.println("Action not found");
//    	}
//    }
//    
//    private static boolean isComponentExist(ArrayList<String> testStep)
//    {
//    	if(Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
//    	{
//    		System.out.println("Component exist");
//    		return true;
//    	}
//    	else
//    	{
//    		System.out.println("Component not exist");
//    		return false;
//    	}
//    }
//    
//    private static void clickComponent(ArrayList<String> testStep)
//    {
//    	Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
//    }
//    
//    private static String selectMessage(int index)
//    {
//    	List<WebElement> messageList = MOBILE.driverList.get(udid).findElementsById(table.get("messageList"));
//    	return messageList.get(messageList.size()-index).getAttribute("text");
//    }
//    
//    private static void verifyMessage(int index,String expect)
//    {
//    	if (selectMessage(index).indexOf(expect)!=-1)
//    	{
//    		LogManager.addStep("verifyMessage",expect , selectMessage(index), "pass", "");
//    	}
//    	else
//    	{
//    		LogManager.addStep("verifyMessage",expect , selectMessage(index), "fail", "");
//    	}
//    }    
//    
//	private static String getOTP(int index)
//	 {
//		 String OTP=selectMessage(index);
//		
//		 int position = OTP.indexOf("OTP");
//		 if (position!=-1)
//		 {
//		 System.out.println("OTP : "+OTP.substring(position+6,position+10));
//		 otpNumber=OTP.substring(position+6,position+10);
//		 return otpNumber;
//		 }
//		 else
//		 {
//		 System.out.println("OTP Not found in message");
//		 return "OTP Not found in message";
//		 }
//	 }
//	 
//	public static void verifyMessageSending(ArrayList<String> testStep)
//	{
//    	String[] parameter = {"Exist","verifyMessageSending","59"};
//    	waitUntil(parameter);
//	}
//	
//	private static boolean waitUntil(ArrayList<String> testStep)
//	{
//		return Utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
//	}
//	
//	private static boolean waitUntil(String[] parameter)
//	{
//		return Utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
//	}
//
//}
//
//>>>>>>> branch 'master' of https://github.com/ArcadiaAutomation/QTRF_Java.git
