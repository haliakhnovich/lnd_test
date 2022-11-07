package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = "3840x2160";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void StudentRegistrationFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("[id=firstName]").setValue(StaticValues.firstName);
        $("[id=lastName]").setValue(StaticValues.lastName);
        $("[id=userEmail]").setValue(StaticValues.email);
        $("#genterWrapper").$(byText(StaticValues.gender)).click();
        $("[id=userNumber]").setValue(StaticValues.mobile);

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(StaticValues.monthOfBirth);
        $(".react-datepicker__year-select").selectOption(StaticValues.yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--0" + StaticValues.dayOfBirth).click();

        $("[id=subjectsInput]").sendKeys(StaticValues.subject);
        $("[id=subjectsInput]").pressEnter();
        $("#hobbiesWrapper").$(byText(StaticValues.hobby)).click();
        $("[id=uploadPicture]").uploadFile(new File("src/resources/pic.png"));
        $("[id=currentAddress]").setValue(StaticValues.currentAddress);
        $("[id=react-select-3-input]").setValue(StaticValues.state).pressEnter();
        $("[id=react-select-4-input]").setValue(StaticValues.city).pressEnter();
        $("[id=submit]").click();

        //Check the output
        $(".table-responsive").shouldHave(
                text(StaticValues.firstName + " " + StaticValues.lastName),
                text(StaticValues.email),
                text(StaticValues.gender),
                text(StaticValues.mobile),
                text(StaticValues.dayOfBirth + " " + StaticValues.monthOfBirth + "," +  StaticValues.yearOfBirth),
                text(StaticValues.subject),
                text(StaticValues.hobby),
                text("pic.png"),
                text(StaticValues.currentAddress),
                text(StaticValues.state + " " + StaticValues.city)
        );

        System.out.println("Student Registration Form. Test passed");
    }

    @Test
    void StudentRegistrationFormRequiredFieldsTest() {

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("[id=firstName]").setValue(StaticValues.firstName);
        $("[id=lastName]").setValue(StaticValues.lastName);
        $("#genterWrapper").$(byText(StaticValues.gender)).click();
        $("[id=userNumber]").setValue(StaticValues.mobile);
        $("[id=submit]").click();

        //Check the output
        //name
        $(By.xpath("//tbody/tr/td[text()=\"Student Name\"]//following::td[1]")).shouldHave(text(StaticValues.firstName), text(StaticValues.lastName));
        //gender
        $(By.xpath("//tbody/tr/td[text()=\"Gender\"]//following::td[1]")).shouldHave(text(StaticValues.gender));
        //mobile
        $(By.xpath("//tbody/tr/td[text()=\"Mobile\"]//following::td[1]")).shouldHave(text(StaticValues.mobile));
        //default DoB
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        Date date = new Date();
        $(By.xpath("//tbody/tr/td[text()=\"Date of Birth\"]//following::td[1]")).shouldHave(text(formatter.format(date)));

        System.out.println("Student Registration Form. Required Fields Test passed");
    }

}
