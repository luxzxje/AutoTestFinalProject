package UserManagermentTest;

import Authentication.Login;
import Authentication.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateUserTest extends BaseTest {
    private JavascriptExecutor js;
    private Login login;

    public CreateUserTest() {
        super();
        this.js = (JavascriptExecutor) driver;
        this.login = new Login();
    }

    public void createUser() {
        try {
            System.out.println("➕ Kiểm thử tạo người dùng mới...");
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");

            WebElement addUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".dt-button.createNew")));
            js.executeScript("arguments[0].click();", addUserButton);

            WebElement userForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-form")));
            System.out.println("✅ Form thêm người dùng đã hiển thị!");

            enterText(By.id("staff_id"), "GV123451");
            enterText(By.id("full_name"), "Nguyễn Văn B");
            enterText(By.id("email"), "giaovien1@vanlanguni.vn");

            selectDropdownValueJS("type", "CH");
            selectDropdownValueJS("role_id", "b8046948-0910-41f4-a79d-9474126fce12");

            clickSaveButton();

            wait.until(ExpectedConditions.invisibilityOf(userForm));
            System.out.println("✅ Người dùng mới đã được tạo thành công!");
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tạo người dùng mới: " + e.getMessage());
            e.printStackTrace();
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
            String script = "$('#" + dropdownId + "').val('" + value + "').trigger('change');";
            js.executeScript(script);
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
        try {
            System.out.println("🔹 Kiểm thử với tài khoản hợp lệ...");
            login.performLogin(true);
            createUser();

            System.out.println("🔹 Kiểm thử với tài khoản không hợp lệ...");
            login.performLogin(false);

        } finally {
            closeBrowser();
        }
    }

    public static void main(String[] args) {
        CreateUserTest test = new CreateUserTest();
        test.runTests();
    }
}
