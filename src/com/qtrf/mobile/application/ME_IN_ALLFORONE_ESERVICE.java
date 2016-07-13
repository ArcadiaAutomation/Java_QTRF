//<<<<<<< HEAD
package com.qtrf.mobile.application;

import java.util.ArrayList;
import java.util.Hashtable;

import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;
import com.qtrf.core.Logger;
import com.qtrf.core.TestStep;

import static org.testng.AssertJUnit.fail;

import io.appium.java_client.android.AndroidKeyCode;

public class ME_IN_ALLFORONE_ESERVICE{
	
	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;
	ME_IN_ALLFORONE_ESERVICE_Repository selfRepository = new ME_IN_ALLFORONE_ESERVICE_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public ME_IN_ALLFORONE_ESERVICE(String runName,Config config,Environment environment)
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
    	case "SELECTMENU" : selectMenu(testStep);
    	break;
    	case "SELECTSUBMENU" : selectSubMenu(testStep);
    	break;   	
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "WAITUNTIL" : waitUntil(testStep);
    	break;
    	case "VERIFYMESSAGE" : verifyMessage(testStep);
    	break;
    	case "SETTEXT" : setText(testStep);
    	break;
    	default : utility.actionMapper(testStep,repository);
    	}    	
    }
    
	private void selectSubMenu(TestStep testStep)
	{			
		try
		{
		if (utility.isComponentExist(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]), "false"))	
			{
			 	TestStep virtualTestStep;
			 	MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
	    	 	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.machine,"selectMenu","Component='"+parameter[2]+"'","","");
	    	 	executor.applicationMapping(virtualTestStep,config,environment);	
	    	 	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.machine,"waitUntil","Option='Exist'|Component='verify"+parameter[2]+"'|sec='20'","","");
	    	 	executor.applicationMapping(virtualTestStep,config,environment);	
	    	 	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.machine,"selectMenu","Component='"+parameter[1]+"'","","");
	    	 	executor.applicationMapping(virtualTestStep,config,environment);    	
	    	 	virtualTestStep=utility.cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.machine,"selectSubMenu",testStep.parameter,"","");
	    	 	executor.applicationMapping(virtualTestStep,config,environment);  	    	 	
			}
		else
		 	{
				utility.clickComponent(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]));
				LogManager.logTable.get(runName).addStep("selectSubMenu : "+parameter[0], "Menu selected", "Menu selected", "pass", "");
		 	}

		}
		catch (Exception e)
		{
			LogManager.logTable.get(runName).addStep("selectSubMenu : "+parameter[0], "Menu selected", e.toString(), "fail", "");	
			fail();
		}
	}
	
	private void selectMenu(TestStep testStep)
	{
		try
		{
		utility.clickComponent(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]));
		LogManager.logTable.get(runName).addStep("selectMenu", "Menu selected : "+parameter[0], "Menu selected", "pass", "");
		}
		catch (Exception e)
		{
			LogManager.logTable.get(runName).addStep("selectMenu : "+parameter[0], "Menu selected", e.toString(), "fail", "");	
			fail();
		}
	}	
	
    private boolean isComponentExist(TestStep testStep)
    {
    	try
    	{
    	if(utility.isComponentExist(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Verify component : true");
    		LogManager.logTable.get(runName).addStep("ComponentIsExist", "Verify component : true", "Verify component : true", "pass", "");
    		return true;
    	}
    	
    	else
    	{
    		System.out.println("Verify component : false");
    		LogManager.logTable.get(runName).addStep("ComponentIsExist", "Verify component : true", "Verify component : false", "fail", "");
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
    	utility.clickComponent(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]));
    }
    
	private void waitUntil(TestStep testStep)
	{
		utility.waitUntil(parameter, udid, repository.table.get(parameter[1]), repository.typeTable.get(parameter[1]));
	}
	
	private void verifyMessage(TestStep testStep)
	{
		try
		{
			
    	TestStep virtualTestStep;
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"OpenApp","NewOpen='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"COMPONENTISEXIST","Component='Sender'|Value='true'","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"VERIFYMESSAGESENDING","","","");
    	executor.applicationMapping(virtualTestStep,config,environment);
    	 virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"COMPONENTCLICK","Component='Sender'|Value='true'","","");
    	 executor.applicationMapping(virtualTestStep,config,environment);
    	 virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"VERIFYMESSAGE",testStep.parameter,"","");
    	 executor.applicationMapping(virtualTestStep,config,environment);   	
    	 virtualTestStep=utility.cloneTestStep("ME_MOOD",testStep.machine,"CloseApp","NewOpen='true'","","");
    	 executor.applicationMapping(virtualTestStep,config,environment);
		}
		catch (Exception e)
		{
			LogManager.logTable.get(runName).addStep("verifyMessage", parameter[1], e.toString(), "fail", "");
			fail();
		}
	}
    
    private void setText(TestStep testStep)
    {
    	utility.setText(udid, repository.table.get(parameter[0]), repository.typeTable.get(parameter[0]), parameter[1]);
    }
}
//=======
//package com.qtrf.mobile.application;
//
//import java.util.ArrayList;
//
//import com.qtrf.core.Config;
//import com.qtrf.core.Environment;
//import com.qtrf.core.Iteration;
//import com.qtrf.core.LogManager;
//
//import io.appium.java_client.android.AndroidKeyCode;
//
//public class ME_IN_ALLFORONE_ESERVICE extends ME_IN_ALLFORONE_ESERVICE_Repository{
//
//	static String[] parameter;
//	static String udid;
//	
//    public static void actionMapper(ArrayList<String> testStep)
//    {   	
//    	parameter = Utility.getParameter(testStep.get(4));
//    	udid = getUdid(testStep.get(2));
//    	ini();
//    	
//    	switch(testStep.get(3).toUpperCase())
//    	{
//    	case "SELECTMENU" : selectMenu(testStep);
//    	break;
//    	case "SELECTSUBMENU" : selectSubMenu(testStep);
//    	break;   	
//    	case "COMPONENTISEXIST" : isComponentExist(testStep);
//    	break;
//    	case "COMPONENTCLICK" : clickComponent(testStep);
//    	break;
//    	case "WAITUNTIL" : waitUntil(testStep,parameter);
//    	break;
//    	case "VERIFYMESSAGE" : verifyMessage(testStep);
//    	break;
//    	default : System.out.println("Action not found");
//    	}    	
//    }
//    
//	private static void selectSubMenu(ArrayList<String> testStep)
//	{	
//		String[] parameter = Utility.getParameter(testStep.get(4));
//		try
//		{
//		if (Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), "false"))	
//			{
//			 	ArrayList<String> virtualTestStep = new ArrayList<String>();
//			 	MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
//	    	 	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.get(2),"selectMenu","Component='"+parameter[2]+"'","","");
//	    	 	Executor.applicationMapping(virtualTestStep);	
//	    	 	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.get(2),"waitUntil","Option='Exist'|Component='verify"+parameter[2]+"'|sec='20'","","");
//	    	 	Executor.applicationMapping(virtualTestStep);	
//	    	 	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.get(2),"selectMenu","Component='"+parameter[1]+"'","","");
//	    	 	Executor.applicationMapping(virtualTestStep);    	
//	    	 	virtualTestStep=cloneTestStep("ME_IN_ALLFORONE_ESERVICE",testStep.get(2),"selectSubMenu",testStep.get(4),"","");
//	    	 	Executor.applicationMapping(virtualTestStep);  	    	 	
//			}
//		else
//		 	{
//				Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
//		 		LogManager.addStep("selectSubMenu : "+parameter[0], "Menu selected", "Menu selected", "pass", "");
//		 	}
//
//		}
//		catch (Exception e)
//		{
//			LogManager.addStep("selectSubMenu : "+parameter[0], "Menu selected", e.toString(), "fail", "");	
//		}
//	}
//	
//	private static void selectMenu(ArrayList<String> testStep)
//	{
//		try
//		{
//		Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
//		LogManager.addStep("selectMenu", "Menu selected : "+parameter[0], "Menu selected", "pass", "");
//		}
//		catch (Exception e)
//		{
//			LogManager.addStep("selectMenu : "+parameter[0], "Menu selected", e.toString(), "fail", "");	
//		}
//	}	
//	
//    private static boolean isComponentExist(ArrayList<String> testStep)
//    {
//    	
//    	try
//    	{
//    	if(Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
//    	{
//    		System.out.println("Verify component : true");
//    		LogManager.addStep("ComponentIsExist", "Verify component : true", "Verify component : true", "pass", "");
//    		return true;
//    	}
//    	
//    	else
//    	{
//    		System.out.println("Verify component : false");
//    		LogManager.addStep("ComponentIsExist", "Verify component : true", "Verify component : false", "fail", "");
//    		return false;
//    	}
//    	
//    	}
//    	catch (Exception e)
//    	{
//    		LogManager.addStep("ComponentIsExist", "Verify component : true", e.toString(), "fail", "");
//    		return false;
//    	}
//    }
//    
//    private static void clickComponent(ArrayList<String> testStep)
//    {
//    	Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
//    }
//    
//	private static void waitUntil(ArrayList<String> testStep,String[] parameter)
//	{
//		Utility.waitUntil(parameter, udid, table.get(parameter[1]), typeTable.get(parameter[1]));
//	}
//	
//	private static void verifyMessage(ArrayList<String> testStep)
//	{
//		String[] parameter = Utility.getParameter(testStep.get(4));
//		try
//		{
//			
//    	ArrayList<String> virtualTestStep = new ArrayList<String>();
//    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"OpenApp","NewOpen='true'","","");
//    	Executor.applicationMapping(virtualTestStep);
//    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"COMPONENTISEXIST","Component='Sender'|Value='true'","","");
//    	Executor.applicationMapping(virtualTestStep);
//    	virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"VERIFYMESSAGESENDING","","","");
//    	Executor.applicationMapping(virtualTestStep);
//    	 virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"COMPONENTCLICK","Component='Sender'|Value='true'","","");
//    	 Executor.applicationMapping(virtualTestStep);
//    	 virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"VERIFYMESSAGE",testStep.get(4),"","");
//    	 Executor.applicationMapping(virtualTestStep);   	
//    	 virtualTestStep=cloneTestStep("ME_MOOD",testStep.get(2),"CloseApp","NewOpen='true'","","");
//    	 Executor.applicationMapping(virtualTestStep);
//		}
//		catch (Exception e)
//		{
//			LogManager.addStep("verifyMessage", parameter[1], e.toString(), "fail", "");
//		}
//	}
//    
//}
//>>>>>>> branch 'master' of https://github.com/ArcadiaAutomation/QTRF_Java.git
