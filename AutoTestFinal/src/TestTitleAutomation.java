import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@SuppressWarnings("unused")
public class TestTitleAutomation {
    public static void main(String[] args) throws InterruptedException {
        Object io;
		// Tự động tải driver nếu dùng WebDriverManager (không cần tải file chromedriver.exe thủ công)

        // Khởi tạo trình duyệt Chrome
        ChromeDriver driver = new ChromeDriver();

        try {
            // Mở trang web cần kiểm thử
            driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");

            // Đợi tối đa 10 giây để kiểm tra tiêu đề trang
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean isTitleCorrect = wait.until(ExpectedConditions.titleContains("Quản lý phân công giảng dạy"));

            // Kiểm tra tiêu đề
            if (isTitleCorrect) {
                System.out.println("✅ Tiêu đề chính xác!");
            } else {
                System.out.println("❌ Tiêu đề không đúng!");
            }

            // Chờ 3 giây trước khi đóng trình duyệt
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng trình duyệt
            driver.wait();
        }
    }
}
