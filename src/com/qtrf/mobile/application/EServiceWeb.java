package com.qtrf.mobile.application;
import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Executor;
import com.qtrf.core.TestStep;
//import com.qtrf.core.LogManager;
import com.qtrf.mobile.application.WebCommon;
public class EServiceWeb {
    
	public static Hashtable<String, String> otpTable= new Hashtable<String, String>();
	
	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	WebCommon webCommon;
	Utility utility;
	EServiceWeb_Repository selfRepository = new EServiceWeb_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public EServiceWeb(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
		webCommon = new WebCommon(runName,config,environment);
	}
    
    public void actionMapper(TestStep testStep)
    {
    	parameter = utility.getParameter(testStep.parameter);
    	udid = utility.getUdid(testStep.machine);
    	
    	switch(testStep.action.toUpperCase())
        {
        case "OPENESERVICEPAGE" : 
            eServicePage();
            break;
        case "INPUTOTP" :
            setOTP(parameter);
            break;
        case "VERIFYLOGIN" :
            VerifyLogin(parameter);
            break;
        case "SELECTMENU" :
            SelectMenu(parameter);
            break;
        case "SELECTLANGUAGE" : 
            SelectLanguage(parameter[0]);
            break;
        case "GETOTP" :
            GetOTPfromMOOD(parameter[0],parameter[1]);
        default : webCommon.actionMapper(testStep);
        }
    }
    private void setOTP(String[] parameter) {
        // TODO Auto-generated method stub
        String type = parameter[0];
        String value = parameter[1];
        String text = otpTable.get(runName);
        webCommon.setText(type,value,text);
        ////LogManager.addStep("SetOTP",text,"","pass","");
    }
    private void VerifyLogin(String[] parameter) {
        String type = parameter[0];
        String value = parameter[1];
        String Ex = parameter[2];
        String Ac = webCommon.getText(type, value);
        if (Ac.equals(Ex)) {
            ////LogManager.addStep("VerifyLogin","Number is "+Ex,"Number is "+Ac,"pass","");
        }
        else {
            ////LogManager.addStep("VerifyLogin","Number is "+Ex,"Number is "+Ac,"fail","");
        }   
    }
    private void eServicePage(){
        webCommon.OpenBrowser("eservice.ais.co.th/eServiceWeb","Chrome");
        webCommon.driver.switchTo().frame(webCommon.driver.findElement(By.id("MainIframe")));
        ////LogManager.addStep("OpenEserviceWeb","eservice.ais.co.th/eServiceWeb","","pass","");
    }
    private void SelectMenu(String[] parameter){
        String Menu = parameter[0];
        switch(Menu.toUpperCase()) {
        case "MAINPAGE" : 
            webCommon.ClickElement("id","menu-37");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "ONENUMBERFORPROMOTION" : 
            webCommon.ClickElement("id","menu-85");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "USAGEDETAILHISTORY" : 
            webCommon.ClickElement("id","menu-86");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "SWOPSERVICE" : 
            webCommon.ClickElement("id","menu-226");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "TOPUP/PAYMENT" : 
            webCommon.ClickElement("id","menu-38");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "ONENUMBERFORSERVICE" : 
            webCommon.ClickElement("id","menu-81");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "OTHERSERVICE" : 
            webCommon.ClickElement("id","menu-51");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "YOURPERSONALINFORMATION" : 
            webCommon.ClickElement("id","menu-27");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        case "PRIVILEGES" : 
            webCommon.ClickElement("id","menu-99");
            ////LogManager.addStep("SelectMenu",Menu,"","pass","");
            break;
        default : ////LogManager.addStep("SelectMenu","cannot found Menu : "+Menu,"","fail","");
        }
    }
    private void SelectLanguage(String Lang) {
        switch(Lang.toUpperCase()) {
        case "EN" :
            webCommon.ClickElement("xpath","//*[@id=\"toppage\"]/div/div[2]/ul/li[1]/div");
            ////LogManager.addStep("SelectMenu",Lang,Lang,"pass","");
            break;
        case "TH" :
            ////LogManager.addStep("SelectMenu",Lang,Lang,"pass","");
            break;
        }
    }
    private String GetOTPfromMOOD(String IP,String udid){
        
        webCommon.OpenAppMoblie(IP,udid);
        webCommon.Mdriver.findElementByName("AIS").click();
        webCommon.wait(3);
        otpTable.put(runName, webCommon.getOTP(1));
        return otpTable.get(runName);
    }
}