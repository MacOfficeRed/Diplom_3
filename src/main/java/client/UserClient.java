package client;

import io.qameta.allure.Step;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient extends TestBaseApi {
    private String getUserToken(User user) {
        return given()
                .spec(getSpecificationWithJson())
                .body(user)
                .when()
                .post(AUTH_LOGIN_PATH)
                .jsonPath()
                .getString("accessToken");
    }

    @Step("Create a user")
    public void createUserUsingApi(User user) {
        given()
                .spec(getSpecificationWithJson())
                .body(user)
                .when()
                .post(AUTH_REGISTER_PATH);
    }

    @Step("Delete a user")
    public void deleteUserUsingApi(User user) {
        String accessToken = getUserToken(user);
        // if the user is not created, then we don't try to delete it
        // for the checkErrorMessageWhenPasswordIsInvalid test
        if (accessToken != null) {
            given()
                    .spec(getSpecificationWithToken(accessToken))
                    .when()
                    .delete(AUTH_USER_PATH);
        }
    }
}
