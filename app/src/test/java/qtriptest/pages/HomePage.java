package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    RegisterPage registerPage;
    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
    public static String InvalidMsg = "No City found";

    @FindBy(xpath = "//a[text()='Register']")
    private WebElement RegisterHomeButton;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutHomeButton;

    @FindBy(className = "hero-input")
    private WebElement searchBox;

    @FindBy(id = "results")
    private WebElement NoCityFoundMsg;

    @FindBy(xpath = "//li[text()=' Goa ']")
    private WebElement CityFoundMsg;

    public boolean isRegisterButtonVisible() throws InterruptedException{
        Thread.sleep(5000);
        return RegisterHomeButton.isDisplayed() && RegisterHomeButton.isEnabled();
    }

    public void navigateToRegister() throws InterruptedException{
        RegisterHomeButton.click();
        Thread.sleep(3000);
    }
    public boolean isLogoutButtonVisible() throws InterruptedException{
        Thread.sleep(2000);
        //waitForElementVisibility(driver, logoutHomeButton);
        return logoutHomeButton.isDisplayed();
    }
    public void logoutUser() throws InterruptedException{
        logoutHomeButton.click();
        Thread.sleep(1000);
    }
    public void searchInvalidData() throws InterruptedException{
        searchBox.sendKeys("invalid");
        Thread.sleep(1000);
    }
    public boolean validateInvalidMsg() throws InterruptedException{
        return NoCityFoundMsg.getText().equals(InvalidMsg);
    }
    public void searchValidData(String cityName) throws InterruptedException{
        Thread.sleep(3000);
        searchBox.clear();
        Thread.sleep(2000);
        searchBox.sendKeys(cityName);
        Thread.sleep(2000);
    }
    public boolean validateValidMsg(String CityName) throws InterruptedException{
        return CityFoundMsg.getText().equals(CityName);
    }

}