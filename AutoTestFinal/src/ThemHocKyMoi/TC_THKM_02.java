package ThemHocKyMoi;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.*;
import LoginAuthen.Login;

public class TC_THKM_02 {
	WebDriver driver;
	Login login = new Login();

	@BeforeTest
	public void TruocTest() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
		driver = new ChromeDriver();

		login.TruocTest(driver);
	}

	@Test
	public void KiemTraDinhDangDuLieu() throws InterruptedException {
		Thread.sleep(6000);
		driver.findElement(By.cssSelector("a[href='/Phancong02/Term']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dt-button.createNew.btn.btn-primary")).click();
		Thread.sleep(2000);
		WebElement id = driver.findElement(By.id("id"));
		id.sendKeys("1234");
		WebElement week = driver.findElement(By.cssSelector("input#start_week[value='1']"));
		week.clear();
		week.sendKeys(" ");
		WebElement lesson = driver.findElement(By.cssSelector("input#max_lesson[value='3']"));
		lesson.clear();
		lesson.sendKeys(" ");
		WebElement mclass = driver.findElement(By.cssSelector("input#max_class[value='1']"));
		mclass.clear();
		mclass.sendKeys(" ");

		driver.findElement(By.cssSelector("button[type='submit']")).click();

		KiemTraThongBaoLoi("id-error", "Hoc ky");
		KiemTraThongBaoLoi("start_week-error", "Tuan bat dau");
		KiemTraThongBaoLoi("max_lesson-error", "Tiet toi da");
		KiemTraThongBaoLoi("max_class-error", "Lop toi da");
	}

	public void KiemTraThongBaoLoi(String IdLoi, String Truong) {
		WebElement ThongBaoLoi = driver.findElement(By.id(IdLoi));
		if (ThongBaoLoi.isDisplayed()) {
			System.out.println("PASS: Hiển thị thông báo lỗi khi nhập sai " + Truong + " - " + ThongBaoLoi.getText());
		} else {
			System.out.println("FAIL: Không hiển thị thông báo lỗi cho " + Truong);
		}
	}

	@AfterTest
	public void SauTest() {
		driver.quit();
	}
}
