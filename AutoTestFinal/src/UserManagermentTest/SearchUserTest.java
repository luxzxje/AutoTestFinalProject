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
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.form-control[placeholder='Nhập tìm kiếm...']")));
            searchInput.clear();
            searchInput.sendKeys(staffId, Keys.ENTER);
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            System.out.println("❌ Không tìm thấy người dùng với mã giảng viên: " + staffId);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tìm kiếm người dùng: " + e.getMessage());
        }
    }

    public void runTests() {
        login.performLogin(true);
        searchUserByStaffId("GV123451");
    }

    public static void main(String[] args) {
        SearchUserTest test = new SearchUserTest();
        test.runTests();
    }
}
