import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class TestLesson10_17 extends TestBase {
    
    @Test
    public void TestLesson10_17() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> prods = driver.findElements(By.cssSelector(".dataTable tr.row td > a"));

        List<String> filtredProd = prods
                .stream()
                .filter(p -> p.getAttribute("href").toString().contains("product_id"))
                .filter(p -> p.getText().length() != 0)
                .map(p -> p.getAttribute("href"))
                .collect(Collectors.toList());

        for (String prod : filtredProd) {
            driver.navigate().to(prod);
            driver.manage().logs().get("browser").forEach(l -> Assert.assertTrue(l.equals("")));
        }

    }
}
