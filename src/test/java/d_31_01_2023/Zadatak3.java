package d_31_01_2023;
//Napisati program koji:
//        Ucitava stranicu https://itbootcamp.rs/
//        Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//        Cita sve linkove slika iz slajdera
//        Proverava url svake slike, i za sve slike koje imaju status veci i jednak od 200 a
//        manji od 300, skida i smesta u folder  u okviru projekta
//        Azurirajte gitignore da ignorise itbootcamp_slider folder

import helper.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws InterruptedException, IOException {


        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://itbootcamp.rs/");
        driver.manage().window().maximize();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.scrollToElement(driver.findElement(By.className("slider_bkgd"))).perform();

        List<WebElement> lista = driver.findElements(By.xpath(
                "//*[contains(@class,'slider_bkgd')]//img"));

        Thread.sleep(1000);
            for (int i = 0; i < lista.size(); i++) {
                if (new Helper().getHTTPResponseStatusCode(lista.get(i).getAttribute("src")) >= 200 &&
                        new Helper().getHTTPResponseStatusCode(lista.get(i).getAttribute("src")) < 300
                ) {
                    new Helper().downloadUsingStream
                            (lista.get(i).getAttribute("src"), "itbootcamp_slider/slika"+ i +".jpg");
                } else {
                    System.out.println("Element HTTP Status Code nije odgovarajuc");

        Thread.sleep(3000);
     driver.quit();
    }
}







    }
}