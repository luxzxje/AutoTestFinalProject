package UserManagermentTest;

import Authentication.BaseTest;
import Authentication.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditUserTest extends BaseTest {
    private Login login;
    private JavascriptExecutor js;

    public EditUserTest() {
        super();
        this.login = new Login();
        this.js = (JavascriptExecutor) driver;
    }

    public void editUserByStaffId(String staffId) {
        try {
            System.out.println("🔍 Tìm kiếm người dùng bằng mã giảng viên: " + staffId);
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.form-control[placeholder='Nhập tìm kiếm...']")));
            searchInput.clear();
            searchInput.sendKeys(staffId, Keys.ENTER);
            Thread.sleep(2000);

            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.editRow.text-success")));
            js.executeScript("arguments[0].click();", editButton);

            WebElement userForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-form")));
            System.out.println("✅ Form chỉnh sửa người dùng đã hiển thị!");

            // 📝 Chỉnh sửa thông tin
            enterText(By.id("full_name"), "Nguyễn Văn D");
            enterText(By.id("email"), "updategiaovien@vanlanguni.vn");

            selectDropdownValueJS("type", "CH");
            selectDropdownValueJS("role_id", "b8046948-0910-41f4-a79d-9474126fce12");

            clickSaveButton();

            wait.until(ExpectedConditions.invisibilityOf(userForm));
            System.out.println("✅ Người dùng đã được cập nhật thành công!");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi sửa người dùng: " + e.getMessage());
        }
    }

    private void enterText(By locator, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            js.executeScript("arguments[0].removeAttribute('readonly'); arguments[0].removeAttribute('disabled');", element);

            element.click();
            element.clear();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
            js.executeScript("arguments[0].value=arguments[1];", element, value);
            element.sendKeys(Keys.TAB);
            System.out.println("✅ Đã nhập: " + value);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi nhập liệu vào " + locator + ": " + e.getMessage());
        }
    }

    private void selectDropdownValueJS(String dropdownId, String value) {
        try {
            js.executeScript("$('#" + dropdownId + "').val('" + value + "').trigger('change');");
            System.out.println("✅ Đã chọn giá trị '" + value + "' trong dropdown #" + dropdownId);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi chọn dropdown #" + dropdownId + ": " + e.getMessage());
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
        login.performLogin(true);
        editUserByStaffId("GV123451");
    }

    public static void main(String[] args) {
        EditUserTest test = new EditUserTest();
        test.runTests();
    }
}
