package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class Utils {
    public static void doScroll(WebDriver driver, int heightPixel){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+heightPixel+")");
    }
    public static void waitForElement(WebDriver driver, WebElement webElement, int timeunit_sec){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeunit_sec));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    public static int generateRandomNumber(int min, int max){
        return  (int) Math.round(Math.random()*(max-min)+min);
    }

    public static HashMap<String, String> generateRandomUserInfo(){
        Faker faker = new Faker();
        HashMap<String, String> randomUser = new HashMap<String, String>();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int empId = generateRandomNumber(10000, 99999);
        String employeeId = String.valueOf(empId);
        String username = "test" + generateRandomNumber(1000, 9999);
        String password = "P@ssword123";
        randomUser.put("employeeId", employeeId);
        randomUser.put("firstName", firstName);
        randomUser.put("lastName", lastName);
        randomUser.put("username", username);
        randomUser.put("password", password);
        return randomUser;
    }

    public static JSONObject readJSONFile(String fileLocation) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileLocation)); // Parsing the jsonFile text into Default object type
        JSONObject jsonObject = (JSONObject) obj; // Cast the Default object to JsonObject type object
        return jsonObject;
    }

    public static void writeJsonNewUserArray( String employeeId, String firstName, String lastName, String username, String password) throws IOException, ParseException {
        String fileName="./src/test/resources/NewUsers.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName)); // Parsing the jsonFile text into Default object type
        JSONArray jsonArray = (JSONArray) obj; // Cast the Default object to JsonArray type object
        JSONObject userObj = new JSONObject(); // Declare a blank JsonObject element & the populate the blank object with method parameter

        userObj.put("id",employeeId);
        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("username",username);
        userObj.put("password",password);
        jsonArray.add(userObj); // Add a new node to the JsonArray object

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

//    public static void main(String[] args) throws IOException, ParseException {
////        Check generateRandomNumber method
//        System.out.println(generateRandomNumber(10,50));
//
////        Check readJSONFile method
//        JSONObject userObject = Utils.readJSONFile("./src/test/resources/User.json");
//        String username = (String) userObject.get("username");
//        String password = (String) userObject.get("password");
//        System.out.println(username);
//        System.out.println(password);
//    }
}
