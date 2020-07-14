import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryFormTest {
    @Test
    void CardRequestSuccessTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
            form.$("[data-test-id=date] input").setValue("31.07.2020");
            form.$("[data-test-id=name] input").setValue("Козырева Ксения");
            form.$("[data-test-id=phone] input").setValue("+79215555555");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
        }
    }

    @Test
    void PathWithCityAndDateChoosingTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Са");
            $$(".menu-item__control").find(exactText("Санкт-Петербург")).click();
            form.$(".input__icon").click();
            $$(".calendar__day").find(exactText("20")).click();
            form.$("[data-test-id=name] input").setValue("Козырева Ксения");
            form.$("[data-test-id=phone] input").setValue("+79215555555");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(withText("Встреча успешно забронирована на")).waitUntil(visible, 15000);
        }
    }

    @Test
    void EmptyCityTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=date] input").setValue("31.07.2020");
            form.$("[data-test-id=name] input").setValue("Премудрая Василиса");
            form.$("[data-test-id=phone] input").setValue("+79215555555");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=city]").shouldHave(exactText("Поле обязательно для заполнения"));
        }
    }

    @Test
    void EmptyNameTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Петропавловск-Камчатский");
            form.$("[data-test-id=date] input").setValue("25.07.2020");
            form.$("[data-test-id=phone] input").setValue("+79215555555");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=name]").shouldHave(exactText("Фамилия и имя Поле обязательно для заполнения"));
        }
    }

    @Test
    void EmptyPhoneTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Санкт-Петербург");
            form.$("[data-test-id=date] input").setValue("31.07.2020");
            form.$("[data-test-id=name] input").setValue("Прекрасная Елена");
            form.$("[data-test-id=agreement]").click();
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=phone]").shouldHave(exactText("Мобильный телефон Поле обязательно для заполнения"));
        }
    }

    @Test
    void EmptyСheckBoxTest() {
        {
            open("http://localhost:9999");
            SelenideElement form = $(".form");
            form.$("[data-test-id=city] input").setValue("Уфа");
            form.$("[data-test-id=date] input").setValue("16.07.2020");
            form.$("[data-test-id=name] input").setValue("Ли Юн");
            form.$("[data-test-id=phone] input").setValue("+79215555555");
            form.$$("[role=button]").find(exactText("Забронировать")).click();
            $(".input_invalid[data-test-id=agreement]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
        }
    }
}
