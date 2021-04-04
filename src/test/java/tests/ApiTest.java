package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static restAssured.CustomSpec.spec;

import static org.hamcrest.Matchers.is;
import static utils.FileUtils.readStringFromFile;

public class ApiTest extends TestBaseApi {

    @Test
    void addItemToWishlist() {
        String messageText = readStringFromFile("./src/test/resources/message_text.txt");

        Response response =
                spec().request()
                        .when()
                        .post("/addproducttocart/details/34/2")
                        .then()
                        .statusCode(200)
                        .log().body()
                        .body("success", is(true))
                        .body("message", is(messageText))
                        .extract().response();
        System.out.println(response);
    }

    @Test
    void loginWithCookieTest() {
       // Map<String, String> cookiesMap =
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

      /*  open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookiesMap.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookiesMap.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookiesMap.get("ARRAffinity")));

        open("http://demowebshop.tricentis.com");
        $(".account").shouldHave(text("qaguru@qa.guru"));*/
    }


}
