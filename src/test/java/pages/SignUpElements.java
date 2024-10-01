package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class SignUpElements {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final int LIMIT_WAIT_DRIVER = 5;

    public SignUpElements(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(LIMIT_WAIT_DRIVER));
    }

    public void accessToInputFields(String username, String password) {
        try {
            WebElement userNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username")));
            WebElement userPwField = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-password")));
    
            userNameField.sendKeys(username);
            userPwField.sendKeys(password);
        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al momento de acceder a los campos de texto del modal 'Sign up'" + e);
        }
    }

    public void signUpButtonAccess() {
        try {
            WebElement buttonSignUpModal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='register()']")));

            buttonSignUpModal.click();

        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al momento de acceder al boton 'Sign up' del modal: " + e);
        }
    }

    public void signUpButtonIndex() {
        try {
            WebElement buttonSignUpIndex = wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2")));

            buttonSignUpIndex.click();
        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al momento de acceder al boton 'Sign up' de la pagina principal" + e);
        }
    }

    public String getAlertText() throws InterruptedException {
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = this.driver.switchTo().alert();
        String ad = alert.getText();

        alert.accept();
        Thread.sleep(1000);

        return ad;
    }
}