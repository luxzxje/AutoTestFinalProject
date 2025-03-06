package UserManagermentTest;

import Authentication.BaseTest;
import Authentication.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DeleteUserTest extends BaseTest {
    private Login login;

    public DeleteUserTest() {
        super();
        this.login = new Login();
    }

    public void searchUserByStaffId(String staffId) {
        try {
            System.out.println("🔍 Đang tìm kiếm người dùng với mã giảng viên: " + staffId);
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/User");

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.form-control[placeholder='Nhập tìm kiếm...']")));
            searchInput.clear();
            searchInput.sendKeys(staffId, Keys.ENTER);

            // Đợi kết quả hiển thị
            Thread.sleep(2000);
            WebElement userRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table[@id='tblUser']//tr[td[2][text()='" + staffId + "']]")));

            System.out.println("✅ Người dùng " + staffId + " đã được tìm thấy.");

        } catch (TimeoutException e) {
            System.err.println("❌ Không tìm thấy người dùng: " + staffId);
        } catch (Exception e) {
            System.err.println("❌ Lỗi khi tìm kiếm người dùng: " + e.getMessage());
        }
    }

    	public void deleteUserByStaffId(String staffId) {
    	    try {
    	        System.out.println("🗑 Đang xóa người dùng: " + staffId);

    	        // Tìm kiếm người dùng
    	        searchUserByStaffId(staffId);

    	        // Kiểm tra nếu tồn tại trước khi xóa
    	        WebElement userRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	            By.xpath("//table[@id='tblUser']//tr[td[2][text()='" + staffId + "']]")));

    	        WebElement deleteButton = userRow.findElement(By.xpath(".//a[contains(@class, 'deleteRow')]"));
    	        deleteButton.click();

    	        // Xác nhận xóa trong hộp thoại SweetAlert2
    	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal2-confirm")));
    	        WebElement confirmDeleteButton = driver.findElement(By.className("swal2-confirm"));
    	        confirmDeleteButton.click();

    	        System.out.println("✅ Đã nhấn xác nhận xóa người dùng: " + staffId);

    	        // Kiểm tra lại để xác nhận đã xóa
    	        Thread.sleep(60);
    	        searchUserByStaffId(staffId);
    	        Thread.sleep(60);

    	        boolean isDeleted = driver.findElements(By.xpath("//table[@id='tblUser']//tr[td[2][text()='" + staffId + "']]")).isEmpty();
    	        if (isDeleted) {
    	            System.out.println("✅ Xóa thành công: Không tìm thấy người dùng sau khi xóa.");
    	        } else {
    	            System.err.println("❌ Xóa thất bại: Người dùng vẫn tồn tại.");
    	        }

    	    } catch (TimeoutException e) {
    	        System.err.println("❌ Không thể xóa vì không tìm thấy người dùng.");
    	    } catch (Exception e) {
    	        System.err.println("❌ Lỗi khi kiểm thử xóa người dùng: " + e.getMessage());
    	    } finally {
    	        closeBrowser();
    	    }
    	}


    public void runTests() {
        login.performLogin(true);
        deleteUserByStaffId("GV123451");
    }

    public static void main(String[] args) {
        DeleteUserTest deleteUserTest = new DeleteUserTest();
        deleteUserTest.runTests();
    }
}
