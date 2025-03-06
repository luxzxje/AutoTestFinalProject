package LoginTest;

import Authentication.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ValidLoginTest extends BaseTest {
    public static void main(String[] args) {
        ValidLoginTest test = new ValidLoginTest();
        test.runTest();
    }

    public void runTest() {
        System.out.println("✅ Kiểm thử đăng nhập đúng...");
        openLoginPage();

        // Nhập email
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        emailInput.clear();
        emailInput.sendKeys(validEmail);

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        nextButton.click();

        // Nhập mật khẩu
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        passwordInput.sendKeys(validPassword);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        submitButton.click();

        // Kiểm tra trang "Stay signed in?"
        try {
            WebElement staySignedInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
            staySignedInButton.click();
            System.out.println("🔄 Đã xử lý trang 'Stay signed in?'");
        } catch (Exception e) {
            System.out.println("ℹ Không có trang 'Stay signed in?'. Tiếp tục...");
        }

        // Kiểm tra đăng nhập thành công
        try {
            wait.until(ExpectedConditions.urlContains("cntttest.vanlanguni.edu.vn"));
            System.out.println("🎉 Đăng nhập thành công!");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi kiểm thử đăng nhập đúng: " + e.getMessage());
        }

        closeBrowser();
    }
}
