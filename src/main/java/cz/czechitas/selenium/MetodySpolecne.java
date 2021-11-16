package cz.czechitas.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MetodySpolecne {

    public static void nacteniUvodniStranky(WebDriver prohlizec) {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
    }

    public static void klikNaTlacitkoOdkazujiciNaJinouStrankuDleHref(WebDriver prohlizec, String adresa) {
        prohlizec.findElement(By.xpath("//a[@href=\"" + adresa + "\"]")).click();
    }

    public static void klikNaTlacitkoOdkazujiciNaJinouStrankuDleHrefACastiClass(WebDriver prohlizec, String adresa, String classCast) {
        prohlizec.findElement(By.xpath("//a[@href=\"" + adresa + "\" and contains (@class, '" + classCast + "')]")).click();
    }

    public static void vyplneniPoleSId(WebDriver prohlizec, String id, String textNaVyplneniPole) {
        prohlizec.findElement(By.id(id)).sendKeys(textNaVyplneniPole);
    }

    public static void zavreniVyskakovacihoOkna(WebDriver prohlizec, String toast) {
        prohlizec.findElement(By.className(toast)).click();
    }

}
