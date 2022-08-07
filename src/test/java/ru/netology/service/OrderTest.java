package ru.netology.service;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderTest {

    @Test
    void notShouldSubmitRequestWrongName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Vasilievich Vasiliy");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notShouldSubmitRequestWrongPhone() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Васильевич Василий");
        form.$("[data-test-id=phone] input").setValue("+7927000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notShouldSubmitRequestNoClickCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Васильевич Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[class=button__text]").click();
        $("[class=checkbox__text]").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void notShouldSubmitRequestWrongPhoneWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Васильевич Василий");
        form.$("[data-test-id=phone] input").setValue("79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notShouldSubmitRequestWrongNameWithSymbol() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("!Васильевич 1Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void notShouldSubmitRequestWrongNameWithEng() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("A A");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ShouldSubmitRequestNameWithDash() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Васильевич-Петров Василий");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=order-success").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void notShouldSubmitRequestWrongPhoneWithSymbol() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Васильевич Василий");
        form.$("[data-test-id=phone] input").setValue("+7927000000A");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void notShouldSubmitRequestAllStringWrong() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Vasilievich Vasiliy");
        form.$("[data-test-id=phone] input").setValue("+7927000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void notShouldSubmitRequestAllWrong() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=App_appContainer__3jRx1]");
        form.$("[data-test-id=name] input").setValue("Vasilievich Vasiliy");
        form.$("[data-test-id=phone] input").setValue("+7927000000");
        form.$("[class=button__text]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

}