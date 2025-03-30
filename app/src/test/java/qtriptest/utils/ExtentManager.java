package qtriptest.utils;
import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.Extentreports;


public class ExtentManager {

    static ExtentReports extentreports;
    final static String reportPath = System.getProperty("user.dir")+File.separator+"reports"+File.separator+"qtripTestResults.html";

    public static synchronized ExtentReports getExtentReport(){
        if(extentreports == null){
            extentreports = new ExtentReports(reportPath, true);
        }
        return extentreports;

    }
}
