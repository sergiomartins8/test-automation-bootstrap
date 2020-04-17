package base;

import org.mockserver.client.MockServerClient;
import utils.config.CustomConfiguration;

import java.net.URI;

/**
 * Context for objects for mocking purposes.
 */
public class MockContext {

    private static MockServerClient mockServerClient;

    /**
     * Initializes the mock server client based on {@link CustomConfiguration} settings.
     */
    public static void initializeMockServerClient() {
        MockContext.mockServerClient = new MockServerClient(
                URI.create(CustomConfiguration.mockServerAddress).getHost(),
                URI.create(CustomConfiguration.mockServerAddress).getPort());
    }

    public static void resetMockServerClient() {
        mockServerClient.reset();
    }

    public static MockServerClient getMockServerClient() {
        return mockServerClient;
    }
}
