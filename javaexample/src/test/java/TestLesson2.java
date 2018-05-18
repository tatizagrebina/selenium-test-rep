import org.junit.Test;
import org.openqa.selenium.By;

public class TestLesson2 extends TestBase {

    @Test
    public void TestLesson2() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");

    }

}