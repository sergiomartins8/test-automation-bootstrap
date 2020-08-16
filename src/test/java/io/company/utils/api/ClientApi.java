package io.company.utils.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

/**
 * Abstract class to be extended by other API model classes.
 * <br>
 * Provides the singleton http client and other utility constants.
 * Also, contains the implementation of a generic response handler in order to avoid code duplication and promote re-usability.
 */
public abstract class ClientApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientApi.class);

    protected static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    protected static final MediaType JSON = MediaType.parse("application/json");
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected static final String HEADER_AUTHORIZATION = "authorization";

    /**
     * Generic response handler.
     * Handles responses of any http requests.
     *
     * @param request object with the request to be performed
     * @param clazz   object to be returned
     * @param <T>     class type
     * @return concrete object
     */
    public static <T> T responseHandler(Request request, Class<T> clazz) {
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                LOGGER.error("Failed request with response: {}", response);
                return null;
            }
            JsonNode node = OBJECT_MAPPER.readTree(requireNonNull(response.body()).string());
            return OBJECT_MAPPER.treeToValue(node, clazz);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            OK_HTTP_CLIENT.connectionPool().evictAll();
        }
        return null;
    }
}
