package io.company.utils.mocks.model;

import io.company.utils.mocks.MockDefinition;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

public class MockExampleModel implements MockDefinition {

    @Override
    public String urlPattern() {
        return "https://www.google.pt/mock";
    }

    @Override
    public HttpMethod methodName() {
        return HttpMethod.GET;
    }

    @Override
    public HttpResponseStatus responseStatus() {
        return HttpResponseStatus.OK;
    }

    @Override
    public String contentBody() {
        return "{\"MESSAGE\":\"MOCKED_MESSAGE\"}";
    }

    @Override
    public HttpHeaders headers() {
        DefaultHttpHeaders defaultHttpHeaders = new DefaultHttpHeaders();
        defaultHttpHeaders.set("random", "example");
        return defaultHttpHeaders;
    }
}
