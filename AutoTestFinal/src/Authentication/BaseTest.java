package Authentication;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected static WebDriver driver;  // Đổi thành static để chỉ có 1 WebDriver duy nhất
    protected static WebDriverWait wait;
    protected String validEmail, validPassword;
    protected String invalidEmail, invalidPassword;

    public BaseTest() {
        if (driver == null) { // Chỉ khởi tạo driver nếu chưa có
            System.setProperty("webdriver.chrome.driver", "D:\\java-2024-12\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            driver.manage().window().maximize(); // Đảm bảo trình duyệt full màn hình
        }
        loadCredentials();
    }

    private void loadCredentials() {
        Properties prop = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\eclipse-workspace\\AutoTestFinal\\credentials.txt"))) {
            prop.load(reader);
            validEmail = prop.getProperty("valid_email");
            validPassword = prop.getProperty("valid_password");
            invalidEmail = prop.getProperty("invalid_email");
            invalidPassword = prop.getProperty("invalid_password");
        } catch (IOException e) {
            System.err.println("⚠ Lỗi khi đọc file credentials: " + e.getMessage());
        }
    }

    public void openLoginPage() {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("OpenIdConnect")));
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("login.microsoftonline.com"));
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null; // Đặt lại driver để tránh mở lại khi chạy test khác
        }
    }
}
