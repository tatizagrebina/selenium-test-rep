import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeMoreThan;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestLesson6_12 extends TestBase {

    public void clickOnElementBy(List<WebElement> listElements, String equalString) {
        for (int i = 0; i < listElements.size(); i++) {
            String text = listElements.get(i).getText();
            if (text.equals(equalString)) {
                listElements.get(i).click();
                break;
            }
        }
    }

    public WebElement waitWebElement(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        WebElement element = wait.until(presenceOfElementLocated(by));
        return element;
    }

    public List<WebElement> waitWebElements(By by, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        List<WebElement> elements = wait.until(numberOfElementsToBeMoreThan(by, 1));
        return elements;
    }

    public String getPathProductImage(String productName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(productName).getFile());
        return file.getAbsolutePath();
    }

    public void setDateEx(WebElement datePicker, int addDays, int addMonths, int addYears) {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, addDays);
        cal.add(Calendar.MONTH, addMonths);
        cal.add(Calendar.YEAR, addYears);
        String nowDate = dateFormat.format(cal.getTime());
        datePicker.sendKeys(nowDate);
    }

    public String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return String.valueOf(timestamp.getTime());
    }

    @Test
    public void TestLesson6_12() {

        String testProductName = "Bat-duck" + getTimeStamp();

        driver.navigate().to("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menuAdmin = driver.findElements(By.cssSelector("#box-apps-menu > li"));
        clickOnElementBy(menuAdmin, "Catalog");

        List<WebElement> buttons = waitWebElements(By.cssSelector("#content a.button"), 10);
        clickOnElementBy(buttons, "Add New Product");

        //Заполнение вкладки "General"
        List<WebElement> status = waitWebElements(By.cssSelector("#tab-general label"), 10);
        clickOnElementBy(status, "Enabled");

        driver.findElement(By.cssSelector("#tab-general span.input-wrapper > input")).sendKeys(testProductName);
        driver.findElement(By.name("code")).sendKeys("rd123");
        driver.findElement(By.cssSelector("div.input-wrapper [data-name=Root]")).click();
        driver.findElement(By.cssSelector("div.input-wrapper [data-name='Rubber Ducks']")).click();
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.name("quantity")).sendKeys("15");
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(getPathProductImage("batduck.jpg"));
        setDateEx(
                driver.findElement(By.cssSelector("[name=date_valid_from]")),
                0,
                0,
                0);
        setDateEx(
                driver.findElement(By.cssSelector("[name=date_valid_to]")),
                0,
                1,
                1);

        //Заполнение вкладки "Information"
        List<WebElement> tabs = driver.findElements(By.cssSelector(".tabs ul >li a"));
        clickOnElementBy(tabs, "Information");

        WebElement manufact = waitWebElement(By.cssSelector("[name=manufacturer_id]"), 10);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", manufact);

        driver.findElement(By.name("keywords")).sendKeys("batduck, duck, batman");
        driver.findElement(By.name("short_description[en]")).sendKeys("Bat-duck");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Резиновая уточка – это резиновая игрушка для ванной в виде маленькой уточки с плоским дном, как правило, желтого цвета. Ее история началась с XIX века и прошла длительную эволюцию. Лишь в 1949 году в Калифорнии русско-американский скульптор Питер Ганин запатентовал маленькую желтую резиновую уточку, предназначенную для купания малышей в ванной.");
        driver.findElement(By.name("head_title[en]")).sendKeys("Bat-duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("Bat-duck");

        //Заполнение вкладки "Prices"
        clickOnElementBy(tabs, "Prices");

        WebElement currency = waitWebElement(By.cssSelector("[name=purchase_price_currency_code]"), 10);
        js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", currency);

        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.name("purchase_price")).sendKeys("20");
        driver.findElement((By.name("prices[USD]"))).sendKeys("20");

        //Сохранение товара
        driver.findElement(By.name("save")).click();

        //Проверка добавления товара в каталог
        List<WebElement> products = waitWebElements(By.cssSelector("tr.row > td > a"), 10);
        String prodName = "";
        for (int i = 0; i < products.size(); i++) {
            prodName = products.get(i).getText();
            if (prodName.equals(testProductName)) {
                Assert.assertTrue(true);
                break;
            }
        }
    }
}
