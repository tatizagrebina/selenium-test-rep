import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestLesson5_92 extends TestBase {

    @Test
    public void TestLesson5_92() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countries = driver.findElements(By.className("row"));
        List<String> linkCountries = new ArrayList<>();

        for (int i = 0; i < countries.size(); i++) {

            String link = countries.get(i).findElement(By.tagName("a")).getAttribute("href");
            linkCountries.add(link);
        }

        List<WebElement> zonesCountries;

        for (int i = 0; i < linkCountries.size(); i++) {

            driver.navigate().to(linkCountries.get(i));
            zonesCountries = driver.findElement(By.className("dataTable")).findElements(By.cssSelector("tr > td:nth-of-type(3) > select"));
            List<String> nameZones = new ArrayList<>();

            for (int j = 0; j < zonesCountries.size(); j++) {

                String nameZone = zonesCountries.get(j).findElement(By.cssSelector("[selected=selected]")).getAttribute("textContent");
                nameZones.add(nameZone);
            }

            List<String> sortZones = new ArrayList<>(nameZones);
            Collections.sort(nameZones);
            assertTrue(Arrays.equals(sortZones.toArray(), nameZones.toArray()));
        }
    }
}
