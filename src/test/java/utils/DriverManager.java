package utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;
    private static EnvironmentVariables env;

    public static WebDriver getBrowserDriver() {
        if (driver == null) {
            if (env == null) {
                env = SystemEnvironmentVariables.createEnvironmentVariables();
            }
            String driverSelected = EnvironmentSpecificConfiguration.from(env).getProperty("browser");

            System.out.println(driverSelected);

            switch (driverSelected) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window();
                    break;
                default:
                    throw new IllegalArgumentException("Navegador incorrecto: " + "'" + driverSelected + "'");
            }
        }
        return driver;
    }

    public static void quitDriver() {
       driver.close();
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }
}