package UserManagermentTest;

import Authentication.BaseTest;
import Authentication.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchUserTest extends BaseTest {
    private Login login;
    private JavascriptExecutor js;

    public SearchUserTest() {
        super();
        this.login = new Login();
        this.js = (JavascriptExecutor) driver;
    }

    public void searchUserByStaffId(String staffId) {
        try {
            System.out.println("🔍 Tìm kiếm người dùng bằng mã giảng viên: " + staffId);
            
            // Chỉ mở lại trang nếu chưa đúng trang tìm kiếm
            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("/User")) {
                driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");
            }

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.form-control[placeholder='Nhập tìm kiếm...']")));
            searchInput.clear();
            searchInput.sendKeys(staffId, Keys.ENTER);
            Thread.sleep(1000);

            // Kiểm tra nếu có nút chỉnh sửa thì xác nhận tìm thấy người dùng
            try {
                WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a.editRow.text-success")));
                js.executeScript("arguments[0].click();", editButton);
                System.out.println("✅ Đã tìm thấy người dùng!");
            } catch (TimeoutException e) {
                System.out.println("❌ Không tìm thấy người dùng với mã giảng viên: " + staffId);
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tìm kiếm người dùng: " + e.getMessage());
        }
    }

    public void runTests() {
        if (!driver.getCurrentUrl().contains("/User")) {
            login.performLogin(true);
        }
        searchUserByStaffId("GV123451");
    }

    public static void main(String[] args) {
        SearchUserTest test = new SearchUserTest();
        test.runTests();
        BaseTest.closeBrowser(); // Đóng trình duyệt sau khi chạy xong
    }
}
