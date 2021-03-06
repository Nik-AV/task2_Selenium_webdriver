package org.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Double.parseDouble;

public class Verification {

    private WebDriver driver;

    public Verification(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean verifyResolution(String preconditionResolution) {

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"schema-product__description\"]/span"));

        for (WebElement element : elements) {

            if (element.getText().contains(preconditionResolution) == false) {

                System.out.println("Resolution check failed");
                return false;
            }
        }

        System.out.println("Resolution check passed");
        return true;
    }

    public boolean verifyInches(String preconditionMinInches, String preconditionMaxInches) {

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"schema-product__description\"]/span"));

        for (WebElement element : elements) {

            String str = element.getText().split("\"")[0];
            double inches = parseDouble(str);

            if (inches < Integer.parseInt(preconditionMinInches) ||
                    inches > Integer.parseInt(preconditionMaxInches)) {

                System.out.println("Inches check failed");
                return false;
            }
        }

        System.out.println("Inches check passed");
        return true;
    }

    public boolean verifyTitle(String preconditionTile) {

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"schema-product__title\"]//span"));

        for (WebElement element : elements) {

            if (element.getText().contains(preconditionTile) == false) {

                System.out.println("Titles check failed");
                return false;
            }
        }

        System.out.println("Titles check passed");
        return true;
    }

    public boolean verifyPrice(String preconditionPrice) {

        List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"schema-product__price\"]/*/*"));

        for (WebElement element : elements) {

            String str = element.getText();
            str = str.substring(0,str.length()-6) + "." + str.substring(str.length()-5,str.length()-3);
            double price = parseDouble(str);
            if (price > Double.parseDouble(preconditionPrice)) {

                System.out.println("Price check failed");
                return false;
            }

        }

        System.out.println("Price check passed");
        return true;
    }
}
