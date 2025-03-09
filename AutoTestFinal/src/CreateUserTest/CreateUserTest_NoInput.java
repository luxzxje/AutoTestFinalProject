package CreateUserTest;

import Authentication.Login;
import Authentication.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateUserTest_NoInput extends BaseTest {
    private JavascriptExecutor js;
    private Login login;

    public CreateUserTest_NoInput() {
        super();
        this.js = (JavascriptExecutor) driver;
        this.login = new Login();
    }

    public void createUserWithoutInput() {
        try {
            System.out.println("➕ Kiểm thử tạo người dùng không nhập dữ liệu...");
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");

            WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dt-button.createNew")));
            js.executeScript("arguments[0].click();", addUserButton);

            WebElement userForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-form")));
            System.out.println("✅ Form thêm người dùng đã hiển thị!");

            clickSaveButton();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
            System.out.println("❌ Lỗi xuất hiện: " + errorMessage.getText());
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi kiểm thử không nhập dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clickSaveButton() {
        try {
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Lưu')]")));

            js.executeScript("arguments[0].removeAttribute('disabled');", saveButton);
            js.executeScript("arguments[0].classList.remove('disabled');", saveButton);
            js.executeScript("arguments[0].click();", saveButton);

            System.out.println("✅ Đã nhấn nút Lưu!");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi nhấn nút Lưu: " + e.getMessage());
        }
    }

    public void runTests() {
        try {
            System.out.println("🔹 Kiểm thử với tài khoản hợp lệ...");
            login.performLogin(true);
            createUserWithoutInput();

            System.out.println("🔹 Kiểm thử với tài khoản không hợp lệ...");
            login.performLogin(false);

        } finally {
            closeBrowser();
        }
    }

    public static void main(String[] args) {
    	CreateUserTest_NoInput test = new CreateUserTest_NoInput();
        test.runTests();
    }
}
