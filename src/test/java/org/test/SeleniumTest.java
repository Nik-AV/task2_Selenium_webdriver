package org.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.CatalogMainPage;
import org.pages.OnlinerMainPage;
import org.pages.TVPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.verification.Verification;

import java.util.concurrent.TimeUnit;

public class SeleniumTest  {

    public static OnlinerMainPage onlinerMainPage;
    public static CatalogMainPage catalogMainPage;
    public static TVPage tvPage;
    public static Verification verification;
    public static WebDriver driver;

    @Parameters({"startPage", "chromedriver"})
    @BeforeTest
    public static void setup(String startPage, String chromedriver) {

        System.setProperty("webdriver.chrome.driver", chromedriver);

        driver = new ChromeDriver();

        onlinerMainPage = new OnlinerMainPage(driver);
        catalogMainPage = new CatalogMainPage(driver);
        tvPage = new TVPage(driver);
        verification = new Verification(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(startPage);
    }

    @Parameters({"preconditionPrice", "preconditionResolution", "preconditionTile", "preconditionMinInches", "preconditionMaxInches"})
    @Test
    public void test(String preconditionPrice, String preconditionResolution, String preconditionTile,String preconditionMinInches, String preconditionMaxInches)  throws InterruptedException{

        onlinerMainPage.entryCatalog();

        catalogMainPage.clickElectronics();

        catalogMainPage.clickTelevision();

        catalogMainPage.clickTV();

        tvPage.selectSamsung();

        tvPage.setPrice();

        tvPage.selectResolution();

        tvPage.selectMinInch();

        tvPage.selectMaxInch();

        Thread.sleep(3000);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(verification.verifyTitle(preconditionTile), true);
        softAssert.assertEquals(verification.verifyResolution(preconditionResolution), true);
        softAssert.assertEquals(verification.verifyPrice(preconditionPrice), true);
        softAssert.assertEquals(verification.verifyInches(preconditionMinInches, preconditionMaxInches), true);

    }

    @AfterTest
    public static void tearDown() {

        driver.quit();
    }
}