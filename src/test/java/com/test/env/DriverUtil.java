package com.test.env;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

public class DriverUtil {
	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver != null) {
			return driver;
		}

		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", true);
		driver = chooseDriver(capabilities);
		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
		String browserName = System.getProperty("browser");
		if(browserName==null)
			browserName="chrome";
		switch (browserName.toLowerCase()) {
		case "firefox":
			// return new PhantomJSDriver(capabilities);
			FirefoxOptions options = new FirefoxOptions();
			// capabilities.s
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			final FirefoxDriver firefox = new FirefoxDriver();
			return firefox;
		case "chrome":
			//final ChromeOptions chromeOptions = new ChromeOptions();
			//capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			System.out.println("********************* before driver created");
			ChromeDriver driver = new ChromeDriver(capabilities);
			System.out.println("********************* after driver created");
			ErrorHandler handler = new ErrorHandler();
			handler.setIncludeServerErrors(false);
			driver.setErrorHandler(handler);
			return driver;
		default:
			System.out.println("********************* before driver created");
			ChromeDriver driver1 = new ChromeDriver(capabilities);
			System.out.println("********************* after driver created");
			ErrorHandler handler1 = new ErrorHandler();
			handler1.setIncludeServerErrors(false);
			driver1.setErrorHandler(handler1);
			return driver1;
		}
	}	

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.close();
				driver.quit(); // fails in current geckodriver! TODO: Fixme
			} catch (NoSuchMethodError nsme) { // in case quit fails
			} catch (NoSuchSessionException nsse) { // in case close fails
			} catch (SessionNotCreatedException snce) {
			} // in case close fails
			driver = null;
		}
	}

}
