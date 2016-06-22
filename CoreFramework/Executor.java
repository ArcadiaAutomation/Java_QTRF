package CoreFramework;

import java.util.ArrayList;

import Command.*;

public class Executor {
	
	public static void run(){	
		for (int i=1;i<TestCase.getTestCase().size();i++)
		{
			System.out.println("Start step : "+i);
			applicationMapping(TestCase.getTestCase().get(i));
		}
	}
	
	public static void applicationMapping(ArrayList<String> testStep)
	{
		switch(testStep.get(1).toUpperCase())
		{
		case "MOBILE" : MOBILE.actionMapper(testStep);
		break;
		case "ME_IN_ALLFORONE" : ME_IN_ALLFORONE.actionMapper(testStep);
		break;		
		case "ME_IN_ALLFORONE_ESERVICE" : ME_IN_ALLFORONE_ESERVICE.actionMapper(testStep);
		break;
		case "ME_MOOD" : System.out.println(testStep);
		break;
		case "UTILITY" : Utility.actionMapper(testStep);
		break;		
		case "ME_WIFICONECT" : ME_WIFICONNECT.actionMapper(testStep);
		break;		
		default : System.out.println("Command not found");
		}
	}
}
