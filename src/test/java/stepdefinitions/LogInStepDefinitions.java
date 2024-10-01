package stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Before;
import pages.LogInElements;
import utils.AccessContext;
import utils.DriverManager;

import java.io.IOException;

public class LogInStepDefinitions {
    private LogInElements logInElements;
    private String userName;
    AccessContext accessContext;

    @Before
    public void setup() {
        WebDriver driver = DriverManager.getBrowserDriver();
        this.logInElements = new LogInElements(driver);
        this.accessContext = AccessContext.getInstance();
    }

    @Dado("que el usuario selecciona SingIn")
    public void queElUsuarioSeleccionaSingIn() {
        this.logInElements.logInButtonIndex();
    }

    @Cuando("the user fills in the input fields in the Log In modal with the data from {string} and presses the action button")
    public void the_user_fills_in_the_input_fields_in_the_log_in_modal_with_the_data_from_and_presses_the_action_button(String csvFilePath) throws IOException {
        String password = accessContext.getPassword();

        userName = accessContext.getUserName();

        logInElements.accessToInputFields(userName, password);
        logInElements.logInButtonAccess();
    }

    @Entonces("the user is logged in to the website and returned to the index page with the Username")
    public void the_user_is_logged_in_to_the_website_and_returned_to_the_index_page_with_the_username() {
        Assert.assertEquals("Welcome " + this.userName, this.logInElements.getUsernameIndex());
    }

}
