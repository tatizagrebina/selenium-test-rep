import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestLesson7_13 extends TestBase {

    private boolean existsElement(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Test
    public void TestLesson7_13() {

        driver.navigate().to("http://localhost/litecart/en/");

        //Добавление первого товара в корзину
        driver.findElement(By.cssSelector("#box-most-popular a.link")).click();
        if (existsElement(By.name("options[Size]"))) {
            WebElement size = driver.findElement(By.name("options[Size]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", size);
        }

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(driver1 -> driver1
                .findElement(By.cssSelector("span.quantity[style]"))
                .getText()
                .equals(String.valueOf("1"))
        );

        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity")).getText().equals("1"));

        driver.findElement(By.id("logotype-wrapper")).click();

        //Добавление второго товара в корзину
        driver.findElement(By.cssSelector("#box-most-popular a.link")).click();
        if (existsElement(By.name("options[Size]"))) {
            WebElement size = driver.findElement(By.name("options[Size]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", size);
        }

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(driver1 -> driver1
                .findElement(By.cssSelector("span.quantity[style]"))
                .getText()
                .equals(String.valueOf("2"))
        );

        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity")).getText().equals("2"));

        driver.findElement(By.id("logotype-wrapper")).click();

        //Добавление третьего товара в корзину
        driver.findElement(By.cssSelector("#box-most-popular a.link")).click();
        if (existsElement(By.name("options[Size]"))) {
            WebElement size = driver.findElement(By.name("options[Size]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", size);
        }

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(driver1 -> driver1
                .findElement(By.cssSelector("span.quantity[style]"))
                .getText()
                .equals(String.valueOf("3"))
        );

        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity")).getText().equals("3"));

        //Удаление товаров из корзины
        driver.findElement(By.id("cart")).click();
        int countTableLines = driver.findElements(By.cssSelector(".dataTable.rounded-corners tr")).size();
        int countAfterRemove;

        while (1 == 1) {
            List<WebElement> shortcuts = driver.findElements(By.cssSelector("ul.shortcuts li"));
            List<WebElement> buttonsRemove = driver.findElements(By.name("remove_cart_item"));
            shortcuts.get(0).click();
            buttonsRemove.get(0).click();
            countAfterRemove = countTableLines - 1;
            wait.until(numberOfElementsToBe(By.cssSelector(".dataTable.rounded-corners tr"), countAfterRemove));
            countTableLines = countAfterRemove;
            buttonsRemove = driver.findElements(By.name("remove_cart_item"));
            if (buttonsRemove.size() == 1) {
                buttonsRemove.get(0).click();
                break;
            }
        }

        wait.until(presenceOfElementLocated(By.tagName("em")));
        Assert.assertTrue(driver.findElement(By.tagName("em")).getText().equals("There are no items in your cart."));
    }
}
