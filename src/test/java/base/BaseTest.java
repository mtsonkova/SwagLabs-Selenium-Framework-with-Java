package base;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pageObjects.ProductInfoPage;

import java.io.*;
import java.nio.file.Files;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BaseTest {


    protected static WebDriver driver;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public String jsonFilePath = "src/test/utilities/testData.json";
    JSONObject jsonObject = readJSONFile(jsonFilePath);

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();

    }

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\utilities\\globalConfig.properties");
        properties.load(input);

        String browser = "";
        if (System.getProperty("browser") != null) {
            browser = System.getProperty("browser");
        } else {
            browser = properties.getProperty("browser");
        }
        String baseUrl = properties.getProperty("baseUrl");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.addArguments("--headless");

            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");

            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--headless");

            driver = new EdgeDriver();
        }

        driverThreadLocal.set(driver);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver;
    }

    public static JSONObject readJSONFile(String filePath) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filePath));

            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static File getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destPath = "D:\\Projects\\SwagLabsTestAutomation\\Screenshots\\" + testCaseName + ".png";
        File file = new File(destPath);
        Files.copy(source.toPath(), file.toPath());
        return file;
    }

    @DataProvider(name = "products")
    public Object[][] productsDataProvider() {
        Object value = null;
        var result = (JSONArray) jsonObject.get("purchasedProducts");
        Object[][] data = new Object[result.size()][1];
        for (int i = 0; i < result.size(); i++) {
            // Get the JSON object at index i
            JSONObject jsonObj = (JSONObject) result.get(i);
            //get the values of the jsonObj

            value = jsonObj.get("products");


            //put JSONArray values in Java ArrayList

            data[i][0] = value;
        }

        return data;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void waitForElementToAppear(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static ProductInfoPage clickOnProductName(String name) {
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        for (WebElement product : products) {
            if (product.getText().equals(name)) {
                product.click();
                break;
            }
        }
        return new ProductInfoPage(driver);
    }
}