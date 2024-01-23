package baseTests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.step;


public class myTest {

    @BeforeEach
    public void logging() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @DisplayName("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @Description("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @TmsLink("WITZAQ")
    @Owner("kirillova")
//    @EnvironmentType({BILETIX})
    public void bookingWithPlacesTest() throws Exception {
        step("test");
        ope();
        openURL("https://ya.ru/");
        ope();
    }

    @Test
    @DisplayName("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @Description("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @TmsLink("WITZAQ")
    @Owner("kirillova")
//    @EnvironmentType({BILETIX})
    public void bookingTest() throws Exception {
        step("test");
        ope();
        openURL("https://ya2.ru/");
        ope();
    }

    @Step
    private void openURL(String url) {
//        open("https://biletix.ru/");
        open(url);
        screenshot("AAAAA");
    }

    @Step
    private void ope() {
        System.out.println(2 + 2);
    }
}
