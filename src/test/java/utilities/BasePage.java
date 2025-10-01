package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    private final static int defaultTimeOut = 60;
    private final int timeOut;

    public BasePage(int timeOut) {
        this.timeOut = timeOut;
    }

    public BasePage() {
        this(defaultTimeOut);
    }

    protected WebDriver getDriver() {
        return WebDriverProvider.get();
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(this.timeOut));
    }

    protected WebDriverWait getWait(int customTimeOut) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(customTimeOut));
    }

    protected void waitPage(By locator, String pageName) {
        Logs.info("Esperando que la pagina %s cargue", pageName);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        Logs.info("%s ha cargado satisfactoriamente", pageName);
    }

    // Metodo básico - sin espera (manteniendo compatibilidad)
    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

    // Nuevos métodos con esperas
    protected WebElement findWithWait(By locator) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement findClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected List<WebElement> findAll(By locator) {
        return getDriver().findElements(locator);
    }

    protected List<WebElement> findAllWithWait(By locator) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> findAllVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    // Método para verificar que un elemento no está presente
    protected boolean isElementNotPresent(By locator) {
        return getWait(5).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Método para verificar que un elemento está presente
    protected boolean isElementPresent(By locator) {
        try {
            findWithWait(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // En BasePage, agrega estos métodos:
    protected void logAction(String action) {
        Logs.info("Action: " + action);
    }

    protected void highlightElement(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].style.border='2px solid yellow'", element);
            Thread.sleep(100);
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].style.border=''", element);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    protected void scrollToElement(WebElement element) {
        try {
            Logs.info("Haciendo scroll al elemento...");
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
                    element
            );
            Thread.sleep(500); // tiempo corto, solo para ver efecto
            Logs.info("Scroll completado");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Scroll interrumpido", e);
        } catch (Exception e) {
            Logs.warning("Error en scroll suave, intentando scroll simple");
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
        }
    }

    protected void highlightElement(WebElement element, String color) {
        try {
            String originalStyle = element.getAttribute("style");
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].style.border='3px solid " + color + "'; arguments[0].style.backgroundColor='yellow';",
                    element
            );
            Thread.sleep(300);
            ((JavascriptExecutor) getDriver()).executeScript(
                    "arguments[0].style.border='" + (originalStyle != null ? originalStyle : "") + "';",
                    element
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Logs.debug("No se pudo resaltar elemento: " + e.getMessage());
        }
    }
    public abstract void waitPageToLoad();
    public abstract void verifyPage();
}