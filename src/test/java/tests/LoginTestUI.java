package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTestUI extends TestBaseApi {

    @Test
    void loginWithUiTest() {
        open("/login");
        $("#Email").val("qaguru@qa.guru");
        $("#Password").val("qaguru@qa.guru1").pressEnter();
        $(".account").shouldHave(text("qaguru@qa.guru"));
    }
}
