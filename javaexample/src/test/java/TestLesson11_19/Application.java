package TestLesson11_19;

import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Application extends TestBase {

    @Test
    public void Application() {
        MainPage mainPage = new MainPage(driver);
        ProductPage productPage = new ProductPage(driver);
        BasketPage basketPage = new BasketPage(driver);

        WebElement product;

        for (int i = 1; i <=3; i++) {
            mainPage.openPage();
            product = mainPage.getPopularProduct();

            productPage
                    .openProductCart(product)
                    .addProductToBasket();
        }

        basketPage
                .openPage()
                .removeAllProducts();

    }

}
