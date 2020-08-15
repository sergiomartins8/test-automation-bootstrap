package io.company.utils.mocks;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Concrete definition of a mock.
 * <br>
 */
public interface MockDefinition {
    String url();

    default HttpMethod methodName() {
        return HttpMethod.GET;
    }

    default HttpResponseStatus responseStatus() {
        return HttpResponseStatus.OK;
    }

    default String contentBody() {
        return null;
    }

    default HttpHeaders headers() {
        return null;
    }
}
