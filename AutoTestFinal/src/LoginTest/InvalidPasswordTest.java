package LoginTest;

import Authentication.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvalidPasswordTest extends BaseTest {
    public static void main(String[] args) {
        InvalidPasswordTest test = new InvalidPasswordTest();
        test.runTest();
    }

    public void runTest() {
        System.out.println("🔍 Kiểm thử trường hợp sai mật khẩu...");
        openLoginPage();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
        emailInput.clear();
        emailInput.sendKeys(validEmail);

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        nextButton.click();

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
        passwordInput.sendKeys(invalidPassword);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
        submitButton.click();

        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
            System.out.println("⚠️ Thông báo lỗi xuất hiện: " + errorMessage.getText());
        } catch (Exception e) {
            System.err.println("⚠️ Không tìm thấy thông báo lỗi cho mật khẩu sai");
        }

        closeBrowser();
    }
}
