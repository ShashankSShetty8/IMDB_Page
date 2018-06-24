package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class Driver {
	public static WebDriver driver=null;

	
	public static WebDriver intialize(String sBrowser) {
		if (sBrowser.equals("Chrome"))

		{
			System.setProperty("webdriver.chrome.driver",
					"C:\\Selenium_Testing\\chromedriver_win32 (4)\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (sBrowser.equals("Firefox"))

		{
			System.setProperty("webdriver.gecko.driver",
					"C:\\Selenium_Testing\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		else if (sBrowser.equals("IE"))

		{
			System.setProperty("webdriver.ie.driver",
					"C:\\Selenium_Testing\\IEDriverServer_x64_3.12.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			driver = null;
		}

		return driver;
	}


}
