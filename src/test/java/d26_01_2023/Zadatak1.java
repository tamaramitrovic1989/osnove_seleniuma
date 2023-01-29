package d26_01_2023;
//Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//        Visit Paris
//        Visit Prague
//        Visit London
//        Visit New York
//        Visit Belgrade
//        Maksimizirati prozor
//        Ucitati stranicu https://example.cypress.io/todo
//        Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//        Nakon svakog unosa todo-a, unosi se enter
//        Validira da li je novi todo dodat na stranici
//        Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x
//         dugme svakog todo-a)
//        Validirati da je na kraju programa broj todo-a na stranici 0.
//        Cekanje od 5s
//        Zatvorite pretrazivac

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        ArrayList<String> niz = new ArrayList<>();
        niz.add("Visit Paris");
        niz.add("Visit Prague");
        niz.add("Visit London");
        niz.add("Visit New York");
        niz.add("Visit Belgrade");

        driver.manage().window().maximize();


        driver.get("https://example.cypress.io/todo");
        for (int i = 0; i < niz.size(); i++) {

            driver.findElement(By.xpath("//input[@placeholder = 'What needs to be done?']"))
                    .sendKeys(niz.get(i));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@placeholder = 'What needs to be done?']")).
                    sendKeys(Keys.ENTER);

        }
            List<WebElement> elements = driver
                    .findElements(By.xpath("//*[@class = 'todo-list']/li"));
            Thread.sleep(2000);

            List<WebElement> elementList = driver
                    .findElements(By.xpath("//*[@class = 'todo-list']/li"));

        for (int i = 0; i < elementList.size() ; i++) {
            Thread.sleep(1000);
            for (int j = 0; j < niz.size(); j++) {
                if (elementList.get(i).getText().equals(niz.get(j))) {
                    System.out.println("Uneto je: "+ niz.get(i));
                    Thread.sleep(2000);
                }
            }
        }
        Actions actions = new Actions(driver);
        List<WebElement> button = driver.findElements(By.xpath("//button[contains(@class,'destroy')]"));

            button.forEach(element -> {
                actions.moveToElement(element.findElement(By.xpath("//div/label"))).build().perform();
                element.click();
            });


        if(elementList.size() == 0){
            Thread.sleep(1000);
           System.out.println("Svi todos su obrisani");
        }

        Thread.sleep(5000);

        driver.quit();
    }

}