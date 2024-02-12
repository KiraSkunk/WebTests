import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.GZIPOutputStream;

import static com.codeborne.selenide.Selenide.$;


abstract public class BaseTest {

    public static void main(final String[] args) throws Exception {

    }

    public void setUp() {
//        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
//        Configuration.browserVersion="121.0.6167.161";
//        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;

//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @Attachment(type = "text/html", value = "html.outerHTML")
    public String saveHTML() {
        return $("html").getAttribute("outerHTML");
    }

    @Attachment(type = "text/plain", value = "{0}")
    public static String saveText(final String name, final String text) {
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

    @Attachment(type = "image/png", value = "{0}")
    public byte[] saveScreenshot(final String screenshotDescription) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
