package runnerClass;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "./src/featureDefinition/Cucumber.feature"
		,glue={"featureDefinition"},
		format= {
				"pretty",
				"html: Output/Reporting"
		},
		
		tags= {
				"~@ignore",
				"~@RunMe",
				"@Test"
		}
		
		)
 
public class runnerClass 

{

}
