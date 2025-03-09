package LoginAuthen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

    public void TruocTest(WebDriver driver) throws InterruptedException {
        driver.get("https://cntttest.vanlanguni.edu.vn:18081/Phancong02/");
        driver.manage().window().maximize();
        
        driver.findElement(By.id("details-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("proceed-link")).click();
        Thread.sleep(2000);
        
        WebElement login = driver.findElement(By.id("OpenIdConnect"));
        login.click();
        Thread.sleep(6000);
        
        WebElement username = driver.findElement(By.xpath("//*[@id=\"i0116\"]"));
        username.sendKeys("huy.2174802010294@vanlanguni.vn");
        Thread.sleep(5000);
        
        WebElement password = driver.findElement(By.xpath("//*[@id=\"i0118\"]"));
        password.sendKeys("VLU03032003");

        String targetUrl = "https://cntttest.vanlanguni.edu.vn:18081/Phancong02/";
        while (!driver.getCurrentUrl().equals(targetUrl)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
