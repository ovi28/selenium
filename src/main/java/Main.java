import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Main
{
    public static void main(String[] args) throws InterruptedException 
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ovi28\\Desktop\\Test\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath(".//*[@id='u_0_1']")).sendKeys("Test Selenium");
        driver.findElement(By.xpath(".//*[@id='u_0_j']/span[2]/label")).click();
        
        //select class for drop downs
        Select sel1 = new Select(driver.findElement(By.xpath(" .//*[@id='month']")));
        sel1.selectByIndex(3);//will pick the forth
       // sel1.selectByVisibleText("");
       
        Thread.sleep(5000);
        driver.findElement(By.xpath(".//*[@id='u_0_l']")).click();
        
        //click a link
        driver.findElement(By.xpath(" .//*[@id='reg_pages_msg']/a")).click();
        driver.navigate().back();
        
        
        
        
        
       
       
        
    }
}
