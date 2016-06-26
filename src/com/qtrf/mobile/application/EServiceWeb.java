package com.qtrf.mobile.application;
import java.util.ArrayList;
import org.openqa.selenium.By;

import com.qtrf.core.LogManager;

public class EServiceWeb extends EServiceWeb_Repository{
	
	static String[] parameter;
	static String udid;
	WebCommon webCommon = new WebCommon();
    public static void actionMapper(ArrayList<String> testStep)
    {
    	parameter = Utility.getParameter(testStep.get(4));
    	EServiceWeb_Repository.ini();
    	String Action = testStep.get(3);
    	switch(Action.toUpperCase())
    	{
    	case "OPENESERVICEPAGE" : 
    		eServicePage();
    		break;
    	case "INPUTOPT" :
    		setOTP(parameter);
    		break;
    	case "VERIFYLOGIN" :
    		VerifyLogin(parameter);
    		break;
    	case "SELECTMENU" :
    		SelctMenu(parameter);
    		break;
    	default : WebCommon.actionMapper(testStep);
    	}
    }
    private static void setOTP(String[] parameter) {
		// TODO Auto-generated method stub
    	String type = parameter[0];
    	String value = parameter[1];
    	String text = ME_MOOD.otpNumber;
    	WebCommon.setText(type,value,text);
    	LogManager.addStep("SetOTP",text,"","pass","");
	}
    private static void VerifyLogin(String[] parameter) {
    	String type = parameter[0];
    	String value = parameter[1];
    	String Ex = parameter[2];
    	String Ac = WebCommon.getText(type, value);
    	if (Ac.equals(Ex)) {
    		LogManager.addStep("VerifyLogin","Number is "+Ex,"Number is "+Ac,"pass","");
    	}
    	else {
    		LogManager.addStep("VerifyLogin","Number is "+Ex,"Number is "+Ac,"fail","");
    	}	
    }
    private static void eServicePage(){
		WebCommon.OpenBrowser("eservice.ais.co.th/eServiceWeb","Chrome"); 
		WebCommon.driver.switchTo().frame(WebCommon.driver.findElement(By.id("MainIframe")));
		LogManager.addStep("OpenEserviceWeb","eservice.ais.co.th/eServiceWeb","","pass","");
    }
    private static void SelctMenu(String[] parameter){
    	String Menu = parameter[0];
    	switch(Menu.toUpperCase()) {
    	case "MAINPAGE" : 
    		WebCommon.ClickElement("id","menu-37");
    		LogManager.addStep("SelectMenu","MAINPAGE","","pass","");
    		break;
    	case "ONENUMBERFORPROMOTION" : 
    		WebCommon.ClickElement("id","menu-85");
    		LogManager.addStep("SelectMenu","ONENUMBERFORPROMOTION","","pass","");
    		break;
    	case "USAGEDETAILHISTORY" : 
    		WebCommon.ClickElement("id","menu-86");
    		LogManager.addStep("SelectMenu","USAGEDETAILHISTORY","","pass","");
    		break;
    	case "SWOPSERVICE" : 
    		WebCommon.ClickElement("id","menu-226");
    		LogManager.addStep("SelectMenu","SWOPSERVICE","","pass","");
    		break;
    	case "TOPUP/PAYMENT" : 
    		WebCommon.ClickElement("id","menu-38");
    		LogManager.addStep("SelectMenu","TOPUP/PAYMENT","","pass","");
    		break;
    	case "ONENUMBERFORSERVICE" : 
    		WebCommon.ClickElement("id","menu-81");
    		LogManager.addStep("SelectMenu","ONENUMBERFORSERVICE","","pass","");
    		break;
    	case "OTHERSERVICE" : 
    		WebCommon.ClickElement("id","menu-51");
    		LogManager.addStep("SelectMenu","OTHERSERVICE","","pass","");
			break;
    	case "YOURPERSONALINFORMATION" : 
    		WebCommon.ClickElement("id","menu-27");
    		LogManager.addStep("SelectMenu","YOURPERSONALINFORMATION","","pass","");
			break;
    	case "PRIVILEGES" : 
    		WebCommon.ClickElement("id","menu-99");
    		LogManager.addStep("SelectMenu","PRIVILEGES","","pass","");
			break;
    	default : System.out.println("Action not found");
    	}
    }
}