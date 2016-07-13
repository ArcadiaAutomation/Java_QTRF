package com.qtrf.mobile.application;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.qtrf.core.Config;
import com.qtrf.core.Environment;
import com.qtrf.core.TestStep;

import io.appium.java_client.android.AndroidDriver;
//import com.qtrf.core.LogManager;
public class WebCommon {
        
	String runName;
	String[] parameter;
	String udid;
	Executor executor;
	Config config;
	Environment environment;
	Utility utility;

	public WebCommon(String runName,Config config,Environment environment)
	{
		this.runName=runName;
		this.config=config;
		this.environment=environment;
		utility = new Utility(runName,config,environment);
		executor = new Executor(runName);
	}
	
        WebDriver driver;
        AndroidDriver Mdriver = null;
        public void actionMapper(TestStep testStep)
        {
            parameter = utility.getParameter(testStep.parameter);
            String Action = testStep.action;
            switch(Action.toUpperCase())
            {
            case "OPENBROWSER" :
                OpenBrowser(parameter[0],parameter[1]);
                break;
            case "CLOSEBROWSER" :
                CloseBrowser();
                break;
            case "SETTEXT" : 
                setText(parameter[0],parameter[1],parameter[2]);
                break;
            case "CLICKELEMENT" :
                ClickElement(parameter[0],parameter[1]);
                break;
            case "CHECKELEMENT" :
                CheckElement(parameter[0],parameter[1]);
                break;
            case "SELECTDROPDOWN" :
                SelectDropDown(parameter[0],parameter[1],parameter[2]);
                break;
            case "ISEXISTS" :
                if (elementIsExists(parameter[0],parameter[1])){
                    
                }
                else {
                    
                }
                break;
            case "OPENBROWSERPARALLEL" :
                OpenBrowserParallel(parameter[0],parameter[1]);
                break;
            case "ACTIVEESERVICEWEB" :
                ActiveEserviceWeb();
                break;
            default : System.out.println("Action not found");
            }   
        }
        
