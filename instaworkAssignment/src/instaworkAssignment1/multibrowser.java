package instaworkAssignment1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;


public class multibrowser {
	public WebDriver driver  ;
	public String searchelement;
  @Test
  public void f() {
	  WebElement element = driver.findElement(By.name("q"));
      element.sendKeys(searchelement);	  
      element.submit();
      WebElement searchresult = (new WebDriverWait(driver, 10))
              .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
      System.out.println("Sucessfully loaded the search result!");
      List<WebElement> searchlist = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
      String expectedmatch = ("https://www.instawork.com/");
      int listsize = searchlist.size();
      System.out.println("Total number of links in resultant page:"+listsize);
      int i =0,flag=0;
      for (i =0;i<listsize;i++)
      {

      	String actualmatch = searchlist.get(i).getAttribute("href");
      	if (actualmatch.equals(expectedmatch))
      	{
      		 System.out.println(searchlist.get(i).getAttribute("href"));
      		 flag = 1;
      		 break;
      	}

      }
      if(i==0 && flag == 1)
      	System.out.println("It appears at the first position");
      else if (flag==1)
      	System.out.println("It appears at"+i+"th position");
      else
      	System.out.println("Not found");
  }
  @Parameters("browser")
  @BeforeClass
  public void beforeClass(String browser) {
		  if(browser.equalsIgnoreCase("firefox")) {	
			  System.setProperty("webdriver.gecko.driver", "/Users/admin/Downloads/geckodriver");
			  FirefoxOptions options = new FirefoxOptions();
			  options.setLogLevel(FirefoxDriverLogLevel.TRACE);
			   driver  = new FirefoxDriver();
			   System.out.println("Firefox browser is launched");
			 
			  }
		  else if (browser.equalsIgnoreCase("chrome")) { 	 
			  ChromeOptions options = new ChromeOptions();
		      options.addArguments("--disable-notifications");
		       driver =new ChromeDriver(options);
		       System.out.println("chrome browser is launched");
		     
		  } 
		  driver.get("https://www.google.com");
	      driver.manage().window().maximize();	  
		  System.out.println("Successfully opened Google website");
	     
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Enter the text to be searched in google:");
	  Scanner sc = new Scanner(System.in);
	  searchelement = sc.next();
  }

}
