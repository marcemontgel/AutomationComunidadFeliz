package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    private final By feeComboBox = By.id("service_billing_number_of_fees");
    private final By saveButton = By.xpath("//button[contains(text(),'Guardar') or contains(text(),'Save')]");

    private WebDriver driver;

    public ExpensePage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void waitPageToLoad() {
        waitPage(newExpenseButton, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        waitPage(newExpenseButton, "New Expense");
    }

    public void clickNewExpense(){
        findClickable(newExpenseButton).click();
        find(newExpensePage);
    }

    public void selectCategory(String category) {
        WebElement dropdown = findClickable(categoryDropdown);
        dropdown.click();

        By specificCategory = By.xpath(String.format(
                "//*[@id='category_name-dropdown-menu']//div[@class='option-name' and contains(.,'%s')]",
                category
        ));

        WebElement categoryElement = findClickable(specificCategory);
        categoryElement.click();

        logAction("Selected category: " + category);
    }

    public void fillFormProviderTest(String desc, String amount){
        Logs.info("Ingresar Información");
        find(descriptionInput).sendKeys(desc);

        Logs.info("Ingresar monto");
        find(amountInput).sendKeys(amount);
    }

    public void selectProvider(String provider){
        WebElement dropdown = findClickable(typeProviderDropdown);
        dropdown.click();

        By specificProvider = By.xpath(String.format(
                "//*[@id='supplier_id-dropdown-menu']//div[@class='option-name' and contains(.,'%s')]",
                provider
        ));

        WebElement providerElement = findClickable(specificProvider);
        providerElement.click();

        logAction("Select provider: " + provider);
    }

    public void fillFormProviderOther(String name, String rut, String acount){
        Logs.info("Ingresar nombre");
        find(nameInput).sendKeys(name);

        Logs.info("Ingresar rut");
        find(rutInput).sendKeys(rut);

        Logs.info("Seleccionar subcuenta");
        selectAcount(acount);
    }

    public void selectAcount(String acount){
        WebElement dropdown = findClickable(countDropdown);
        dropdown.click();

        By specificAcount = By.xpath(String.format(
                "//*[@id='supplier_accounting_subaccount_id-dropdown-menu']//*[@class='option-name' and contains(.,'%s') ]",
                acount
        ));

        WebElement acountElement = findClickable(specificAcount);
        acountElement.click();

        logAction("Select acount: " + acount);
    }

    public void setNumFee(String num) {
        Logs.info("Seleccionar número de cuotas: " + num);

        WebElement dropdown = find(feeComboBox);
        Select select = new Select(dropdown);

        // IMPORTANTE: Según tu imagen, los valores son "0", "2", "3", "4", etc.
        // "0" = "Sin cuotas", "2" = "2 cuotas", etc.
        select.selectByValue(num);

        Logs.info("Cuota seleccionada exitosamente: " + num);
    }

   /*****************/

// ---------------- LOCATORS (reemplaza los que tengas similares) ----------------
   private final By paymentDateField = By.xpath("(//*[@id='date-selector-dropdown'])[1]");
   private final By billingDateField = By.xpath("(//*[@id='date-selector-dropdown'])[2]");


      // ===================== MÉTODOS PÚBLICOS =====================
    public void setPaymentDate(String date) {
        String[] parts = date.split("/"); // dd/MM/yyyy
        String day = parts[0];
        String month = getMonthName(Integer.parseInt(parts[1]));
        String year = parts[2];

        selectPaymentDate(day, month, year);
    }

    public void setBillingDate(String date) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement anchor = driver.findElement(By.id("service_billing_bank_account_id"));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", anchor);

        String[] parts = date.split("/"); // dd/MM/yyyy
        String day = parts[0];
        String month = getMonthName(Integer.parseInt(parts[1]));
        String year = parts[2];

        selectBillingDate(day, month, year);
    }

    // ===================== MÉTODOS PRIVADOS =====================
    private void selectPaymentDate(String day, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrir datepicker
        WebElement opener = driver.findElement(paymentDateField);
        opener.click();

        // Seleccionar día (ajusta el locator según el HTML del calendario de pago)
        By dayLocator = By.xpath("//*[@data-action='click->date-selector#changeValue' and text()='" + Integer.parseInt(day) + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();
    }

    private void selectBillingDate(String day, String month, String year) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abrir datepicker
        WebElement opener = driver.findElement(billingDateField);
        opener.click();

        // Seleccionar día (ajusta el locator según el HTML del calendario de facturación)
        By dayLocator = By.xpath("//*[@data-action='click->date-selector#changeValue' and text()='" + Integer.parseInt(day) + "']");
        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dayElement.click();
    }

    // ===================== UTILIDAD =====================
    private String getMonthName(int month) {
        return new DateFormatSymbols(new Locale("es")).getMonths()[month - 1];
    }

   /**************/



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


    public void saveExpense() {
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
