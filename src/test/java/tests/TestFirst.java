package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class TestFirst {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void Test1() {
        open("/text-box");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("[id=userName]").setValue(StaticValues.firstName);
        $("[id=userEmail]").setValue(StaticValues.email);
        $("[id=currentAddress]").setValue(StaticValues.currentAddress);
        $("[id=permanentAddress]").setValue("testPermAddress");

        $("[id=submit]").click();

        $("[id=output]").shouldHave(text(StaticValues.firstName), text(StaticValues.email));

         System.out.println("Test passed");
    }
}
