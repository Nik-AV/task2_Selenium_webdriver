package org.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnlinerMainPage {

    private WebDriver driver;

    public OnlinerMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='b-main-navigation__text'][contains(text(), 'Каталог')]")
    private WebElement catalog;

    public void entryCatalog() {
        catalog.click(); }

}
