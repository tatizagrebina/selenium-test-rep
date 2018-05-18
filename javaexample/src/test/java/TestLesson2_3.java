import org.openqa.selenium.By;

public class TestLesson2_3 extends Base {


    public TestLesson2_3() {
        start();
    }

    public static void main(String[] args) throws Exception

    {
        new TestLesson2_3().TestLesson();
    }

    public void TestLesson() {

        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog");

    }


}