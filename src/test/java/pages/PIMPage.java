package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {
    @FindBy(css = ".oxd-sidepanel-body ul li:nth-child(2)")
    public List<WebElement> linkPimPage0th;
    @FindBy(css = "[name=firstName]")
    public WebElement txtFirstName;
    @FindBy(css = "[name=lastName]")
    public WebElement txtLastName;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtEmployeeId4th;
    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggleLoginDetails;
    @FindBy(tagName = "input")
    public List<WebElement> txtUserName7th;
    @FindBy(tagName = "input")
    public List<WebElement> txtPassword10th;
    @FindBy(tagName = "input")
    public List<WebElement> txtConfirmPassword11th;
    @FindBy(css = "[type=submit]")
    public WebElement btnSaveEmployee;
    @FindBy(tagName = "input")
    public List<WebElement> txtSearchEmpName1st;
    @FindBy(tagName = "button")
    public List<WebElement> btnUpdateEmployee6th;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtUpdateEmployeeId5th;

    public PIMPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String employeeId, String firstname, String lastname, String username, String password) throws InterruptedException {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        WebElement empID = txtEmployeeId4th.get(4);
        Thread.sleep(1000);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);
        Thread.sleep(1000);
        btnToggleLoginDetails.click();
        txtUserName7th.get(7).sendKeys(username);
        txtPassword10th.get(10).sendKeys(password);
        txtConfirmPassword11th.get(11).sendKeys(password);
        Thread.sleep(1500);
        btnSaveEmployee.click();
    }
    public void createEmployeeWithoutUsername(String firstname, String lastname, String employeeId, String password) throws InterruptedException {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        WebElement empID = txtEmployeeId4th.get(4);
        Thread.sleep(1000);
        empID.clear();
        empID.sendKeys(Keys.CONTROL + "a");
        empID.sendKeys(employeeId);
        Thread.sleep(1000);
        btnToggleLoginDetails.click();
        txtPassword10th.get(10).sendKeys(password);
        txtConfirmPassword11th.get(11).sendKeys(password);
        Thread.sleep(1500);
        btnSaveEmployee.click();
    }
    public void SearchEmployeeByInvalidName(String employeeName) throws InterruptedException {
        txtSearchEmpName1st.get(1).sendKeys(employeeName);
        Thread.sleep(1500);
        btnSaveEmployee.click();
    }
    public void SearchEmployeeByValidName(String employeeName) throws InterruptedException {
        txtSearchEmpName1st.get(1).sendKeys(employeeName);
        Thread.sleep(1500);
        btnSaveEmployee.click();
    }
    public void updateEmployeeById(String employeeId) throws InterruptedException {
        btnUpdateEmployee6th.get(6).click();
        txtUpdateEmployeeId5th.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        Thread.sleep(1000);
        txtUpdateEmployeeId5th.get(5).sendKeys(employeeId);
        Thread.sleep(1500);
        btnUpdateEmployee6th.get(1).click();
    }
    public void SearchEmployeeByValidId(String randomEmployeeId) throws InterruptedException {
        txtEmployeeId4th.get(1).sendKeys(randomEmployeeId);
        Thread.sleep(1500);
        btnSaveEmployee.click();
    }
}
