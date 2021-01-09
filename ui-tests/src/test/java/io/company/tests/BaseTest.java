package io.company.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.company.utils.api.auth.AuthApi;
import io.company.utils.api.auth.model.Authentication;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

/**
 * Base abstraction for concrete types of tests.
 * Holds the implementation of methods to be reusable across tests.
 * <br>
 * Example: login methods, cookies, username and password credentials
 */
public abstract class BaseTest extends Base {

    /**
     * This method allows to bypass the login process, injecting the necessary tokens in the browser local storage or cookies,
     * adapt accordingly.
     * <p>
     * The login bypass — A better approach to automated regression testing
     * https://medium.com/@sergiomartins8/the-login-bypass-a-better-approach-to-automated-regression-testing-f3c23f32d7b8
     * </p>
     * <p>
     * <b>USAGE EXAMPLE:</b>
     * ProfilePage profilePage = bypassLogin(User.EMAIL, User.PASSWORD, Urls.PROFILE, ProfilePage.class);
     * </p>
     *
     * @param email    user email
     * @param password user password
     * @param url      page url
     * @param clazz    page object
     * @return page object that represents the current page
     */
    public <T> T bypassLogin(String email, String password, String url, Class<T> clazz) {
        // Collaborate with your team in order to find out what you need to implement the code below.
        // This should authenticate the user and return his authentication tokens.
        Authentication authentication = AuthApi.getAuthentication(email, password);

        // Make sure you have the browser opened before setting cookies, otherwise you'll get an exception.
        if (!WebDriverRunner.hasWebDriverStarted()) {
            open(url);
        }

        setAuthenticationLocalStorage(authentication);
        return open(url, clazz);
    }

    /**
     * Set authentication tokens on browser local storage.
     *
     * @param authentication authentication object containing tokens
     */
    private void setAuthenticationLocalStorage(Authentication authentication) {
        localStorage().setItem("accessToken", authentication.getAccessToken());
        localStorage().setItem("refreshToken", authentication.getRefreshToken());
        logger().info("Authentication tokens are set");
    }
}
