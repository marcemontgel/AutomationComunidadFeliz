package utilities;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class CommonFlows {


    private WebDriver getDriver() {
        return WebDriverProvider.get();
    }

    public void goToLoginPage() {
        Logs.info("Navegando a la url");
        getDriver().get("https://app.comunidadfeliz.com/");

        new LoginPage().waitPageToLoad();
    }

}
