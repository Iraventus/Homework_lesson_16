package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class LoginTest extends TestBase {

    @Test
    @DisplayName("Successful UI login")
    void loginWithUiTest() {
        open("/login");
        $("#Email").val("qaguru@qa.guru");
        $("#Password").val("qaguru@qa.guru1").pressEnter();
        $(".account").shouldHave(text("qaguru@qa.guru"));
    }

    @Test
    @DisplayName("Successful API login")
    void loginWithCookieTest() {
        Map<String, String> cookiesMap =
        given()
                .contentType(ContentType.URLENC)
                .formParam("Email", "qaguru@qa.guru")
                .formParam("Password", "qaguru@qa.guru1")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .log().body()
                .extract().cookies();

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesMap.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesMap.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookiesMap.get("ARRAffinity")));

        open("");
        $(".account").shouldHave(text("qaguru@qa.guru"));
    }
}
