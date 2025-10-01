package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BasePage;
import utilities.Logs;

public class BurgerMenuPage extends BasePage {
    private final By searchIcon = By.cssSelector("div.new-sidebar-search-icon");
    private final By searchInput = By.cssSelector("div.new-sidebar-search-input input");

    @Override public void waitPageToLoad() {
        waitPage(searchIcon, "Burger Menu");
    }

    @Override public void verifyPage() {
        Logs.info("Verificando Menu Lateral este visible");
        if (!isElementPresent(searchIcon)) {
        throw new IllegalStateException("El menú lateral (burger menu) no está visible.");
        }
    }

    public void clickSearchIcon() {
        Logs.info("Dando clic en el icono de búsqueda");
        findClickable(searchIcon).click();
    }

    public void typeInSearch(String text) {
        Logs.info("Limpiando el campo de busqueda e insertando información");
        WebElement input = findVisible(searchInput);
        input.clear();
        input.sendKeys(text);
    }

    public void selectOption(String optionText) {
        Logs.info("Seleccionando opción requerida");
        By option = By.xpath("//span[@class='option-text' and normalize-space()='"
                + optionText + "']"
        );
        findClickable(option).click();
    }

    public boolean isOptionPresent(String optionText) {
        Logs.info("Verificando que la opción este presente");
        By option = By.xpath("//span[@class='option-text' and contains(normalize-space(),'"
                + optionText + "')]"
        );
        return isElementPresent(option);
    }
}
