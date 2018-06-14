import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestLesson8_14 extends TestBase {

    public ExpectedCondition<Boolean> thereIsWindowsOtherThan(String mainWindow) throws Throwable {

        List<Set<String>> handles = new ArrayList<Set<String>>();
        handles.add(driver.getWindowHandles());
        handles.remove(mainWindow);
        if (handles.size() > 0) {
            return driver -> true;
        }
        throw new Exception("Новой вкладки нет");
    }


    @Test
    public void TestLesson8_14() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a.button")).click();

        List<WebElement> extLinks = driver.findElements(By.cssSelector("table td > a"));
        for (int i = 0; i < extLinks.size(); i++) {
            if (extLinks.get(i).getText().equals("?")) {
                extLinks.remove(i);
            }
        }

        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows;
        for (int i = 0; i < extLinks.size(); i++) {
            extLinks.get(i).click();
            try {
                wait.until(thereIsWindowsOtherThan(mainWindow));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            allWindows = driver.getWindowHandles();
            allWindows.remove(mainWindow);
            String newWindow = allWindows.size() == 1 ? allWindows.iterator().next() : "";
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }

    }
}
