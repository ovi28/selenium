
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitSelenium {

    private static final int WAIT_MAX = 4;
    static WebDriver driver;

    @BeforeClass
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ovi28\\Desktop\\Test\\chromedriver.exe");

        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        com.jayway.restassured.RestAssured.given().get("http://localhost:3000/reset");
    }

    @Test
    public void test1() throws Exception {
        (new WebDriverWait(driver, WAIT_MAX)).until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            WebElement e = d.findElement(By.tagName("tbody"));
            List<WebElement> rows = e.findElements(By.tagName("tr"));
            Assert.assertThat(rows.size(), is(5));
            return true;
        });
    }

    @Test
    //Verify the filter functionality 
    public void test2() throws Exception {
        //No need to WAIT, since we are running test in a fixed order, we know the DOM is ready (because of the wait in test1)
        WebElement element = driver.findElement(By.id("filter"));
        //Complete this
        element.sendKeys("2002");//we write in the field
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(2));
    }

    @Test
    public void test3() throws Exception {
        WebElement element = driver.findElement(By.id("filter"));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        Thread.sleep(10000);
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(5));

    }

    @Test
    public void test4() throws Exception {
        driver.findElement(By.xpath(".//*[@id='h_year']")).click();
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));

        assertTrue(rows.get(0).getText().contains("938"));
        assertTrue(rows.get(rows.size() - 1).getText().contains("940"));
    }

    @Test
    public void test5() throws Exception {
        driver.findElement(By.xpath(".//*[@id='tbodycars']/tr[1]/td[8]/a[1]")).click();
        //Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='description']")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.xpath(".//*[@id='description']")).sendKeys(Keys.DELETE);
        driver.findElement(By.xpath(".//*[@id='description']")).sendKeys("Cool Car");
        //Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='save']")).click();
        //Thread.sleep(5000);
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getText().contains("938")) {
                assertTrue(rows.get(i).getText().contains("Cool Car"));

            }
        }

    }

    @Test
    public void test6() throws Exception {
        driver.findElement(By.xpath(".//*[@id='new']")).click();
        driver.findElement(By.xpath(".//*[@id='save']")).click();
        System.out.println("The text is " + driver.findElement(By.xpath(".//*[@id='submiterr']")).getText());
        String text = driver.findElement(By.xpath(".//*[@id='submiterr']")).getText();
        Assert.assertEquals(text, "All fields are required");
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(5));
    }

    @Test
    public void test7() throws Exception {
        driver.findElement(By.xpath(".//*[@id='new']")).click();
        driver.findElement(By.xpath(".//*[@id='year']")).sendKeys("2008");
        driver.findElement(By.xpath(".//*[@id='registered']")).sendKeys("2002-5-5");
        driver.findElement(By.xpath(".//*[@id='make']")).sendKeys("Kia");
        driver.findElement(By.xpath(".//*[@id='model']")).sendKeys("Rio");
        driver.findElement(By.xpath(".//*[@id='description']")).sendKeys("As new");
        driver.findElement(By.xpath(".//*[@id='price']")).sendKeys("31000");
        driver.findElement(By.xpath(".//*[@id='save']")).click();
        WebElement e = driver.findElement(By.tagName("tbody"));
        List<WebElement> rows = e.findElements(By.tagName("tr"));
        Assert.assertThat(rows.size(), is(6));
        assertTrue(rows.get(rows.size() - 1).getText().contains("Rio"));
        assertTrue(rows.get(rows.size() - 1).getText().contains("Kia"));
    }

}
    
//}
