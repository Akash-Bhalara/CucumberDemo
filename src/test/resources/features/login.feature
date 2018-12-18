 Feature: 

	
	@DEM-16 @login.feature
	Scenario: Login with correct username and password
		Given I navigate to the login page
		And I enter the following for Login
				| username | password      | 
				| admin    | adminpassword | 
		And I click login button
		Then I should see the userform page
