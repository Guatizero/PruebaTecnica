package stepdefinitions;

import io.cucumber.java.es.Y;
import pages.CartPageElements;
import utils.DriverManager;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class CartPageStepDefinitions {
    private WebDriver driver;

    private CartPageElements cartPageElements;

    @Before
    public void setup() {
        this.driver = DriverManager.getBrowserDriver();
    }

    @Y("esta ne la pagina del producto")
    public void estaNeLaPaginaDelProducto() {
        this.cartPageElements = new CartPageElements(this.driver);
    }

    @Y("selecciona ir al carrito")
    public void seleccionaIrAlCarrito() {
        this.cartPageElements.clickCartHref();
    }

}
