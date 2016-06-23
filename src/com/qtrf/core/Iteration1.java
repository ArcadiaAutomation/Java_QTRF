package com.qtrf.core;

import java.util.ArrayList;

public class Iteration1 {

	private static ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	public static boolean isIteration(String path,String sheetName)
	{
		return ExcelManager.isSheetExist(path,sheetName);
	}
	
	public static void readIteration(String testCasePath,String sheetName)
	{
		table = ExcelManager.getArray(testCasePath,sheetName);
	}
	
	public static ArrayList<ArrayList<String>> getIteration()
	{
		return table;
	}
}
