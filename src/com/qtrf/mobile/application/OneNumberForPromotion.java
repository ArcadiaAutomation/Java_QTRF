package com.qtrf.mobile.application;

import java.util.ArrayList;
import java.util.Hashtable;

import com.qtrf.core.Config;
import com.qtrf.core.DriverManagerParallel;
import com.qtrf.core.Environment;
import com.qtrf.core.Executor;
import com.qtrf.core.LogManager;
import com.qtrf.core.Logger;
import com.qtrf.core.TestStep;

import static org.testng.AssertJUnit.fail;

public class OneNumberForPromotion extends OneNumberForPromotion_Repository {
	
	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	WebCommon webCommon;
	Utility utility;
	OneNumberForPromotion_Repository selfRepository = new OneNumberForPromotion_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);

	public OneNumberForPromotion(String runName,Config config,Environment environment)
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
    	String Action = testStep.action;
    	switch(Action.toUpperCase())
    	{
    	case "SELECTSUBMENU" : 
    		SelectSubMenu(parameter[0]);
    		break;
    	default : webCommon.actionMapper(testStep);
    	}
    }
    public void Setup(){
    	webCommon.ClickElement(typeTable.get("Menu"),table.get("Menu"));
    }
    
    public void SelectSubMenu(String SubMenu){
       	switch(SubMenu.toUpperCase())
    	{
    	case "YOURCURRENTPACKAGE" : 
    		webCommon.ClickElement("id","menu-83");
    		LogManager.logTable.get(runName).addStep("SelectSubMenu",SubMenu,"","pass","");
    		break;
    	case "CHANGEMAINPACKAGE" :
    		webCommon.ClickElement("id","menu-135");
    		LogManager.logTable.get(runName).addStep("SelectSubMenu",SubMenu,"","pass","");
    		break;
    	case "APPLYONTOPPACKAGE" :
    		webCommon.ClickElement("id","menu-136");
    		LogManager.logTable.get(runName).addStep("SelectSubMenu",SubMenu,"","pass","");
    		break;
    	case "CANCLEONTOPPACKAGE" :
    		webCommon.ClickElement("id","menu-108");
    		LogManager.logTable.get(runName).addStep("SelectSubMenu",SubMenu,"","pass","");
    		break;
    	default : LogManager.logTable.get(runName).addStep("SelectSubMenu","cannot found submenu : "+SubMenu,"","fail","");
    	}
    }
}
