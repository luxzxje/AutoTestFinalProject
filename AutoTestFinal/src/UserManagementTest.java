import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UserManagementTest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\java-2024-12\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            System.out.println("🚀 Bắt đầu kiểm thử User Management...");

            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
            System.out.println("🔗 Đã mở trang User Management");

            // Gọi các test case
            viewListOfUsers(driver, wait);
            createUser(driver, wait);
            updateUser(driver, wait);
            deleteUser(driver, wait);
            searchUser(driver, wait);
            lockUser(driver, wait);

        } catch (Exception e) {
            System.err.println("❌ Lỗi trong quá trình kiểm thử: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewListOfUsers(WebDriver driver, WebDriverWait wait) {
        System.out.println("📋 Kiểm thử xem danh sách người dùng...");
        driver.findElement(By.id("viewUsers")) .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userTable")));
        System.out.println("✅ Danh sách người dùng đã tải thành công");
    }

    private static void createUser(WebDriver driver, WebDriverWait wait) {
        System.out.println("➕ Kiểm thử tạo người dùng mới...");
        driver.findElement(By.id("createUser")) .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userForm")));
        driver.findElement(By.id("username")) .sendKeys("testuser");
        driver.findElement(By.id("password")) .sendKeys("password123");
        driver.findElement(By.id("submit")) .click();
        System.out.println("✅ Người dùng mới đã được tạo");
    }

    private static void updateUser(WebDriver driver, WebDriverWait wait) {
        System.out.println("✏️ Kiểm thử cập nhật người dùng...");
        driver.findElement(By.id("editUser")) .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("updateForm")));
        driver.findElement(By.id("username")) .clear();
        driver.findElement(By.id("username")) .sendKeys("updateduser");
        driver.findElement(By.id("submit")) .click();
        System.out.println("✅ Người dùng đã được cập nhật");
    }

    private static void deleteUser(WebDriver driver, WebDriverWait wait) {
        System.out.println("🗑️ Kiểm thử xóa người dùng...");
        driver.findElement(By.id("deleteUser")) .click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("✅ Người dùng đã bị xóa");
    }

    private static void searchUser(WebDriver driver, WebDriverWait wait) {
        System.out.println("🔍 Kiểm thử tìm kiếm người dùng...");
        driver.findElement(By.id("searchBox")) .sendKeys("testuser");
        driver.findElement(By.id("searchButton")) .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchResults")));
        System.out.println("✅ Tìm kiếm hoàn tất");
    }

    private static void lockUser(WebDriver driver, WebDriverWait wait) {
        System.out.println("🔒 Kiểm thử khóa người dùng...");
        driver.findElement(By.id("lockUser")) .click();
        wait.until(ExpectedConditions.textToBe(By.id("status"), "Locked"));
        System.out.println("✅ Người dùng đã bị khóa");
    }
}
