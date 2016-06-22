package command;

import io.appium.java_client.android.AndroidKeyCode;

import java.util.ArrayList;

import org.openqa.selenium.By;

import core.Config;
import core.Environment;
import core.Iteration;
import core.LogManager;



public class ME_IN_ALLFORONE extends ME_IN_ALLFORONE_Repository {

	static String[] parameter;
	static String udid;

    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	udid = getUdid(testStep.get(2));
    	ME_IN_ALLFORONE_Repository.ini();
    	
    	switch(testStep.get(3).toUpperCase())
    	{
    	case "OPENAPP" : openApp(testStep,table.get("package"),table.get("activity"),table.get("waitActivity"));
    	break;
    	case "OPENSUBAPP" : System.out.println("open sub app");
    	break;
    	case "COMPONENTISEXIST" : isComponentExist(testStep);
    	break;
    	case "CLOSEAPP" : closeApp();
    	break;
    	default : System.out.println("Action not found");
    	}
    }
    
    private static void isComponentExist(ArrayList<String> testStep)
    {
    	if(Utility.isComponentExist(udid, ME_IN_ALLFORONE_Repository.table.get(parameter[0]), ME_IN_ALLFORONE_Repository.typeTable.get(parameter[0]), parameter[1]))
    	{
    		System.out.println("Component exist");
    	}
    	else
    	{
    		System.out.println("Component not exist");
    	}
    }
    
    private static void closeApp()
    {
    	MOBILE.driverList.get(udid).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
    	MOBILE.driverList.get(udid).findElement(By.name("Yes")).click();
    }
}
