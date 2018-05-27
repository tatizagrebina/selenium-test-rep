import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

public class TestLesson4_8 extends TestBase {

    @Test
    public void TestLesson4_8() throws InterruptedException {

        driver.navigate().to("http://localhost/litecart/en/");
        List<WebElement> ducks = driver.findElements(By.className("image-wrapper"));

        for (Iterator<WebElement> duck = ducks.iterator(); duck.hasNext(); ) {
            WebElement elem = duck.next();
            int stickerCount = elem.findElements(By.className("sticker")).size();
            Assert.assertTrue(stickerCount == 1);
        }

    }
}