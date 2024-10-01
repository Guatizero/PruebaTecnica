package pages;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class CategoriesArticlesElements {
    WebDriver driver;
    WebDriverWait wait;
    private final int LIMIT_WAIT_DRIVER = 1;
    public String articleFullName;
    public String articleDescription;
    public String articlePrice;

    public CategoriesArticlesElements(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(LIMIT_WAIT_DRIVER));
    }

    public void clickCategory(String categoryUser, List<String> groupCategoriesIndex) {
        for (String filterCategories : groupCategoriesIndex) {
            if (filterCategories.equalsIgnoreCase(categoryUser)) {
                try {
                    WebElement categoryElement = wait.until(ExpectedConditions
                            .elementToBeClickable(By.xpath("//a[text()='" + filterCategories + "']")));

                    categoryElement.click();

                    Thread.sleep(2000);
                } catch (TimeoutException e) {
                    System.out.println("Error al encontrar la categoria: " + categoryUser);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Error en la espera: " + e);
                }
                break;
            }
        }
    }

    public void clickArticle(String article) {
        boolean isMatched = false;
        WebElement divArticlesContainer = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id='tbodyid']")));

        List<WebElement> articlesList = divArticlesContainer
                .findElements(By.xpath("./child::*"));

        for (WebElement h4 : articlesList) {
            articleFullName = h4.findElement(By.className("hrefch")).getText();
            articleDescription = h4.findElement(By.className("card-text")).getText();
            articlePrice = h4.findElement(By.tagName("h5")).getText();

            if (articleFullName.toLowerCase().contains(article.toLowerCase())) {
                isMatched = true;

                WebElement categoryElement = h4.findElement(By.className("hrefch"));

                categoryElement.click();

                break;
            }
        }

        if (!isMatched) {
            nextButtonArticlesList(article);
        }
    }

    public void nextButtonArticlesList(String article) {
        try {
            // Boton Next de la lista de articulos
            WebElement buttonNext = wait.until(ExpectedConditions
                    .elementToBeClickable(By.id("next2")));

            buttonNext.click();

            Thread.sleep(1000);

            clickArticle(article);

        } catch (TimeoutException a) {
            System.out.println("No hay ningun articulo con el nombre: " + article +
                    "\n" + a);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error en la espera: " + e);
        }
    }

    public List<String> getCategories() {
        List<String> itemsText = new ArrayList<>();

        try {
            WebElement categories = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("list-group")));
            List<WebElement> items = categories.findElements(By.tagName("a"));

            for (WebElement extractItems : items) {
                itemsText.add(extractItems.getText());
            }
        } catch (TimeoutException e) {
            System.out.println("Error de TimeOut al momento de acceder a los items de las categorias: " + e);
        }
        return itemsText;
    }
}
