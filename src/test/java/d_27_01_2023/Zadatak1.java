package d_27_01_2023;

//Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//        Klik na svako dugme od PRIMARY do DARK
//        Sacekati da se toasts u desnom gornjem uglu pojavi
//        Pauza izmedju klikova 1s
//        Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {


    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");
        Thread.sleep(1000);

        List<WebElement> dugmici = driver.findElements
                (By.xpath("//button[contains(@id, 'basic-')]"));

        List<WebElement> baneri = driver.findElements(By.xpath("//div[contains(@id, 'basic-')]"));

        for (int i = 0; i < dugmici.size(); i++) {
            dugmici.get(i).click();
            Thread.sleep(1000);

            if (!baneri.get(i).isDisplayed()) {
                i = 8;

            }


        }
        driver.quit();

    }
}
