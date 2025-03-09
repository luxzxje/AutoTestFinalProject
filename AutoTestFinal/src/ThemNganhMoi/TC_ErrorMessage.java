package ThemNganhMoi;

import org.testng.annotations.Test;
import LoginAuthen.Login;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.*;
import java.util.List;
import java.awt.*;
import java.awt.event.InputEvent;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TC_ErrorMessage {
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

		checkAndFillMajorForm();
	}

	public void checkAndFillMajorForm() throws InterruptedException, AWTException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("D:\\major.json");
		JSONObject jsonObject = (JSONObject) parser.parse(reader);
		JSONArray majors = (JSONArray) jsonObject.get("majors");

		for (Object obj : majors) {
			JSONObject major = (JSONObject) obj;
			String id = (String) major.get("id");
			String name = (String) major.get("name");
			String abbreviation = (String) major.get("abbreviation");

			clickSaveButton();

			if (checkData("label#id-error", "Bạn chưa nhập mã ngành")) {
				enterData("input#id", id);
			}
			if (checkData("label#name-error", "Bạn chưa nhập tên ngành")) {
				enterData("input#name", name);
			}
			if (checkData("label#abbreviation-error", "Bạn chưa nhập tên viết tắt của ngành")) {
				enterData("input#abbreviation", abbreviation);
			}

			if (checkData("label#program_type-error", "Bạn chưa chọn CTĐT")) {
				selectProgramType();
			}

			checkDuplicateError();
		}
	}

	private void clickSaveButton() throws InterruptedException {
		robot.mouseMove(947, 644);
		Thread.sleep(500);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		Thread.sleep(1000);
	}

	private boolean checkData(String errorSelector, String expectedMessage) {
		List<WebElement> errorElements = driver.findElements(By.cssSelector(errorSelector));

		if (!errorElements.isEmpty()) {
			String actualErrorMessage = errorElements.get(0).getText().trim();
			if (actualErrorMessage.equals(expectedMessage)) {
				System.out.println("PASS: Hiển thị thông báo lỗi thành công: " + actualErrorMessage);
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				return true;
			} else {
				System.out.println(
						"FAIL: Lỗi không khớp. Mong đợi: " + expectedMessage + " | Hiện tại: " + actualErrorMessage);
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				return false;
			}
		} else {
			System.out.println("FAIL: Không hiển thị thông báo lỗi: " + expectedMessage);
			System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			return false;
		}
	}

	private void enterData(String inputSelector, String inputValue) throws InterruptedException {
		WebElement inputField = driver.findElement(By.cssSelector(inputSelector));
		actions.moveToElement(inputField).click().sendKeys(inputValue).perform();
		clickSaveButton();
	}

	private void selectProgramType() throws InterruptedException {
		WebElement dropdownCTDT = driver.findElement(By.cssSelector("span.select2-selection--single"));
		actions.moveToElement(dropdownCTDT).click().perform();
		Thread.sleep(1000);

		WebElement optionDacBiet = driver.findElement(By.cssSelector("li.select2-results__option:nth-child(2)"));
		actions.moveToElement(optionDacBiet).click().perform();
		Thread.sleep(1000);

		clickSaveButton();
	}

	private void checkDuplicateError() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			WebElement errorMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.swal2-html-container")));

			String loiHienTai = errorMessage.getText().trim();
			String loiMongDoi = "Mã ngành này đã tồn tại!";

			if (loiHienTai.equals(loiMongDoi)) {
				System.out.println("PASS: Hiển thị đúng thông báo lỗi - " + loiHienTai);
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			} else {
				System.out.println("FAIL: Thông báo lỗi không đúng. Lỗi hiện tại: " + loiHienTai + " | Lỗi mong đợi: "
						+ loiMongDoi);
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			}
		} catch (TimeoutException e) {
			System.out.println("FAIL: Không hiển thị thông báo lỗi: ");
			System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
		}
	}

	@AfterTest
	public void SauTest() {
		driver.close();
	}
}
