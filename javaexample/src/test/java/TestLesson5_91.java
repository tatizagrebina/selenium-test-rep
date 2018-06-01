import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestLesson5_91 extends TestBase {

    @Test
    public void TestLesson5_91() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(By.className("row"));
        List<String> nameCountries = new ArrayList<>();
        List<String> linksCountries = new ArrayList<>();

        for (int i = 0; i < countries.size(); i++) {

            String name = countries.get(i).findElement(By.tagName("a")).getText();
            nameCountries.add(name);

            String zones = countries.get(i).findElement(By.cssSelector(".row td:nth-of-type(6)")).getAttribute("textContent");

            if (!zones.equals("0")) {

                String linkCountry = countries.get(i).findElement(By.cssSelector(".row td:nth-of-type(7) a")).getAttribute("href");
                linksCountries.add(linkCountry);
            }
        }

        List<String> sortCountries = new ArrayList<>(nameCountries);
        Collections.sort(sortCountries);
        assertTrue(Arrays.equals(sortCountries.toArray(), nameCountries.toArray()));

        for (int i = 0; i < linksCountries.size(); i++) {

            driver.navigate().to(linksCountries.get(i));
            List<WebElement> zonesCountries = driver.findElement(By.className("dataTable")).findElements(By.cssSelector("tr > td:nth-of-type(3)"));
            List<String> nameZones = new ArrayList<>();

            for (int j = 0; j < zonesCountries.size(); j++) {

                String nameZone = zonesCountries.get(j).getText();

                if (!nameZone.equals("")) {

                    nameZones.add(nameZone);
                }
            }

            List<String> sortZones = new ArrayList<>(nameZones);
            Collections.sort(nameZones);
            assertTrue(Arrays.equals(sortZones.toArray(), nameZones.toArray()));
        }
    }
}