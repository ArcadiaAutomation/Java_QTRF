package com.qtrf.mobile.application;

import java.util.ArrayList;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Iteration;
import com.qtrf.core.LogManager;

public class ME_IN_ALLFORONE_ESERVICE extends ME_IN_ALLFORONE_ESERVICE_Repository{

	static String[] parameter;
	static String udid;
	
    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	udid = getUdid(testStep.get(2));
    	ini();
    	
    	switch(testStep.get(3).toUpperCase())
    	{
    	case "OPENSUBAPP" : openSubApp(testStep);
    	break;
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "COMPONENTCLICK" : clickComponent(testStep);
    	break;
    	case "WAITUNTIL" : waitUntil(testStep);
    	break;
    	default : System.out.println("Action not found");
    	}    	
    }
    
	private static void openSubApp(ArrayList<String> testStep)
	{
		Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
	}
	
    private static boolean isComponentExist(ArrayList<String> testStep)
    {
    	
    	try
    	{
    	if(Utility.isComponentExist(udid, table.get(parameter[0]), typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Verify component : true");
    		LogManager.addStep("ComponentIsExist", "Verify component : true", "Verify component : true", "pass", "");
    		return true;
    	}
    	else
    	{
    		
    		System.out.println("Verify component : false");
    		LogManager.addStep("ComponentIsExist", "Verify component : true", "Verify component : false", "fail", "");
    		return false;
    	}
    	}
    	catch (Exception e)
    	{
    		LogManager.addStep("ComponentIsExist", "Verify component : true", e.toString(), "fail", "");
    		return false;
    	}
    }
    
    private static void clickComponent(ArrayList<String> testStep)
    {
    	Utility.clickComponent(udid, table.get(parameter[0]), typeTable.get(parameter[0]));
    }
    
	public static boolean waitUntil(ArrayList<String> testStep)
	{
		String startTime = getCurrentSec();
		while (divideSec(startTime)<Integer.parseInt(parameter[2]))
		{
			if (parameter[0].toUpperCase().equals("Exist"))
			{
				if (Utility.isComponentExist(udid, table.get(parameter[1]), typeTable.get(parameter[1]), "true"))
				{
					System.out.println("Wait until exist : true");
					return true;
				}
			}
			else
			{
				if (Utility.isComponentExist(udid, table.get(parameter[1]), typeTable.get(parameter[1]), "false"))
				{
					System.out.println("Wait until not exist : true");
					return true;
				}
			}
		}
		System.out.println("Wait until : false");
		return false;
	}
    
}
