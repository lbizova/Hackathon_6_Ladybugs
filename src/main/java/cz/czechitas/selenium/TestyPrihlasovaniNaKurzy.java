package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver fFox;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        fFox = new FirefoxDriver();
        fFox.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /* 1. Rodič s existujícím účtem se musí být schopen přihlásit do webové aplikace.
    Poznámka: Nepište automatizovaný test na zakládání nového účtu rodiče. Účet si připravte dopředu
    ručně.  Rodič Dítěte123, ditete123@rodic.com 123@Rodic*/
    @Test
    public void poStiskuTlacikaLoginMusiBytZobrazenaStrankaLoggedIn() //throws InterruptedException
    {
        MetodySpolecne.nacteniUvodniStranky(fFox);
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/prihlaseni");

        vyplneniPrihlaseni("ditete123@rodic.com", "123@Rodic");
        provedeniPrihlaseni();
        Assertions.assertEquals("Přihlášky", fFox.findElement(By.xpath("//h1")).getText(), "Nenačetla se správná stránka.");
    }

    /*2. Rodič musí být schopen vybrat kurz, přihlásit se do aplikace a přihlásit na kurz svoje dítě.
                Poznámka: I zde použijte už existující účet rodiče, jen se k němu v průběhu testu přihlašte.
        Poznámka: Úspěšné přihlášení dítěte na kurz je třeba po vyplnění přihlášky ověřit ve svém seznamu
        přihlášek. */
    @Test
    public void VybratKurzPrihlasitSeDoAppAPrihlasitDiteNaKurz() {
        MetodySpolecne.nacteniUvodniStranky(fFox);
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu");
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1");
        vyplneniPrihlaseni("ditete123@rodic.com", "123@Rodic");
        provedeniPrihlaseni();
        vyplneniPovinnychPoliFormulare();
        potvrzeniFormulare();
        aktualniCas();
        MetodySpolecne.zavreniVyskakovacihoOkna(fFox, "toast-close-button");
        assertionPorovnaniCasuAktualnihoANaFormulari();
    }

    /*3. Rodič se musí být schopen přihlásit do aplikace, vyhledat kurz a přihlásit na něj svoje dítě.
        Poznámka: I zde použijte pro přihlášení do aplikace existující účet rodiče a nezapomeňte ověřit, že
        přihláška na kurz proběhla úspěšně (v rodičově seznamu přihlášek). */
    @Test
    public void PrihlaseniDoAppVyhledaniKurzuAPrihlaseniDiteteNaKurz() {
        MetodySpolecne.nacteniUvodniStranky(fFox);
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/prihlaseni");
        vyplneniPrihlaseni("ditete123@rodic.com", "123@Rodic");
        provedeniPrihlaseni();
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHrefACastiClass(fFox, "https://cz-test-jedna.herokuapp.com/zaci/pridat", "btn");
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu");
        MetodySpolecne.klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(fFox, "https://cz-test-jedna.herokuapp.com/zaci/pridat/41-html-1");
        vyplneniPovinnychPoliFormulare();
        potvrzeniFormulare();
        aktualniCas();
        MetodySpolecne.zavreniVyskakovacihoOkna(fFox, "toast-close-button");
        assertionPorovnaniCasuAktualnihoANaFormulari();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        fFox.findElement(By.xpath("//span[text() = 'Přihlášen']/../a")).click();
        fFox.findElement(By.id("logout-link")).click();
        fFox.quit();
    }

    public void vyplneniPrihlaseni(String email, String heslo) {
        WebElement poleEmail = fFox.findElement(By.id("email"));
        poleEmail.sendKeys(email);
        WebElement poleHeslo = fFox.findElement(By.id("password"));
        poleHeslo.sendKeys(heslo);
    }

    public void provedeniPrihlaseni() {
        fFox.findElement(By.xpath("//button[@type = 'submit']")).click();
        ;
    }

    public void vyplneniPovinnychPoliFormulare() {
        WebElement termin = fFox.findElement(By.className("filter-option-inner-inner"));
        termin.click();
        //výběr 1. volného termínu
        fFox.findElement(By.xpath("//a[@id = 'bs-select-1-0']/span[1]")).click();
        MetodySpolecne.vyplneniPoleSId(fFox, "forename", "Dítě");
        MetodySpolecne.vyplneniPoleSId(fFox, "surname", "123");
        MetodySpolecne.vyplneniPoleSId(fFox, "birthday", "02.10.2010");
        fFox.findElement(By.xpath("//label[@for = 'terms_conditions']")).click();
        fFox.findElement(By.xpath("//label[@for= 'payment_cash']")).click();

    }

    public void potvrzeniFormulare() {
        fFox.findElement(By.xpath("//*[@value = 'Vytvořit přihlášku']")).click();
    }


    public String aktualniCas() {
        LocalDateTime AktualniDatumACas = LocalDateTime.now();
        int hour = AktualniDatumACas.getHour();
        int min = AktualniDatumACas.getMinute();
        int year = AktualniDatumACas.getYear();
        int month = AktualniDatumACas.getMonth().getValue();
        int day = AktualniDatumACas.getDayOfMonth();
        String datumACas = "";
        if (hour >= 10 && min >= 10) {
            datumACas = day + "." + month + "." + year + " " + hour + ":" + min;
        } else if (hour >= 10) {
            datumACas = day + "." + month + "." + year + " " + hour + ":0" + min;
        } else if (min >= 10) {
            datumACas = day + "." + month + "." + year + " 0" + hour + ":" + min;
        } else {
            datumACas = day + "." + month + "." + year + " 0" + hour + ":0" + min;
        }
        return datumACas;
    }

    public void assertionPorovnaniCasuAktualnihoANaFormulari() {
        Assertions.assertEquals(aktualniCas(), fFox.findElement(By.xpath("//td[text() = 'Vytvořen:']/../td[2]")).getText(), "Čas vytvoření objednávky nesouhlasí s aktuálním časem. Ověřte vytvoření objednávky.");
    }

}
