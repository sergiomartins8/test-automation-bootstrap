package utils.mocks.model;

import com.beust.jcommander.internal.Lists;
import com.google.gson.Gson;
import org.mockserver.model.Cookie;
import org.mockserver.model.Header;
import org.mockserver.model.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Concrete definition of a mocked request.
 * <p>
 * A mock request is represented by a path and a mapping request (e.g. GET, POST)
 * Optionally, it also may contain a list of query parameters, a list of cookies, a body and a list of headers
 * <p>
 * Each query parameter is represented by {@link MockQueryStringParameter}
 * <p>
 * Each cookie is represented by {@link MockCookie}
 * <p>
 * Each header is represented by {@link MockHeader}
 * </p>
 */
@SuppressWarnings("unused")
public final class MockRequest {

    /**
     * Mapping request path.
     */
    private String path;

    /**
     * Type of mapping request (e.g. GET, POST).
     */
    private String method;

    /**
     * List of query parameters.
     */
    private List<MockQueryStringParameter> queryStringParameters;

    /**
     * List of cookies.
     */
    private List<MockCookie> cookies;

    /**
     * Request body.
     */
    private Object body;

    /**
     * List of headers.
     */
    private List<MockHeader> headers;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * Returns an empty list if the expectation/mock json file has no query parameters defined.
     *
     * @return List of parameters
     */
    public List<Parameter> getQueryStringParameters() {
        return Optional.ofNullable(queryStringParameters)
                .map(mockQueryStringParameters -> mockQueryStringParameters.stream()
                        .map(mockParameters -> new Parameter(mockParameters.getName(), mockParameters.getValues()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public void setQueryStringParameters(List<MockQueryStringParameter> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    /**
     * Returns an empty list if the expectation/mock json file has no cookies defined.
     *
     * @return List of cookies
     */
    public List<Cookie> getCookies() {
        return Optional.ofNullable(cookies)
                .map(mockCookies -> mockCookies.stream()
                        .map(mockCookie -> new Cookie(mockCookie.getName(), mockCookie.getValue()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public void setCookies(List<MockCookie> cookies) {
        this.cookies = cookies;
    }

    /**
     * Returns an empty body if the expectation/mock json file has no body defined.
     *
     * @return request body
     */
    public String getBody() {
        return Optional.ofNullable(body)
                .map(mockBody -> new Gson().toJson(mockBody))
                .orElse("{}");
    }

    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Returns an empty list if the expectation/mock json file has no headers defined.
     *
     * @return List of headers
     */
    public List<Header> getHeaders() {
        return Optional.ofNullable(headers)
                .map(mockHeaders -> mockHeaders.stream()
                        .map(mockHeader -> new Header(mockHeader.getName(), mockHeader.getValues()))
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    public void setHeaders(List<MockHeader> headers) {
        this.headers = headers;
    }
}
