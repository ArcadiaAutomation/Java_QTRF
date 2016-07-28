package com.qtrf.mobile.application;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Miscellaneous;
import com.qtrf.core.TestStep;

public class BIZLIVE {
	
	String runName;
	String[] parameter;
	String window;
	Executor executor;
	Config config;
	Environment environment;
	WebCommonKit webCommon;
	Utility utility;
	BIZLIVE_Repository selfRepository = new BIZLIVE_Repository();
	Repository repository = new Repository(selfRepository.table,selfRepository.typeTable);
	
	public BIZLIVE(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		executor = new Executor(runName);
		utility = new Utility(runName,config,environment);
		webCommon = new WebCommonKit(runName,config,environment);
	}
    
    public void actionMapper(TestStep testStep)
    {
    	parameter = utility.getParameter(testStep.parameter);
    	window = testStep.machine;
    	
    	switch(testStep.action.toUpperCase())
        {
        case "ACTIVEURLBROWSER" : activeUrlBrowser(testStep);
         	break;
        case "OPENBROWSER" : WEB web = new WEB(runName,config,environment);web.actionMapper(testStep,repository);
            break;
        case "INSERTTEXTBOX" : webCommon.setText(testStep,repository);
            break;
        case "PRESSTEXTBOX" : webCommon.clickComponent(testStep,repository);
            break;     
        case "PRESSIMAGE" : webCommon.clickComponent(testStep,repository);
         	break;              
        case "PRESSLINK" : webCommon.clickComponent(testStep,repository);
            break;    
        case "SELECTCALENDAR" : selectCalendar(testStep);
            break; 
        case "SELECTCHECKBOX" : webCommon.clickComponent(testStep,repository);
            break;
        case "SELECTLISTBOX" : selectListBox(testStep);
            break;
        case "VERIFYLABEL" : verifyLabel(testStep);
            break;
        case "SELECTRADIO" : webCommon.clickComponent(testStep,repository);
            break;
        case "VERIFYREPORTSUMMARYSENDSMS2" : 
            break;
        case "SELECTCALENDARVOTE" : selectCalendarVote(testStep);
            break;
        default : webCommon.actionMapper(testStep,repository);
        }
    }
    
    public boolean verifyLabel(TestStep testStep)
    {
    	List<WebElement> text = WEB.driverList.get(window).findElements(By.xpath(repository.table.get(parameter[0])));
    	if (text.get(0).getText().equals(parameter[1]))
    	{
    		System.out.println("verifyLabel : true");
    		return true;
    	}
    	else
    	{
    		System.out.println("verifyLabel : false");
    		return false;   		
    	}
    }
    
    public void activeUrlBrowser(TestStep testStep)
    {
    	Object[] page = WEB.driverList.get(window).getWindowHandles().toArray();
		 for (int i=0;i<page.length;i++)
		 {
			 if (WEB.driverList.get(window).switchTo().window(page[i].toString()).getTitle().equals(parameter[0]))
			 {
				 WEB.driverList.get(window).switchTo().window(page[i].toString());
			 }
		 }
    }
    
    public void selectCalendar(TestStep testStep)
    {
    	Hashtable<String,Integer> monthTable = new Hashtable<String,Integer>();
    	monthTable.put("January", 1);	monthTable.put("February", 2);	monthTable.put("March", 3);
    	monthTable.put("April", 4);		monthTable.put("May", 5);		monthTable.put("June", 6);
    	monthTable.put("July", 7);		monthTable.put("August", 8);	monthTable.put("September", 9);
    	monthTable.put("October", 10);	monthTable.put("November", 11);	monthTable.put("December", 12);
    	
    	String[] date = parameter[0].split("/");
    	String[] calendarDate = webCommon.getComponent(window,repository.table.get("calendarDate"),repository.typeTable.get("calendarDate")).getText().split(" ");
    	
    	int diffMonth = monthTable.get(calendarDate[0])-Integer.parseInt(date[1]);
    	int diffYear = Integer.parseInt(calendarDate[1])-Integer.parseInt(date[2]);
    	
    	if (diffMonth>0)
    	{
    		for (int i=0;i<diffMonth;i++) {
    		webCommon.getComponent(window,repository.table.get("calendarPreviousMonth"),repository.typeTable.get("calendarPreviousMonth")).click();
    		}
    	}
    	else if (diffMonth<0)
    	{
    		for (int i=0;i<(-1*diffMonth);i++) {
    		webCommon.getComponent(window,repository.table.get("calendarNextMonth"),repository.typeTable.get("calendarNextMonth")).click();
    		}
    	} else {
    		
    	}
    	
      	if (diffYear>0)
    	{
    		for (int i=0;i<diffYear;i++) {
    		webCommon.getComponent(window,repository.table.get("calendarPreviousYear"),repository.typeTable.get("calendarPreviousYear")).click();
    		}
    	}
    	else if (diffMonth<0)
    	{
    		for (int i=0;i<(-1*diffYear);i++) {
    		webCommon.getComponent(window,repository.table.get("calendarNextYear"),repository.typeTable.get("calendarNextYear")).click();
    		}
    	} else {
    		
    	}
      	
      	if (date[0].charAt(0)=='0')
      	{
      		date[0]=date[0].substring(1, 2);
      	}
      	
      	String calendarDayXpath = repository.table.get("calendarDay")+date[0]+"']";
      	webCommon.getComponent(window,calendarDayXpath,repository.typeTable.get("calendarDay")).click();
    }
    
