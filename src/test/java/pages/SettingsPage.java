package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.FileManager;
import utilities.Logs;

import java.time.Duration;
import java.util.List;

public class SettingsPage extends BasePage {
    private final By settingsMenu = By.xpath("//a[@data-tab='setting' or contains(@href,'tab=setting')]");
    private final By expensesIncomePanelHeader = By.id("heading_service_billings_conf");
    private final By billingDateLabel = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]");
    private final By billingDateDropdown = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]/following::button[1]");
    private final By dropdownOptions = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]/following::div[6]/span/div/div[@class='option-name']");
    private final By saveButton = By.xpath(
            "//input[@type='submit' and contains(@value, 'Guardar')] | "
                    + "//button[contains(text(), 'Guardar')]"
    );
    private final int WAIT_SECONDS = 15;

    @Override
    public void waitPageToLoad() {
        waitPage(settingsMenu, "Community Settings");
    }

    @Override
    public void verifyPage() {
        if (!isElementPresent(settingsMenu)) {
            throw new IllegalStateException("La página Community Settings no está cargada");
        }
    }

    private void clickElement(By locator, String elementName) {
        WebElement element = findClickable(locator);
        highlightElement(element);
        element.click();
        Logs.info(elementName + " clickeado");
    }

    private void clickWithJS(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        Logs.info("Click con JS realizado en elemento: " + element.getText());
    }

    public void openSettingsMenu() {
        clickElement(settingsMenu, "Settings Menu");
    }

    public void openExpensesIncomePanel() {
        WebElement panelHeader = findClickable(expensesIncomePanelHeader);
        highlightElement(panelHeader);

        String ariaExpanded = panelHeader.getAttribute("aria-expanded");
        if (!"true".equals(ariaExpanded)) {
            panelHeader.click();
            getWait().until(ExpectedConditions.attributeContains(panelHeader, "aria-expanded", "true"));
        }
    }

    public void selectBillingDateOption(String optionText) {
        try {
            Logs.info("Seleccionando opción del dropdown: " + optionText);

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_SECONDS));

            wait.until(ExpectedConditions.visibilityOfElementLocated(billingDateLabel));
            WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(billingDateDropdown));
            highlightElement(dropdownToggle);

            String currentValue = dropdownToggle.getText().trim();
            if (currentValue.equalsIgnoreCase(optionText)) {
                Logs.info("✅ El valor ya está en '" + optionText + "'. No se necesita cambio.");
                return;
            }

            clickWithJS(dropdownToggle);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));

            selectOptionFromDropdown(optionText, wait);

            // Validar selección
            String newValue = dropdownToggle.getText().trim();
            if (!newValue.equalsIgnoreCase(optionText)) {
                throw new RuntimeException("La selección no se actualizó. Esperado: '" + optionText + "', Actual: '" + newValue + "'");
            }

            Logs.info("Opción '" + optionText + "' seleccionada correctamente");

        } catch (Exception e) {
            Logs.error("Error al seleccionar opción '" + optionText + "': " + e.getMessage());
            FileManager.getScreenshot("error_seleccion_" + optionText);
            throw new RuntimeException("Error al seleccionar opción: " + optionText, e);
        }
    }

    private void selectOptionFromDropdown(String optionText, WebDriverWait wait) {
        List<WebElement> options = getDriver().findElements(dropdownOptions);
        Logs.info("Opciones encontradas: " + options.size());

        for (WebElement option : options) {
            String optionLabel = option.getText().trim();
            String dataName = option.getAttribute("data-name");

            if (optionLabel.equalsIgnoreCase(optionText) || (dataName != null && dataName.equalsIgnoreCase(optionText))) {
                Logs.info("Seleccionando opción: " + optionLabel);
                clickWithJS(option);
                return;
            }
        }
        throw new RuntimeException("No se encontró la opción '" + optionText + "' en el dropdown");
    }

    public void saveSettings() {
        try {
            WebElement saveBtn = findClickable(saveButton);
            scrollToElement(saveBtn);
            highlightElement(saveBtn, "red");
            saveBtn.click();
            getWait().until(ExpectedConditions.invisibilityOf(saveBtn));
            Logs.info("Configuración guardada exitosamente");
        } catch (Exception e) {
            Logs.error("Error al guardar: " + e.getMessage());
            FileManager.getScreenshot("error_guardar_config");
            throw new RuntimeException("Error al guardar la configuración", e);
        }
    }
}
