package ${package}.utils.mocks.model;

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
 * <br>
 * A mock request is represented by a path and a mapping request (e.g. GET, POST)
 * <br>
 * Optionally, it also may contain a list of query parameters, a list of cookies, a body and a list of headers
 * <br>
 * Each query parameter is represented by {@link MockQueryStringParameter}
 * <br>
 * Each cookie is represented by {@link MockCookie}
 * <br>
 * Each header is represented by {@link MockHeader}
 */
@SuppressWarnings("unused")
public class MockRequest {

    private String path;

    private String method;

    private List<MockQueryStringParameter> queryStringParameters;

    private List<MockCookie> cookies;

    private Object body;

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
     * @return list of parameters
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
     * @return list of cookies
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
                .orElse("");
    }

    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Returns an empty list if the expectation/mock json file has no headers defined.
     *
     * @return list of headers
     */
    public List<Header> getHeaders() {
        return Optional.ofNullable(headers)
                .map(mockHeaders -> mockHeaders.stream()
                        .map(mockHeader -> new Header(mockHeader.getName(), mockHeader.getValues()))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public void setHeaders(List<MockHeader> headers) {
        this.headers = headers;
    }
}
