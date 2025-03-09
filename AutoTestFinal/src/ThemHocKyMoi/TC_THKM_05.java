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

public class TC_THKM_05 {
	WebDriver driver;
	Login login = new Login();

	@BeforeTest
	public void TruocTest() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
		driver = new ChromeDriver();

		login.TruocTest(driver);
	}

	@Test
	public void KiemTraLuuThanhCong() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[href='/Phancong02/Term']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".dt-button.createNew.btn.btn-primary")).click();
		Thread.sleep(1000);
		WebElement HocKy = driver.findElement(By.id("id"));
		WebElement TuanBatDau = driver.findElement(By.cssSelector("input#start_week[value='1']"));
		WebElement TietToiDa = driver.findElement(By.cssSelector("input#max_lesson[value='3']"));
		WebElement LopToiDa = driver.findElement(By.cssSelector("input#max_class[value='1']"));

		HocKy.sendKeys("221");
		TuanBatDau.clear();
		TuanBatDau.sendKeys("10");
		TietToiDa.clear();
		TietToiDa.sendKeys("10");
		LopToiDa.clear();
		LopToiDa.sendKeys("10");

		driver.findElement(By.cssSelector("button[type='submit']")).click();
		if (driver.findElements(By.className("error")).isEmpty()) {

			WebElement ThongBaoThanhCong = driver.findElement(By.xpath("//div[contains(text(),'Lưu thành công!')]"));

			if (ThongBaoThanhCong.isDisplayed()) {
				System.out.println("PASS: Hiển thị thông báo Lưu học kỳ thành công");
			} else {
				System.out.println("FAIL: Không hiển thị thông báo Lưu thành công");
			}
		} else {
			System.out.println("FAIL: Có lỗi khi lưu học kỳ");
		}
	}

	@AfterTest
	public void SauTest() {
		driver.close();
	}
}
