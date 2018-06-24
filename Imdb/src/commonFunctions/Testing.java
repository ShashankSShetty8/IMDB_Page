package commonFunctions;


import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import imdbPage.Movie;
import objectRepository.PageClass;
import objectRepository.SQLVariables;
public class Testing {
	
	    public static WebDriver wbDriver; 
	        
	    @Test
	    public static void test() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException, IOException
	    {
	    	Logger logger=Logger.getLogger("Testing");
	    	PropertyConfigurator.configure("Log4j.properties");
	    	
	    	wbDriver=Driver.intialize("Firefox");
	    	logger.info("Browser Opened");
	    	
	    	CommonFunctions.Launch_URL(wbDriver);
	    	logger.info("URL is launched");
	    	
	    	WebElement element=wbDriver.findElement(By.xpath(PageClass.sMoviesTVShowtimes));
	    	
	    	//Using Actions class for mousehover over the dropdown
	    	//use Mouse hover action for the dropdown element
	    	Actions action = new Actions(wbDriver);
	        action.moveToElement(element).perform();
	         
	        //Using wait method called from CommonFunctions
	        CommonFunctions.waitMethod(wbDriver, By.xpath(PageClass.sTop250Movies));
	        WebElement subElement = wbDriver.findElement(By.xpath(PageClass.sTop250Movies));
	         
	        // use Mouse hover action for the Top250Movies element
	        action.moveToElement(subElement).build().perform();
	         
	        // finally click on that element
	        action.click(subElement).build().perform();
	        logger.info("IMBD Top 250 Page is displayed");
	        
	        
	        List<Movie> topMovies = getTop250Movies(wbDriver);
	 	    
	 	    Class.forName(SQLVariables.sSQLite);
	 	    SQLVariables.con = DriverManager.getConnection(SQLVariables.sDBPath);

	 	    SQLVariables.sQuery= "Drop table if exists Top250Movies";
	    	SQLVariables.stmt=SQLVariables.con.createStatement();
	    	SQLVariables.stmt.execute(SQLVariables.sQuery);
	    	SQLVariables.sQuery= "Create table if not exists Top250Movies (Movie VARCHAR, Rating VARCHAR, Year VARCHAR)";
	    	SQLVariables.stmt.execute(SQLVariables.sQuery);
	    	SQLVariables.stmt.close();
	    	
	    	for(Movie movie: topMovies)
	    	{
	    		insertIntoTable(movie);
	    	}
	    	
	    	printAllMovies();
	    	inputFile.testData();
	    	SQLVariables.con.close();
	    }
	    
	    public static void insertIntoTable(Movie movie) throws SQLException
	    {
	    	SQLVariables.sQuery=String.format("Insert into Top250Movies (Movie,Rating,Year) Values ('%s', '%s', '%s')",
	    			movie.movieName.replace("'", "''"), movie.movieRating, movie.movieYear);
	    	SQLVariables.stmt=SQLVariables.con.createStatement();
	    	SQLVariables.stmt.executeUpdate(SQLVariables.sQuery);
	    	SQLVariables.stmt.close();
	    }
	    
	    public static void printAllMovies() throws SQLException
	    {
	    	SQLVariables.sQuery="Select * from Top250Movies";
	    	SQLVariables.stmt=SQLVariables.con.createStatement();
	    	SQLVariables.resultSet = SQLVariables.stmt.executeQuery(SQLVariables.sQuery);
	    	
	    	int i = 1;
	    	System.out.println("S.No.\tMovie Name\tMovie Rating\tMovie Year"); 
	    	System.out.println("------------------------------------------------------------------------------");
	    	
	    	while (SQLVariables.resultSet.next()) 
	    	{
	    		String movieName=SQLVariables.resultSet.getString(1);
	    		String movieRating=SQLVariables.resultSet.getString(2);
	    		String movieYear=SQLVariables.resultSet.getString(3);	
				System.out.println(String.valueOf(i) + ".\t" + movieName+ "\t" + movieRating + "\t" + movieYear ); 
				i++;
			}
	    	
	    	SQLVariables.resultSet.close();
	    	SQLVariables.stmt.close();
	    	
	    	
	    }
	    
	    
	    
	    public static List<Movie> getTop250Movies(WebDriver wbDriver) throws InterruptedException
	    {
	    	List<Movie> topMovies = new ArrayList<Movie>();
	    	
	    	CommonFunctions.waitMethod(wbDriver, By.xpath("//table[@class='chart full-width']"));
	    	Thread.sleep(3000);
	    		
	    	List<WebElement> sMovieName= wbDriver.findElements(By.xpath("//table[@class='chart full-width']//tr//td[2]/a"));
	    			
	    	List<WebElement> sMovieYear= wbDriver.findElements(By.xpath("//table[@class='chart full-width']//tr//td[2]/span"));
	    		
	    	List<WebElement> sMovieRating= wbDriver.findElements(By.xpath("//table[@class='chart full-width']//tr//td[3]/strong"));
	    		
	    		
	    	for(int i=0;i<sMovieName.size();i++)
	    		{
	    			String sMName=sMovieName.get(i).getText();
	    			String sMYear=sMovieYear.get(i).getText().substring(1,5);
	    			String sMRating=sMovieRating.get(i).getText();
	    			
	    			topMovies.add(new Movie(sMName, sMRating, sMYear));
	    		
	    		}
	    	
	    return topMovies;
	    	
	    }
	    
	    @AfterTest
	    public static void close()
	    {
	    	wbDriver.close();
	    }
	    
	}
	
	


