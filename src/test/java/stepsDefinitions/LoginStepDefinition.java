package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import utilities.CommonFlows;

public class LoginStepDefinition {
    private final CommonFlows commonFlows = new CommonFlows();
    private final LoginPage loginPage = new LoginPage();

    @Given("El usuario navega a la pagina de login")
    public void goToPageLogin() {
        commonFlows.goToLoginPage();
    }

    @Then("El usuario verifica que la UI de la pagina de login sea")
    public void verifyPageLogin() {
        loginPage.verifyPage();
    }
}
