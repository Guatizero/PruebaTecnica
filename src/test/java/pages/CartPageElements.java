package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PlaceOrderContext;

public class CartPageElements {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int LIMIT_WAIT_DRIVER = 1;

    public CartPageElements(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(LIMIT_WAIT_DRIVER));
    }


    public void clickCartHref() {
        try {
            WebElement buttonAddCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartur")));
            buttonAddCart.click();

        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al momento de encontrar el modulo 'Cart': " + e);
        }
    }

    public void getPlaceOrderModal() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        } catch (TimeoutException e) {
            System.out.println("No s eencontro formulario: " + e);
        }
    }
    public void clickPlaceOrderButton() {
        try {
            WebElement placeOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-target='#orderModal']")));
            placeOrderButton.click();

        } catch (TimeoutException e) {
            System.out.println("Error al ingresar datos': " + e);
        }
    }

    public void accessToInputFields(PlaceOrderContext p) {
        try {
            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
            WebElement countryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
            WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
            WebElement creditCardField = wait.until(ExpectedConditions.elementToBeClickable(By.id("card")));
            WebElement monthField = wait.until(ExpectedConditions.elementToBeClickable(By.id("month")));
            WebElement yearField = wait.until(ExpectedConditions.elementToBeClickable(By.id("year")));

            nameField.sendKeys(p.getName());
            countryField.sendKeys(p.getCountry());
            cityField.sendKeys(p.getCity());
            creditCardField.sendKeys(p.getCreditCard());
            monthField.sendKeys(p.getCreditCardMonth());
            yearField.sendKeys(p.getCreditCardYear());

        } catch (TimeoutException e) {
            System.out.println("Error al ingresar datos': " + e);
        }
    }

    public void clickPurchaseButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='purchaseOrder()']"))).click();
        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al presionar el boton 'Purchase': " + e);
        }
    }

    public String checkOutMessagePurchase() {
        String message = "";

        try {
            List<WebElement> divS = wait.until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='sweet-alert  showSweetAlert visible']")));

            for (WebElement we : divS) {
                message = we.findElement(By.tagName("h2")).getText();

                break;
            }

            return message;

        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al presionar el boton 'Purchase': " + e);
            return null;
        }
    }
}

