package tests;

import pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationTestsWithPageObjects extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void registrationTestSuccessful() {
        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Smith")
                .setEmail("Smith@gmail.com")
                .setGender("Other")
                .setUserNumber("7984562113")
                .setDateOfBirth("02", "July", "1999");

        $("#subjectsInput").setValue("Arts").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("upload1.jpeg");
        ;
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Panipat")).click();
        $("#submit").click();
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("John"), text("Smith"),
                text("Smith@gmail.com"), text("7984562113"));
        registrationPage.checkResult("Student Name", "John Smith")
                .checkResult("Student Email", "Smith@gmail.com");
    }

    @Test
    void registrationTestWithMinParameters () {
        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Smith")
                .setGender("Other")
                .setUserNumber("7984562113");
        $("#submit").click();
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("John"), text("Smith"),text("7984562113"));
        registrationPage.checkResult("Student Name", "John Smith");
    }

    @Test
    void registrationTestWithoutLastName () {
        registrationPage.openPage()
                .setFirstName("John")
                .setGender("Other")
                .setUserNumber("7984562113");
        $("#submit").click();
        registrationPage.checkNegativeResult("Practice Form");
    }
}