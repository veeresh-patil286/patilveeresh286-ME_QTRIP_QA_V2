package qtriptest.tests;


import qtriptest.pages.HomePage;
import static qtriptest.DriverSingleton.*;
import static qtriptest.utils.ExtentManager.*;
import static qtriptest.utils.ExtentTestManager.*;
import java.io.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class baseTest {
   RemoteWebDriver driver;
   
   @BeforeSuite(alwaysRun = true)
   public void initTestSuite() throws InterruptedException, MalformedURLException{
    driver = getDriverInstance("chrome");
    //launchApp(HomePage.url);
    HomePage home= new HomePage(driver);
    home.navigateToHomePage();

   }

   @BeforeMethod(alwaysRun = true)
   public void beforeTest(Method m){
    startTest("testCase_01").getTest();
   }

   @AfterMethod(alwaysRun = true)
   public void afterTest(ITestResult iTestResult){
    if(iTestResult.getStatus() == ITestResult.SUCCESS){
            logPass("Step is passed");
    }else if(iTestResult.getStatus() == ITestResult.FAILURE){
        logFail("Trace the Log: "+ iTestResult.getThrowable().getMessage());
        //getTest().log(LogStatus.FAIL, getTest().addScreenCapture(capture(driver)));
        getTest().log(LogStatus.FAIL, getTest().addScreenCapture(capture(driver)));
    }else{
        logSkip("step got skipped");
    }

   }

   private String capture(RemoteWebDriver driver2) {
    return null;
}

@AfterSuite(alwaysRun = true)
   public void afterTestSuite(){
    driver.quit();
    endReport();
   }
}

    
