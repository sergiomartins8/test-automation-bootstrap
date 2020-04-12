package utils.mocks.model;

import com.beust.jcommander.internal.Lists;
import com.google.gson.Gson;
import org.mockserver.model.Cookie;
import org.mockserver.model.Header;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Concrete definition of a mocked response.
 * <p>
 * A mocked response is represented by a body and a status code.
 * Optionally, it also may contain a list of headers and a list of cookies.
 * <p>
 * Each header is represented by {@link MockHeader}
 * <p>
 * Each cookie is represented by {@link MockCookie}
 * </p>
 */
@SuppressWarnings("unused")
public final class MockResponse {

    /**
     * Holds the whole response body.
     */
    private Object body;

    /**
     * Status code response.
     */
    private Integer statusCode;

    /**
     * List of headers within the response.
     */
    private List<MockHeader> headers;

    /**
     * List of cookies.
     */
    private List<MockCookie> cookies;

    public String getBody() {
        return Optional.ofNullable(body)
                .map(mockBody -> new Gson().toJson(mockBody))
                .orElse("{}");
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
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

}
