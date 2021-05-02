package org.test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class SeleniumTest  {

    public static OnlinerMainPage onlinerMainPage;
    public static CatalogMainPage catalogMainPage;
    public static TVPage tvPage;
    public static Verification verification;
    public static WebDriver driver;

    @BeforeTest
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));

        driver = new ChromeDriver();

        onlinerMainPage = new OnlinerMainPage(driver);
        catalogMainPage = new CatalogMainPage(driver);
        tvPage = new TVPage(driver);
        verification = new Verification(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("startPage"));
    }

    @Test
    public void test() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor)driver;

        String URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://www.onliner.by/");

        onlinerMainPage.entryCatalog();
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/");

        catalogMainPage.clickElectronics();
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/");

        catalogMainPage.clickTelevision();
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/");

        catalogMainPage.clickTV();
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv");

        tvPage.selectSamsung();
        Thread.sleep(1000);
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv?mfr%5B0%5D=samsung");

        tvPage.setPrice();
        Thread.sleep(1000);
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv?mfr%5B0%5D=samsung&price%5Bto%5D=1000.00");

        tvPage.selectResolution();
        Thread.sleep(1000);
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv?mfr%5B0%5D=samsung&price%5Bto%5D=1000.00&resolution_tv%5B0%5D=1920x1080&resolution_tv%5Boperation%5D=union");

        tvPage.selectMinInch();
        Thread.sleep(1000);
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv?mfr%5B0%5D=samsung&price%5Bto%5D=1000.00&diagonal_tv%5Bfrom%5D=400&resolution_tv%5B0%5D=1920x1080&resolution_tv%5Boperation%5D=union");

        tvPage.selectMaxInch();
        Thread.sleep(1000);
        URL = js.executeScript("return document.URL;").toString();
        Assert.assertEquals(URL, "https://catalog.onliner.by/tv?mfr%5B0%5D=samsung&price%5Bto%5D=1000.00&diagonal_tv%5Bfrom%5D=400&diagonal_tv%5Bto%5D=500&resolution_tv%5B0%5D=1920x1080&resolution_tv%5Boperation%5D=union");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(verification.verifyTitle(), true);
        softAssert.assertEquals(verification.verifyResolution(), true);
        softAssert.assertEquals(verification.verifyPrice(), true);
        softAssert.assertEquals(verification.verifyInches(), true);

    }

    @AfterTest
    public static void tearDown() {

        driver.quit();
    }
}
