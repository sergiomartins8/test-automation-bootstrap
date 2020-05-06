package ${package}.utils.mocks;

import com.google.gson.GsonBuilder;
import ${package}.utils.mocks.model.MockRequest;
import ${package}.utils.mocks.model.MockResponse;

/**
 * Concrete definition of a mock.
 * <br>
 * A mock is represented by a {@link MockRequest} and by a {@link MockResponse}
 */
@SuppressWarnings("unused")
public class MockDefinition {

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

    /**
     * Similar to a `toString()` implementation. Useful for debugging purposes.
     *
     * @return current object in a pretty json format.
     */
    public String prettyPrint() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
