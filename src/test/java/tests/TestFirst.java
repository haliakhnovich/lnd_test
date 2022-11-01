package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TestFirst {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void Test1() {
        String name = "TestName";

        open("/text-box");
        $("[id=userName]").setValue(name);
        $("[id=userEmail]").setValue("testEmail@test.com");
        $("[id=currentAddress]").setValue("testAddress");
        $("[id=permanentAddress]").setValue("testPermAddress");

        $("[id=submit]").click();

        $("[id=output]").shouldHave(text(name), text("testEmail@test.com"));

         System.out.println("passed");
    }
}
