package utils.mocks;

import utils.mocks.model.MockRequest;
import utils.mocks.model.MockResponse;

/***
 * Concrete definition of a mock.
 * <p>
 * A mock is represented by a {@link MockRequest} and by a {@link MockResponse}
 * </p>
 */
@SuppressWarnings("unused")
public final class MockDefinition {

    /**
     * Represents the mocked request.
     */
    private MockRequest request;

    /**
     * Represents the mocked response.
     */
    private MockResponse response;

    public MockRequest getRequest() {
        return request;
    }

    public MockResponse getResponse() {
        return response;
    }

    public void setResponse(MockResponse response) {
        this.response = response;
    }

    public void setRequest(MockRequest request) {
        this.request = request;
    }
}
