package com.qtrf.mobile.application;

import java.util.Hashtable;

public class ME_IN_ALLFORONE_Repository extends Utility {

	public static Hashtable<String,String> table = new Hashtable<String,String>();
	public static Hashtable<String,String> typeTable = new Hashtable<String,String>();
	public static void ini()
	{
		table.put("package", "com.ais.mimo.eservice");
		table.put("activity", "com.nextzy.allforone.view.DummyActivity");
		table.put("waitActivity", "com.nextzy.allforone.view.menu.MenuActivity");
		table.put("eService", "com.ais.mimo.eservice");
		typeTable.put("eService", "content-desc");
		table.put("4G", "AIS 4G ADVANCED");
		typeTable.put("4G", "content-desc");
		table.put("fibre", "AIS Fibre");
		typeTable.put("fibre", "content-desc");
		table.put("previlege", "Privilege");
		typeTable.put("previlege", "content-desc");
		table.put("roaming", "Roaming");
		typeTable.put("roaming", "content-desc");
		table.put("superCombo", "AIS SUPER COMBO");
		typeTable.put("superCombo", "content-desc");
		table.put("Home", "//android.widget.TextView[contains(@resource-id,'com.ais.mimo.eservice:id/tv_menu_level_one') and @text='Home']");
		typeTable.put("Home", "xpath");
		table.put("Store", "//android.widget.TextView[contains(@resource-id,'com.ais.mimo.eservice:id/tv_menu_level_one') and @text='Store']");
		typeTable.put("Store", "xpath");
		table.put("Site", "//android.widget.TextView[contains(@resource-id,'com.ais.mimo.eservice:id/tv_menu_level_one') and @text='Site']");
		typeTable.put("Site", "xpath");
		table.put("Settings", "//android.widget.TextView[contains(@resource-id,'com.ais.mimo.eservice:id/tv_menu_level_one') and @text='Settings']");
		typeTable.put("Settings", "xpath");
	}
	
}

