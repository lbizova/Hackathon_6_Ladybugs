package cz.czechitas.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MetodySpolecne {

    public static void nacteniUvodniStranky(WebDriver prohlizec) {
        prohlizec.navigate().to("http://czechitas-datestovani-hackathon.cz/en/");
    }

        public static void klikNaTlacitkoDleClass(WebDriver prohlizec, String jmenoTridy) {
        prohlizec.findElement(By.className(jmenoTridy)).click();
    }
        public static void klikNaTlacitkoDleTextu(WebDriver prohlizec, String text) {
        prohlizec.findElement(By.xpath("//*[text()=\"" + text + "\"]")).click();
    }

    public static void vyplneniPoleSId(WebDriver prohlizec, String id, String textNaVyplneniPole) {
        prohlizec.findElement(By.id(id)).sendKeys(textNaVyplneniPole);
    }

}
