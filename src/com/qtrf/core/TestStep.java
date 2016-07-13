package com.qtrf.core;

import java.util.ArrayList;

public class TestStep {

	public String step;
	public String application;
	public String machine;
	public String action;
	public String parameter;
	public String onErrorResume;
	public String remark;
	
	public TestStep(ArrayList<String> testStep)
	{
		this.step=testStep.get(0);
		this.application=testStep.get(1);
		this.machine=testStep.get(2);
		this.action=testStep.get(3);
		this.parameter=testStep.get(4);
		this.onErrorResume=testStep.get(5);
		this.remark=testStep.get(6);
	}
	
}
