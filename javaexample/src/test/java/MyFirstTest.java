
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @Test
    public void mySecondTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @Test
    public void myThirdTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    @Test
    public void test10() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
        wait.until(titleIs("webdriver - Поиск в Google"));
    }

    }
}