    public void selectCalendarVote(TestStep testStep)
    {
    	Hashtable<String,Integer> monthTable = new Hashtable<String,Integer>();
    	monthTable.put("January", 1);	monthTable.put("February", 2);	monthTable.put("March", 3);
    	monthTable.put("April", 4);		monthTable.put("May", 5);		monthTable.put("June", 6);
    	monthTable.put("July", 7);		monthTable.put("August", 8);	monthTable.put("September", 9);
    	monthTable.put("October", 10);	monthTable.put("November", 11);	monthTable.put("December", 12);
    	
    	String[] date = parameter[0].split("/");
    	
    	List<WebElement> calendar = WEB.driverList.get(window).findElements(By.xpath("//div[@class='calendar']"));
    	int index = calendar.size();
    	
    	
    	
    	String[] calendarDate = WEB.driverList.get(window).findElement(By.xpath("//div[@class='calendar']["+index+"]/table/thead/tr/td[@class='title']")).getText().split(", ");
    	
    	int diffMonth = monthTable.get(calendarDate[0])-Integer.parseInt(date[1]);
    	int diffYear = Integer.parseInt(calendarDate[1])-Integer.parseInt(date[2]);
    	
    	if (diffMonth>0)
    	{
    		for (int i=0;i<diffMonth;i++) {
    		webCommon.getComponent(window,"//div[@class='calendar']["+index+"]/table/thead/tr[@class='headrow']/td[2]","xpath").click();
    		}
    	}
    	else if (diffMonth<0)
    	{
    		for (int i=0;i<(-1*diffMonth);i++) {
    		webCommon.getComponent(window,"//div[@class='calendar']["+index+"]/table/thead/tr[@class='headrow']/td[4]","xpath").click();
    		}
    	} else {
    		
    	}
    	
      	if (diffYear>0)
    	{
    		for (int i=0;i<diffYear;i++) {
    		webCommon.getComponent(window,"//div[@class='calendar']["+index+"]/table/thead/tr[@class='headrow']/td[1]","xpath").click();
    		}
    	}
    	else if (diffMonth<0)
    	{
    		for (int i=0;i<(-1*diffYear);i++) {
    		webCommon.getComponent(window,"//div[@class='calendar']["+index+"]/table/thead/tr[@class='headrow']/td[5]","xpath").click();
    		}
    	} else {
    		
    	}
      	
      	if (date[0].charAt(0)=='0')
      	{
      		date[0]=date[0].substring(1, 2);
      	}
      	
      	webCommon.getComponent(window,"//div[@class='calendar']["+index+"]/table/tbody/tr/td[text()='"+date[0]+"']","xpath").click();
    }
    
    public void selectListBox(TestStep testStep)
    {
    	webCommon.clickComponent(testStep, repository);
		Select select = new Select(webCommon.getComponent(testStep,repository));
		select.selectByVisibleText(parameter[1]);
    }
    
    public boolean verifyReportSummarySendSms2(TestStep testStep)
    {
    	String start = WEB.driverList.get(window).findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr[4]/td/div/div/div[2]/table[2]/tbody/tr[1]/td/strong/span[1]")).getText();
    	String stop = WEB.driverList.get(window).findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr[4]/td/div/div/div[2]/table[2]/tbody/tr[1]/td/strong/span[2]")).getText();
    	
    	if (start.equals(parameter[0])&&stop.equals(parameter[1]))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
}
