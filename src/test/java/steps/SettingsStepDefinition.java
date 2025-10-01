package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.SettingsPage;
import utilities.Logs;

public class SettingsStepDefinition {

   private final SettingsPage settingsPage = new SettingsPage();

    @Given("El usuario abre la configuración de la comunidad")
    public void openCommunitySettings() {
        settingsPage.openSettingsMenu();
    }

    @When("El usuario ingresa al panel de egresos e ingresos")
    public void openExpensesAndIncomePanel() {
        settingsPage.openExpensesIncomePanel();
    }

    @When("El usuario selecciona la opción {string} en el campo fecha de facturación en egresos")
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

    @When("Guarda los cambios")
    public void saveCommunitySettings() {
        settingsPage.saveSettings();
    }
}
