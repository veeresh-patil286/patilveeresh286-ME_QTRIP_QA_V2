package qtriptest.pages;

// import java.rmi.Remote;
import java.util.UUID;
// import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    RemoteWebDriver driver;
    public String DynamicUser;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    public String email = null;
    private static String registerEndPoint  = "/pages/register/";

    @FindBy(xpath = "//h2[@class='formtitle']")
    WebElement registerHead;

    @FindBy(name = "email")
    private WebElement emailTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordTextBox;

    @FindBy(xpath = "//button[text()='Register Now']")
    private WebElement registerButton;

    public boolean isRegisterNavigationSucceded(){
        return driver.getCurrentUrl().contains(registerEndPoint) && 
        registerHead.getText().equals("Register");

    }

    public void onBoardNewUser(String email,String password,String confirmPassword, boolean isDynamicUser) throws InterruptedException{
        if(isDynamicUser){
            email = String.format("testuser_%s@email.com", UUID.randomUUID().toString());
            this.email = email;
        }
        emailTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        confirmPasswordTextBox.sendKeys(confirmPassword);
        
        registerButton.click();
        Thread.sleep(1000);
    }
}
