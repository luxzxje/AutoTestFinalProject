package Authentication;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends BaseTest {
    private JavascriptExecutor js;

    public Login() {
        super();
        this.js = (JavascriptExecutor) driver;
    }

    public void performLogin(boolean useValidCredentials) {
        try {
            System.out.println("🔑 Đang thực hiện đăng nhập...");

            openLoginPage();

            String email = useValidCredentials ? validEmail : invalidEmail;
            String password = useValidCredentials ? validPassword : invalidPassword;

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            emailInput.sendKeys(email);
            driver.findElement(By.id("idSIButton9")).click();

            Thread.sleep(2000);
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.sendKeys(password);
            driver.findElement(By.id("idSIButton9")).click();

            // Kiểm tra có hiển thị lỗi không (trường hợp sai mật khẩu)
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
                System.err.println("❌ Đăng nhập thất bại: " + errorMessage.getText());
                return;
            } catch (TimeoutException e) {
                System.out.println("✅ Không có lỗi mật khẩu, tiếp tục...");
            }

            // Xử lý trang 'Stay Signed In?'
            try {
                WebElement staySignedInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
                staySignedInButton.click();
            } catch (Exception e) {
                System.out.println("ℹ Không có trang 'Stay signed in?'. Tiếp tục...");
            }

            wait.until(ExpectedConditions.urlContains("cntttest.vanlanguni.edu.vn"));
            System.out.println("✅ Đăng nhập thành công!");

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi đăng nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
