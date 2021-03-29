package api;

import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;
public class Auth {
    public Map<String, String> login(String login, String password) {
        return
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
    }
}
