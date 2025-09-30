package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class LoginPage extends BasePage {
    private final By userNameInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[contains(text(),'Iniciar')]");

    @Override
    public void waitPageToLoad() {
        waitPage(userNameInput, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando Login de la Página");

        Assertions.assertAll(
                () -> Assertions.assertTrue(find(userNameInput).isDisplayed()),
                () -> Assertions.assertTrue(find(passwordInput).isDisplayed()),
                () -> Assertions.assertTrue(find(loginButton).isDisplayed())
        );
    }

    public void fillLogin(String userName, String password) {
        Logs.info("Ingresar Información");
        find(userNameInput).sendKeys(userName);

        Logs.info("Ingresar Password");
        find(passwordInput).sendKeys(password);

        Logs.info("Haciendo click en el botón de Login");
        find(loginButton).click();
    }
}
