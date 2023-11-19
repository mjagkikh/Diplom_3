package pageobjects;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class RestClient {
    protected static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    protected static final String BASE_PATH = "/api";
    private static final String REGISTER = "/auth/register";
    private static final String LOGIN = "/auth/login";
    private static final String USER = "/auth/user";

    public RequestSpecification spec() {
        return given().log().all()
                .contentType(JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }

    public ValidatableResponse createUser(String name, String email, String password) {
        return spec()
                .body(new User(email, password, name))
                .when()
                .post(REGISTER)
                .then().log().all();
    }

    @Step("Send DELETE request to /api/auth/user")
    public ValidatableResponse delete(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .delete(USER)
                .then().log().all();
    }

    public String getAccess(ValidatableResponse response) {
        return  response.extract().path("accessToken");
    }
}