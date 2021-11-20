package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LadyBugsAccountLogin {

    WebDriver fFox;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        fFox = new FirefoxDriver();
        fFox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    public void prihlasitSeKUctuUzivatele() {
        MetodySpolecne.nacteniUvodniStranky(fFox);
        fFox.findElement(By.xpath("//span[text()='Sign in']")).click();
        MetodySpolecne.vyplneniPoleSId(fFox,"email", "coco@cib.cib");
        MetodySpolecne.vyplneniPoleSId(fFox, "passwd", "cocococo");
        MetodySpolecne.klikNaTlacitkoDleClass(fFox, "icon-lock");
        Assertions.assertEquals("MY ACCOUNT", fFox.findElement(By.className("page-heading")).getText(), "Porovnavane texty nejsou stejne.");
    }

    @AfterEach
    public void tearDown() {
        MetodySpolecne.klikNaTlacitkoDleClass(fFox, "caret");
        MetodySpolecne.klikNaTlacitkoDleTextu(fFox, "Logout");
        fFox.quit();
    }


}
