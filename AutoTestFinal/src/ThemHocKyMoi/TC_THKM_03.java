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

public class TC_THKM_03 {
	WebDriver driver;
	Login login = new Login();

	@BeforeTest
	public void TruocTest() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
		driver = new ChromeDriver();

		login.TruocTest(driver);
	}

	@Test
	public void GiaTriMacDinh() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[href='/Phancong02/Term']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".dt-button.createNew.btn.btn-primary")).click();
		Thread.sleep(1000);
		WebElement NamBatDau = driver.findElement(By.cssSelector(".select2-selection__rendered[title='2025']"));
		WebElement NamKetThuc = driver.findElement(By.cssSelector(".select2-selection__rendered[title='2026']"));

		String namBatDauText = NamBatDau.getText();
		String namKetThucText = NamKetThuc.getText();

		if (namBatDauText.equals("2025") && namKetThucText.equals("2026")) {
			System.out.println("PASS: Hiện đúng giá trị mặc định");
		} else {
			System.out.println("FAIL: Hiện sai giá trị mặc định");
		}
	}

	@AfterTest
	public void SauTest() {
		driver.close();
	}
}
