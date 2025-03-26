package qtriptest.tests;

import qtriptest.DP;
// import qtriptest.ExternalDataProvider;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class testCase_01 {
    RemoteWebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    SoftAssert softAssert;

    @BeforeClass
    public void config() throws MalformedURLException{
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), chromeOptions);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app");

        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver,registerPage);
        
    }

    @Test(dataProvider = "Data-Provider", dataProviderClass = DP.class)
    public void TestCase01(String UserName, String Password) throws InterruptedException{
        softAssert = new SoftAssert();
        System.out.println("Data from DP");
        // String userName = "test256@gmail.com";
        // String password = "test@123";
        String confirmPassword = Password;


        softAssert.assertTrue(homePage.isRegisterButtonVisible(),"RegisterPage navigation is failed");

        System.out.println("Navigate to Register page");
        homePage.navigateToRegister();

        softAssert.assertTrue(registerPage.isRegisterNavigationSucceded(), 
        "RegisterPage navigation from home is failed");
        Thread.sleep(3000);

        System.out.println("OnBoard new user");
        registerPage.onBoardNewUser(UserName, Password, confirmPassword, true);

        softAssert.assertTrue(loginPage.isLoginNavigationSucceded(), 
        "Login navigation is failed");
        Thread.sleep(2000);
        
        System.out.println("Login new user");
        loginPage.loginUser(UserName, Password, true);
        Thread.sleep(3000);
        homePage.isLogoutButtonVisible();

        Thread.sleep(3000);
        homePage.logoutUser();

        softAssert.assertAll();

    }
}