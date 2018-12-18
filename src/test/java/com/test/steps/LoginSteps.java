package com.test.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.base.BaseClass;
import com.test.pageobject.LoginPage;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginSteps extends BaseClass {

	private BaseClass base;
	private LoginPage page;

	public LoginSteps(BaseClass base) {
		this.base = base;		
		page = new LoginPage(base.Driver);
	}
	

	@Then("^I should see the userform page$")
	public void iShouldSeeTheUserformPage() throws Throwable {

		Assert.assertEquals("Its not displayed", base.Driver.findElement(By.id("Initial")).isDisplayed(), true);
	}

	@Given("^I navigate to the login page$")
	public void iNavigateToTheLoginPage() throws Throwable {
		System.out.println("Navigate Login Page");
		base.Driver.navigate().to("http://www.executeautomation.com/demosite/Login.html");
	}

	@And("^I click login button$")
	public void iClickLoginButton() throws Throwable {
		page.ClickLogin();
	}

	@And("^I enter the following for Login$")
	public void iEnterTheFollowingForLogin(DataTable table) throws Throwable {
		// Create an ArrayList
		List<User> users = new ArrayList<User>();
		// Store all the users
		users = table.asList(User.class);	

		for (User user : users) {
			page.Login(user.username, user.password);
		}
	}

	@And("^I enter ([^\"]*) and ([^\"]*)$")
	public void iEnterUsernameAndPassword(String userName, String password) throws Throwable {
		System.out.println("UserName is : " + userName);
		System.out.println("Password is : " + password);
	}

	@Then("^I should see the userform page wrongly$")
	public void iShouldSeeTheUserformPageWrongly() throws Throwable {

		Assert.assertEquals("Its not displayed", base.Driver.findElement(By.id("sdfgdsfsd")).isDisplayed(), true);
	}

	public class User {
		public String username;
		public String password;

		public User(String userName, String passWord) {
			username = userName;
			password = passWord;
		}
	}

}