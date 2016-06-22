package com.qtrf.core;

import java.util.Hashtable;
import java.io.*;
import java.nio.*;

public class Environment {
	
	public static Hashtable<String, String> table= new Hashtable<String, String>();
	
	public static void setValue(String key,String value)
	{
		table.put(key,value);
	}
	
	public static String getValue (String key)
	{
		return table.get(key);
	}	
}
