package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "User can not do login if wrong credential is provided")
    public void doLoginWIthInvalidCreds() {
        loginPage = new LoginPage(driver);
        String message_actual = loginPage.doLoginWithInvalidCreds("admin", "wrongpass");
        String message_expected = "Invalid credentials";
        Assert.assertTrue(message_actual.contains(message_expected));
        System.out.println("Login Test-1");
    }

    @Test(priority = 2, description = "User can do login successfully")
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        String username, password;
        JSONObject userObject = Utils.readJSONFile("./src/test/resources/User.json");
        if (System.getProperty("username") != null && System.getProperty("password") != null) {
            username = System.getProperty("username");
            password = System.getProperty("password");
        } else {
            username = (String) userObject.get("username");
            password = (String) userObject.get("password");
        }

        loginPage.doLogin(username, password);

        WebElement headerText = driver.findElement(By.tagName("h6"));
        String header_actual = headerText.getText();
        String header_expected = "Dashboard";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(header_actual, header_expected);
        WebElement profileImageElement = driver.findElement(By.className("oxd-userdropdown-img"));
        softAssert.assertTrue(profileImageElement.isDisplayed());
        softAssert.assertAll();
        System.out.println("Login Test-2");
    }

    @Test(priority = 3, description = "User can do log-out successfully")
    public void doLogOut() {
        loginPage = new LoginPage(driver);
        String message_actual = loginPage.doLogOut();
        String message_expected = "Login";
        Assert.assertTrue(message_actual.contains(message_expected));
        System.out.println("Login Test-3");
    }
}

