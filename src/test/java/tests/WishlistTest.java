package tests;

import api.Auth;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static utils.FileUtils.readStringFromFile;

public class WishlistTest extends TestBase {

    @Test
    void addItemToWishlist() {
        Map<String, String> cookies = new Auth().login("qaguru@qa.guru", "qaguru@qa.guru1");
        String bodyText = readStringFromFile("./src/test/resources/body.txt");
        String messageText = readStringFromFile("./src/test/resources/message_text.txt");

        Response response =
                given()
                        .contentType(ContentType.URLENC)
                        .cookies(cookies)
                        .body(bodyText)
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
}
