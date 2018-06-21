package TestLesson11_19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class MainPage extends Page{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openPage() {
        driver.navigate().to("http://localhost/litecart/en/");
        return this;
    }

    public WebElement getPopularProduct() {
        //Добавление первого товара в корзину
//        driver.findElement(By.cssSelector("#box-most-popular a.link")).click();
        WebElement product = driver.findElement(By.cssSelector("#box-most-popular a.link"));

        return product;
    }
}
