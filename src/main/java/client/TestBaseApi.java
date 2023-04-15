package client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TestBaseApi {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    public static final String AUTH_USER_PATH = "/api/auth/user";
    public static final String AUTH_LOGIN_PATH = "/api/auth/login";
    public static final String AUTH_REGISTER_PATH = "/api/auth/register";

    public static RequestSpecification getSpecificationWithJson() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON).build();
    }

    public static RequestSpecification getSpecificationWithToken(String bearerToken) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .build().header("Authorization", bearerToken);
    }
}
