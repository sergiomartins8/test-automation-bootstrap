package base;

import org.mockserver.client.MockServerClient;

public final class MockContext {

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
