package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArticlePageElements {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int LIMIT_WAIT_DRIVER = 5;

    public ArticlePageElements(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(LIMIT_WAIT_DRIVER));
    }



    public void clickAddCartButton() {
        try {
            WebElement buttonAddCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid > div.row > div > a")));
            buttonAddCart.click();
        } catch (TimeoutException e) {
            System.out.println("No se encontro el boton del carrito': " + e);
        }
    }

}
