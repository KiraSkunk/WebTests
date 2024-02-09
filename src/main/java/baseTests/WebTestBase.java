package baseTests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.invoke.MethodHandles.lookup;

public abstract class WebTestBase {

    public static final boolean DOCKER_ENABLED =
            !"false".equals(System.getProperty("selenium.docker.enabled"));

    private final static Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
    private final static String selenideProperties = "selenide.properties";


    @BeforeAll
    static void setupClass() throws IOException {
        /*
         * Add AllureSelenide listener
         */
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        /*
         * Load selenide.properties file in resources
         */
        Properties props = new Properties();
        InputStream inputStream = WebTestBase.class
                .getClassLoader()
                .getResourceAsStream(selenideProperties);
        props.load(inputStream);

        if (!props.isEmpty()) {
            for (Object propObj : props.keySet()) {
                String prop = String.valueOf(propObj);

                if (!System.getProperties().containsKey(prop)) {
                    System.setProperty(prop, props.getProperty(prop));
                }
            }
        }

        logger.info("Loading selenide properties as {}", selenideProperties);
    }

    @AfterAll
    static void cleanupClass() {
        /*
         * Generate environment properties to Allure report
         * */
        ImmutableMap.Builder<String, String> environmentBuilder = ImmutableMap.builder();
        /*
         * From selenide.properties
         * */
        System.getProperties().forEach((key, val) -> {
            if (key.toString().startsWith("selenide.")) {
                environmentBuilder.put(key.toString(), val.toString());
            }
        });
        /*
         * From allure.properties
         * */
        System.getProperties().forEach((key, val) -> {
            if (key.toString().startsWith("allure.")) {
                environmentBuilder.put(key.toString(), val.toString());
            }
        });
        AllureEnvironmentWriter.allureEnvironmentWriter(
                environmentBuilder.build(),
                System.getProperty("allure.results.directory") + "/"
        );

        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
        SelenideLogger.removeListener("AllureSelenide");
    }


    @AfterEach
    protected void cleanupTest() {
        WebDriverRunner.closeWindow();
    }

//    private String collectBrowserLogs() {
//        StringBuilder result = new StringBuilder();
//        Logs logs = driver.manage().logs();
//        List<LogEntry> logEntries = logs.get(LogType.BROWSER).getAll();
//        for (LogEntry le : logEntries) {
//            result.append(
//                    String.format("%s [%s] %s\n", le.getLevel().getName(),
//                            FORMATTER_TIMESTAMP.format(LocalDateTime.ofInstant(
//                                    Instant.ofEpochMilli(le.getTimestamp()),
//                                    ZoneId.systemDefault())),
//                            le.getMessage()));
//        }
//        return result.toString();
//    }

//    @Attachment(type = "image/png", value = "{0}")
//    private byte[] saveScreenshot(final String screenshotDescription) {
//        return driver.getScreenshotAs(OutputType.BYTES);
//    }

    @Attachment(type = "text/html", value = "html.outerHTML")
    private String saveHTML() {
        return $("html").getAttribute("outerHTML");
    }

    @Attachment(type = "text/plain", value = "{0}")
    private String saveText(final String name, final String text) {
        return text;
    }

    @Attachment(type = "text/html", value = "Описание ошибки")
    private String saveErrorHTML() {
        return $("html .sln-error-content").getAttribute("outerHTML");
    }

    private ByteBuffer compressString(final String text) {
        byte[] compressed = new byte[10240];
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(text.getBytes());
            gzip.close();
            compressed = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ByteBuffer.wrap(compressed);
    }

    /*
     * you cannot pass lists or arrays to parameters,
     * because for the title of attachments
     * implicitly each element of the array
     * parse to String
     * (if it is an array of bytes
     * then Java dies due to lack of memory)
     */
    @Attachment(type = "application/gzip", value = "{0}")
    private byte[] saveGzip(final String name, final ByteBuffer text) {
        return text.array();
    }
}
