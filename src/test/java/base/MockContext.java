package base;

import org.mockserver.client.MockServerClient;
import utils.config.CustomConfiguration;

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
                getMockServerHost(),
                getMockServerPort());
    }

    public static void resetMockServerClient() {
        mockServerClient.reset();
    }

    public static MockServerClient getMockServerClient() {
        return mockServerClient;
    }

    private static String getMockServerHost() {
        return CustomConfiguration.mockServerAddress.split(":")[0];
    }

    private static int getMockServerPort() {
        return Integer.parseInt(CustomConfiguration.mockServerAddress.split(":")[1]);
    }
}
