import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LoginPageTests {

    private static final String VALID_EMAIL = "dmitriy.gontsa@gmail.com";
    private static final String INVALID_EMAIL = "dmitriygontsagmailcom";
    private static final String VALID_PASSWORD = "!Q@W#E4r5t";
    private static final String INVALID_PASSWORD = "12345";
    private static final By EMAIL_INPUT_LOCATOR = By.name("email");
    private static final By PASSWORD_INPUT_LOCATOR = By.name("password");
    private static final By LOG_IN_BUTTON_LOCATOR = By.className("submit");
    private static final By ERROR_MASSAGES_LOCATOR = By.cssSelector(".error>*");
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void startUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.get("https://semantic-ui.com/examples/login.html");
    }

    @Test
    public void positiveTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_LOCATOR)).sendKeys(VALID_EMAIL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_LOCATOR)).sendKeys(VALID_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_IN_BUTTON_LOCATOR)).click();
        Assert.assertTrue(driver.findElements(ERROR_MASSAGES_LOCATOR).size() == 0);
    }

    @Test
    public void invalidEmailTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_LOCATOR)).sendKeys(INVALID_EMAIL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_LOCATOR)).sendKeys(VALID_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_IN_BUTTON_LOCATOR)).click();
        Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'Please enter a valid e-mail')]")).size() > 0);
    }

    @Test
    public void invalidPasswordTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_LOCATOR)).sendKeys(VALID_EMAIL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_LOCATOR)).sendKeys(INVALID_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_IN_BUTTON_LOCATOR)).click();
        Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'Your password must be at least 6 characters')]")).size() > 0);
    }

    @Test
    public void emptyEmailTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT_LOCATOR)).sendKeys(VALID_PASSWORD);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_IN_BUTTON_LOCATOR)).click();
        Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'Please enter your e-mail')]")).size() > 0);
    }

    @Test
    public void emptyPasswordTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT_LOCATOR)).sendKeys(VALID_EMAIL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOG_IN_BUTTON_LOCATOR)).click();
        Assert.assertTrue(driver.findElements(By.xpath("//li[contains(text(),'Please enter your password')]")).size() > 0);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
