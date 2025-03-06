package UserManagermentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteUserTest {
    private WebDriver driver;
    private WebDriverWait wait;

    public DeleteUserTest(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void run() {
        System.out.println("🗑️ Kiểm thử xóa người dùng...");
        driver.findElement(By.id("deleteUser")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        System.out.println("✅ Người dùng đã bị xóa");
    }
}
