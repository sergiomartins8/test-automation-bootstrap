package ${package}.base;

import org.mockserver.client.MockServerClient;
import ${package}.utils.config.CustomConfiguration;

/**
 * Holds methods that allow mock external dependency services.
 */
public class MockContext {

    private static MockServerClient mockServerClient;

    /**
     * Instantiates {@link #mockServerClient} object if it needs to be instantiated.
     * <br>
     * Synchronized enssures the singleton client instance.
     *
     * @return mock server client instance
     */
    public static synchronized MockServerClient getMockServerClient() {
        if (mockServerClient == null) {
            mockServerClient = new MockServerClient(getMockServerHost(), getMockServerPort());
        }

        return mockServerClient;
    }

    private static String getMockServerHost() {
        return CustomConfiguration.mockServerAddress.split(":")[0];
    }

    private static int getMockServerPort() {
        return Integer.parseInt(CustomConfiguration.mockServerAddress.split(":")[1]);
    }
}
