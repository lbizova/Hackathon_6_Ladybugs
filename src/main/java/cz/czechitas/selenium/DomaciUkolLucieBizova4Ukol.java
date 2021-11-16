package cz.czechitas.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DomaciUkolLucieBizova4Ukol {

    WebDriver fFox;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        fFox = new FirefoxDriver();
        fFox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /* 4. Jeden další scénář dle své úvahy.  */
    /* 4. Škola se přepne na objednávku pro ZŠ/MŠ, vyplní požadované 2 termíny (1 náhradní) a objedná Školu v přírodě.
     */
    @Test
    public void skolaVyhledaFormularObjednavkyProSkolyAProvedeObjednavkuSkolyVPrirodeS2Terminy() {
        MetodySpolecne.nacteniUvodniStranky(fFox);
        klikNaTlacitkoRozbalujiciNabidluDleHrefACastiClass("https://cz-test-jedna.herokuapp.com/pro-ucitele", "dropdown");
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHrefACastiClass(fFox, "https://cz-test-jedna.herokuapp.com/objednavka/pridat", "dropdown");
        vyplneniPoliFormulare();
        potvrzeniFormulare();
        assertionPorovnaniTextu();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(3_000);


        fFox.quit();
    }

    public void klikNaTlacitkoRozbalujiciNabidluDleHrefACastiClass(String adresa, String classCast) {
        fFox.findElement(By.xpath("//a[@href=\"" + adresa + "\" and contains (@class, '" + classCast + "')]")).click();
    }

    public void vyplneniPoliFormulare() {

        MetodySpolecne.vyplneniPoleSId(fFox, "ico", "124578");
        klikDoJinehoPole("client");
        MetodySpolecne.zavreniVyskakovacihoOkna(fFox, "toast-close-button");
        MetodySpolecne.vyplneniPoleSId(fFox, "client", "ZŠ základní");
        MetodySpolecne.vyplneniPoleSId(fFox, "address", "Základní 1, Brno");
        MetodySpolecne.vyplneniPoleSId(fFox, "substitute", "Paní Ředitelka");
        MetodySpolecne.vyplneniPoleSId(fFox, "contact_name", "Alžběta Učítelka");
        MetodySpolecne.vyplneniPoleSId(fFox, "contact_tel", "123456789");
        MetodySpolecne.vyplneniPoleSId(fFox, "contact_mail", "Alzbeta@zsz.cz");
        MetodySpolecne.vyplneniPoleSId(fFox, "start_date_1", "10.2.2022");
        MetodySpolecne.vyplneniPoleSId(fFox, "end_date_1", "17.2.2022");
        MetodySpolecne.vyplneniPoleSId(fFox, "start_date_2", "17.2.2022");
        MetodySpolecne.vyplneniPoleSId(fFox, "end_date_2", "24.2.2022");
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "#nav-profile");
        MetodySpolecne.vyplneniPoleSId(fFox, "nature-students", "20");
        MetodySpolecne.vyplneniPoleSId(fFox, "nature-age", "12");
        MetodySpolecne.vyplneniPoleSId(fFox, "nature-adults", "3");
        MetodySpolecne.vyplneniPoleSId(fFox, "nature-start_time", "16:00");
        vyberJidla("nature-start_food", "dinner");
        MetodySpolecne.vyplneniPoleSId(fFox, "nature-end_time", "14:00");
        vyberJidla("nature-end_food", "lunch");
    }

    public void klikDoJinehoPole(String id) {
        fFox.findElement(By.id(id)).click();
    }

    private void vyberJidla(String id, String jidlo) {
        fFox.findElement(By.id(id)).click();
        fFox.findElement(By.xpath("//select[@id = '" + id + "']/option[@value = '" + jidlo + "']")).click();
    }

    public void potvrzeniFormulare() {
        fFox.findElement(By.xpath("//input[@name = 'school_nature']")).click();
    }

    public void assertionPorovnaniTextu() {
        Assertions.assertEquals("Děkujeme za objednávku", fFox.findElement(By.xpath("//h3")).getText(), "Ověřte vytvoření objednávky.");
    }


}
