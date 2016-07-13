package com.qtrf.core;

import java.util.ArrayList;

public class Iteration {

	String runName;
	ExcelManager excelManager;
	
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	public boolean isIteration(String path,String sheetName)
	{
		return excelManager.isSheetExist(path,sheetName);
	}
	
	public void readIteration(String testCasePath,String sheetName)
	{
		table = excelManager.getArray(testCasePath,sheetName);
	}
	
	public ArrayList<ArrayList<String>> getIteration()
	{
		return table;
	}
	
	public Iteration(String runName)
	{
		this.runName=runName;
		excelManager = new ExcelManager(runName);
	}
}
