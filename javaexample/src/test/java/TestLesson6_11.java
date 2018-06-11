import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;


public class TestLesson6_11 extends TestBase {

    public String generateRandomEmail(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@testdata.com";
        return email;
    }

    public String generateRandomPass(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String password = RandomStringUtils.random(length, allowedChars);
        return password;
    }

    public void Logout(WebDriver driver) {
        List<WebElement> menu = driver.findElements(By.cssSelector("#box-account ul.list-vertical >li"));
        String menuItem;

        for (int i = 0; i < menu.size(); i++) {
            menuItem = menu.get(i).findElement(By.cssSelector("a")).getText();
            if (menuItem.equals("Logout")) {
                menu.get(i).findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    @Test
    public void TestLesson6_11() {

        driver.navigate().to("http://localhost/litecart/en/create_account");

        String email = generateRandomEmail(20);
        String pass = generateRandomPass(5);

        driver.findElement(By.name("tax_id")).sendKeys("123");
        driver.findElement(By.name("company")).sendKeys("New company");
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Doe");
        driver.findElement(By.name("address1")).sendKeys("Street");
        driver.findElement(By.name("address2")).sendKeys("str");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("City");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+3371584575");
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("confirmed_password")).sendKeys(pass);

        WebElement country = driver.findElement(By.name("country_code"));
        List<String> countryList = Arrays.asList(country.getText().split("\n"));
        int i;
        for (i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).equals("  United States")) {
                break;
            }
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].selectedIndex=" + i + "; arguments[0].dispatchEvent(new Event ('change'))", country);

        driver.findElement(By.name("create_account")).click();

        Logout(driver);

        driver.findElement(By.cssSelector("#box-account-login > .content [name=login_form]")).findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.cssSelector("#box-account-login > .content [name=login_form]")).findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.cssSelector("#box-account-login > .content [name=login_form]")).findElement(By.name("login")).click();

        Logout(driver);
    }
}
