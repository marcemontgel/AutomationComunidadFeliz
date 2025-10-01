package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.BurgerMenuPage;

public class BurgerMenuStepDefinition {
    private final BurgerMenuPage burgerMenuPage = new BurgerMenuPage();

    @Given("El usuario ingresa al menú lateral")
    public void goToBurgerMenu() {
        burgerMenuPage.waitPageToLoad(); burgerMenuPage.verifyPage();
    }

    @When("El usuario busca el módulo {string} muestra {string} selecciona {string}")
    public void findModule(String module, String selectedoption, String option) {
        burgerMenuPage.clickSearchIcon();
        burgerMenuPage.typeInSearch(module);
        boolean present = burgerMenuPage.isOptionPresent(option);

        Assertions.assertTrue(present,
                () -> "La opción '" + option + "' no está visible en el menú");
        burgerMenuPage.selectOption(selectedoption);
    }
}
