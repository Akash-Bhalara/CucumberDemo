package com.test.env;

import org.testng.annotations.Parameters;

import com.test.base.BaseClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks extends BaseClass {

	private BaseClass base;

	public ServiceHooks(BaseClass base) {
		this.base = base;
	}

	@Before	
	public void InitializeTest() {
		
		this.base.Driver =  DriverUtil.getDriver();
	}

	@After
	public void TearDownTest(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take screenshot logic goes here
			System.out.println(scenario.getName());
		}
		this.base.Driver.close();
		System.out.println("Closing the browser : MOCK");
	}

}
