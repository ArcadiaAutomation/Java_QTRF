package com.qtrf.mobile.application;

import io.appium.java_client.android.AndroidKeyCode;

import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.DriverManager;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;
import com.qtrf.core.Logger;
import com.qtrf.core.TestStep;
import com.qtrf.core.Executor;

import static org.testng.AssertJUnit.fail;

public class ME_IN_ALLFORONE extends ME_IN_ALLFORONE_Repository {

	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	ME_IN_ALLFORONE_Repository selfRepository = new ME_IN_ALLFORONE_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public ME_IN_ALLFORONE(String runName,Config config,Environment environment)
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
    	case "OPENAPP" : 
    		try {
    			utility.openApp(udid,table.get("package"),table.get("activity"),table.get("waitActivity"));
    			LogManager.logTable.get(runName).addStep("openApp", "Switch application", "Switch application", "pass", "");
    		} catch (Exception e) {
    			LogManager.logTable.get(runName).addStep("openApp", "Switch application", e.toString(), "fail", "");	
    			fail();
    		}
    	break;
    	case "OPENSUBAPP" : openSubApp(testStep);
    	break;
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "SETTEXT" : setText(testStep);
    	break;
    	case "SETOTP" : setOTP(testStep);
    	break;
    	case "WAITUNTIL" : waitUntil(testStep);
    	break;
    	case "BACK" : MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	break;
    	case "CLOSEAPP" : closeApp(testStep);
    	break;
    	default : utility.actionMapper(testStep,repository);
    	}
    }
    
    private boolean isComponentExist(TestStep testStep)
    {
    	String udid = utility.getUdid(testStep.machine);
    	String[] parameter = utility.getParameter(testStep.parameter);
    	
    	try
    	{
    	if(utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Verify component : true");
    		LogManager.logTable.get(runName).addStep("ComponentIsExist", "Verify component : true", "Verify component : true", "pass", "");
    		return true;
    	}
    	else
    	{
    		System.out.println("Verify component : false");
    		LogManager.logTable.get(runName).addStep("ComponentIsExist", "Verify component : true", "Verify component : false", "fail", "");
    		fail();
    		return false;
    	}
    	}
    	catch (Exception e)
    	{
    		LogManager.logTable.get(runName).addStep("ComponentIsExist", "Verify component : true", e.toString(), "fail", "");
    		fail();
    		return false;
    	}
    }

    private void clickComponent(TestStep testStep)
    {
    	utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
    }

    private void setText(TestStep testStep)
    {    	
    	utility.setText(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]);
    }
    
    private void setOTP(TestStep testStep)
    {    	
    	utility.setText(udid, table.get(parameter[0]), typeTable.get(parameter[0]), ME_MOOD.otpTable.get(runName));
    }
    
    private void openSubApp(TestStep testStep)
    {
    	try
    	{
    		switch (parameter[0].toUpperCase())
    		{
    			case "ESERVICE":eService(testStep);
    			break;
    			case "ESERVICE3G":eService3G(testStep);
    			break;
    			default:System.out.println("Sub application not found");
    		}
    		LogManager.logTable.get(runName).addStep("openSubApp", "Open sub application", "Open sub application", "pass", "");
    	}
    	catch (Exception e)
    	{
    		LogManager.logTable.get(runName).addStep("openSubApp", "Open sub application", e.toString(), "fail", "");	
    		fail();
    	}
    }
    
    private void eService3G(TestStep testStep)
    {
    	TestStep virtualTestStep;
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"COMPONENTCLICK","Component='eService'|Value='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment); 
    	Miscellaneous.wait(2);
    }
    
    private void eService(TestStep testStep)
    {    	
    	TestStep virtualTestStep;
    	String mobileNumber = config.getConfig().get("adb:RUN_"+testStep.machine.charAt(testStep.machine.length()-1)+"_Number");
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"COMPONENTCLICK","Component='eService'|Value='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);   
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"waitUntil","Option='Exist'|ComponentName='mobileNumber'|sec='59'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);    
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"SetText","Component='mobileNumber'|Value='"+mobileNumber+"'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"COMPONENTCLICK","Component='sentOTP'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"waitUntil","Option='Exist'|ComponentName='otpNumber'|sec='59'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);    			
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"OpenApp","NewOpen='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"COMPONENTISEXIST","Component='Sender'|Value='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"VERIFYMESSAGESENDING","","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"COMPONENTCLICK","Component='Sender'|Value='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"GETOTP","index='1'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"CloseApp","NewOpen='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"SetOTP","Component='otpNumber'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"COMPONENTCLICK","Component='submitOTP'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);  
    	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE",testStep.machine,"waitUntil","Option='Exist'|ComponentName='innerEService'|sec='59'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    }

	private void waitUntil(TestStep testStep)
	{
		utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
	}
    
    private void closeApp(TestStep testStep)
    {
    	MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	MOBILE.driverList.get(udid).findElement(By.name("Yes")).click();
    }
}
