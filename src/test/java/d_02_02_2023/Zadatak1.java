package d_02_02_2023;
//Kreirati BootstrapTableTests klasu koja ima:
//        Base url: https://s.bootsnipp.com
//        Test #1: Edit Row
//        Podaci:
//        First Name: ime polaznika
//        Last Name: prezime polaznika
//        Middle Name: srednje ime polanzika
//        Koraci:
//        Ucitati stranu /iframe/K5yrx
//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        Klik na Edit dugme prvog reda
//        Sacekati da dijalog za Editovanje bude vidljiv
//        Popuniti formu podacima.
//        Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji, za to se koristi metoda clear. Koristan link
//        Klik na Update dugme
//        Sacekati da dijalog za Editovanje postane nevidljiv
//        Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
//        Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
//        Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//Test #2: Delete Row
//        Podaci:
//        First Name: ime polaznika
//        Last Name: prezime polaznika
//        Middle Name: srednje ime polanzika
//        Koraci:
//        Ucitati stranu /iframe/K5yrx
//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        Klik na Delete dugme prvog reda
//        Sacekati da dijalog za brisanje bude vidljiv
//        Klik na Delete dugme iz dijaloga
//        Sacekati da dijalog za Editovanje postane nevidljiv
//        Verifikovati da je broj redova u tabeli za jedan manji
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//
//        Test #3: Take a Screenshot
//        Koraci:
//        Ucitati stranu  /iframe/K5yrx
//        Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//        Kreirati screenshot stranice.
//        Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg. Na putanji: screenshots/slike.png



import helper.Helper;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak1 {


    private String baseUrl = "https://s.bootsnipp.com";
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get(baseUrl + "/iframe/K5yrx");
    }

    @Test(priority = 10)
    @Description("Test1-Edit row")
    public void editRow() {

        Assert.assertEquals(this.driver.getTitle(), ("Table with Edit and Update Data - Bootsnipp.com"),
                "Homepage title doesn't match");
        this.driver.findElement(By.xpath("//button[@data-uid='1'][contains(@class, 'update')]"))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        this.driver.findElement(By.id("fn")).clear();
        this.driver.findElement(By.id("ln")).clear();
        this.driver.findElement(By.id("mn")).clear();
        this.driver.findElement(By.id("fn")).sendKeys("Tamara");
        this.driver.findElement(By.id("ln")).sendKeys("Mitrovic");
        this.driver.findElement(By.id("mn")).sendKeys("Taki");
        this.driver.findElement(By.className("btn btn-warning")).click();
        this.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
        Assert.assertEquals(this.driver.findElement(By.id("f1")).getText(), "Tamara",
                "Invalid first name");
        Assert.assertEquals(this.driver.findElement(By.id("l1")).getText(), "Mitrovic",
                "Invalid last name");
        Assert.assertEquals(this.driver.findElement(By.id("m1")).getText(), "Taki",
                "Invalid middle name");

    }
        @Test(priority = 20)
        @Description("Test2-Delete row")
        public void deleteRow(){
            Assert.assertEquals(this.driver.getTitle(), ("Table with Edit and Update Data - Bootsnipp.com"),
                    "Homepage title doesn't match");
            this.driver.findElement(By.className("delete btn btn-danger btn-sm"))
                    .click();
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("del")));
            List<WebElement> lista1 = this.driver.findElements(By.xpath("//*[@class='table table-hover table-responsive']/tbody/tr"));
            this.driver.findElement(By.id("del")).click();
            this.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("del")));
            List<WebElement> lista2 = this.driver.findElements(By.xpath("//*[@class='table table-hover table-responsive']/tbody/tr"));
            Assert.assertFalse(lista1.size() == (lista2.size() + 1),
                    "Row not deleted");

    }

    @Test(priority = 30)
    @Description("Test3-Take a Screenshot")
    public void takeScreenshot() throws IOException {

        Assert.assertEquals(this.driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Homepage title doesn't match");
        new Helper().takeScreenshot(this.driver, "screenshots/screenshot1.jpg");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("End of test");
    }
    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(5000);
        this.driver.quit();
    }
}




