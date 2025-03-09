package ThemNganhMoi;

import org.testng.annotations.Test;
import LoginAuthen.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.*;
import java.awt.*;
import java.awt.event.InputEvent;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TC_SaveMajor {
	WebDriver driver;
	Login login = new Login();
	Actions actions;
	Robot robot;

	@BeforeTest
	public void TruocTest() throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver2.exe");
		driver = new ChromeDriver();
		login.TruocTest(driver);
	}

	@Test
	public void LuuNganhHoc() throws InterruptedException, AWTException, IOException, ParseException {
		actions = new Actions(driver);
		robot = new Robot();

		Thread.sleep(6000);
		driver.findElement(By.cssSelector("a[href='/Phancong02/Term']")).click();
		Thread.sleep(3000);
		WebElement Nganh = driver.findElement(By.cssSelector("a[href='/Phancong02/Major']"));
		actions.moveToElement(Nganh).click().perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dt-button.createNew.btn.btn-primary")).click();
		Thread.sleep(2000);

		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("D:\\saveMajor.json");
		JSONObject jsonObject = (JSONObject) parser.parse(reader);
		JSONArray majors = (JSONArray) jsonObject.get("majors");

		for (Object obj : majors) {
			JSONObject major = (JSONObject) obj;
			String id = (String) major.get("id");
			String name = (String) major.get("name");
			String abbreviation = (String) major.get("abbreviation");

			enterData("input#id", id);
			enterData("input#name", name);
			enterData("input#abbreviation", abbreviation);

			selectProgramType();

			clickSaveButton();

			checkSaveSuccess();
		}
	}

	private void enterData(String inputSelector, String inputValue) throws InterruptedException {
		WebElement inputField = driver.findElement(By.cssSelector(inputSelector));
		actions.moveToElement(inputField).click().sendKeys(inputValue).perform();
		Thread.sleep(1000);
	}

	private void selectProgramType() throws InterruptedException {
		WebElement dropdownCTDT = driver.findElement(By.cssSelector("span.select2-selection--single"));
		actions.moveToElement(dropdownCTDT).click().perform();
		Thread.sleep(1000);

		WebElement optionDacBiet = driver.findElement(By.cssSelector("li.select2-results__option:nth-child(2)"));
		actions.moveToElement(optionDacBiet).click().perform();
		Thread.sleep(1000);
	}

	private void clickSaveButton() throws InterruptedException {
		robot.mouseMove(947, 644);
		Thread.sleep(500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(1000);
	}

	private void checkSaveSuccess() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			WebElement successMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.toast-message")));

			if (successMessage.getText().trim().equals("Lưu thành công!")) {
				System.out.println("PASS: Đã lưu Ngành học thành công");
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			} else {
				System.out.println("FAIL: Nội dung thông báo không đúng. Thực tế: " + successMessage.getText());
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			}
		} catch (TimeoutException e) {
			System.out.println("FAIL: Lưu Ngành học không thành công, hãy điền thông tin chính xác");
			System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
		}
	}

	@AfterTest
	public void SauTest() {
		driver.close();
	}
}
