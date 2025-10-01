package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class DashboardPage extends BasePage {

    // Elementos principales del dashboard
    private final By communityName = By.xpath("//*[@id='community_principal_name']/h4");

    @Override
    public void waitPageToLoad() {
        Logs.info("Esperando a que cargue el Dashboard de la Comunidad");
        waitPage(communityName, this.getClass().getSimpleName());
    }

    @Override
    public void verifyPage() {
        Logs.info("Verificando el Dashboard de la Comunidad");

        Assertions.assertAll(
                // Verificar elementos principales del header
                () -> Assertions.assertTrue(findVisible(communityName).isDisplayed(),
                        "El nombre de la comunidad debería mostrarse")
        );
        Logs.info("Dashboard de la Comunidad verificado exitosamente");
    }

    // Métodos adicionales útiles para interactuar con el dashboard
    public void verifyCommunityName(String expectedName) {
        Logs.info("Verificando Nombre de la Comunidad");
        String actualName = findVisible(communityName).getText();
        Assertions.assertTrue(actualName.contains(expectedName),
                "El nombre de la comunidad debería ser: " + expectedName);
    }
}
