package com.qtrf.mobile.application;

import java.util.Hashtable;

public class Repository {

	public Hashtable<String,String> table = new Hashtable<String,String>();
	public Hashtable<String,String> typeTable = new Hashtable<String,String>();
	
	public Repository(Hashtable<String,String> table,Hashtable<String,String> typeTable)
	{
		this.table = table;
		this.typeTable = typeTable;
	}
	
}
