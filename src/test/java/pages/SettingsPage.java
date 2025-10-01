package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class SettingsPage extends BasePage {
    private final By settingsMenu = By.xpath("//a[@data-tab='setting' or contains(@href,'tab=setting')]");
    private final By expensesIncomePanelHeader = By.id("heading_service_billings_conf");

    // Selectores mejorados para el dropdown de fecha de facturación
    private final By billingDateLabel = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]");
    private final By billingDateDropdown = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]/following::button[1]");

    // Selector para las opciones del dropdown
    private final By dropdownOptions = By.xpath("//label[contains(., 'Mostrar el campo fecha de facturación en los egresos')]/following::div[6]/span/div/div[@class='option-name']");

    private final By saveButton = By.xpath(
            "//input[@type='submit' and contains(@value, 'Guardar')] | " +
                    "//button[contains(text(), 'Guardar')]"
    );

    private WebDriver driver;

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
    }

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

    public void openSettingsMenu() {
        WebElement menu = findClickable(settingsMenu);
        highlightElement(menu);
        menu.click();
    }

    public void openExpensesIncomePanel() {
        // Esperar a que el panel esté disponible
        WebElement panelHeader = findClickable(expensesIncomePanelHeader);
        highlightElement(panelHeader);

        // Verificar si ya está expandido
        String ariaExpanded = panelHeader.getAttribute("aria-expanded");
        if (!"true".equals(ariaExpanded)) {
            panelHeader.click();
            // Esperar a que el contenido se expanda
            getWait().until(ExpectedConditions.attributeContains(panelHeader, "aria-expanded", "true"));
        }
    }

    public void selectBillingDateOption(String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            Logs.info("Intentando seleccionar opción: " + optionText);

            // 1. Esperar a que el panel esté cargado
            wait.until(ExpectedConditions.visibilityOfElementLocated(billingDateLabel));

            // 2. Buscar el dropdown de forma más directa
            WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(billingDateDropdown));
            Logs.info("Dropdown toggle encontrado");

            // 3. Obtener el valor actual
            String currentValue = dropdownToggle.getText().trim();
            Logs.info("Valor actual del dropdown: '" + currentValue + "'");

            if (currentValue.equalsIgnoreCase(optionText)) {
                Logs.info("✅ El valor ya está en '" + optionText + "'. No se necesita cambio.");
                return;
            }

            // 4. Abrir el dropdown
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownToggle);
            Logs.info("Dropdown abierto");
            Thread.sleep(1000);

            // 5. Buscar y seleccionar la opción
            selectOptionFromDropdown(optionText, wait);

            // 6. Verificar que se seleccionó correctamente
            Thread.sleep(1000);
            String newValue = dropdownToggle.getText().trim();
            Logs.info("Nuevo valor del dropdown: '" + newValue + "'");

            if (!newValue.equalsIgnoreCase(optionText)) {
                throw new RuntimeException("La selección no se actualizó. Esperado: '" + optionText + "', Actual: '" + newValue + "'");
            }

            Logs.info("✅ Opción '" + optionText + "' seleccionada correctamente");

        } catch (Exception e) {
            Logs.error("❌ Error al seleccionar opción '" + optionText + "': " + e.getMessage());
            takeDebugScreenshot("error_seleccion_" + optionText);
            throw new RuntimeException("Error al seleccionar opción: " + optionText, e);
        }
    }

    private void selectOptionFromDropdown(String optionText, WebDriverWait wait) {
        try {
            // Esperar a que el dropdown esté visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));

            // Buscar todas las opciones disponibles
            List<WebElement> options = driver.findElements(dropdownOptions);
            Logs.info("Opciones encontradas en dropdown: " + options.size());

            // Buscar la opción que coincida
            for (WebElement option : options) {
                String optionLabel = option.getText().trim();
                String dataName = option.getAttribute("data-name");
                Logs.info("Opción disponible: '" + optionLabel + "' (data-name: '" + dataName + "')");

                if (optionLabel.equalsIgnoreCase(optionText) ||
                        (dataName != null && dataName.equalsIgnoreCase(optionText))) {

                    Logs.info("Seleccionando opción: " + optionLabel);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                    return;
                }
            }

            // Si no se encontró la opción, lanzar error
            throw new RuntimeException("No se encontró la opción '" + optionText + "' en el dropdown");

        } catch (Exception e) {
            Logs.error("Error al seleccionar opción del dropdown: " + e.getMessage());
            throw e;
        }
    }

    // MÉTODOS AUXILIARES IMPLEMENTADOS
    private void scrollToElement(WebElement element) {
        try {
            Logs.info("Haciendo scroll al elemento...");
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
                    element
            );
            Thread.sleep(1000);
            Logs.info("Scroll completado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Scroll interrumpido", e);
        } catch (Exception e) {
            Logs.warning("Error en scroll suave, intentando scroll simple");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        }
    }

    private void highlightElement(WebElement element, String color) {
        try {
            String originalStyle = element.getAttribute("style");
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.border='3px solid " + color + "'; arguments[0].style.backgroundColor='yellow';",
                    element
            );
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].style.border='" + (originalStyle != null ? originalStyle : "") + "';",
                    element
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Logs.debug("No se pudo resaltar elemento: " + e.getMessage());
        }
    }

    private void takeDebugScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            Logs.info("Screenshot de debug tomado: " + name);
        } catch (Exception e) {
            Logs.error("Error al tomar screenshot: " + e.getMessage());
        }
    }

    public void saveSettings() {
        try {
            WebElement saveBtn = findClickable(saveButton);
            scrollToElement(saveBtn);
            highlightElement(saveBtn, "red");
            saveBtn.click();
            Logs.info("Configuración guardada");
            Thread.sleep(2000);
        } catch (Exception e) {
            Logs.error("Error al guardar: " + e.getMessage());
            throw new RuntimeException("Error al guardar la configuración", e);
        }
    }

}
