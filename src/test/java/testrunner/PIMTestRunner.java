package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.HashMap;

public class PIMTestRunner extends Setup {
     DashboardPage dashboardPage;
     LoginPage loginPage;
     PIMPage pimPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject = Utils.readJSONFile("./src/test/resources/User.json");
        String username = (String) userObject.get("username");
        String password = (String) userObject.get("password");
        loginPage.doLogin(username, password);
        System.out.println("Execute doLogin");
    }

    @Test(priority = 1, description = "Check blank username validation is given", enabled = true)
    public void addEmployeeWithoutUsername() throws InterruptedException {
         pimPage = new PIMPage(driver);
         pimPage.linkPimPage0th.get(0).click();
         pimPage.btnAddEmployee2nd.get(2).click();
         HashMap<String, String> newUserData = Utils.generateRandomUserInfo();
         pimPage.createEmployee(newUserData.get("employeeId"), newUserData.get("firstName"), newUserData.get("lastName"), "", newUserData.get("password"));
         Thread.sleep(7000);
         String header_actual = driver.findElements(By.className("oxd-text")).get(16).getText();
         String header_expected = "Required";
         Assert.assertTrue(header_actual.contains(header_expected));
         System.out.println("Invalid Employee End");
     }

    @Test(priority = 2, description = "Create employee 1", enabled = true)
    public void addEmployee1() throws InterruptedException, IOException, ParseException {
         pimPage = new PIMPage(driver);
         pimPage.linkPimPage0th.get(0).click();
         pimPage.btnAddEmployee2nd.get(2).click();
         HashMap<String, String> newUserData = Utils.generateRandomUserInfo();
         pimPage.createEmployee(newUserData.get("employeeId"), newUserData.get("firstName"), newUserData.get("lastName"), newUserData.get("username"), newUserData.get("password"));
         Thread.sleep(7000);
         String header_actual = driver.findElement(By.className("orangehrm-main-title")).getText();
         String header_expected = "Personal Details";
         Assert.assertTrue(header_actual.contains(header_expected));

         Utils.writeJsonNewUserArray(newUserData.get("employeeId"), newUserData.get("firstName"), newUserData.get("lastName"), newUserData.get("username"), newUserData.get("password"));
         System.out.println("1st Employee End");
     }

    @Test(priority = 3, description = "Create employee 2", enabled = true)
    public void addEmployee2() throws InterruptedException, IOException, ParseException {
         pimPage = new PIMPage(driver);
         pimPage.linkPimPage0th.get(0).click();
         pimPage.btnAddEmployee2nd.get(2).click();
         HashMap<String, String> newUserData = Utils.generateRandomUserInfo();
         pimPage.createEmployee(newUserData.get("employeeId"), newUserData.get("firstName"), newUserData.get("lastName"), newUserData.get("username"), newUserData.get("password"));
         Thread.sleep(7000);
         String header_actual = driver.findElement(By.className("orangehrm-main-title")).getText();
         String header_expected = "Personal Details";
         Assert.assertTrue(header_actual.contains(header_expected));

         Utils.writeJsonNewUserArray(newUserData.get("employeeId"), newUserData.get("firstName"), newUserData.get("lastName"), newUserData.get("username"), newUserData.get("password"));
         System.out.println("2nd Employee End");
    }

//     @Test(priority = 1, description = "User can view exisitng employee list")
//     public static void main(String[] args) {
//         System.out.println(Utils.generateRandomUserInfo());
//         HashMap<String, String> newUserData = Utils.generateRandomUserInfo();
//         System.out.println("Hello");
//         System.out.println(newUserData.get("firstName"));
//     }

//     @Test(priority = 1, description = "User can view exisitng employee list", enabled = false)
//     public void searchEmployeeInfo() throws InterruptedException {
//         dashboardPage = new DashboardPage(driver);
//         Thread.sleep(3000);
//         dashboardPage.menus.get(1).click(); //Click on PIM menu
//         Thread.sleep(3000);
//         String isUserFound = driver.findElements(By.className("oxd-text--span")).get(11).getText();
//         Assert.assertTrue(isUserFound.contains("Records Found"));
//     }

//     @Test(priority = 3, description = "User can search employee by employee status", enabled = false)
//     public void selectEmployeeStatus() throws InterruptedException {
//         dashboardPage = new DashboardPage(driver);
//         dashboardPage.dropdowns.get(0).click();
//         dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
//         Thread.sleep(500);
//         dashboardPage.dropdowns.get(0).sendKeys(Keys.ARROW_DOWN);
//         Thread.sleep(500);
//         dashboardPage.dropdowns.get(0).sendKeys(Keys.ENTER);
//         driver.findElement(By.cssSelector("[type=submit]")).click();
//         Utils.doScroll(driver, 200);

//     }

    @AfterTest
    public void doLogOut() {
        loginPage = new LoginPage(driver);
        loginPage.doLogOut();
        System.out.println("Logout from Pim");
    }
}
