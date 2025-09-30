package utilities;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private static final String screenshotPath = "src/test/resources/screenshots";
    private static final String pageSourcePath = "src/test/resources/pageStructure";
    //private static final Logger log = LoggerFactory.getLogger(FileManager.class);

    public static void getScreenshot(String screenshotName) {
        Logs.debug("Tomando Screenshot");

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get())
                .getScreenshotAs(OutputType.FILE);

        final var path = String.format("%s%s.png", screenshotPath, screenshotName);

        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Error tomar screenshot: %s",
                    ioException.getLocalizedMessage());
        }
    }

    public static void getPageSource(String fileName) {
        Logs.debug("Tomando page source");

        final var path = String.format("%s/%s.html", pageSourcePath, fileName);

        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres si es que no existen");
            if (file.getParentFile().mkdir()) {
                final var fileWriter = new FileWriter(file);
                final var pageSource = WebDriverProvider.get().getPageSource();
                assert pageSource != null;
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }
        } catch (IOException ioException) {
            Logs.error("Error tomar page source: %s", ioException.getLocalizedMessage());
        }
    }

    public static void deletePreviousEvidence() {
        try {
            Logs.debug("Borrando las carpetas de evidencias");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageSourcePath));
        } catch (IOException ioException) {
            Logs.error("Error al borrar la evidencia anterior: %s",
                    ioException.getLocalizedMessage());

        }
    }

    public static void attachScreenshot(Scenario scenario) {
        final var screenshotFile = ((TakesScreenshot) WebDriverProvider
                .get()).getScreenshotAs(OutputType.BYTES);

        scenario.attach(
                screenshotFile,
                "image/png",
                scenario.getName()

        );
    }

    public static void attachPageSource(Scenario scenario) {
        final var pageSource = WebDriverProvider.get().getPageSource();
        final var parsedPageSource = Jsoup.parse(pageSource).toString();
        scenario.attach(
                parsedPageSource,
                "text/plain",
                scenario.getName()
        );
    }

}

