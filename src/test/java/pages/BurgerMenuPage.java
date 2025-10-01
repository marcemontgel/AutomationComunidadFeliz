package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BasePage;

public class BurgerMenuPage extends BasePage {
    private final By searchIcon = By.cssSelector("div.new-sidebar-search-icon");
    // Locator robusto: cualquier input dentro de la sección de búsqueda
    private final By searchInput = By.cssSelector("div.new-sidebar-search-input input");

    @Override public void waitPageToLoad() {
        waitPage(searchIcon, "Burger Menu");
    }

    @Override public void verifyPage() {
        if (!isElementPresent(searchIcon)) {
        throw new IllegalStateException("El menú lateral (burger menu) no está visible.");
        }
    }

    public void clickSearchIcon() {
        findClickable(searchIcon).click();
    }

    public void typeInSearch(String text) {
        WebElement input = findVisible(searchInput);
        input.clear(); input.sendKeys(text);
    }

    public void selectOption(String optionText) {
        By option = By.xpath("//span[@class='option-text' and normalize-space()='"
                + optionText + "']");
        findClickable(option).click();
    }

    public boolean isOptionPresent(String optionText) {
        By option = By.xpath("//span[@class='option-text' and contains(normalize-space(),'"
                + optionText + "')]");
        return isElementPresent(option);
    }
}
