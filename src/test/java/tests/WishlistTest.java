package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static restAssured.CustomSpec.spec;

import static org.hamcrest.Matchers.is;
import static utils.FileUtils.readStringFromFile;

public class WishlistTest extends TestBase {

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
}
