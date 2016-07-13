package com.qtrf.core;

import java.util.ArrayList;
import java.util.Hashtable;

public class Config {

	String runName;
	public Hashtable<String, String> table= new Hashtable<String, String>();
	ExcelManager excelManager;
	
	public Config(String runName)
	{
		this.runName=runName;
		excelManager = new ExcelManager(runName);
	}
	
	public void readConfig(String configPath,String sheetName)
	{
		ArrayList<ArrayList<String>> arr = excelManager.getArray(configPath,sheetName);
		for (int i=1;i<arr.size();i++)
		{
			table.put(arr.get(i).get(1), arr.get(i).get(2));
		}
	}
	
	public Hashtable<String, String> getConfig()
	{
		return table;
	}

}
