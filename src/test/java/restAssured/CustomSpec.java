package restAssured;

import api.Auth;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static tests.Templates.filters;
import static utils.FileUtils.readStringFromFile;

public class CustomSpec {

    Map<String, String> cookies = new Auth().login("qaguru@qa.guru", "qaguru@qa.guru1");
    String bodyText = readStringFromFile("./src/test/resources/body.txt");

    private final RequestSpecification request = given()
            .contentType(ContentType.URLENC)
            .cookies(cookies)
            .body(bodyText)
            .filter(filters().customTemplates())
            .log().uri();

    public static CustomSpec spec() {
        return new CustomSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}

