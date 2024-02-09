import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class FirstTest extends BaseTest{

    @Test
    @DisplayName("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @Description("Авиа - Бронь и отмена - В одну сторону - 1Взр+1Реб+1Млд - С местами")
    @TmsLink("WITZAQ")
    @Owner("kira")
//    @EnvironmentType({DEVELOPMENT})
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
    public void bookingTest() throws Exception {
        Allure.step("test");
        ope();
        openURL("https://ya2.ru/");
        ope();
    }

    @Step("Ifu1")
    private void openURL(String url) {
        open(url);
        saveScreenshot("AAAAA");
        saveHTML();
        saveText("тест прошел?", "вроде да");
    }

    @Step ("f")
    private void ope() {
        System.out.println(2 + 2);
    }
}
