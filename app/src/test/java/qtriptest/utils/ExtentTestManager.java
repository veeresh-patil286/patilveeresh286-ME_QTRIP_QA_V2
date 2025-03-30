
package qtriptest.utils;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static ExtentReports extentReports = ExtentManager.getExtentReport();
    static Map<Object, Object> extentMap = new HashMap<>();

    public static ExtentTest startTest(String testName){
        ExtentTest extentTest = extentReports.startTest(testName);
        extentMap.put(Thread.currentThread().getId(), extentTest);
        return extentTest;
    
    }

    public static ExtentTest getTest(){
        return (ExtentTest) extentMap.get(Thread.currentThread().getId());
    }

    public static void logInfo(String info){
        getTest().log(LogStatus.INFO, info);
    }  
    public static void logPass(String info){
        getTest().log(LogStatus.PASS, info);
    }
    public static void logFail(String info){
        getTest().log(LogStatus.FAIL, info);
    } 
    public static void logSkip(String info){
        getTest().log(LogStatus.SKIP, info);
    } 
    public static void endReport(){
        extentReports.endTest(getTest());
        extentReports.flush();
    } 
}
