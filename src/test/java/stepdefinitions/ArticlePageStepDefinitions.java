package stepdefinitions;

import io.cucumber.java.es.Y;
import pages.ArticlePageElements;
import utils.DriverManager;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class ArticlePageStepDefinitions {
    private WebDriver driver;
    private ArticlePageElements articlePageElements;

    @Before
    public void setup() {
        this.driver = DriverManager.getBrowserDriver();
    }

    @Y("esta en la pagina del producto")
    public void estaEnLaPaginaDelProducto() {
        this.articlePageElements = new ArticlePageElements(this.driver);
    }

    @Y("selecciona agregar al carrito")
    public void seleccionaAgregarAlCarrito() {
        this.articlePageElements.clickAddCartButton();
    }

}
