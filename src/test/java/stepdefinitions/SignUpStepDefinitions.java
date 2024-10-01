package stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.SignUpElements;
import utils.AccessContext;
import utils.CsvDataLoader;
import utils.DriverManager;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class SignUpStepDefinitions {

    private WebDriver driver;
    private SignUpElements signUpElements;
    private AccessContext accessContext;

    @Before
    public void setup() {
        this.driver = DriverManager.getBrowserDriver();
        this.signUpElements = new SignUpElements(this.driver);
        this.accessContext = AccessContext.getInstance();
    }

    @Dado("el usuario se encuentra en la pagina DEMOBLAZE")
    public void elUsuarioSeEncuentraEnLaPaginaDEMOBLAZES() {

        driver.get("https://www.demoblaze.com/index.html");

    }

    @Cuando("seleeciona singUp")
    public void seleecionaSingUp() {
        this.signUpElements.signUpButtonIndex();
    }


    @Cuando("ingresa los datos del rormulñario de registro")
    public void ingresaLosDatosDelRormulnarioDeRegistro() throws IOException {

        CsvDataLoader getCsvData = new CsvDataLoader("src/test/resources/data/datosUsuario.csv");
        String userName = getCsvData.getInputDataFromCsv().get(0);
        String password = getCsvData.getInputDataFromCsv().get(1);

        accessContext.setUsername(userName);
        accessContext.setPassword(password);

        this.signUpElements.accessToInputFields(userName, password);
        this.signUpElements.signUpButtonAccess();
    }

    @Entonces("se crea el nuevo usuario y se visualiza un mensaje : Sign up successful.")
    public void seCreaElNuevoUsuarioYSeVisualizaUnMensajeSignUpSuccessful() throws InterruptedException {

        String mensajeConfirmacion = "Sign up successful.";
        Assert.assertEquals(mensajeConfirmacion, this.signUpElements.getAlertText());

    }
}