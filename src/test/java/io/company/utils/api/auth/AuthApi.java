package io.company.utils.api.auth;

import io.company.utils.api.ClientApi;
import io.company.utils.api.auth.model.Authentication;

/**
 * Example of how to model your authentication API to get the desired tokens to use other APIs.
 * <br>
 * Customize and model this API according to your needs.
 */
public class AuthApi extends ClientApi {

    /**
     * Used to get authentication tokens in order to perform authorized requests.
     *
     * @param email    user email
     * @param password user password
     * @return {@link Authentication} object
     */
    public static Authentication getAuthentication(String email, String password) {
        // 1. get authentication tokens

        // your code here

        // 2. build your authentication object
        Authentication authentication = new Authentication();
        authentication.setAccessToken("YourAccessToken");
        authentication.setRefreshToken("YourRefreshToken");

        // 3. return authentication object
        return authentication;
    }
}
