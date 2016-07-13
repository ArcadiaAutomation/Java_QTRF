package com.qtrf.core;

import java.util.ArrayList;

public class TestCase {
	
	private String runName;
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	ExcelManager excelManager;
	
	public void  readTestCase(String testCasePath,String sheetName)
	{
		table = excelManager.getArray(testCasePath,sheetName);
	}
	
	public ArrayList<ArrayList<String>> getTestCase()
	{
		return table;
	}
	
	public TestCase(String runName)
	{
		this.runName=runName;
		excelManager = new ExcelManager(runName);
	}
}
