package stepdefinitions;


import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.Assert;
import pages.CartPageElements;
import utils.AccessContext;
import utils.PlaceOrderContext;
import utils.DriverManager;
import io.cucumber.java.Before;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import utils.CsvDataLoader;

import java.io.IOException;

public class PlaceOrderStepDefinitions {
    private WebDriver driver;
    private CartPageElements cartPageElements;
    public PlaceOrderContext placeOrderContext;
    public AccessContext accessContext;

    @Before
    public void setup() {
        this.driver = DriverManager.getBrowserDriver();
        this.placeOrderContext = new PlaceOrderContext();
        this.accessContext = AccessContext.getInstance();
    }

    @Y("esta en la pagina del carrito")
    public void estaEnLaPaginaDelCarrito() {
        this.cartPageElements = new CartPageElements(this.driver);
    }


    @Y("selecciona el boton de place order")
    public void seleccionaElBotonDePlaceOrder() {
        this.cartPageElements.clickPlaceOrderButton();
    }

    @Y("Se visualizan los campos para ingresar los datos de la compra")
    public void seVisualizanLosCamposParaIngresarLosDatosDeLaCompra() {
        this.cartPageElements.getPlaceOrderModal();
    }

    @Y("ingresa los datos parametrizados en {string}")
    public void ingresaLosDatosParametrizadosEn(String archivo) throws IOException {
        CsvDataLoader getCsvData = new CsvDataLoader(archivo);

        this.placeOrderContext.setName(getCsvData.getInputDataFromCsv().get(0));
        this.placeOrderContext.setCountry(getCsvData.getInputDataFromCsv().get(1));
        this.placeOrderContext.setCity(getCsvData.getInputDataFromCsv().get(2));
        this.placeOrderContext.setCreditCard(getCsvData.getInputDataFromCsv().get(3));
        this.placeOrderContext.setCreditCardMonth(getCsvData.getInputDataFromCsv().get(4));
        this.placeOrderContext.setCreditCardYear(getCsvData.getInputDataFromCsv().get(5));

        this.cartPageElements.accessToInputFields(this.placeOrderContext);
    }

    @Y("selecciona el boton purchased")
    public void seleccionaElBotonPurchased() {
        this.cartPageElements.clickPurchaseButton();
    }

    @Entonces("Se visualiza el mensaje  {string}, con la informacion de la compra")
    public void seVisualizaElMensajeConLaInformacionDeLaCompra(String mensajeConfirmacion) {
        Assert.assertEquals(mensajeConfirmacion, this.cartPageElements.checkOutMessagePurchase());
    }

    @After
    public void closeDriver() {
        DriverManager.quitDriver();
    }

}
