package base;

import org.mockserver.client.MockServerClient;
import utils.listeners.MockListener;

/**
 * Single responsibility is to hold a mock server object so that it can be used by the {@link MockListener}.
 * Initialization and teardown of the {@link MockContext} is responsibility of the {@link FrameworkBootstrap} class.
 */
public final class MockContext {

    /**
     * Thread safe implementation that holds the mock server client.
     */
    private static ThreadLocal<MockServerClient> mockServerClientThreadLocal = new ThreadLocal<>();

    public static MockServerClient getMockServerClient() {
        return mockServerClientThreadLocal.get();
    }

    public static void setMockServerClient(MockServerClient mockServerClient) {
        mockServerClientThreadLocal.set(mockServerClient);
    }

    public static void resetMockServerClient() {
        mockServerClientThreadLocal.get().reset();
    }
}
