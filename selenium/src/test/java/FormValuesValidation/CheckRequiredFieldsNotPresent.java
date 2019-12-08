package FormValuesValidation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckRequiredFieldsNotPresent {
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/kkb/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckRequiredFieldsNotPresent() throws Exception {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.findElement(By.id("email_create")).clear();

        String emailValue = new SimpleDateFormat("yyyyMMddHHmm'@email.com'").format(new Date());
        driver.findElement(By.id("email_create")).sendKeys(emailValue);

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Email address'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Create an account'])[1]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='DNI / NIF / NIE'])[1]/following::span[1]")).click();
        try {
            assertEquals("There are 8 errors\n" +
                    "You must register at least one phone number.\n" +
                    "lastname is required.\n" +
                    "firstname is required.\n" +
                    "passwd is required.\n" +
                    "address1 is required.\n" +
                    "city is required.\n" +
                    "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
                    "This country requires you to choose a State.",
                    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Create an account'])[1]/following::div[1]")).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
