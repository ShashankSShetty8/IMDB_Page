package commonFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonFunctions {
	

	
	
	public static void Launch_URL(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	    String strHomePageTitle = "IMDb - Movies, TV and Celebrities - IMDb";
		driver.get("https://www.imdb.com/");
		if (strHomePageTitle==driver.getTitle())
		{	
			Assert.assertTrue(true, "Page Title verifies with the Actual Title");
		}
		
			
	}
	
	public static void waitMethod(WebDriver driver,By by)
	
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
			
	}
	
	
	public static void clearText(WebDriver driver,By by)
	{	
		WebElement ele=driver.findElement(by);
		ele.clear();
		
	}

	public static void click(WebDriver driver,By by)
	{	
		WebElement ele=driver.findElement(by);
		ele.click();
			
	}

	public static void enterValue(WebDriver driver,By by,String sValue)
	{	
		WebElement ele=driver.findElement(by);
		ele.sendKeys(sValue);
			
	}
	
	public static void selectFromDropdown(WebDriver driver,By by,String sValue)
	{	
		WebElement ele=driver.findElement(by);
		Select dropdown=new Select (ele);
		dropdown.selectByVisibleText(sValue);
			
	}
	
	public static void selectRadioOrCheckbox(WebDriver driver,By by,String sValue)
	{
		List<WebElement> oRad=driver.findElements(by);
		int iSize=oRad.size();
		for (int i=0;i<iSize;i++)
		{
			String sActualValue=oRad.get(i).getAttribute("value");
			if (sActualValue.equalsIgnoreCase(sValue))
			{
				oRad.get(i).click();
			}
			
		}
		
		
		
	}
	

}
