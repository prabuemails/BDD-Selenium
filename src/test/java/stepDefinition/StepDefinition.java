package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class StepDefinition {

    ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    @Before
    public void createDriver(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        tlDriver.set(driver);
    }

    @Given("^navigate to google site$")
    public void navigateUrl(){
        tlDriver.get().navigate().to("https://www.google.com/");
    }

    @When("search for {string}")
    public void search_for(String string) {
        tlDriver.get().findElement(By.xpath("//input[@title='Search']")).sendKeys(string);
        tlDriver.get().findElement(By.xpath("//input[@type='submit']")).click();
    }
    @Then("user should get results for {string}")
    public void user_should_get_results_for(String string) {
        Assert.assertTrue("Search Results not found.", tlDriver.get().findElement(By.xpath("//a[contains(.,'Apple')]")).isDisplayed());
    }

    @AfterStep
    public void addScreenshot(Scenario scenario){
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) tlDriver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");
        }
    }

    @After
    public void closeDriver(){
        try{
            if(tlDriver.get() != null){
                tlDriver.get().quit();
            }
        }catch (Exception e){

        }
    }
}
