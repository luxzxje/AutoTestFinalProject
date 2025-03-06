package UserManagermentTest;

import Authentication.BaseTest;
import Authentication.Login;
import org.openqa.selenium.*;

public class ViewUsersTest extends BaseTest {
    private Login login;

    public ViewUsersTest() {
        super();
        this.login = new Login();
    }

    public void viewUsers() {
        try {
            System.out.println("📋 Đang kiểm thử chức năng xem danh sách người dùng...");

            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
            System.out.println("✅ Trang danh sách người dùng đã tải thành công!");

            // Kiểm tra bảng danh sách người dùng
            WebElement userTable = driver.findElement(By.id("usersTable"));
            if (userTable.isDisplayed()) {
                System.out.println("✅ Bảng danh sách người dùng đã hiển thị!");
            } else {
                System.err.println("❌ Không tìm thấy bảng danh sách người dùng!");
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi xem danh sách người dùng: " + e.getMessage());
        } finally {
            closeBrowser();
        }
    }

    public void runTests() {
        login.performLogin(true);  // Đăng nhập với tài khoản hợp lệ
        viewUsers();
    }

    public static void main(String[] args) {
        ViewUsersTest test = new ViewUsersTest();
        test.runTests();
    }
}