        public void setText(String type ,String value, String text) {
            
            switch(type.toUpperCase()) {
            case "ID" : 
                driver.findElement(By.id(value)).sendKeys(text);
                break;
            case "NAME" : 
                driver.findElement(By.name(value)).sendKeys(text);
                break;
            case "LINK TEXT" : 
                driver.findElement(By.linkText(value)).sendKeys(text);
                break;
            case "TAG NAME" : 
                driver.findElement(By.tagName(value)).sendKeys(text);
                break;
            case "CLASS" : 
                driver.findElement(By.className(value)).sendKeys(text);
                break;
            case "CSS" : 
                driver.findElement(By.cssSelector(value)).sendKeys(text);
                break;
            case "XPATH" : 
                driver.findElement(By.xpath(value)).sendKeys(text);
                break;
            default : System.out.println("Action not found");
            }
        }
        public void ClickElement(String type, String value) {
            
            switch(type.toUpperCase()) {
            case "ID" : 
                driver.findElement(By.id(value)).click();
                break;
            case "NAME" : 
                driver.findElement(By.name(value)).click();
                break;
            case "LINK TEXT" : 
                driver.findElement(By.linkText(value)).click();
                break;
            case "TAG NAME" : 
                driver.findElement(By.tagName(value)).click();
                break;
            case "CLASS" : 
                driver.findElement(By.className(value)).click();
                break;
            case "CSS" : 
                driver.findElement(By.cssSelector(value)).click();
                break;
            case "XPATH" : 
                driver.findElement(By.xpath(value)).click();
                break;
            default : System.out.println("Action not found");
            }
        }
        public String getText(String type, String value) {
            
            switch(type.toUpperCase()) {
            case "ID" : 
                return driver.findElement(By.id(value)).getText();
            case "NAME" : 
                return driver.findElement(By.name(value)).getText();
            case "LINK TEXT" : 
                return driver.findElement(By.linkText(value)).getText();
            case "TAG NAME" : 
                return driver.findElement(By.tagName(value)).getText();
            case "CLASS" : 
                return driver.findElement(By.className(value)).getText();
            case "CSS" : 
                return driver.findElement(By.cssSelector(value)).getText();
            case "XPATH" : 
                return driver.findElement(By.xpath(value)).getText();
            default : System.out.println("Action not found");
                return "";
            }
        }
        public void OpenBrowser(String Url, String type) {
            
              System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
              driver = new ChromeDriver();
              driver.get("https://"+Url);
              driver.manage().window().maximize();
        }
        public void OpenBrowserParallel(String Node,String url){
             System.out.println(" Executing on CHROME");
             DesiredCapabilities cap = DesiredCapabilities.chrome();
             cap.setBrowserName("chrome");
             System.out.println(Node);
             try {
                driver = new RemoteWebDriver(new URL(Node), cap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             driver.manage().window().maximize();
             driver.navigate().to(url);
             
        }
        public void ActiveEserviceWeb(){
            driver.switchTo().frame(driver.findElement(By.id("MainIframe")));
        }
        public void CloseBrowser() {
              driver.quit();
              //LogManager.addStep("CloseBrowser","","","pass","");
        }
        public void SelectDropDown(String type, String value, String text) {
            Select dropdown ;
            switch(type.toUpperCase()) {
            case "ID" : 
                dropdown = new Select(driver.findElement(By.id(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "NAME" : 
                dropdown = new Select(driver.findElement(By.name(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "LINK TEXT" : 
                dropdown = new Select(driver.findElement(By.linkText(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "TAG NAME" : 
                dropdown = new Select(driver.findElement(By.tagName(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "CLASS" : 
                dropdown = new Select(driver.findElement(By.className(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "CSS" : 
                dropdown = new Select(driver.findElement(By.cssSelector(value)));
                dropdown.selectByVisibleText(text);
                break;
            case "XPATH" : 
                dropdown = new Select(driver.findElement(By.xpath(value)));
                dropdown.selectByVisibleText(text);
                break;
            default : System.out.println("Action not found");
            }
        }
        public void CheckElement(String type, String value) {
            
            switch(type.toUpperCase()) {
            case "ID" : 
                List<WebElement> elements = driver.findElements(By.id(value));
                elements.get(0).click();
                break;
            case "NAME" : 
                driver.findElement(By.name(value)).click();
                break;
            case "LINK TEXT" : 
                driver.findElement(By.linkText(value)).click();
                break;
            case "TAG NAME" : 
                driver.findElement(By.tagName(value)).click();
                break;
            case "CLASS" : 
                driver.findElement(By.className(value)).click();
                break;
            case "CSS" : 
                driver.findElement(By.cssSelector(value)).click();
                break;
            case "XPATH" : 
                driver.findElement(By.xpath(value)).click();
                break;
            default : System.out.println("Action not found");
            }
        }
        public boolean elementIsExists(String type, String value) {
            
            switch(type.toUpperCase()) {
            case "ID" : 
                return driver.findElement(By.id(value)).isDisplayed();
            case "NAME" : 
                return driver.findElement(By.name(value)).isDisplayed();
            case "LINK TEXT" : 
                return driver.findElement(By.linkText(value)).isDisplayed();
            case "TAG NAME" : 
                return driver.findElement(By.tagName(value)).isDisplayed();
            case "CLASS" : 
                return driver.findElement(By.className(value)).isDisplayed();
            case "CSS" : 
                return driver.findElement(By.cssSelector(value)).isDisplayed();
            case "XPATH" : 
                return driver.findElement(By.xpath(value)).isDisplayed();
            default : System.out.println("Action not found");
                return false;
            }
        }
        public void OpenAppMoblie(String IP,String udid) {
            System.out.println(" Executing on Mobile"+" at "+IP);
            DesiredCapabilities capabilities = null;
            
            capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "any");
            capabilities.setCapability("udid", udid); 
            //capabilities.setCapability("autoLaunch","false");
            capabilities.setCapability("appPackage", "com.calea.echo"); 
            capabilities.setCapability("appActivity", "com.calea.echo.MainActivity");
            wait(10);
            try {
                Mdriver = new AndroidDriver(new URL(IP),capabilities);
                wait(5);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        public String getOTP(int index) {
             String OTP=selectMessage(index);
            
             int position = OTP.indexOf("OTP");
             if (position!=-1)
             {
             System.out.println("OTP : "+OTP.substring(position+6,position+10));
             String otpNumber=OTP.substring(position+6,position+10);
             return otpNumber;
             }
             else
             {
             System.out.println("OTP Not found in message");
             return "OTP Not found in message";
             }
         }
        public String selectMessage(int index)
        {
            List<WebElement> elements = Mdriver.findElementsByXPath("//*[@resource-id='com.calea.echo:id/content']");
            System.out.println(elements.get(elements.size()-index).getAttribute("text"));
            return elements.get(elements.size()-index).getAttribute("text");
        }
        public void wait(int sec)
        {
            try {
                Thread.sleep(1000*sec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}