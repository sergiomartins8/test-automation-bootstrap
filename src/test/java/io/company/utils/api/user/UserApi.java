package io.company.utils.api.user;

import io.company.utils.api.ClientApi;
import io.company.utils.api.auth.model.Authentication;
import io.company.utils.api.user.model.User;
import io.company.utils.constants.Urls;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * Example model of an API to get info from users.
 * Contains methods such as post, get and patch.
 * <br>
 * Customize and model this API according to your needs.
 */
public class UserApi extends ClientApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    /**
     * Method to create user.
     *
     * @param url            api base url
     * @param authentication user authentication
     * @param user           user to be created
     * @return the created user
     */
    public static User post(String url, Authentication authentication, User user) {
        try {
            RequestBody requestBody = RequestBody.create(JSON, OBJECT_MAPPER.writeValueAsString(user));

            Request request = new Request.Builder()
                    .url(url + Urls.Api.USERS)
                    .addHeader(HEADER_AUTHORIZATION, authentication.getAccessToken())
                    .post(requireNonNull(requestBody))
                    .build();

            return responseHandler(request, User.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Method to list all users.
     *
     * @param url            api base url
     * @param authentication user authentication
     * @return the list of users
     */
    public static User[] get(String url, Authentication authentication) {
        Request request = new Request.Builder()
                .url(url + Urls.Api.USERS)
                .addHeader(HEADER_AUTHORIZATION, authentication.getAccessToken())
                .get()
                .build();

        return responseHandler(request, User[].class);
    }

    /**
     * Method to get the details from a single user.
     *
     * @param url            api base url
     * @param authentication user authentication
     * @param id             user id
     * @return the user details
     */
    public static User get(String url, Authentication authentication, int id) {
        Request request = new Request.Builder()
                .url(url + Urls.Api.USERS + id)
                .addHeader(HEADER_AUTHORIZATION, authentication.getAccessToken())
                .get()
                .build();

        return responseHandler(request, User.class);
    }

    /**
     * Method to update user details.
     *
     * @param url            api base url
     * @param authentication user authentication
     * @param user           user details to be updated
     * @return the updated user
     */
    public static User patch(String url, Authentication authentication, User user) {
        try {
            RequestBody requestBody = RequestBody.create(JSON, OBJECT_MAPPER.writeValueAsString(user));

            Request request = new Request.Builder()
                    .url(url + Urls.Api.USERS + user.getId())
                    .addHeader(HEADER_AUTHORIZATION, authentication.getAccessToken())
                    .patch(requireNonNull(requestBody))
                    .build();

            return responseHandler(request, User.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
