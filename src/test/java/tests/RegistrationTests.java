package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationTests {
    @BeforeAll
    static void setupEnvironment() {
        Configuration.browserSize = "1800x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void registrationForm () {
        //открыть браузер
        open("/automation-practice-form");

        //обойти рекламу внизу страницы
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        //Основная информация
        $("#firstName").setValue("John");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("Smith@gmail.com");

        //Пол
        $("#genterWrapper").$(byText("Female")).click();

        //Номер телефона
        $("#userNumber").setValue("7984562113");

        //Дата рождени
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText("July")).click();
        $(".react-datepicker__year-select").$(byText("1999")).click();
        $(".react-datepicker__day--002").click();

        //Предметы
        $("#subjectsInput").setValue("Arts").pressEnter();

        //Хобби
        $("#hobbiesWrapper").$(byText("Sports")).click();

        //Изображение
        $("#uploadPicture").uploadFromClasspath("upload1.jpg");

        //Адрес
        $("#currentAddress").scrollTo().shouldBe(visible);
        $("#currentAddress").setValue("Address");

        //Штат и город
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();

        //Отправка формы
        $("#submit").click();

        //Проверка данных в модальном окне
        $(".modal-body").find(byText("Student Name")).closest("tr").shouldHave(text("John Smith"));
        $(".modal-body").find(byText("Student Email")).closest("tr").shouldHave(text("Smith@gmail.com"));
        $(".modal-body").find(byText("Mobile")).closest("tr").shouldHave(text("7984562113"));
        $(".modal-body").find(byText("Date of Birth")).closest("tr").shouldHave(text("02 July,1999"));
        $(".modal-body").find(byText("Subjects")).closest("tr").shouldHave(text("Arts"));
        $(".modal-body").find(byText("Hobbies")).closest("tr").shouldHave(text("Sports"));
        $(".modal-body").find(byText("Picture")).closest("tr").shouldHave(text("Upload1.jpg"));
        $(".modal-body").find(byText("Address")).closest("tr").shouldHave(text("Address"));
        $(".modal-body").find(byText("State and City")).closest("tr").shouldHave(text("Haryana Panipat"));

        //Закрытие модального окна
        $(".modal-footer").scrollTo().shouldBe(visible);
        $("#closeLargeModal").click();
    }
}


