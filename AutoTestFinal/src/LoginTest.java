import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\java-2024-12\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            System.out.println("🚀 Bắt đầu kiểm thử đăng nhập...");

            // 1️⃣ Truy cập trang đăng nhập
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/Account/Login");
            System.out.println("🔗 Đã mở trang đăng nhập");

            // 2️⃣ Nhấn nút "Đăng nhập"
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("OpenIdConnect")));
            loginButton.click();
            System.out.println("✅ Đã nhấn vào nút Đăng nhập");

            // 3️⃣ Chờ chuyển hướng đến trang nhập email
            wait.until(ExpectedConditions.urlContains("login.microsoftonline.com"));
            System.out.println("🔄 Đang chuyển hướng đến trang đăng nhập Microsoft...");

            // 🔹 Kiểm thử trường hợp sai tên đăng nhập
            testInvalidUsername(driver, wait);
            
            // 🔹 Kiểm thử trường hợp sai mật khẩu
            testInvalidPassword(driver, wait);
            
            // 🔹 Kiểm thử đăng nhập đúng
            testValidLogin(driver, wait);

        } catch (Exception e) {
            System.err.println("❌ Lỗi trong quá trình kiểm thử: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testInvalidUsername(WebDriver driver, WebDriverWait wait) {
        try {
            System.out.println("🔍 Kiểm thử trường hợp sai tên đăng nhập...");
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            emailInput.clear();
            emailInput.sendKeys("sai_email@vanlanguni.vn");
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            nextButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameError")));
            System.out.println("⚠️ Thông báo lỗi xuất hiện: " + errorMessage.getText());
        } catch (Exception e) {
            System.err.println("⚠️ Không tìm thấy thông báo lỗi cho tên đăng nhập sai");
        }
    }

    private static void testInvalidPassword(WebDriver driver, WebDriverWait wait) {
        try {
            System.out.println("🔍 Kiểm thử trường hợp sai mật khẩu...");
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            emailInput.clear();
            emailInput.sendKeys("hoc.2174802010422@vanlanguni.vn");
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            nextButton.click();
            
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.sendKeys("sai_mat_khau");
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            submitButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
            System.out.println("⚠️ Thông báo lỗi xuất hiện: " + errorMessage.getText());
        } catch (Exception e) {
            System.err.println("⚠️ Không tìm thấy thông báo lỗi cho mật khẩu sai");
        }
    }

    private static void testValidLogin(WebDriver driver, WebDriverWait wait) {
        try {
            System.out.println("✅ Kiểm thử đăng nhập đúng...");
            
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.sendKeys("VLU@2174802010422");
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            submitButton.click();

            wait.until(ExpectedConditions.urlContains("cntttest.vanlanguni.edu.vn"));
            System.out.println("🎉 Đăng nhập thành công!");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi kiểm thử đăng nhập đúng: " + e.getMessage());
        }
    }
}
