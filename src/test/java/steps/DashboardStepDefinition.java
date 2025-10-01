package steps;

import io.cucumber.java.en.Then;
import pages.DashboardPage;

public class DashboardStepDefinition {

    private final DashboardPage communityDashboardPage = new DashboardPage();

    @Then("El usuario debería ver el dashboard de la comunidad")
    public void userShouldSeeCommunityDashboard() {
        communityDashboardPage.waitPageToLoad();
    }

    @Then("El usuario verifica que el dashboard de la comunidad se muestre correctamente")
    public void verifyCommunityDashboard() {
        communityDashboardPage.verifyPage();
    }

    @Then("El usuario verifica que el nombre de la comunidad sea {string}")
    public void verifyCommunityName(String expectedName) {
        communityDashboardPage.verifyCommunityName(expectedName);
    }


}
