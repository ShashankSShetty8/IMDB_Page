package featureDefinition;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import commonFunctions.CommonFunctions;
import commonFunctions.Driver;
import commonFunctions.inputFile;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import imdbPage.Movie;
import objectRepository.PageClass;
import objectRepository.SQLVariables;

public class FeatureDefinition 
{
	public static WebDriver wbDriver;
	public Scenario scenario;
	
	public static List<Movie> Movies;
	
	@Before
	public void setUp(Scenario scenario)
	{
		this.scenario=scenario;
	}

	@Given("^I have the IMDB url$")
	public static void i_have_the_IMDB_url() throws Throwable 
	{
		
    	wbDriver=Driver.intialize("Firefox");
	}

	@When("^I enter the IMDB url$")
	public static void i_enter_the_IMDB_url() throws Throwable 
	{
		CommonFunctions.Launch_URL(wbDriver);
    	
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

	}

	@When("^TOP IMDB movies is displayed$")
	public static void top_IMDB_movies_is_displayed() throws Throwable 
	{
		Movies = new ArrayList<Movie>();
    	
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
    			System.out.println(sMName);
    			System.out.println(sMRating);
    			System.out.println(sMYear);
    			Movies.add(new Movie(sMName, sMRating, sMYear));
    		
    		}
    	
    	
    	
    	
	}

	@Then("^fetch the Top movies and store it in database$")
	public static void fetch_the_Top_movies_and_store_it_in_database() throws Throwable 
	{
			
		Class.forName(SQLVariables.sSQLite);
	 	    SQLVariables.con = DriverManager.getConnection(SQLVariables.sDBPath);

	 	    SQLVariables.sQuery= "Drop table if exists Top250Movies";
	    	SQLVariables.stmt=SQLVariables.con.createStatement();
	    	SQLVariables.stmt.execute(SQLVariables.sQuery);
	    	SQLVariables.sQuery= "Create table if not exists Top250Movies (Movie VARCHAR, Rating VARCHAR, Year VARCHAR)";
	    	SQLVariables.stmt.execute(SQLVariables.sQuery);
	    	SQLVariables.stmt.close();
	    	
	    	
	    	for(Movie movie: Movies)
	    	{
	    		SQLVariables.sQuery=String.format("Insert into Top250Movies (Movie,Rating,Year) Values ('%s', '%s', '%s')",
		    			movie.movieName.replace("'", "''"), movie.movieRating, movie.movieYear);
		    	SQLVariables.stmt=SQLVariables.con.createStatement();
		    	SQLVariables.stmt.executeUpdate(SQLVariables.sQuery);
		    	SQLVariables.stmt.close();
	    	}
	    	
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
	    	inputFile.testData();
	    	SQLVariables.con.close();
	    	wbDriver.close();
	}


	
	
}
