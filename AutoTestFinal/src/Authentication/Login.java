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

            // Kiểm tra nếu đã đăng nhập thì không đăng nhập lại
            if (driver.getCurrentUrl().contains("cntttest.vanlanguni.edu.vn")) {
                System.out.println("✅ Đã đăng nhập, không cần đăng nhập lại.");
                return;
            }

            openLoginPage();

            String email = useValidCredentials ? validEmail : invalidEmail;
            String password = useValidCredentials ? validPassword : invalidPassword;

            // Nhập email và nhấn nút tiếp tục
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            emailInput.clear();
            emailInput.sendKeys(email);
            driver.findElement(By.id("idSIButton9")).click();

            Thread.sleep(1500);

            // Nhập mật khẩu và nhấn đăng nhập
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            passwordInput.clear();
            passwordInput.sendKeys(password);
            driver.findElement(By.id("idSIButton9")).click();

            // Kiểm tra nếu có lỗi đăng nhập (sai mật khẩu)
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordError")));
                System.err.println("❌ Đăng nhập thất bại: " + errorMessage.getText());
                return;
            } catch (TimeoutException e) {
                System.out.println("✅ Không có lỗi mật khẩu, tiếp tục...");
            }

            // Xử lý trang 'Stay Signed In?' nếu xuất hiện
            try {
                WebElement staySignedInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
                staySignedInButton.click();
            } catch (TimeoutException e) {
                System.out.println("ℹ Không có trang 'Stay signed in?'. Tiếp tục...");
            }

            // Đợi đến khi đăng nhập thành công
            wait.until(ExpectedConditions.urlContains("cntttest.vanlanguni.edu.vn"));
            System.out.println("✅ Đăng nhập thành công!");

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi đăng nhập: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
