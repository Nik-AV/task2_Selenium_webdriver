package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

public class TVPage {

    private WebDriver driver;

    public TVPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@value='samsung']")
    private WebElement samsung;

    public void selectSamsung() {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", samsung);

    }

    @FindBy(xpath = "//input[@value='1920x1080']")
    private WebElement resolution;

    public void selectResolution() {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", resolution);
    }

    @FindBy(xpath = "//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price'][@placeholder='до']")
    private WebElement price;

    public void setPrice() {

        price.sendKeys("1000");
    }


    @FindBy(xpath = "//div[@class='schema-filter__group']/*[1]//option[@value='400']")
    private WebElement minInch;

    public void selectMinInch() {
        minInch.click();
    }

    @FindBy(xpath = "//div[@class='schema-filter__group']/*[2]//option[@value='500']")
    private WebElement maxInch;

    public void selectMaxInch() {
        maxInch.click();
    }

}
