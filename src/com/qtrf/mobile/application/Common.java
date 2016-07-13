//package com.qtrf.mobile.application;
//
//
//import java.util.ArrayList;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Common {
//	static String[] parameter;
//    public static void actionMapper(ArrayList<String> testStep)
//    {
//    	parameter = Utility.getParameter(testStep.get(4));
//    	EServiceWeb_Repository.ini();
//    	String Action = testStep.get(3);
//    	switch(Action.toUpperCase())
//    	{
//    	case "SETTEXT" : setText(parameter) ;
//    	break;
//    	default : System.out.println("Action not found");
//    	}
//    }
//	private static void setText(String[] parameter2) {
//		// TODO Auto-generated method stub
//		
//	}
//    
//
//}
