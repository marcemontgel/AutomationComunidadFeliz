package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.SettingsPage;
import utilities.Logs;
import utilities.WebDriverProvider;

public class SettingsStepDefinition {

    private WebDriver driver;
    private SettingsPage settingsPage;

    public SettingsStepDefinition() {
        this.driver = WebDriverProvider.get();
        this.settingsPage = new SettingsPage(driver);
    }

    @Given("que el usuario abre la configuración de la comunidad")
    public void openCommunitySettings() {
        settingsPage.openSettingsMenu();
    }

    @When("el usuario ingresa al panel de egresos e ingresos")
    public void openExpensesAndIncomePanel() {
        settingsPage.openExpensesIncomePanel();
    }

    @When("selecciona la opción {string} en el campo fecha de facturación en egresos")
    public void selectBillingDateOption(String option) {
        Logs.info("Intentando seleccionar opción: " + option);
        try {
            settingsPage.selectBillingDateOption(option);
            Logs.info("Opción seleccionada exitosamente");
        } catch (Exception e) {
            Logs.error("Error al seleccionar opción: " + e.getMessage());
            throw new RuntimeException("Error al seleccionar opción: " + option, e);
        }
    }

    @When("guarda los cambios")
    public void saveCommunitySettings() {
        settingsPage.saveSettings();
    }
}
