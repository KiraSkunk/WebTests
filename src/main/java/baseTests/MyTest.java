package baseTests;

import hello.junit.EnvironmentType;
import hello.junit.Functionality;
import hello.junit.WebsiteFunctionality;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;
import static hello.junit.TestEnvironmentType.DEVELOPMENT;
import static hello.junit.TestEnvironmentType.STAGING;

@Functionality(WebsiteFunctionality.NEW_TESTS)

public class MyTest {

    @Test
    @DisplayName("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @Description("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @TmsLink("WITZAQ")
    @Owner("kirillova")
    @EnvironmentType({DEVELOPMENT})
    public void bookingWithPlacesTest() throws Exception {
        Allure.step("test");
        ope();
        openURL("https://ya.ru/");
        ope();
    }

    @Test
    @DisplayName("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @Description("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @TmsLink("WITZAQ")
    @Owner("kirillova")
    @EnvironmentType({STAGING})
    public void bookingTest() throws Exception {
        Allure.step("test");
        ope();
        openURL("https://ya2.ru/");
        ope();
    }

    @Step("Ifu1")
    private void openURL(String url) {
        open(url);
        screenshot("AAAAA");
    }

    @Step ("f")
    private void ope() {
        System.out.println(2 + 2);
    }
}
