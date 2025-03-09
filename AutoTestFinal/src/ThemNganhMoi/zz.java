package ThemNganhMoi;

import org.testng.annotations.Test;
import LoginAuthen.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.*;
import org.openqa.selenium.TimeoutException;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;

public class zz {
	WebDriver driver;
	Login login = new Login();

	@BeforeTest
	public void TruocTest() throws InterruptedException, IOException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
		driver = new ChromeDriver();

		login.TruocTest(driver);
	}
  @Test
  public void LuuNganhHoc() throws InterruptedException, AWTException {
		Thread.sleep(6000);
		driver.findElement(By.cssSelector("a[href='/Phancong02/Term']")).click();
		Thread.sleep(3000);
		WebElement Nganh = driver.findElement(By.cssSelector("a[href='/Phancong02/Major']"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(Nganh).click().perform();
        Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dt-button.createNew.btn.btn-primary")).click();
		Thread.sleep(2000);
		
		
		
		 WebElement maNganh = driver.findElement(By.cssSelector("input#id"));
		    actions.moveToElement(maNganh).click().sendKeys("CNTT118").perform();

	
		    WebElement tenNganh = driver.findElement(By.cssSelector("input#name"));
		    actions.moveToElement(tenNganh).click().sendKeys("Công nghệ thông tin").perform();


		    WebElement vietTat = driver.findElement(By.cssSelector("input#abbreviation"));
		    actions.moveToElement(vietTat).click().sendKeys("CNTT118").perform();


		    WebElement dropdownCTDT = driver.findElement(By.cssSelector("span.select2-selection--single"));
		    actions.moveToElement(dropdownCTDT).click().perform();
		    Thread.sleep(1000);


		    WebElement optionDacBiet = driver.findElement(By.cssSelector("li.select2-results__option:nth-child(2)"));
		    actions.moveToElement(optionDacBiet).click().perform();
		    Thread.sleep(1000);

		    Robot robot = new Robot();


		    robot.mouseMove(947, 644);
		    Thread.sleep(500); 

		    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		    try {
		    WebDriverWait wait = new WebDriverWait(driver, 1);
	        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-message")));

	        if (successMessage.getText().trim().equals("Lưu thành công!")) {
	            System.out.println("PASS: Đã lưu Ngành học thành công");
	        } else {
	            System.out.println("FAIL: Nội dung thông báo không đúng. Thực tế: " + successMessage.getText());
	        }
		    } catch (TimeoutException e) {
		        System.out.println("Lưu Ngành học không thành công, hãy điền thông tin chính xác");
		    }
	  
  }
  @AfterTest
	public void SauTest() {
		driver.close();
	}
}
