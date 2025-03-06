package LoginTest;

import Authentication.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvalidUsernameTest extends BaseTest {
    public static void main(String[] args) {
        InvalidUsernameTest test = new InvalidUsernameTest();
        test.runTest();
    }

    public void runTest() {
        System.out.println("🔍 Kiểm thử trường hợp nhập sai tên đăng nhập...");
        openLoginPage();

        // Nhập email không hợp lệ
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        emailInput.clear();
        emailInput.sendKeys(invalidEmail);

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        nextButton.click();

        // Kiểm tra lỗi hiển thị
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116Error")));
            System.out.println("⚠️ Thông báo lỗi hiển thị: " + errorMessage.getText());
        } catch (Exception e) {
            System.out.println("🔄 Không tìm thấy thông báo lỗi trong phần tử có ID 'i0116Error', kiểm tra cách khác...");

            // Kiểm tra lỗi bằng class hoặc thông báo khác
            try {
                WebElement altErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-error, .message-text, .input-error")));
                System.out.println("⚠️ Thông báo lỗi khác được tìm thấy: " + altErrorMessage.getText());
            } catch (Exception ex) {
                System.err.println("❌ Không tìm thấy thông báo lỗi. Kiểm thử thất bại!");
            }
        }

        closeBrowser();
    }
}
