import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestLesson5_10 extends TestBase {

    @Test
    public void TestLesson5_10() {

        driver.navigate().to("http://localhost/litecart/en/");

        getCrossPrice("Цена не зачеркнута", driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper s")).getText());
        getBoldPrice("Цена не выделена жирным", driver.findElement(By.cssSelector("#box-campaigns a.link  .price-wrapper strong")).getText());
        isGreyColor("Цвет не серый", driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper s")).getCssValue("color"));
        isRedColor("Цвет не красный", driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper strong")).getCssValue("color"));

        compareDefaultAndSalePrice(
                "Шрифт основной цены больше шрифта акционной",
                driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper s")).getCssValue("font-size"),
                driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper strong")).getCssValue("font-size")
                );

        String mainName = driver.findElement(By.cssSelector("#box-campaigns a.link > div.name")).getText();
        String mainPrice = driver.findElement(By.cssSelector("#box-campaigns a.link .price-wrapper s")).getText();
        String mainSale = driver.findElement(By.cssSelector("#box-campaigns a.link  .price-wrapper strong")).getText();

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();

        String prodName = driver.findElement(By.cssSelector("#box-product h1")).getText();
        String prodPrice = driver.findElement(By.cssSelector("#box-product .price-wrapper s")).getText();
        String prodSale = driver.findElement(By.cssSelector("#box-product .price-wrapper strong")).getText();

        Assert.assertTrue("Наимменование товара не совпадает", mainName.equals(prodName));
        Assert.assertTrue("Основная цена товара не совпадает", mainPrice.equals(prodPrice));
        Assert.assertTrue("Акционная цена товара не совпадает", mainSale.equals(prodSale));

        getCrossPrice("Цена не зачеркнута", driver.findElement(By.cssSelector("#box-product .price-wrapper s")).getText());
        getBoldPrice("Цена не выделена жирным", driver.findElement(By.cssSelector("#box-product .price-wrapper strong")).getText());
        isGreyColor("Цвет не серый", driver.findElement(By.cssSelector("#box-product .price-wrapper s")).getCssValue("color"));
        isRedColor("Цвет не красный", driver.findElement(By.cssSelector("#box-product .price-wrapper strong")).getCssValue("color"));

        compareDefaultAndSalePrice(
                "Шрифт основной цены больше шрифта акционной",
                driver.findElement(By.cssSelector("#box-product .price-wrapper s")).getCssValue("font-size"),
                driver.findElement(By.cssSelector("#box-product .price-wrapper strong")).getCssValue("font-size")
        );

    }

    public void getCrossPrice(String message, String str ) {
        try {
            Assert.assertTrue(!str.isEmpty());

        } catch (Exception e) {
            Assert.fail(message);

        }
    }

    public void getBoldPrice(String message, String str) {
        try {
            Assert.assertTrue(!str.isEmpty());
        } catch (Exception e) {
            Assert.fail(message);
        }
    }

    public void isGreyColor(String message, String str) {
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(str);
        List<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(1));
        }

        //Проверка на серый цвет
        if (matches.get(0).equals(matches.get(1)) && matches.get(1).equals(matches.get(2))) {
            Assert.assertTrue(true);
        } else {
            Assert.fail(message);
        }
    }

    public void isRedColor(String message, String str) {
        Pattern p = Pattern.compile("(\\d+,.0,.0)");
        Matcher m = p.matcher(str);

        if (m.find()) {
            Assert.assertTrue(true);
        } else {
            Assert.fail(message);
        }
    }

    public void compareDefaultAndSalePrice(String message, String defaultPrice, String salePrice) {

        defaultPrice = defaultPrice.replaceAll("px","");
        salePrice = salePrice.replaceAll("px","");

        double doubleDefaultPrice = Double.parseDouble(defaultPrice);
        double doubleSalePrice = Double.parseDouble(salePrice);

        if (doubleSalePrice > doubleDefaultPrice) {
            Assert.assertTrue(true);
        } else {
            Assert.fail(message);
        }
    }
}