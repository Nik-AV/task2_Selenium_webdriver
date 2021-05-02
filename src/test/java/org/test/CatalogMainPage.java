package org.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CatalogMainPage {

    private WebDriver driver;

    public CatalogMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='catalog-navigation-classifier__item-title-wrapper'][contains(text(), 'Электроника')]")
    private WebElement electronics;

    public void clickElectronics() {
        electronics.click(); }

        @FindBy(xpath = "//div[@class='catalog-navigation-list__aside-title'][contains(text(), 'Телевидение')]")
    private WebElement television;

    public void clickTelevision() {
        television.click(); }

    @FindBy(xpath = "//span[@class='catalog-navigation-list__dropdown-title'][contains(text(), 'Телевизоры')]")
    private WebElement tv;

    public void clickTV() {
        tv.click(); }


}
