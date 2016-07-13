package com.qtrf.core;

import java.io.IOException;
import java.util.ArrayList;
import static org.testng.Assert.fail;
import com.qtrf.mobile.application.*;

public class Executor {
	
	String runName;
	
	public Executor(String runName)
	{
		this.runName=runName;
	}
	
	public void run(TestCase testCase,Config config,Environment environment){	
		for (int i=1;i<testCase.getTestCase().size();i++)
		{
			TestStep testStep = new TestStep(testCase.getTestCase().get(i));
			System.out.println("Start step : "+i);
			if (!applicationMapping(testStep,config,environment))
			{
				break;
			}
		}
	}
	
	public boolean applicationMapping(TestStep testStep,Config config,Environment environment)
	{
		switch(testStep.application.toUpperCase())
		{
		case "MOBILE" : MOBILE mobile = new MOBILE(runName,config,environment);mobile.actionMapper(testStep);
		break;
		case "ME_IN_ALLFORONE" : ME_IN_ALLFORONE mInAllForOne = new ME_IN_ALLFORONE(runName,config,environment);mInAllForOne.actionMapper(testStep);
		break;		
		case "ME_IN_ALLFORONE_ESERVICE" : ME_IN_ALLFORONE_ESERVICE mInAllForOneEService = new ME_IN_ALLFORONE_ESERVICE(runName,config,environment);mInAllForOneEService.actionMapper(testStep);
		break;
		case "ME_MOOD" : ME_MOOD mMood = new ME_MOOD(runName,config,environment);mMood.actionMapper(testStep);
		break;
		case "UTILITY" : Utility utility = new Utility(runName,config,environment);utility.actionMapper(testStep);
		break;		
		case "ME_WIFICONECT" : ME_WIFICONNECT mWifiConnect = new ME_WIFICONNECT(runName,config,environment);mWifiConnect.actionMapper(testStep);
		break;		
//		case "ESRVICEWEB"	: EServiceWeb.actionMapper(testStep);
//		break;
//		case "ONENUMBERFORSERVICE" : OneNumberForPromotion.actionMapper(testStep);
//		break;
		default : System.out.println("Command not found");fail("Application not found");
		return false;
		}
		return true;
	}
}
