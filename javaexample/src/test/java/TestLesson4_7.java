import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestLesson4_7 extends TestBase {

    @Test
    public void TestLesson4_7() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> LIs = driver.findElements(By.cssSelector("#box-apps-menu > li"));

        for (int i = 0; i < LIs.size(); i++) {
            LIs = driver.findElements(By.cssSelector("#box-apps-menu > li"));
            LIs.get(i).click();
            List<WebElement> subLis = driver.findElements(By.cssSelector("#box-apps-menu .selected ul > li"));

            for (int j = 1; j < subLis.size(); j++) {
                subLis = driver.findElements(By.cssSelector("#box-apps-menu .selected ul > li"));
                subLis.get(j).click();

                Assert.assertFalse(driver.findElement(By.cssSelector("#content > h1")).getText().isEmpty());
            }
        }
    }

}
