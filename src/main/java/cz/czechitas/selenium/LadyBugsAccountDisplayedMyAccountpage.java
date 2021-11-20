package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LadyBugsAccountDisplayedMyAccountpage {

    WebDriver fFox;



    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        fFox = new FirefoxDriver();
        fFox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fFox.navigate().to("http://czechitas-datestovani-hackathon.cz/en/login?back=my-account");
        MetodySpolecne.vyplneniPoleSId(fFox,"email", "coco@cib.cib");
        MetodySpolecne.vyplneniPoleSId(fFox, "passwd", "cocococo");
        MetodySpolecne.klikNaTlacitkoDleClass(fFox, "icon-lock");
    }


    @Test
    public void displayedLinkOrderHistoryAndDetails() {
        fFox.navigate().to("http://czechitas-datestovani-hackathon.cz/en/my-account");
        MetodySpolecne.klikNaTlacitkoDleTextu(fFox, "Order history and details");

        Assertions.assertEquals("ORDER HISTORY AND DETAILS", fFox.findElement(By.xpath("//span[text()='Order history and details']")).getText(), "Porovnavane texty nejsou stejne.");
    }


    @AfterEach
    public void tearDown() {
        MetodySpolecne.klikNaTlacitkoDleClass(fFox, "caret");
        MetodySpolecne.klikNaTlacitkoDleTextu(fFox, "Logout");
        fFox.quit();
    }


}
