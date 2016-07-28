package com.qtrf.mobile.application;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.Miscellaneous;
import com.qtrf.core.TestStep;

import io.appium.java_client.android.AndroidDriver;

public class WebCommonKit {

	int normalWait = 0;
	String runName;
	String[] parameter;
	String window;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;

	public WebCommonKit(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		utility = new Utility(runName,config,environment);
		executor = new Executor(runName);
	}
	
    WebDriver driver;
    public void actionMapper(TestStep testStep,Repository repository)
    {
    	parameter = utility.getParameter(testStep.parameter);
    	window = testStep.machine;
    	
		switch(testStep.action.toUpperCase())
		{
		case "ACCEPTALERT" : acceptAlert(testStep,repository);
		break;
		default : System.out.println("Action not found");
		}
    	
    }
    
	public void setText(TestStep testStep,Repository repository)
	{
    	String[] parameter = utility.getParameter(testStep.parameter);
    	String window = testStep.machine;
    	String path = repository.table.get(parameter[0]);
    	String findOption = repository.typeTable.get(parameter[0]);
    	String text = parameter[1];
    	
		switch(findOption)
		{
		case "id" : WEB.driverList.get(window).findElement(By.id(path)).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);
		Miscellaneous.wait(normalWait);
		break;
		case "xpath" : WEB.driverList.get(window).findElement(By.xpath(path)).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);
		Miscellaneous.wait(normalWait);
		break;
		case "text" : WEB.driverList.get(window).findElement(By.xpath("//*[text()='"+path+"']")).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);
		Miscellaneous.wait(normalWait);
		break;
		case "class" : WEB.driverList.get(window).findElement(By.className(path)).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);
		Miscellaneous.wait(normalWait);
		break;
		case "css" : WEB.driverList.get(window).findElement(By.cssSelector(path)).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);	
		Miscellaneous.wait(normalWait);
		break;
		case "linkText" : WEB.driverList.get(window).findElement(By.linkText(path)).sendKeys(Keys.chord(Keys.CONTROL, "a"),text);	
		Miscellaneous.wait(normalWait);
		break;
		default: System.out.println("Element not found");
		}
	}
	
	public void clickComponent(TestStep testStep,Repository repository)
	{
    	String[] parameter = utility.getParameter(testStep.parameter);
    	String window = testStep.machine;
    	String path = repository.table.get(parameter[0]);
    	String findOption = repository.typeTable.get(parameter[0]);
    	
		switch(findOption)
		{
		case "id" : WEB.driverList.get(window).findElement(By.id(path)).click();Miscellaneous.wait(normalWait);
		break;
		case "xpath" : WEB.driverList.get(window).findElement(By.xpath(path)).click();Miscellaneous.wait(normalWait);
		break;
		case "text" : WEB.driverList.get(window).findElement(By.xpath("//*[text()='"+path+"']")).click();Miscellaneous.wait(normalWait);
		break;
		case "class" : WEB.driverList.get(window).findElement(By.className(path)).click();Miscellaneous.wait(normalWait);
		break;
		case "css" : WEB.driverList.get(window).findElement(By.cssSelector(path)).click();Miscellaneous.wait(normalWait);
		break;
		case "linkText" : WEB.driverList.get(window).findElement(By.linkText(path)).click();Miscellaneous.wait(normalWait);
		break;
		default: System.out.println("Element not found");
		}
	}
	
	public WebElement getComponent(String window,String path,String findOption)
	{
    	WebElement result = null;
    	
		switch(findOption)
		{
		case "id" : result = WEB.driverList.get(window).findElement(By.id(path));Miscellaneous.wait(normalWait);
		break;
		case "xpath" : result = WEB.driverList.get(window).findElement(By.xpath(path));Miscellaneous.wait(normalWait);
		break;
		case "text" : result = WEB.driverList.get(window).findElement(By.xpath("//*[text()='"+path+"']"));Miscellaneous.wait(normalWait);
		break;
		case "class" : result= WEB.driverList.get(window).findElement(By.className(path));Miscellaneous.wait(normalWait);
		break;
		case "css" : result = WEB.driverList.get(window).findElement(By.cssSelector(path));Miscellaneous.wait(normalWait);
		break;
		case "linkText" : result = WEB.driverList.get(window).findElement(By.linkText(path));Miscellaneous.wait(normalWait);
		break;
		default: System.out.println("Element not found");
		}
		return result;
	}
	
	public WebElement getComponent(TestStep testStep,Repository repository)
	{
    	String[] parameter = utility.getParameter(testStep.parameter);
    	String window = testStep.machine;
    	String path = repository.table.get(parameter[0]);
    	String findOption = repository.typeTable.get(parameter[0]);
    	WebElement result = null;
    	
		switch(findOption)
		{
		case "id" : result = WEB.driverList.get(window).findElement(By.id(path));Miscellaneous.wait(normalWait);
		break;
		case "xpath" : result = WEB.driverList.get(window).findElement(By.xpath(path));Miscellaneous.wait(normalWait);
		break;
		case "text" : result = WEB.driverList.get(window).findElement(By.xpath("//*[text()='"+path+"']"));Miscellaneous.wait(normalWait);
		break;
		case "class" : result= WEB.driverList.get(window).findElement(By.className(path));Miscellaneous.wait(normalWait);
		break;
		case "css" : result = WEB.driverList.get(window).findElement(By.cssSelector(path));Miscellaneous.wait(normalWait);
		break;
		case "linkText" : result = WEB.driverList.get(window).findElement(By.linkText(path));Miscellaneous.wait(normalWait);
		break;
		default: System.out.println("Element not found");
		}
		
		return result;
	}
	
	public void acceptAlert(TestStep testStep,Repository repository)
	{
		WEB.driverList.get(window).switchTo().alert().accept();
	}
	
}
