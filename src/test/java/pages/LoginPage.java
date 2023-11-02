package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(name = "username")
    WebElement txtUserName;
    @FindBy(name="password")
    WebElement txtPassword;
    @FindBy(css = "[type=submit]")
    WebElement btnLogin;
    @FindBy(tagName = "p")
    WebElement lblInvalidCreds;
    @FindBy(className = "oxd-userdropdown")
    List <WebElement> btnTopCornerMenu0th;
    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    List <WebElement> linkLogOut0th;
    @FindBy(className = "orangehrm-login-title")
    List <WebElement> lblLoginPageHeading0th;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLogin(String username, String password){
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public String doLoginWithInvalidCreds(String username, String password){
        txtUserName.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return lblInvalidCreds.getText();
    }

    public String doLogOut(){
        btnTopCornerMenu0th.get(0).click();
        linkLogOut0th.get(0).click();
        return lblLoginPageHeading0th.get(0).getText();
    }
}
