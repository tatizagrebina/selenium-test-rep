package TestLesson11_19;

import org.junit.Assert;
import org.openqa.selenium.*;

class ProductPage extends Page {

    private int productInBasket = 0;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private boolean existsElement(By by) {
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public ProductPage openProductCart(WebElement product) {
        product.click();
        if (existsElement(By.name("options[Size]"))) {
            WebElement size = driver.findElement(By.name("options[Size]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event ('change'))", size);
        }
        return this;
    }

    public ProductPage addProductToBasket() {
        productInBasket += 1;

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(driver1 -> driver1
                .findElement(By.cssSelector("span.quantity[style]"))
                .getText()
                .equals(String.valueOf(String.valueOf(productInBasket)))
        );


        Assert.assertTrue(driver.findElement(By.cssSelector("span.quantity")).getText().equals(String.valueOf(productInBasket)));
        return this;
    }
}
