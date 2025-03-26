package qtriptest.pages;

// import java.io.IOException;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    RemoteWebDriver driver;
    RegisterPage registerPage;
    public LoginPage(RemoteWebDriver driver,RegisterPage registerPage){
        this.driver = driver;
        AjaxElementLocatorFactory ajax =  new AjaxElementLocatorFactory(driver, 25);
        PageFactory.initElements(ajax, this);
        this.registerPage = registerPage;

    }

    private static final String Login_Page_Endpoint = "/pages/login";

    @FindBy(css = "h2.formtitle")
    private WebElement loginFormHeader;

    @FindBy(name = "email")
    private WebElement loginEmailTextBox;

    @FindBy(name = "password")
    private WebElement loginPasswordTextBox;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    private WebElement loginButton;

    public boolean isLoginNavigationSucceded() throws InterruptedException{
        Thread.sleep(3000);
        return driver.getCurrentUrl().contains(Login_Page_Endpoint)
         && loginFormHeader.getText().equals("Login");
    }
    public void loginUser(String email, String password, boolean isDynamicUser) throws InterruptedException{

        if(isDynamicUser){
            email = registerPage.email;
        }

        loginEmailTextBox.sendKeys(email);
        loginPasswordTextBox.sendKeys(password);
        loginButton.click();
        Thread.sleep(1000);
    }


    
}