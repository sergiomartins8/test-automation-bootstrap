package utils.config;

import utils.logging.Loggable;

public class CustomConfigurationHolder implements Loggable {

    private final String mockServerAddress = System.getProperty("mock.server.address");

    public String mockServerAddress() {
        logger().info("Mock server address: " + mockServerAddress);
        return mockServerAddress;
    }
}
