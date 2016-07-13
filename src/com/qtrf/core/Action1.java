package com.qtrf.core;

import org.junit.runner.JUnitCore;

public class Action1 {

	public static void main(String[] args) {
		
		JUnitCore core = new JUnitCore(); 
		core.run(DriverManager.class);
	}
}
