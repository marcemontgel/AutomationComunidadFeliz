package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.util.Locale;

public class ExpensePage extends BasePage {

    private final By newExpenseButton = By.xpath("//*[@data-intro='Agregar nuevo egreso.']");
    private final By newExpensePage = By.xpath("//div[text()='Nuevo egreso']");
    private final By categoryDropdown = By.id("category_name-button");
    private final By typeProviderDropdown = By.id("supplier_id-button");
    private final By descriptionInput = By.id("description-field");
    private final By amountInput = By.id("service_billing_price");
    private final By nameInput = By.id("supplier_name");
    private final By rutInput = By.id("supplier_rut");
    private final By countDropdown = By.id("supplier_accounting_subaccount_id-button");
    private final By paymentDateField = By.xpath("(//*[@id='date-selector-dropdown'])[1]");
    private final By billingDateField = By.xpath("(//*[@id='date-selector-dropdown'])[2]");
    private final By feeComboBox = By.id("service_billing_number_of_fees");
    private final By saveButton = By.xpath("//button[contains(text(),'Guardar') or contains(text(),'Save')]");
    private final By messageErrorRut = By.id("supplier-rut-warning");


    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando cargue el menú de Egresos");
        waitPage(newExpenseButton, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando el menu de Egreso");
        waitPage(newExpenseButton, "New Expense");
    }

    public void clickNewExpense(){
        Logs.info("Dando clic en el botón Nuevo Egreso");
        findClickable(newExpenseButton).click();
        find(newExpensePage);
    }

    private void selectFromDropdown(By dropdownLocator, String menuId, String optionText, String elementName) {
        Logs.info("Seleccionando el item");
        WebElement dropdown = findClickable(dropdownLocator);
        scrollToElement(dropdown);
        dropdown.click();

        By optionLocator = By.xpath(String.format(
                "//*[@id='%s']//div[@class='option-name' and contains(.,'%s')]",
                menuId, optionText
        ));

        WebElement option = findClickable(optionLocator);
        option.click();

        logAction("Selected " + elementName + ": " + optionText);
    }

    public boolean isBillingDateFieldVisible() {
        try {
            WebElement field = getDriver().findElement(billingDateField);
            return field.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectCategory(String category) {
        Logs.info("Seleccionando el item de la lista de categoría");
        selectFromDropdown(categoryDropdown,
                "category_name-dropdown-menu",
                category,
                "category");
    }

    public void selectProvider(String provider) {
        Logs.info("Seleccionando el item de la lista de proveedores");
        selectFromDropdown(typeProviderDropdown,
                "supplier_id-dropdown-menu",
                provider,
                "provider");
    }

    public void selectAcount(String account) {
        Logs.info("Seleccionando el item de la lista de subcuenta");
        selectFromDropdown(countDropdown,
                "supplier_accounting_subaccount_id-dropdown-menu",
                account,
                "account");
    }

    public void fillFormProviderTest(String desc, String amount){
        Logs.info("Ingresar Información");
        find(descriptionInput).sendKeys(desc);

        Logs.info("Ingresar monto");
        find(amountInput).sendKeys(amount);
    }

    public void fillFormProviderOther(String name, String rut, String acount){
        Logs.info("Ingresar nombre");
        find(nameInput).sendKeys(name);

        Logs.info("Ingresar rut");
        find(rutInput).sendKeys(rut);

        Logs.info("Seleccionar subcuenta");
        selectAcount(acount);
    }

    public void setNumFee(String num) {
        Logs.info("Seleccionar número de cuotas: " + num);

        WebElement dropdown = find(feeComboBox);
        Select select = new Select(dropdown);

        select.selectByValue(num);

        Logs.info("Cuota seleccionada exitosamente: " + num);
    }

    private void selectDate(By dateField, String date) {
        Logs.info("Seleccionando la fecha");
        String[] parts = date.split("/"); // dd/MM/yyyy
        String day = parts[0];
        String month = getMonthName(Integer.parseInt(parts[1]));
        String year = parts[2];

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement opener = findClickable(dateField);
        scrollToElement(opener);
        opener.click();

        By dayLocator = By.xpath("//*[@data-action='click->date-selector#changeValue' and text()='" + Integer.parseInt(day) + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();

        logAction("Selected date: " + date);
    }

    private String getMonthName(int month) {
        Logs.info("Seleccionando el mes");
        return new DateFormatSymbols(new Locale("es")).getMonths()[month - 1];
    }

    public void setPaymentDate(String date) {
        Logs.info("Asignando la fecha de pago");
        selectDate(paymentDateField, date);
    }

    public void setBillingDate(String date) {
        try {
            if (!isBillingDateFieldVisible()) {
                Logs.info("El campo de fecha de facturación no está visible. Se omite asignación.");
                return;
            }

            Logs.info("Asignando la fecha de facturación: " + date);

            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            WebElement anchor = getDriver().findElement(By.id("service_billing_bank_account_id")); // sigue siendo tu referencia
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", anchor);

            selectDate(billingDateField, date);

            Logs.info("Fecha de facturación asignada correctamente: " + date);

        } catch (Exception e) {
            Logs.error("Error al asignar fecha de facturación: " + e.getMessage());
            utilities.FileManager.getScreenshot("billing_date_error");
        }
    }

    public void verifyMessageErrorRut(String textError){
        Logs.info("Verificando mensaje de error en el campo de RUT");

        By messageErrorRut = By.id("supplier-rut-warning");

        // Espera explícita hasta 10 segundos a que el texto del mensaje no esté vacío
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement errorLabel = wait.until(driver -> {
            WebElement el = driver.findElement(messageErrorRut);
            if (el.isDisplayed() && !el.getText().trim().isEmpty()) {
                return el;
            }
            return null;
        });

        String actualText = errorLabel.getText().trim();
        Logs.info("Texto del mensaje de error detectado: '" + actualText + "'");

        Assertions.assertEquals(textError, actualText, "El texto del error no coincide");
    }

    public void saveExpense() {
        Logs.info("Guardando información de egreso");
        try {
            WebElement saveBtn = findClickable(saveButton);
            scrollToElement(saveBtn);
            highlightElement(saveBtn, "red");
            saveBtn.click();

            WebDriverWait shortWait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            By rutRegisteredPopup = By.xpath("//h5[text()='RUT ya registrado']");
            By cancelBtn = By.xpath("//*[@class='btn btn-default btn-block modal-btn']");

            try {
                WebElement popup = shortWait.until(ExpectedConditions.visibilityOfElementLocated(rutRegisteredPopup));
                Logs.info("Popup de RUT ya registrado detectado");
                WebElement cancelButton = popup.findElement(cancelBtn);
                cancelButton.click();
                Logs.info("Se dio click en 'Cancelar' del popup");
            } catch (Exception e) {
                Logs.info("No apareció popup de RUT, continuando normalmente");
            }

            WebDriverWait waitDisappear = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(newExpensePage));

            Logs.info("Egreso guardado exitosamente");

        } catch (Exception e) {
            Logs.error("Error al guardar: " + e.getMessage());
            utilities.FileManager.getScreenshot("save_expense_error");
            throw new RuntimeException("Error al guardar el egreso", e);
        }
    }
}
