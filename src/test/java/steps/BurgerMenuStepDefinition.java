package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.BurgerMenuPage;

public class BurgerMenuStepDefinition {
    private final BurgerMenuPage burgerMenuPage = new BurgerMenuPage();

    @Given("El usuario ingresa al menú lateral")
    public void goToBurgerMenu() {
        burgerMenuPage.waitPageToLoad(); burgerMenuPage.verifyPage();
    }

    @When("El usuario busca el módulo {string}")
    public void searchModule(String modulo) {
        burgerMenuPage.clickSearchIcon();
        burgerMenuPage.typeInSearch(modulo);
    }

    @Then("El usuario selecciona la opción {string}")
    public void selectedOption(String opcion) {
        burgerMenuPage.selectOption(opcion);
    }

    @Then("El sistema debe mostrar la opción {string}")
    public void showOptionBurgerMenu(String opcion) {
        boolean present = burgerMenuPage.isOptionPresent(opcion);

        Assertions.assertTrue(present,
                () -> "La opción '" + opcion + "' no está visible en el menú");
    }


}
