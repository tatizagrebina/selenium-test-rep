package TestLesson11_19;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

class BasketPage extends Page {

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage openPage() {
        driver.findElement(By.id("cart")).click();
        return this;
    }

    public BasketPage removeAllProducts() {
        int countTableLines = driver.findElements(By.cssSelector(".dataTable.rounded-corners tr")).size();
        int countAfterRemove;

        while (true) {
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
        return this;
    }
}
