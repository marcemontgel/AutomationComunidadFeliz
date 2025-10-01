package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.ExpensePage;
import utilities.WebDriverProvider;

public class ExpenseStepDefinition {

    private WebDriver driver;
    private ExpensePage expensePage;

    public ExpenseStepDefinition(){
        this.driver = WebDriverProvider.get();
        this.expensePage = new ExpensePage(driver);
    }

    @Given("El usuario ingresa al menú de Egresos")
    public void goToExpenseMenu() {
        expensePage.waitPageToLoad();
    }

    @When("El usuario ingresa a Nuevo Egreso")
    public void goToCreateExpense() {
        expensePage.clickNewExpense();
    }

    @When("El usuario completa el formulario de egreso con categoría {string}")
    public void selectCategory(String category) {
        expensePage.selectCategory(category);
    }

    @And("El usuario ingresa descripción {string} y monto {string}")
    public void fillDescription(String desc, String amount) {
        expensePage.fillFormProviderTest(desc, amount);
    }

    @And("El usuario selecciona el proveedor {string}")
    public void selectProvider(String provider) {
        expensePage.selectProvider(provider);
    }

    @And("El usuario ingresa información de nombre {string} rut {string} y subcuenta {string}")
    public void fillFormProviderOther(String name, String rut, String acount) {
        expensePage.fillFormProviderOther(name, rut, acount);
    }

    @And("El usuario ingresa fecha de pago {string}")
    public void setPaymentDate(String date) {
       expensePage.setPaymentDate(date);
    }

    @And("El usuario ingresa fecha de facturación {string}")
    public void setBillingDate(String date) {
        expensePage.setBillingDate(date);
    }

    @And("El usuario ingresa el número de cuotas {string}")
    public void setNumFee(String num) {
        expensePage.setNumFee(num);
    }

    @Then("El usuario guarda los cambios")
    public void saveSettings() {
        expensePage.saveExpense();
    }
}
